<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { slide } from "svelte/transition";
  import type { UserMe } from "../../libs/generalTypes";
  import { USER } from "../../libs/sdk";
  import StatusOverlays from "../../components/StatusOverlays.svelte";
  import Icon from "@iconify/svelte";

  USER.ensureLoggedIn()

  let me: UserMe;
  let error: string;
  let submitting = ""

  const fields = [
    [ 'displayName', "Display Name" ],
    [ 'username', "Username" ],
    [ 'password', "Password" ],
    [ 'profileLocation', "Location" ],
    [ 'profileBio', "Bio" ],
  ]

  // Fetch user data
  USER.me().then(m => {
    me = m
    values = fields.map(([field]) => me[field as keyof UserMe])
  }).catch(e => error = e.message)

  let values = Array(fields.length).fill('')
  let changed: string[] = []

  function submit(field: string, value: string) {
    if (submitting) return
    submitting = field

    USER.setting(field, value).then(() => {
      changed = changed.filter(c => c !== field)
    }).catch(e => error = e.message).finally(() => submitting = "")
  }

  const passwordAction = (node: HTMLInputElement, whether: boolean) => {
    if (whether) node.type = 'password'
  }
</script>

<main class="content">
  <h2>Profile Settings</h2>

  {#each fields as [field, name], i (field)}
    <div class="field">
      <label for={field}>{name}</label>
      <div>
        <input id={field} type="text" use:passwordAction={field === 'password'}
               bind:value={values[i]} on:input={() => changed = [...changed, field]}
               placeholder={field === 'password' ? 'Unchanged' : 'Unset'}/>
        {#if changed.includes(field) && values[i]}
          <button transition:slide={{axis: 'x'}} on:click={() => submit(field, values[i])}>
            {#if submitting === field}
              <Icon icon="line-md:loading-twotone-loop" />
            {:else}
              Save
            {/if}
          </button>
        {/if}
      </div>
    </div>
  {/each}

  <StatusOverlays {error} loading={!me} />
</main>

<style lang="sass">

  .field
    display: flex
    flex-direction: column

    > div
      display: flex
      align-items: center
      gap: 1rem
      margin-top: 0.5rem

      > input
        flex: 1

</style>
