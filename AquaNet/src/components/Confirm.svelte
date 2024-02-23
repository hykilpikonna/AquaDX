<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { fade } from 'svelte/transition'
  import { clz } from "../libs/ui";
  import type { ConfirmProps } from "../libs/generalTypes";

  // Props
  export let show: ConfirmProps
</script>

{#if show}
  <div class="overlay" transition:fade>
    <div>
      <h2>{show.title}</h2>
      <span>{show.message}</span>

      <div class="actions">
        {#if show.cancel}
          <!-- Svelte LSP is very annoying here -->
          <button on:click={() => show.cancel && show.cancel()}>Cancel</button>
        {/if}
        <button on:click={() => show.confirm()} class={clz({error: show.dangerous})}>Confirm</button>
      </div>
    </div>
  </div>
{/if}

<style lang="sass">
  .actions
    display: flex
    gap: 16px

    button
      width: 100%
</style>