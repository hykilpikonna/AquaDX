<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { fade } from 'svelte/transition'
  import { clz } from "../libs/ui";
  import type { ConfirmProps } from "../libs/generalTypes";
  import { DISCORD_INVITE } from "../libs/config";
  import Icon from "@iconify/svelte";

  // Props
  export let confirm: ConfirmProps | null = null
  export let error: string | null
  export let loading: boolean = false
</script>

{#if confirm}
  <div class="overlay" transition:fade>
    <div>
      <h2>{confirm.title}</h2>
      <span>{confirm.message}</span>

      <div class="actions">
        {#if confirm.cancel}
          <!-- Svelte LSP is very annoying here -->
          <button on:click={() => {
            confirm && confirm.cancel && confirm.cancel()

            // Set to null
            confirm = null
          }}>Cancel</button>
        {/if}
        <button on:click={() => confirm && confirm.confirm()} class={clz({error: confirm.dangerous})}>Confirm</button>
      </div>
    </div>
  </div>
{/if}

{#if error}
  <div class="overlay" transition:fade>
    <div>
      <h2 class="error">Error</h2>
      <span>Something went wrong, please try again later or <a href={DISCORD_INVITE}>join our discord for support.</a></span>
      <span>Detail: {error}</span>
<!--      <div class="actions">-->
<!--        <button on:click={() => error = null}>Close</button>-->
<!--      </div>-->

      <div class="actions">
        <button on:click={() => location.reload()} class="error">
          Refresh
        </button>
      </div>
    </div>
  </div>
{/if}

{#if loading && !error}
  <div class="overlay loading" transition:fade>
    <Icon class="icon" icon="svg-spinners:pulse-2"/>
    <span><span>LOADING</span></span>
  </div>
{/if}

<style lang="sass">
  .actions
    display: flex
    gap: 16px

    button
      width: 100%

  .loading.overlay
    font-size: 28rem

    :global(.icon)
      opacity: 0.5

    > span
      position: absolute
      inset: 0
      display: flex
      justify-content: center
      align-items: center
      background: transparent

      letter-spacing: 20px
      margin-left: 20px

      font-size: 1.5rem
</style>