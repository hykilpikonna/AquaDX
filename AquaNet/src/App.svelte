<script lang="ts">
  import { Router, Route } from "svelte-routing";
  import Welcome from "./pages/Welcome.svelte";
  import MaimaiRating from "./pages/MaimaiRating.svelte";
  import UserHome from "./pages/UserHome.svelte";
  import Icon from '@iconify/svelte';
  import Home from "./pages/Home.svelte";
  import Ranking from "./pages/Ranking.svelte";
  import { USER } from "./libs/sdk";

  export let url = "";

  let path = window.location.pathname;
</script>

<nav>
  {#if path !== "/"}
    <a class="logo" href={USER.isLoggedIn() ? "/home" : "/"}>
      <img src="/assets/icons/android-chrome-192x192.png" alt="AquaDX"/>
      <span>AquaNet</span>
    </a>
  {/if}
  <a href="/home">home</a>
  <div>maps</div>
  <a href="/ranking">rankings</a>
  <div><Icon icon="tabler:search" /></div>
</nav>

<Router {url}>
  <Route path="/" component={Welcome} />
  <Route path="/home" component={Home} />
  <Route path="/ranking" component={Ranking} />
  <Route path="/u/:username" component={UserHome} />
  <Route path="/u/:username/:game" component={UserHome} />
  <Route path="/u/:username/:game/rating" component={MaimaiRating} />
</Router>

<style lang="sass">
  @import "vars"

  nav
    display: flex
    justify-content: flex-end
    align-items: center
    gap: 32px
    height: $nav-height

    padding: 0 48px

    z-index: 10
    position: relative

    .logo
      display: flex
      align-items: center
      gap: 8px
      font-weight: bold
      color: $c-main
      letter-spacing: 0.2em
      flex: 1

      img
        width: 1.5rem
        height: 1.5rem
        border-radius: 50%

      @media (max-width: $w-mobile)
        > span
          display: none

    @media (max-width: $w-mobile)
      justify-content: center

</style>