<script lang="ts">
  import { Router, Route } from "svelte-routing";
  import Home from "./pages/Home.svelte";

  import MaimaiRating from "./pages/MaimaiRating.svelte";

  export let url = "";
</script>

<Router {url}>
  <div>
    <Route path="/"><Home /></Route>
    <Route path="/mai/rating"><MaimaiRating /></Route>
  </div>
</Router>
