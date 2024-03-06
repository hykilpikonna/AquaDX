<script lang="ts">
  import { fade } from "svelte/transition";
  import LinkCard from "./Home/LinkCard.svelte";
  import SetupInstructions from "./Home/SetupInstructions.svelte";
  import { DISCORD_INVITE, FADE_IN, FADE_OUT } from "../libs/config";
  import { USER } from "../libs/sdk.js";
  import type { UserMe } from "../libs/generalTypes";
  import StatusOverlays from "../components/StatusOverlays.svelte";
  import ActionCard from "../components/ActionCard.svelte";

  USER.ensureLoggedIn();

  let me: UserMe
  let error = ""

  let tab = 0;
  let tabs = ["Portal", "Link Card", "Game Setup"];

  USER.me().then((m) => me = m).catch(e => error = e.message)
</script>

<main class="content">
<!--  <h2 class="outer-title">&nbsp;</h2>-->
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
    <div out:fade={FADE_OUT} in:fade={FADE_IN} class="action-cards">
      <ActionCard color="255, 192, 203" icon="solar:card-bold-duotone" on:click={() => tab = 1}>
        {#if me && me.cards.length > 1}
          <h3>Manage Cards</h3>
          <span>Link, unlink, and manage your cards</span>
        {:else if me}
          <h3>Link card</h3>
          <span>Link your Amusement IC / Aime card to play games.</span>
        {/if}
      </ActionCard>

      <ActionCard color="82, 93, 233" icon="ic:baseline-discord" on:click={() => window.location.href = DISCORD_INVITE}>
        <h3>Join Discord</h3>
        <span>Join our Discord server to chat with other players and get help.</span>
      </ActionCard>

      <ActionCard on:click={() => tab = 2} icon="uil:link-alt">
        <h3>Setup Connection</h3>
        <span>If you own a cab or arcade setup, begin setting up the connection.</span>
      </ActionCard>
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

<StatusOverlays {error} loading={!me}/>

<style lang="sass">
  @import "../vars"

  .tabs
    display: flex
    gap: 1rem

    div
      &.active
        color: $c-main

  h3
    font-size: 1.3rem
    margin: 0

  .action-cards
    display: flex
    flex-direction: column
    gap: 1rem
</style>
