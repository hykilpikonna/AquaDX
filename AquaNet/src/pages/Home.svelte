<script>
  import { fade } from "svelte/transition";
  import LinkCard from "./Home/LinkCard.svelte";
  import SetupInstructions from "./Home/SetupInstructions.svelte";
  import {FADE_IN, FADE_OUT} from "../libs/config";

  let tab = 0;
</script>

<main class="content">
  <h2>Welcome to AquaDX!</h2>

  <nav class="tabs">
    <button class:active={tab === 0} on:click={() => tab = 0}>Welcome</button>
    <button class:active={tab === 1} on:click={() => tab = 1}>Link Card</button>
    <button class:active={tab === 2} on:click={() => tab = 2}>Setup Instructions</button>
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