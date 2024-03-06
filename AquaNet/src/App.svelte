<script lang="ts">
  import { Router, Route } from "svelte-routing";
  import Welcome from "./pages/Welcome.svelte";
  import MaimaiRating from "./pages/MaimaiRating.svelte";
  import UserHome from "./pages/UserHome.svelte";
  import Home from "./pages/Home.svelte";
  import Ranking from "./pages/Ranking.svelte";
  import { USER } from "./libs/sdk";
  import type { UserMe } from "./libs/generalTypes";
  import Settings from "./pages/User/Settings.svelte";
  import { pfp } from "./libs/ui"

  console.log(`%c
┏━┓         ┳━┓━┓┏━
┣━┫┏━┓┓ ┏┏━┓┃ ┃ ┣┫
┛ ┗┗━┫┗━┻┗━┻┻━┛━┛┗━
     ┗       v${APP_VERSION}`, `
     background: linear-gradient(-45deg, rgba(18,194,233,1) 0%, rgba(196,113,237,1) 50%, rgba(246,79,89,1) 100%);
     font-weight: bold;
     font-size: 2em;
     -webkit-background-clip: text;
     -webkit-text-fill-color: transparent;`)

  export let url = "";
  let me: UserMe

  if (USER.isLoggedIn()) USER.me().then(m => me = m).catch(e => console.error(e))

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
  {#if me}
    <a href="/u/{me.username}">
      <img alt="profile" class="pfp" use:pfp={me}/>
    </a>
  {/if}
</nav>

<Router {url}>
  <Route path="/" component={Welcome} />
  <Route path="/home" component={Home} />
  <Route path="/ranking" component={Ranking} />
  <Route path="/u/:username" component={UserHome} />
  <Route path="/u/:username/:game" component={UserHome} />
  <Route path="/u/:username/:game/rating" component={MaimaiRating} />
  <Route path="/settings" component={Settings} />
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

    img
      width: 1.5rem
      height: 1.5rem
      border-radius: 50%
      object-fit: cover

    .pfp
      width: 2rem
      height: 2rem

    .logo
      display: flex
      align-items: center
      gap: 8px
      font-weight: bold
      color: $c-main
      letter-spacing: 0.2em
      flex: 1

      @media (max-width: $w-mobile)
        > span
          display: none

    @media (max-width: $w-mobile)
      justify-content: center

</style>
