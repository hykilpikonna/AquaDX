<!-- Svelte 4.2.11 -->

<script lang="ts">
  import RatingCompSong from "./RatingCompSong.svelte";
  import { type GameName } from "../libs/scoring";
  import { type MusicMeta } from "../libs/generalTypes";

  export let title: string;
  export let comp: string | undefined;
  export let allMusics: Record<string, MusicMeta>;
  export let game: GameName;
</script>

{#if comp}
<div>
  <h2>{title}</h2>
  <div class="rating-composition">
    {#each comp.split(",").filter(it => it.split(":")[0] !== '0') as map}
      <div>
        <RatingCompSong g={map} meta={allMusics[map.split(":")[0]]} game={game}/>
      </div>
    {/each}
  </div>
</div>
{/if}

<style lang="sass">
  @import "../vars"
  
  .rating-composition
    display: grid
    // 3 columns
    grid-template-columns: repeat(auto-fill, minmax(260px, 1fr))
    gap: $gap

  .rating-composition-2
    display: grid
    // 2 columns
    grid-template-columns: repeat(auto-fill, minmax(290px, 1fr))
    gap: $gap
</style>
