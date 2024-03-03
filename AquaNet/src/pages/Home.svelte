<script>
  import { fade } from "svelte/transition";
  import LinkCard from "./Home/LinkCard.svelte";
  import SetupInstructions from "./Home/SetupInstructions.svelte";
  import {FADE_IN, FADE_OUT} from "../libs/config";

  let tab = 0;
  let tabs = ["Portal", "Link Card", "Game Setup"];
</script>

<main class="content">
  <h2 class="outer-title">Home</h2>
  <nav class="tabs">
    {#each tabs as t, i}
      <div class="clickable"
        class:active={tab === i}
        on:click={() => tab = i}
        on:keydown={(e) => e.key === "Enter" && (tab = i)}
        role="button" tabindex={i}>{t}
      </div>
    {/each}
  </nav>

  {#if tab === 0}
    <div out:fade={FADE_OUT} in:fade={FADE_IN}>
    </div>
  {:else if tab === 1}
    <div out:fade={FADE_OUT} in:fade={FADE_IN}>
      <LinkCard/>
    </div>
  {:else if tab === 2}
    <div out:fade={FADE_OUT} in:fade={FADE_IN}>
      <SetupInstructions/>
    </div>
  {/if}
</main>

<style lang="sass">
  @import "../vars"

  .tabs
    display: flex
    gap: 1rem

    div
      &.active
        color: $c-main
</style>