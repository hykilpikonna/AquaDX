<script lang="ts">
  import { fade } from "svelte/transition"
  import { t } from "../../libs/i18n";
  import ActionCard from "../../components/ActionCard.svelte";
  import StatusOverlays from "../../components/StatusOverlays.svelte";
  import { CARD, GAME, USER } from "../../libs/sdk";
  import Icon from "@iconify/svelte";

  let load = false;
  let error = "";
  let conflict: {
    oldName: string,
    oldRating: number,
    newName: string,
    newRating: number
  } | null;
  let confirmAction: (override: boolean) => void;

  const startImport = async () => {
    const [fileHandle] = await window.showOpenFilePicker({
      id: 'aquadx_import',
      startIn: 'downloads',
      types: [
        {
          description: "Aqua Player Data",
          accept: {
            "application/json": [".json"],
          },
        },
      ],
    });

    if (!fileHandle) return;
    load = true;

    try {
      const file = await fileHandle.getFile();
      const data = JSON.parse(await file.text()) as any;
      const game = getGameByCode(data.gameId);

      const me = await USER.me();
      const userGames = await CARD.userGames(me.username);

      const existed = userGames[game];
      if (existed) {
        conflict = {
          oldName: existed.name,
          oldRating: existed.rating,
          newName: data.userData.userName,
          newRating: data.userData.playerRating
        };
        if (!await new Promise(resolve => confirmAction = resolve)) {
          return;
        }
        conflict = null;
      }

      await GAME.import(game, data);
      location.href = `/u/${me.username}/${game}`;
    } catch (e: any) {
      error = e.message;
    } finally {
      conflict = null;
      load = false;
    }
  }

  const getGameByCode = (code: string) => {
    switch (code.toUpperCase()) {
      case 'SDEZ':
        return 'mai2';
      case 'SDHD':
        return 'chu3';
      default:
        throw new Error(t('home.import.unknown-game'));
    }
  }
</script>

<ActionCard color="209, 124, 102" icon="bxs:file-import" on:click={startImport}>
  <h3>{t('home.import')}</h3>
  <span>{t('home.import-description')}</span>
</ActionCard>

<StatusOverlays {error} loading={load}/>

{#if conflict}
  <div class="overlay" transition:fade>
    <div>
      <h2>{t('home.import.data-conflict')}</h2>
      <p></p>
      <div class="conflict-cards">
        <div class="old card">
          <span class="type">{t('home.linkcard.account-card')}</span>
          <span>{t('home.linkcard.name')}: {conflict.oldName}</span>
          <span>{t('home.linkcard.rating')}: {conflict.oldRating}</span>
          <div class="trash">
            <Icon icon="ph:trash-duotone"/>
          </div>
        </div>
        <div class="icon">
          <Icon icon="icon-park-outline:down"/>
        </div>
        <div class="new card">
          <span class="type">{t('home.import.new-data')}</span>
          <span>{t('home.linkcard.name')}: {conflict.newName}</span>
          <span>{t('home.linkcard.rating')}: {conflict.newRating}</span>
        </div>
      </div>
      <p></p>
      <div class="buttons">
        <button on:click={() => confirmAction(false)}>{t('action.cancel')}</button>
        <button class="error" on:click={() => confirmAction(true)}>{t('action.confirm')}</button>
      </div>
    </div>
  </div>
{/if}

<style lang="sass">
  @import "../../vars"
  h3
    font-size: 1.3rem
    margin: 0

  .conflict-cards
    display: grid
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr))
    gap: 0.5rem
    align-items: center

    span:not(.type)
      font-size: 0.8rem

    .old
      background: #ff6b6b20
      border: 1px solid $c-error
      color: #ffffff99
      position: relative

      .trash
        display: flex
        position: absolute
        bottom: 0.5rem
        right: 0.5rem
        color: $c-error
        opacity: 0.6
        font-size: 2rem

    .new
      background: #646cff20
      border: 1px solid $c-darker

  .buttons
    display: grid
    grid-template-columns: 1fr 1fr
    gap: 1rem

  .icon
    display: flex
    justify-content: center
    font-size: 2rem
</style>
