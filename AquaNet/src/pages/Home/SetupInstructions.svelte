<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { fade, slide } from "svelte/transition";
  import { USER } from "../../libs/sdk";
  import type { AquaNetUser } from "../../libs/generalTypes";
  import { codeToHtml } from 'shiki'
  import { AQUA_CONNECTION, DISCORD_INVITE, FADE_IN, FADE_OUT } from "../../libs/config";
  import { t } from "../../libs/i18n";

  let user: AquaNetUser
  let keychip: string;
  let keychipCode: string;
  let getStartedRequesting = false;

  USER.me().then((u) => {
    user = u;
  });

  function getStarted() {
    if (getStartedRequesting) return;

    getStartedRequesting = true;
    USER.keychip().then(k => {
      getStartedRequesting = false;
      keychip = k;
      codeToHtml(`
[dns]
default=${AQUA_CONNECTION}

[keychip]
enable=1
; ${t('home.setup.keychip-tips')}
id=${keychip.slice(0, 4)}-${keychip.slice(4)}1337`.trim(), {
        lang: 'ini',
        theme: 'rose-pine',
      }).then((html) => {
        keychipCode = html;
      });
    });
  }
</script>

<div class="setup-instructions">
  <h2>{t('home.setup')}</h2>
  <p>
    {t('home.setup.welcome')}
  </p>
  <blockquote>
    {t('home.setup.blockquote')}
  </blockquote>

  {#if user}
    <div transition:slide>
      {#if !keychip && !keychipCode}
        <div class="no-margin" out:fade={FADE_OUT}>
          <button class="emp" on:click={getStarted}>{t('home.setup.get')}</button>
        </div>
      {:else}
        <div class="no-margin" in:fade={FADE_IN}>
          <p>
            {t('home.setup.edit')}:
          </p>

          <div class="code">
            {@html keychipCode}
          </div>

          <p>
            {t('home.setup.test')}
          </p>
          <p>
            {t('home.setup.ask')} <a href={DISCORD_INVITE}>Discord</a> {t('home.setup.support')}.
          </p>
        </div>
      {/if}
    </div>
  {:else}
    <p>Loading...</p>
  {/if}
</div>

<style lang="sass">
  .code
    overflow-x: auto

  :global(pre.shiki)
    background-color: transparent !important

    :global(code)
      counter-reset: step
      counter-increment: step 0

    :global(code .line::before)
      content: counter(step)
      counter-increment: step
      width: 1rem
      margin-right: 1.5rem
      display: inline-block
      text-align: right
      color: rgba(115,138,148,.4)

</style>
