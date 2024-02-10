<main id="home">
  <h1>AquaNet</h1>
  <div>
    <button>Login</button>
    <button>Sign Up</button>
  </div>
</main>

<style lang="sass">
  @import "../vars"

  #home
    color: $c-main

    > h1
      font-family: Quicksand, $font
      user-select: none

      // Gap between text characters
      letter-spacing: 0.2em
    
    > div
      display: flex
      gap: 8px
    
</style>