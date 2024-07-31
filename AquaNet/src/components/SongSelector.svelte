<!-- Svelte 4.2.11 -->

<script lang="ts">

  import { fade } from "svelte/transition";
  import { t } from "../libs/i18n";
  import { DATA } from "../libs/sdk";
  import type { GameName } from "../libs/scoring";
  import type { AllMusic } from "../libs/generalTypes";
  import { DATA_HOST } from "../libs/config";
  import Icon from "@iconify/svelte";

    export let game: GameName = "mai2"
    export let close: () => void;
    export let favSongs: number[];
    export let toggleFav: (id: number) => Promise<void>;

    let allMusic: AllMusic = {};
    let page = 0;
    let pages = 1;
    let search: string = "";
    let matchedSongs: { id: string, name: string }[] = [];

    // Close the overlay when clicking outside of it
    function closeOverlay(this:HTMLDivElement ,event: MouseEvent|KeyboardEvent) {
        if (event.target === this) {
            close();
        }
    }

    DATA.allMusic(game).then(music => {
        allMusic = music;

        renderPage();
    });

    function renderPage() {
        matchedSongs=[];
        if (search!=="") {
            matchedSongs = Object.entries(allMusic).filter(([id, { name }]) => name.toLowerCase().includes(search.toLowerCase())).map(([id, { name }]) => ({ id, name })).slice(page * 25, page * 25 + 25);

            pages = Math.ceil(matchedSongs.length / 25);
        } else {
            matchedSongs = Object.entries(allMusic).map(([id, { name }]) => ({ id, name })).slice(page * 25, page * 25 + 25);

            pages = Math.ceil(Object.keys(allMusic).length / 25);
        }
    }
</script>

<div role="button" tabindex="0" class="overlay" transition:fade on:click={closeOverlay} on:keydown={closeOverlay}>
    <div>
      <h2>{t("home.songlist")}</h2>

        <div class="actions">
            <input type="text" placeholder={t("labels.search")} bind:value={search} on:input={()=>{
                page=0;
                renderPage();
            }}/>

            <div class="songlist">
                <!-- <img src={`${DATA_HOST}/d/${game}/music/00${f.toString().padStart(6, '0').substring(2)}.png`} alt="" on:error={coverNotFound} /> -->
                {#each matchedSongs as { id, name },i}
                <div class:alt={i % 2 === 0}>
                        <img src={`${DATA_HOST}/d/${game}/music/00${id.padStart(6, '0').substring(2)}.png`} alt="" />
                        <div class="info">
                            <div>{name ?? t("UserHome.UnknownSong")}</div>
                        </div>
                        {#if parseInt(id) !== 0}
                        <span>
                        <button class="fav-button" on:click={() => toggleFav(parseInt(id))}>
                            {#if favSongs.includes(parseInt(id))}
                            <Icon icon="material-symbols:star" />
                            {:else}
                            <Icon icon="material-symbols:star-outline" />
                            {/if}
                        </button>
                        </span>
                        {/if}
                    </div>
                {/each}
            </div>

            <div class="paginator">
                <div>
                    <button disabled={page === 0} on:click={() => { page = 0; renderPage(); }}>{t("labels.first")}</button>
                    <button disabled={page === 0} on:click={() => { page--; renderPage(); }}>{t("labels.prev")}</button>
                </div>
                <span>{page + 1} / {pages}</span>
                <div>
                    <button disabled={page === pages - 1} on:click={() => { page++; renderPage(); }}>{t("labels.next")}</button>
                    <button disabled={page === pages - 1} on:click={() => { page = pages - 1; renderPage(); }}>{t("labels.last")}</button>
                </div>
            </div>
        </div>

      <button on:click={close}>{t("actions.close")}</button>
    </div>
  </div>

<style lang="sass">
    @import "../vars"
    
    .actions
        display: flex
        flex-direction: column
        gap: 1em
        align-items: center

        input
            width: 100%
            font-size: 1em

        .paginator
            display: flex
            flex-direction: row
            gap: 1em
            align-items: center
            justify-content: center
            width: 100%

            div
                display: flex
                gap: 1em

        .songlist
            display: grid
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr))
            gap: 1em
            margin-top: 1em
            padding: 1em
            border-radius: 10px
            background-color: var(--background)
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1)
            overflow-y: auto
            max-height: 50vh

            &::-webkit-scrollbar
                border-radius: 10px
                backround-color: var(--background)

            &::-webkit-scrollbar-thumb
                background-color: $c-main
                border-radius: 5px
                border: 3px solid $c-main
                box-shadow: inset 2px 2px 5px rgba(0, 0, 0, 0.1)

            // Image and song info
            > div
                display: flex
                align-items: center
                gap: $gap
                padding-right: 16px
                max-width: 100%
                box-sizing: border-box
        
                img
                    width: 50px
                    height: 50px
                    border-radius: $border-radius
                    object-fit: cover
        
                // Song info and score
                > div.info
                    flex: 1
                    display: flex
                    justify-content: space-between
                    overflow: hidden
                    align-items: center
        
                > div
                    align-items: center
                    display: flex
        
                // Limit song name to one line
                > div:first-child
                    flex: 1
                    min-width: 0
                    overflow: hidden
                    text-overflow: ellipsis
                    white-space: nowrap
        .fav-button
            background: none
            border: none
            cursor: pointer
            padding: 0
            color: $c-main
            font-size: 1.5rem
            display: flex
            margin-left: .5rem
    
        @media (max-width: 600px)
            .paginator
                flex-direction: column
                gap: 1em

            .songlist
                max-height: 40vh

</style>