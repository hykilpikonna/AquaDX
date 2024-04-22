<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { slide } from "svelte/transition";
  import { DATA_HOST } from "../libs/config";
  import { t } from "../libs/i18n";
  import { type GameName, getMult } from "../libs/scoring";
  import { coverNotFound } from "../libs/ui";
  import type { MusicMeta } from "../libs/generalTypes";

  export let g: string
  export let meta: MusicMeta
  export let game: GameName

  let mapData = g.split(":").map(Number)
  let mult = getMult(mapData[3], game)
  let mapRank: number = meta?.notes?.[mapData[1] === 10 ? 0 : mapData[1]]?.lv ?? mapData[1] ?? 0
  console.log(meta?.name, mapData, mapRank, mult)
  </script>

  <div class="map-detail-container" transition:slide>
    <div class="scores">
      <div>
        <img src={`${DATA_HOST}/d/${game}/music/00${mapData[0].toString().padStart(6, '0').substring(2)}.png`} alt="" on:error={coverNotFound} />
        <div class="info">
          <div class="first-line">
            <div class="song-title">{meta?.name ?? t("UserHome.UnknownSong")}</div>
            <span class={`lv level-${mapData[1] === 10 ? 3 : mapData[1]}`}>
              { mapRank }
            </span>
          </div>
          <div class="second-line">
            <span class={`rank-${getMult(mapData[3], game)[2].toString()[0]}`}>

              <span class="rank-text">{("" + getMult(mapData[3], game)[2]).replace("p", "+")}</span>
              <span class="rank-num">{(mapData[3] / 10000).toFixed(2)}%</span>
            </span>
            {#if game === 'mai2'}
              <span class="dx-change">
                { (mapRank * Number(mult[1])).toFixed(0) }
              </span>
            {/if}
          </div>
        </div>
      </div>
    </div>
  </div>

  <style lang="sass">

    @import "../vars"
    $gap: 20px

    .map-detail-container
      background-color: rgb(35,35,35)
      border-radius: $border-radius
      overflow: hidden

      .scores
        display: flex
        flex-direction: column
        flex-wrap: wrap
        gap: $gap

        // Image and song info
        > div
          display: flex
          align-items: center
          gap: 12px
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
            flex-direction: column

            .first-line
              display: flex
              flex-direction: row

            // Limit song name to one line
            .song-title
              flex: 1
              min-width: 0
              overflow: hidden
              text-overflow: ellipsis
              white-space: nowrap

            // Make song score and rank not wrap
            > div:last-child
              white-space: nowrap

            @media (max-width: $w-mobile)
              flex-direction: column
              gap: 0

              .rank-text
                text-align: left

          .rank-S
            // Gold green gradient on text
            background: $grad-special
            -webkit-background-clip: text
            color: transparent

          .rank-A
            color: #ff8a8a

          .rank-B
            color: #6ba6ff

          .lv
            width: 30px
            text-align: center
            background: rgba(var(--lv-color), 0.6)
            padding: 0 6px
            border-radius: 0 $border-radius 0 $border-radius

            // Inset shadow, like it's a paper below this card with a cut
            box-shadow: inset 0 0 10px rgba(0,0,0,0.5)

          span
            display: inline-block
            text-align: left

          .second-line
            display: flex
            justify-content: space-between
            align-items: center

          // Vertical table-like alignment
          span.rank-text
            min-width: 40px
          span.rank-num
            min-width: 60px
          span.dx-change
            margin-right: 0.5rem
            color: $c-good
  </style>
