<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { fade } from 'svelte/transition'

  interface ConfirmProps {
    title: string
    message: string
    confirm: () => void
    cancel?: () => void
  }

  // Props
  export let show: ConfirmProps
</script>

{#if show}
  <div class="overlay" transition:fade>
    <h1>{show.title}</h1>
    <span>{show.message}</span>

    <div class="actions">
      {#if show.cancel}
        <!-- Svelte LSP is very annoying here -->
        <button on:click={() => show.cancel && show.cancel()}>Cancel</button>
      {/if}
      <button on:click={() => show.confirm()}>Confirm</button>
    </div>
  </div>
{/if}

<style lang="sass">

</style>