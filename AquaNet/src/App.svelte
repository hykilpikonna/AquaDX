<script lang="ts">
  import {Router, Route} from "svelte-routing";
  import Welcome from "./pages/Welcome.svelte";
  import MaimaiRating from "./pages/MaimaiRating.svelte";
  import UserHome from "./pages/UserHome.svelte";
  import Home from "./pages/Home.svelte";
  import Ranking from "./pages/Ranking.svelte";
  import {DATA, USER} from "./libs/sdk";
  import {onMount} from "svelte";
  import {fetchUserDetails, type UserDetails} from "./libs/FetchGameUserInfo";
  import {DATA_HOST} from "./libs/config";
  import {pfpNotFound} from "./libs/ui";
  import type {GameName} from "./libs/scoring";

  let userIcon: string | null;
  let game = "mai2"
  let username = "waitApiUpdate" //wait api update
  let userImg:string
  onMount(() => {
    fetchUserDetails(username, <GameName>game).then((details) => {
      userIcon = details?.user?.iconId?.toString().padStart(6, "0") ?? null;
      userImg = DATA_HOST + "/d/" + game + "/assetbundle/icon/" + userIcon + ".png"
      console.log("app", userImg)
    }).catch();
  });

let UserUrl = "/u/"+ username


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
  {#if USER.isLoggedIn()}
  <a href={UserUrl} role="button">
    <img src={userImg} alt="" class="pfp" on:error={pfpNotFound}>
  </a>
    {/if}
</nav>

<Router {url}>
  <Route path="/" component={Welcome}/>
  <Route path="/home" component={Home}/>
  <Route path="/ranking" component={Ranking}/>
  <Route path="/u/:username" component={UserHome}/>
  <Route path="/u/:username/:game" component={UserHome}/>
  <Route path="/u/:username/:game/rating" component={MaimaiRating}/>
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

    .pfp
      width: 40px
      height: 40px
      border-radius: 5px
      object-fit: cover

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
