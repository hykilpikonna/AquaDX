<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { fade } from 'svelte/transition'
  import type { ConfirmProps } from "../libs/generalTypes";
  import { DISCORD_INVITE } from "../libs/config";
  import Icon from "@iconify/svelte";
  import { t } from "../libs/i18n"

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
          }}>{t('action.cancel')}</button>
        {/if}
        <button on:click={() => confirm && confirm.confirm()} class:error={confirm.dangerous}>{t('action.confirm')}</button>
      </div>
    </div>
  </div>
{/if}

{#if error}
  <div class="overlay" transition:fade>
    <div>
      <h2 class="error">{t('status.error')}</h2>
      <span>{t('status.error.hint')}<a href={DISCORD_INVITE}>{t('status.error.hint.link')}</a></span>
      <span>{t('status.detail', { detail: error })}</span>

      <div class="actions">
        <button on:click={() => location.reload()} class="error">
          {t('action.refresh')}
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
