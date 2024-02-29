<script lang="ts">
  import { title } from "../libs/ui";
  import { GAME } from "../libs/sdk";
  import type { GenericRanking } from "../libs/generalTypes";

  title(`Ranking`)

  let d: { users: GenericRanking[] }

  Promise.all([
    GAME.ranking('mai2')
  ]).then(([ users ]) => {
    d = { users }
  })
</script>

<main class="content">
  <h2>Global Leaderboard</h2>

  {#if d}
    {#each d.users as user}
      <div class="lb-placement">
        <p class="placement-rank">{user.rank}</p>
        <h4 class="placement-name">{user.name}</h4>
        <h4 class="placement-accuracy">{(+user.accuracy).toFixed(2)}%</h4>
        <h4 class="placement-rating">{user.rating}</h4>
        <h4 class="placement-full-combo">{user.fullCombo}</h4>
        <h4 class="placement-all-perfect">{user.allPerfect}</h4>
      </div>
    {/each}
  {:else}
    <p>Please Wait...</p>
  {/if}
</main>