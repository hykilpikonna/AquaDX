<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { fade, slide } from "svelte/transition";
  import { USER } from "../../libs/sdk";
  import type { AquaNetUser } from "../../libs/generalTypes";
  import { codeToHtml } from 'shiki'
  import { AQUA_CONNECTION, DISCORD_INVITE, FADE_IN, FADE_OUT } from "../../libs/config";

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
; This is your unique keychip, do not share it with anyone
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
  <h2>Setup Connection</h2>
  <p>
    Welcome! If you own an arcade cabinet or game setup,
    please follow the instructions below to set up the connection with AquaDX.
  </p>
  <blockquote>
    We assume that you already have the required files and can run the game (e.g. ROM and segatools)
    that come with the cabinet or game setup.
    If not, please contact the seller of your device for the required files, as we will not provide them for copyright reasons.
  </blockquote>

  {#if user}
    <div transition:slide>
      {#if !keychip && !keychipCode}
        <div class="no-margin" out:fade={FADE_OUT}>
          <button class="emp" on:click={getStarted}>Get started</button>
        </div>
      {:else}
        <div class="no-margin" in:fade={FADE_IN}>
          <p>
            Please edit your segatools.ini file and modify the following lines:
          </p>

          <div class="code">
            {@html keychipCode}
          </div>

          <p>
            Then, after you restart the game, you should be able to connect to AquaDX. Please verify that the network tests are all GOOD in the test menu.
          </p>
          <p>
            If you have any questions, please ask in our <a href={DISCORD_INVITE}>Discord server</a>.
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
