<main id="home" class="no-margin">
  <h1>AquaNet</h1>
  <div class="btn-group">
    <button>Login</button>
    <button>Sign Up</button>
  </div>

  <div class="light-pollution">
    <div class="l1"></div>
    <div class="l2"></div>
    <div class="l3"></div>
  </div>
</main>

<style lang="sass">
  @import "../vars"

  #home
    color: $c-main
    position: relative
    width: 100%
    height: 100%
    padding-left: 100px
    overflow: hidden

    box-sizing: border-box

    display: flex
    flex-direction: column
    justify-content: center

    margin-top: -$nav-height

    > h1
      font-family: Quicksand, $font
      user-select: none

      // Gap between text characters
      letter-spacing: 0.2em
      margin-top: 0
      opacity: 0.9

    .btn-group
      display: flex
      gap: 8px

    .light-pollution
      pointer-events: none
      opacity: 0.6

      > div
        position: absolute
        z-index: -1

      .l1
        left: -560px
        top: 90px
        height: 1130px
        width: 1500px
        $color: rgb(158, 110, 230)
        background: radial-gradient(50% 50% at 50% 50%, rgba($color, 0.28) 0%, rgba($color, 0) 100%)

      .l2
        left: -200px
        top: 560px
        height: 1200px
        width: 1500px
        $color: rgb(92, 195, 250)
        background: radial-gradient(50% 50% at 50% 50%, rgba($color, 0.28) 0%, rgba($color, 0) 100%)

      .l3
        left: -600px
        opacity: 0.7
        top: -630px
        width: 1500px
        height: 1000px
        $color: rgb(230, 110, 156)
        background: radial-gradient(50% 50% at 50% 50%, rgba($color, 0.28) 0%, rgba($color, 0) 100%)

    @media (max-width: 500px)
      align-items: center
      padding-left: 0
</style>