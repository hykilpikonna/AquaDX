<script lang="ts">
    import {CHARTJS_OPT, clz, registerChart, renderCal, title} from "../libs/ui";
    import { DATA, GAME } from "../libs/sdk";
    import type { GenericRanking } from "../libs/generalTypes";
    title(`Ranking`)

    let d: {
        user: GenericRanking
  } | null = null

  Promise.all([
    GAME.ranking('mai2')
  ]).then(([user]) => {
    console.log(user)

    d = {user}
    localStorage.setItem("tmp-ranking-details", JSON.stringify(d))
  })
</script>

<main class="content">
    <h2>Global Leaderboard</h2>

    {#if d !== null}
    {#each d.user as user}
    <div class="lb-placement">
        <p class="placement-rank">{user.rank}</p>
        <h4 class="placement-name">{user.name}</h4>
        <h4 class="placement-accuracy">{(user.accuracy / 1).toFixed(2)}%</h4>
        <h4 class="placement-rating">{user.rating}</h4>
        <h4 class="placement-full-combo">{user.fullCombo}</h4>
        <h4 class="placement-all-perfect">{user.allPerfect}</h4>
    </div>
    {/each}
    {:else}
    <p>Please Wait...</p>
    {/if}
</main>