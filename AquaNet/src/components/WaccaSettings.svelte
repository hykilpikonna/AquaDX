<script>
  import { fade } from "svelte/transition";
  import { FADE_IN, FADE_OUT } from "../libs/config";
  import GameSettingFields from "./GameSettingFields.svelte";
</script>

<main>
  <div out:fade={FADE_OUT} in:fade={FADE_IN}>
    <GameSettingFields game="wacca"/>
  </div>
</main>
