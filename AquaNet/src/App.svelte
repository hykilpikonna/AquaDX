<script lang="ts">
  import { Router, Route } from "svelte-routing";
  import Home from "./pages/Home.svelte";
  import MaimaiRating from "./pages/MaimaiRating.svelte";
  import UserHome from "./pages/UserHome.svelte";
  import Icon from '@iconify/svelte';

  export let url = "";
</script>

<nav>
  <div>home</div>
  <div>maps</div>
  <div>rankings</div>
  <div><Icon icon="tabler:search" /></div>
</nav>

<div id="wrapper">
  <Router {url}>
    <Route path="/"><Home /></Route>
    <Route path="/u/:userId" component={UserHome}></Route>
    <Route path="/u/:userId/mai/rating" component={MaimaiRating}></Route>
  </Router>
</div>

<style lang="sass">
  @import "vars"

  #wrapper
    display: flex
    flex-direction: column
    align-items: center

    min-height: 100%

  nav
    display: flex
    justify-content: flex-end
    align-items: center
    gap: 32px
    height: $nav-height

    padding: 0 48px

    z-index: 10
    position: relative

    > div
      cursor: pointer
      transition: all 0.2s ease
      text-decoration: underline 1px solid transparent
      text-underline-offset: 0.1em

      display: flex
      align-items: center

      &:hover
        color: $c-main
        text-decoration-color: $c-main
        text-underline-offset: 0.5em

    @media (max-width: $w-mobile)
      justify-content: center

</style>