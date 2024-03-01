<script lang="ts">
  import { clz, title } from "../libs/ui";
  import { GAME } from "../libs/sdk";
  import type { GenericRanking } from "../libs/generalTypes";
  import ErrorMessage from "../ErrorMessage.svelte";

  title(`Ranking`);

  let d: { users: GenericRanking[] };
  let ifError = null;
  Promise.all([GAME.ranking("mai2")])
    .then(([users]) => {
      d = { users };
    })
    .catch((error) => {
      ifError = error;
    });
</script>

<main class="content leaderboard">
  <h2>Global Leaderboard</h2>

  {#if d}
    <div class="leaderboard-container">
      <div class="lb-user">
        <span class="rank"></span>
        <span class="name"></span>
        <span class="rating">Rating</span>
        <span class="accuracy">Accuracy</span>
        <span class="fc">FC</span>
        <span class="ap">AP</span>
      </div>
      {#each d.users as user, i (user.rank)}
        <div class={clz({ alternate: i % 2 === 1 }, "lb-user")}>
          <span class="rank">#{user.rank}</span>
          <a class="name" href="/u/61702139">{user.name}</a>
          <span class="rating">{user.rating.toLocaleString()}</span>
          <span class="accuracy">{(+user.accuracy).toFixed(2)}%</span>
          <span class="fc">{user.fullCombo}</span>
          <span class="ap">{user.allPerfect}</span>
        </div>
      {/each}
    </div>
  {:else if ifError}
  <ErrorMessage {ifError}/>
  {:else}
    <p>Please Wait...</p>
  {/if}
</main>

<style lang="sass">
  @import "../vars"

  .leaderboard-container
    display: flex
    flex-direction: column

  .lb-user
    display: flex
    align-items: center
    justify-content: space-between
    width: 100%
    gap: 12px
    border-radius: 12px
    padding: 6px 12px
    box-sizing: border-box

    > *:not(.name)
      text-align: center

    .name
      min-width: 100px
      flex: 1

    .accuracy, .rating
      width: 15%
      min-width: 45px

    .rating
      font-weight: bold
      color: white

    .fc, .ap
      width: 5%
      min-width: 20px

    @media (max-width: $w-mobile)
      font-size: 0.9rem

      .accuracy
        display: none

    &.alternate
      background-color: $ov-light
</style>
