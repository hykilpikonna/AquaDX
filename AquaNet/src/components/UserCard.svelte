<!-- Svelte 4.2.11 -->

<script lang="ts">
  import type { GenericGameSummary } from "../libs/generalTypes";
  import { GAME } from "../libs/sdk";
  import type { GameName } from "../libs/scoring";
  import { pfp, pfpNotFound } from "../libs/ui";

  export let username: string
  export let game: GameName
  export let setLoading: (loading: boolean) => void = () => {}

  let data: GenericGameSummary
  let error = ""

  setLoading(true)
  GAME.userSummary(username, game).then(d => data = d).catch(e => error = e).finally(_ => setLoading(false))
</script>

{#if !data}
  <div>Loading...</div>
{:else if error}
  <div>Error: {error}</div>
{:else}
  <div class="user-card">
    <img use:pfp={data.aquaUser} alt="Profile Picture" />
    <div class="details">
      <span class="in-game-name">{data.name}</span>
      <span class="username">@{username}</span>
    </div>
  </div>
{/if}

<style lang="sass">
@import "../vars"

.user-card
  display: flex
  align-items: center
  gap: $gap

  .details
    display: flex
    flex-direction: column
    justify-content: center

    .username
      font-size: 0.8em

  img
    width: 50px
    height: 50px
    border-radius: 50%
    object-fit: cover
    object-position: center
</style>
