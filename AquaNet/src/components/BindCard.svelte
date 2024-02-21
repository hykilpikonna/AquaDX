<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { slide } from "svelte/transition"
  import { clz } from "../libs/ui";
  import type { UserMe } from "../libs/generalTypes";
  import { USER } from "../libs/sdk";
  import moment from "moment"

  let error: string = ""
  let me: UserMe | null = null

  // Fetch data for current user
  USER.me().then(m => {
    me = m
    m.cards.sort((a, b) => a.registerTime < b.registerTime ? 1 : -1)
  }).catch(e => error = e.message)

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

  function bindAC() {
    if (inputACRegexFull.test(inputAC)) {
      console.log("Binding access code", inputAC)
      inputAC = ""
    }
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
  
  function bindSN() {
    if (inputSNRegexFull.test(inputSN)) {
      console.log("Binding serial number", inputSN)
      inputSN = ""
    }
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
    return "Unknown"
  }
</script>

<div class="bind-card">
  <h2>Your Cards</h2>
  <p>Here are the cards you have linked to your account:</p>

  {#if me}
    <div class="existing-cards">
      {#each me.cards as card}
        <div class={clz({ghost: cardType(card.luid) === 'Account Card'}, 'existing-card')}>
          <span class="type">{cardType(card.luid)}</span>
          <span class="register">Registered: {moment(card.registerTime).format("YYYY MMM DD")}</span>
          <span class="last">Last used: {moment(card.accessTime).format("YYYY MMM DD")}</span>
          <div/>
          <span class="id">{formatLUID(card.luid)}</span>
        </div>
      {/each}
    </div>
  {:else if error}
    <p>{error}</p>
  {:else}
    <p>Loading...</p>
  {/if}

  <h2>Link Card</h2>
  <p>Please enter the following information:</p>
  <p>1. The 20-digit access code on the back of your card. </p>
  <label>
    <!-- DO NOT change the order of bind:value and on:input. Their order determines the order of reactivity -->
    <input placeholder="e.g. 5200 1234 5678 9012 3456"
           on:keydown={(e) => {
             e.key === "Enter" && bindAC()
             // Ensure key is numeric
             if (e.key.length === 1 && !/[\d ]/.test(e.key)) e.preventDefault()
           }}
           bind:value={inputAC}
           on:input={inputACChange}
           class={clz({error: (inputAC && !inputACRegex.test(inputAC))})}>
    {#if inputAC.length > 0}
      <button transition:slide={{axis: 'x'}} on:click={bindAC}>Bind</button>
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
             e.key === "Enter" && bindSN()
             // Ensure key is hex or colon
             if (e.key.length === 1 && !/[0-9A-Fa-f:]/.test(e.key)) e.preventDefault()
           }}
           bind:value={inputSN}
           on:input={inputSNChange}
           class={clz({error: (inputSN && !inputSNRegex.test(inputSN))})}>
    {#if inputSN.length > 0}
      <button transition:slide={{axis: 'x'}} on:click={bindSN}>Bind</button>
    {/if}
  </label>
</div>

<style lang="sass">
  @import "../vars"

  .bind-card
    input
      width: 100%

    label
      display: flex

      button
        margin-left: 1rem

    .existing-cards
      display: grid
      grid-template-columns: repeat(auto-fill, minmax(250px, 1fr))
      gap: 1rem

      .existing-card
        display: flex
        flex-direction: column
        min-height: 90px

        border-radius: 5px
        padding: 12px 16px
        background: $ov-light

        &.ghost
          background: rgba($c-darker, 0.8)

        .register, .last
          opacity: 0.7

        span:not(.type)
          font-size: 0.8rem

        > div
          flex: 1


</style>