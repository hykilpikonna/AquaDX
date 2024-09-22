<!-- Svelte 4.2.11 -->

<script lang="ts">
  import Icon from "@iconify/svelte";

  export let color: string = '179, 198, 255'
  export let icon: string

</script>

<div class="action-card" style="--card-color: {color}" on:click role="button" tabindex="0" on:keydown>
  <slot/>

  <div class="icon">
    <Icon icon={icon} />
  </div>
</div>

<style lang="sass">
  @import '../vars'

  .action-card
    overflow: hidden
    padding: 1rem
    border-radius: $border-radius
    box-shadow: 0 5px 5px 1px $c-shadow
    transition: all 0.2s ease
    cursor: pointer
    position: relative
    background: linear-gradient(45deg, transparent 20%, rgba(var(--card-color), 0.5) 100%)
    outline: 1px solid transparent
    filter: drop-shadow(0 0 12px rgba(var(--card-color), 0))

    &:hover
      box-shadow: 0 0 0.5rem 0.2rem $c-shadow
      transform: translateY(-3px)

      // Drop shadow glow
      filter: drop-shadow(0 0 12px rgba(var(--card-color), 0.5))
      outline-color: rgba(var(--card-color), 0.5)

    span
      font-size: 1.2rem
      display: block
      margin-bottom: 0.5rem

    .icon
      position: absolute
      display: flex
      color: rgba(var(--card-color), 0.5)
      font-size: 4rem
      right: 0
      bottom: 0
      padding: .5rem
      transition: all 0.2s ease
      z-index: -1
      mask-image: linear-gradient(45deg, transparent 30%, rgba(255,255,255,0.5) 70%, white 100%)
      opacity: 0.8

      @media (max-width: $w-mobile)
        opacity: 0.6
</style>
