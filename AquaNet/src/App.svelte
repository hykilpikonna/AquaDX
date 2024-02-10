<script lang="ts">
  import { Router, Route } from "svelte-routing";
  import Home from "./pages/Home.svelte";

  import MaimaiRating from "./pages/MaimaiRating.svelte";

  export let url = "";
</script>

<Router {url}>
  <div>
    <Route path="/"><Home /></Route>
    <Route path="/u/:userId/mai/rating" component={MaimaiRating}></Route>
  </div>
</Router>
