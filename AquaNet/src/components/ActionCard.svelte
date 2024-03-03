<!-- Svelte 4.2.11 -->

<script lang="ts">
  import Icon from "@iconify/svelte";

  export let color: string = '179, 198, 255'
  export let icon: string
  // const iconPos = [
  //     [1, 0.5],
  //     [0.5, 3.5],
  //     [4, 2],
  //     [4.5, -1],
  // ]
  const iconPos = [
      [1, 0.5, 2],
      [6, 2, 1.5],
      [-0.5, 4.5, 1.3],
      [5, -0.5],
      [3.5, 4.5],
      [9.5, 0.3, 1.2],
      [12.5, 2.5, 0.8],
      [10, 4.4, 0.8],
  ]

  // function genIconPos() {
  //   const n = 15
  //   const xFactor = 3.8
  //   const yFactor = 3.9
  //   let iconPos = []
  //   let lastYStart = -yFactor + 2
  //   for (let i = 0; i < n; i++) {
  //     lastYStart += 0.5
  //     if (lastYStart > 2) lastYStart -= yFactor
  //     let ratio = (1 - i / n)
  //     let y = lastYStart
  //     let x = i * xFactor
  //     while (y < 6) {
  //       iconPos.push([x, y,
  //         ratio / 2 + 0.5,
  //         10 + Math.random() * 20
  //       ])
  //       y += yFactor
  //     }
  //   }
  //   return iconPos
  // }
  // const iconPos = genIconPos()
</script>

<div class="action-card" style="--card-color: {color}" on:click role="button" tabindex="0" on:keydown>
  <slot/>

  <div class="icons">
    {#each iconPos as [x, y, size, rot], i}
      <Icon icon={icon} style={`top: ${y}rem; right: ${x}rem; font-size: ${size || 1}em; rotate: ${rot}deg`} />
    {/each}
  </div>
</div>

<style lang="sass">
  @import '../vars'

  .action-card
    overflow: hidden
    padding: 1rem
    border-radius: 16px
    box-shadow: 0 5px 5px 1px $c-shadow
    transition: all 0.2s ease
    cursor: pointer
    position: relative
    background: linear-gradient(45deg, transparent 20%, rgba(var(--card-color), 0.5) 100%)
    outline: 1px solid transparent
    filter: drop-shadow(0 0 12px rgba(var(--card-color), 0))

    &:hover
      box-shadow: 0 0 0.5rem 0.2rem $c-shadow
      transform: translateY(-0.1rem)

    span
      font-size: 1.2rem
      display: block
      margin-bottom: 0.5rem

    .icons
      position: absolute
      inset: 0
      color: rgba(var(--card-color), 0.5)
      font-size: 2rem
      transition: all 0.2s ease
      z-index: -1
      mask-image: linear-gradient(45deg, transparent 30%, rgba(255,255,255,0.5) 70%, white 100%)

      :global(> svg)
        position: absolute
        rotate: 20deg

    &:hover
      // Drop shadow glow
      filter: drop-shadow(0 0 12px rgba(var(--card-color), 0.5))
      outline-color: rgba(var(--card-color), 0.5)
</style>