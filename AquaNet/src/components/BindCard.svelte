<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { slide } from "svelte/transition"
  import { clz } from "../libs/ui";

  const inputACRegex = /^(\d{4} ){0,4}\d{0,4}$/
  const inputACRegexFull = /^(\d{4} ){4}\d{4}$/
  let inputAC = ""
  let inputSN = ""

  function inputACChange(e: any) {
    e = e as InputEvent
    // Add spaces to the input
    if (e.inputType === "insertText" && inputAC.length % 5 === 4 && inputAC.length < 20) {
      inputAC += " "
    }
  }
</script>

<div class="bind-card">
  <h2>Link Card</h2>
  <p>Please enter the following information:</p>
  <p>1. The 20-digit access code on the back of your card. </p>
  <label>
    <!-- DO NOT change the order of bind:value and on:input. Their order determines the order of reactivity -->
    <input placeholder="e.g. 5200 1234 5678 9012 3456"
           bind:value={inputAC}
           on:input={inputACChange}
           class={clz({error: (inputAC && !inputACRegex.test(inputAC))})}>
    {#if inputAC.length > 0}
      <button transition:slide={{axis: 'x'}}>Bind</button>
    {/if}
  </label>
  <p>2. Download the NFC Tools app on your phone
    (<a href="https://play.google.com/store/apps/details?id=com.wakdev.wdnfc">Android</a> /
    <a href="https://apps.apple.com/us/app/nfc-tools/id1252962749">Apple</a>) and scan your card. Then, enter the Serial Number.
  </p>
  <input placeholder="e.g. 01:2E:XX:XX:XX:XX:XX:XX" bind:value={inputSN}>
</div>

<style lang="sass">
  .bind-card
    input
      width: 100%

    label
      display: flex

      button
        margin-left: 1rem
</style>