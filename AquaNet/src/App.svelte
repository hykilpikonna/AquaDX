<script lang="ts">
  import { Router, Route } from "svelte-routing";
  import Welcome from "./pages/Welcome.svelte";
  import MaimaiRating from "./pages/MaimaiRating.svelte";
  import UserHome from "./pages/UserHome.svelte";
  import Icon from '@iconify/svelte';
  import Home from "./pages/Home.svelte";
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
  <div>rankings</div>
  <div><Icon icon="tabler:search" /></div>
</nav>

<Router {url}>
  <Route path="/" component={Welcome} />
  <Route path="/home" component={Home} />
  <Route path="/u/:userId" component={UserHome} />
  <Route path="/u/:userId/mai/rating" component={MaimaiRating} />
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

    > div, > a
      cursor: pointer
      transition: $transition
      text-decoration: underline 1px solid transparent
      text-underline-offset: 0.1em

      display: flex
      align-items: center
      color: unset
      font-weight: unset

      &:hover
        color: $c-main
        text-decoration-color: $c-main
        text-underline-offset: 0.5em

    @media (max-width: $w-mobile)
      justify-content: center

</style>