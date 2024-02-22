<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { slide, fade } from "svelte/transition"
  import { clz } from "../libs/ui";
  import type { CardSummary, CardSummaryGame, UserMe } from "../libs/generalTypes";
  import { CARD, USER } from "../libs/sdk";
  import moment from "moment"

  // State
  let state = "ready"

  let error: string = ""
  let me: UserMe | null = null
  let accountCardSummary: CardSummary | null = null

  // Fetch data for current user
  const updateMe = () => USER.me().then(m => {
    me = m
    m.cards.sort((a, b) => a.registerTime < b.registerTime ? 1 : -1)
    CARD.summary(m.ghostCard.luid).then(s => accountCardSummary = s.summary)
  }).catch(e => error = e.message)
  updateMe()

  // Data conflict overlay
  let conflictCardID: string = ""
  let conflictSummary: CardSummary | null = null
  let conflictGame: string = ""
  let conflictNew: CardSummaryGame | null = null
  let conflictOld: CardSummaryGame | null = null
  let conflictToMigrate: string[] = []

  async function link(type: 'AC' | 'SN') {
    if (state !== 'ready') return
    state = "linking-AC"
    const id = type === 'AC' ? inputAC : inputSN

    console.log("linking card", id)

    // First, lookup the card summary
    const summary = (await CARD.summary(id)).summary

    // If all games in summary are null or doesn't conflict with the ghost card,
    // we can link the card directly
    // @ts-ignore - TS doesn't understand that k is a key of CardSummary, so it says k cannot be used as index
    if (Object.keys(summary).every(k => summary[k] === null || accountCardSummary[k] === null)) {
      console.log("linking card directly")
      // @ts-ignore
      await CARD.link({cardId: id, migrate: Object.keys(summary).filter(k => summary[k] !== null).join(",")})

      // Refresh the user data
      await updateMe()

      state = ""
    }

    // For each conflicting game, ask the user if they want to migrate the data
    else {
      conflictSummary = summary
      conflictCardID = id
      await linkConflictContinue(null)
    }
  }

  async function linkConflictContinue(choose: "old" | "new" | null) {
    console.log("linking card with migration")

    if (choose) {
      // If old is chosen, nothing needs to be migrated
      // If new is chosen, we need to migrate the data
      if (choose === "new") {
        conflictToMigrate.push(conflictGame)
      }
      // Continue to the next card
      // @ts-ignore
      conflictSummary[conflictGame] = null
    }

    let isConflict = false
    for (const k in conflictSummary) {
      // @ts-ignore
      conflictNew = conflictSummary[k]
      // @ts-ignore
      conflictOld = accountCardSummary[k]
      conflictGame = k
      if (!conflictNew || !conflictOld) continue

      isConflict = true
      break
    }

    // If there are no longer conflicts, we can link the card
    if (!isConflict) {
      await CARD.link({cardId: conflictCardID, migrate: conflictToMigrate.join(",")})
      await updateMe()
      
      // Reset the conflict state
      linkConflictCancel()
    }
  }
  
  function linkConflictCancel() {
    state = ""
    conflictSummary = null
    conflictCardID = ""
    conflictGame = ""
    conflictNew = null
    conflictOld = null
    conflictToMigrate = []
  }

  // Access code input
  const inputACRegex = /^(\d{4} ){0,4}\d{0,4}$/
  const inputACRegexFull = /^(\d{4} ){4}\d{4}$/
  let inputAC = ""

  function inputACChange(e: any) {
    e = e as InputEvent
    // Add spaces to the input
    if (e.inputType === "insertText" && inputAC.length % 5 === 4 && inputAC.length < 24)
      inputAC += " "
    inputAC = inputAC.slice(0, 24)
  }

  // Serial number input
  const inputSNRegex = /^([0-9A-Fa-f]{0,2}:){0,7}[0-9A-Fa-f]{0,2}$/
  const inputSNRegexFull = /^([0-9A-Fa-f]{2}:){4,7}[0-9A-Fa-f]{2}$/
  let inputSN = ""

  function inputSNChange(e: any) {
    e = e as InputEvent
    // Add colons to the input
    if (e.inputType === "insertText" && inputSN.length % 3 === 2 && inputSN.length < 23)
      inputSN += ":"
    inputSN = inputSN.toUpperCase().slice(0, 23)
  }

  function formatLUID(luid: string) {
    switch (cardType(luid)) {
      case "Felica SN":
        return (+luid).toString(16).toUpperCase().padStart(16, "0").match(/.{1,2}/g)!.join(":")
      case "Access Code":
        return luid.match(/.{4}/g)!.join(" ")
      case "Account Card":
        return luid.slice(0, 6) + " " + luid.slice(6).match(/.{4}/g)!.join(" ")
      default:
        return luid
    }
  }

  function cardType(luid: string) {
    if (luid.startsWith("00")) return "Felica SN"
    if (luid.length === 20) return "Access Code"
    if (luid.length === 18) return "Account Card"
    if (luid.includes(":")) return "Felica SN"
    if (luid.includes(" ")) return "Access Code"
    return "Unknown"
  }

  function isInput(e: KeyboardEvent) {
    return e.key.length === 1 && !e.altKey && !e.ctrlKey && !e.metaKey && !e.shiftKey
  }
</script>

<div class="link-card">
  <h2>Your Cards</h2>
  <p>Here are the cards you have linked to your account:</p>

  {#if me}
    <div class="existing-cards" transition:slide>
      {#each me.cards as card}
        <div class={clz({ghost: cardType(card.luid) === 'Account Card'}, 'existing card')}>
          <span class="type">{cardType(card.luid)}</span>
          <span class="register">Registered: {moment(card.registerTime).format("YYYY MMM DD")}</span>
          <span class="last">Last used: {moment(card.accessTime).format("YYYY MMM DD")}</span>
          <div/>
          <span class="id">{formatLUID(card.luid)}</span>
        </div>
      {/each}
    </div>
  {:else if error}
    <span class="error">{error}</span>
  {:else}
    <span>Loading...</span>
  {/if}

  <h2>Link Card</h2>
  <p>Please enter the following information:</p>
  <p>1. The 20-digit access code on the back of your card. </p>
  <label>
    <!-- DO NOT change the order of bind:value and on:input. Their order determines the order of reactivity -->
    <input placeholder="e.g. 5200 1234 5678 9012 3456"
           on:keydown={(e) => {
             e.key === "Enter" && link('AC')
             // Ensure key is numeric
             if (isInput(e) && !/[\d ]/.test(e.key)) e.preventDefault()
           }}
           bind:value={inputAC}
           on:input={inputACChange}
           class={clz({error: (inputAC && !inputACRegex.test(inputAC))})}>
    {#if inputAC.length > 0}
      <button transition:slide={{axis: 'x'}} on:click={() => link('AC')}>Link</button>
    {/if}
  </label>
  <p>2. Download the NFC Tools app on your phone
    (<a href="https://play.google.com/store/apps/details?id=com.wakdev.wdnfc">Android</a> /
    <a href="https://apps.apple.com/us/app/nfc-tools/id1252962749">Apple</a>) and scan your card.
    Then, enter the Serial Number.
  </p>
  <label>
    <input placeholder="e.g. 01:2E:1A:2B:3C:4D:5E:6F"
           on:keydown={(e) => {
             e.key === "Enter" && link('SN')
             // Ensure key is hex or colon
             if (isInput(e) && !/[0-9A-Fa-f:]/.test(e.key)) e.preventDefault()
           }}
           bind:value={inputSN}
           on:input={inputSNChange}
           class={clz({error: (inputSN && !inputSNRegex.test(inputSN))})}>
    {#if inputSN.length > 0}
      <button transition:slide={{axis: 'x'}} on:click={() => link('SN')}>Link</button>
    {/if}
  </label>

  {#if conflictOld && conflictNew && me}
    <div class="overlay" transition:fade>
      <div>
        <h2>Data Conflict</h2>
        <p>The card contains data for {conflictGame}, which is already present on your account.
          Please choose the data you would like to keep</p>
        <div class="conflict-cards">
          <div class="old card clickable" on:click={() => linkConflictContinue('old')}
               role="button" tabindex="0" on:keydown={e => e.key === "Enter" && linkConflictContinue('old')}>
            <span class="type">Account Card</span>
            <span>Name: {conflictOld.name}</span>
            <span>Rating: {conflictOld.rating}</span>
            <span>Last Login: {moment(conflictOld.lastLogin).format("YYYY MMM DD")}</span>
            <span class="id">{formatLUID(me.ghostCard.luid)}</span>
          </div>
          <div class="new card clickable" on:click={() => linkConflictContinue('new')}
               role="button" tabindex="0" on:keydown={e => e.key === "Enter" && linkConflictContinue('new')}>
            <span class="type">{cardType(conflictCardID)}</span>
            <span>Name: {conflictNew.name}</span>
            <span>Rating: {conflictNew.rating}</span>
            <span>Last Login: {moment(conflictNew.lastLogin).format("YYYY MMM DD")}</span>
            <span class="id">{conflictCardID}</span>
          </div>
        </div>
        <button class="error" on:click={linkConflictCancel}>Cancel</button>
      </div>
    </div>
  {/if}
</div>

<style lang="sass">
  @import "../vars"

  .link-card
    input
      width: 100%

    label
      display: flex

      button
        margin-left: 1rem

    .existing-cards, .conflict-cards
      display: grid
      grid-template-columns: repeat(auto-fill, minmax(250px, 1fr))
      gap: 1rem

      .existing.card
        min-height: 90px

        &.ghost
          background: rgba($c-darker, 0.8)

        .register, .last
          opacity: 0.7

        span:not(.type)
          font-size: 0.8rem

        > div
          flex: 1

    .conflict-cards
      .card
        transition: $transition

      .card:hover
        background: $c-darker

      span:not(.type)
        font-size: 0.8rem

      .id
        opacity: 0.7

</style>