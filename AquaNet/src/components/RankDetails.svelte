<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { slide } from "svelte/transition";
  import { t } from "../libs/i18n";
  import type { GenericGameSummary } from "../libs/generalTypes";

  export let g: GenericGameSummary

  const detail = Object.entries(g.detailedRanks).toSorted((a, b) => +b[0] - +a[0])
</script>

<div class="rank-detail-container fw-block" transition:slide>
  <div>
    <h2>{t("UserHome.RankDetail.Title")}</h2>
    <table>
      <!-- rankDetails: { Level : { Rank : Count } } -->
      <!-- Rows are levels, columns are ranks -->

      <!-- Headers -->
      <tr>
        <th>{t("UserHome.RankDetail.Level")}</th>
        {#each Object.values(g.ranks) as rankMap}<th>{rankMap.name}</th>{/each}
      </tr>

      <!-- Data -->
      {#each detail as [level, rankMap]}
        <tr>
          <td>{level}</td>
          {#each Object.values(rankMap) as count}<td>{count}</td>{/each}
        </tr>
      {/each}
    </table>
  </div>
</div>

<style lang="sass">
  @import "../vars"

  .rank-detail-container
    > div
      margin: 1em auto
      max-width: 500px

    table
      width: 100%
      border-collapse: collapse
      table-layout: fixed

      th:not(:first-child)
        background: $grad-special
        -webkit-background-clip: text
        -webkit-text-fill-color: transparent
        background-clip: text
        color: $c-main
        padding: 0.5em

      th, td
        padding: 0.5em
        text-align: center

        &:first-child
          color: $c-main
</style>
