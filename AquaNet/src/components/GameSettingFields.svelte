<script lang="ts">
  import { SETTING } from "../libs/sdk";
  import type { GameOption } from "../libs/generalTypes";
  import { ts } from "../libs/i18n";
  import StatusOverlays from "./StatusOverlays.svelte";

  export let game: string;
  let gameFields: GameOption[] = []
  let submitting = ""
  let error: string;

  SETTING.get().then(s => {
    gameFields = s.filter(it => it.game === game)
  })

  function submitGameOption(field: string, value: any) {
    if (submitting) return
    submitting = field

    SETTING.set(field, value).catch(e => error = e.message).finally(() => submitting = "")
  }
</script>

<div class="fields">
  {#each gameFields as field}
    <div class="field">
      {#if field.type === "Boolean"}
        <div class="bool">
          <input id={field.key} type="checkbox" bind:checked={field.value}
                 on:change={() => submitGameOption(field.key, field.value)}/>
          <label for={field.key}>
            <span class="name">{ts(`settings.fields.${field.key}.name`)}</span>
            <span class="desc">{ts(`settings.fields.${field.key}.desc`)}</span>
          </label>
        </div>
      {/if}
    </div>
  {/each}
</div>

<StatusOverlays {error} loading={!gameFields.length && !!submitting}/>

<style lang="sass">
  .fields
    display: flex
    flex-direction: column
    gap: 12px

  .bool
    display: flex
    align-items: center
    gap: 1rem

    label
      display: flex
      flex-direction: column

      .desc
        opacity: 0.6

  .field
    display: flex
    flex-direction: column

    label
      max-width: max-content

    > div:not(.bool)
      display: flex
      align-items: center
      gap: 1rem
      margin-top: 0.5rem

      > input
        flex: 1
</style>
