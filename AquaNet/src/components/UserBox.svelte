<!-- Svelte 4.2.11 -->

<script lang="ts">
  import {
    UserBoxItemKind,
    type AquaNetUser,
    type ChangeUserBoxReq,
  } from "../libs/generalTypes";
  import { USER, USERBOX } from "../libs/sdk";
  import { t, ts } from "../libs/i18n";
  import { DATA_HOST, HAS_USERBOX_ASSETS } from "../libs/config";
  import { FADE_IN, FADE_OUT } from "../libs/config";
  import { fade, slide } from "svelte/transition";
  import StatusOverlays from "./StatusOverlays.svelte";
  import Icon from "@iconify/svelte";

  let user: AquaNetUser;
  let aimeId = "";
  let loading = true;
  let error = "";
  let submitting = "";
  let changed: string[] = [];

  let tab = 0;
  const tabs = ["chusan", "ongeki", "maimai"];

  // Things that can be changed in the userbox
  const userBoxFields = [
    {
      key: "frame",
      label: t("userbox.frame"),
      userBoxKey: "frameId",
      kind: UserBoxItemKind.frame,
    },
    {
      key: "nameplate",
      label: t("userbox.nameplate"),
      userBoxKey: "nameplateId",
      kind: UserBoxItemKind.nameplate,
    },
    {
      key: "trophy",
      label: t("userbox.trophy"),
      userBoxKey: "trophyId",
      kind: UserBoxItemKind.trophy,
    },
    {
      key: "mapicon",
      label: t("userbox.mapicon"),
      userBoxKey: "mapIconId",
      kind: UserBoxItemKind.mapicon,
    },
    {
      key: "voice",
      label: t("userbox.voice"),
      userBoxKey: "voiceId",
      kind: UserBoxItemKind.sysvoice,
    },
    {
      key: "wear",
      label: t("userbox.wear"),
      userBoxKey: "avatarWear",
      kind: UserBoxItemKind.avatar,
    },
    {
      key: "head",
      label: t("userbox.head"),
      userBoxKey: "avatarHead",
      kind: UserBoxItemKind.avatar,
    },
    {
      key: "face",
      label: t("userbox.face"),
      userBoxKey: "avatarHead",
      kind: UserBoxItemKind.avatar,
    },
    {
      key: "skin",
      label: t("userbox.skin"),
      userBoxKey: "avatarSkin",
      kind: UserBoxItemKind.avatar,
    },
    {
      key: "item",
      label: t("userbox.item"),
      userBoxKey: "avatarItem",
      kind: UserBoxItemKind.avatar,
    },
    {
      key: "front",
      label: t("userbox.front"),
      userBoxKey: "avatarFront",
      kind: UserBoxItemKind.avatar,
    },
    {
      key: "back",
      label: t("userbox.back"),
      userBoxKey: "avatarBack",
      kind: UserBoxItemKind.avatar,
    },
  ] as const;

  // The different types available (avatar is a special case, as it has multiple categories)
  const userBoxItems = userBoxFields
    .map((f) => f.kind)
    .filter((v, i, a) => a.indexOf(v) === i);

  // Available options for the current user on each field
  let availableOptions = {
    nameplate: [],
    frame: [],
    trophy: [],
    mapicon: [],
    voice: [],
    wear: [],
    head: [],
    face: [],
    skin: [],
    item: [],
    front: [],
    back: [],
  } as Record<string, { id: number; label: string }[]>;

  // Current values of the userbox
  let values = {
    nameplate: undefined,
    frame: undefined,
    trophy: undefined,
    mapicon: undefined,
    voice: undefined,
    wear: undefined,
    head: undefined,
    face: undefined,
    skin: undefined,
    item: undefined,
    front: undefined,
    back: undefined,
  } as Record<string, number | undefined>;

  function submit(body: ChangeUserBoxReq, field: string) {
    if (submitting) return;
    submitting = body.kind;

    USERBOX.setUserBox(body)
      .then(() => {
        changed = changed.filter((c) => c !== field);
      })
      .catch((e) => {
        error = e.message;
        submitting = "";
      })
      .finally(() => {
        submitting = "";
      });
  }

  async function fetchData(card: string) {
    const userId = await USERBOX.getAimeId(card);

    if (!userId) return;

    aimeId = userId.luid;

    const currentValues = await USERBOX.getProfile(userId.luid).catch((e) => {
      loading = false;
      error = t("userbox.error.noprofile")
      return;
    });

    if(!currentValues) return;

    values = {
      nameplate: currentValues.nameplateId,
      frame: currentValues.frameId,
      trophy: currentValues.trophyId,
      mapicon: currentValues.mapIconId,
      voice: currentValues.voiceId,
      wear: currentValues.avatarWear,
      head: currentValues.avatarHead,
      face: currentValues.avatarFace,
      skin: currentValues.avatarSkin,
      item: currentValues.avatarItem,
      front: currentValues.avatarFront,
      back: currentValues.avatarBack,
    };

    const itemLabels = await USERBOX.getItemLabels().catch((e) => {
      loading = false;
      error = t("userbox.error.nodata")
      return
    });

    if(!itemLabels) return;

    await Promise.all(
      userBoxItems.map(async (kind) => {
        // Populate info about the items
        return USERBOX.getUnlockedItems(userId.luid, kind).then((items) => {
          switch (kind) {
            case UserBoxItemKind.nameplate:
              // Add the item id and the label to the available options
              availableOptions.nameplate = items.map((i) => {
                return {
                  id: i.itemId,
                  label: itemLabels.nameplate[i.itemId],
                };
              });
              break;
            case UserBoxItemKind.frame:
              availableOptions.frame = items.map((i) => {
                return {
                  id: i.itemId,
                  label: itemLabels.frame[i.itemId],
                };
              });
              break;
            case UserBoxItemKind.trophy:
              availableOptions.trophy = items.map((i) => {
                return {
                  id: i.itemId,
                  label: itemLabels.trophy[i.itemId],
                };
              });
              break;
            case UserBoxItemKind.mapicon:
              availableOptions.mapicon = items.map((i) => {
                return {
                  id: i.itemId,
                  label: itemLabels.mapicon[i.itemId],
                };
              });
              break;
            case UserBoxItemKind.sysvoice:
              availableOptions.voice = items.map((i) => {
                return {
                  id: i.itemId,
                  label: itemLabels.sysvoice[i.itemId],
                };
              });
              break;
            case UserBoxItemKind.avatar:
              // Depending of the second number of the item id, we can know the kind of item

              items.forEach((i) => {
                const kind = i.itemId.toString().split("")[1];
                switch (kind) {
                  case "1":
                    availableOptions.wear.push({
                      id: i.itemId,
                      label: itemLabels.avatar[i.itemId],
                    });
                    break;
                  case "2":
                    availableOptions.head.push({
                      id: i.itemId,
                      label: itemLabels.avatar[i.itemId],
                    });
                    break;
                  case "3":
                    availableOptions.face.push({
                      id: i.itemId,
                      label: itemLabels.avatar[i.itemId],
                    });
                    break;
                  case "4":
                    availableOptions.skin.push({
                      id: i.itemId,
                      label: itemLabels.avatar[i.itemId],
                    });
                    break;
                  case "5":
                    availableOptions.item.push({
                      id: i.itemId,
                      label: itemLabels.avatar[i.itemId],
                    });
                    break;
                  case "6":
                    availableOptions.front.push({
                      id: i.itemId,
                      label: itemLabels.avatar[i.itemId],
                    });
                    break;
                  case "7":
                    availableOptions.back.push({
                      id: i.itemId,
                      label: itemLabels.avatar[i.itemId],
                    });
                    break;
                }
              });
              break;
          }
        });
      }),
    );

    loading = false;
  }

  function generateBodyFromKind(
    kind:
      | "frame"
      | "nameplate"
      | "trophy"
      | "mapicon"
      | "voice"
      | "wear"
      | "head"
      | "face"
      | "skin"
      | "item"
      | "front"
      | "back",
    value: number,
    aimeId: string,
  ): ChangeUserBoxReq {
    switch (kind) {
      case "frame":
        return { kind: "frame", frameId: value, aimeId };
      case "nameplate":
        return { kind: "plate", nameplateId: value, aimeId };
      case "trophy":
        return { kind: "trophy", trophyId: value, aimeId };
      case "mapicon":
        return { kind: "mapicon", mapiconid: value, aimeId };
      case "voice":
        return { kind: "sysvoice", voiceId: value, aimeId };
      case "wear":
        return { kind: "avatar", accId: value, category: 1, aimeId };
      case "head":
        return { kind: "avatar", accId: value, category: 2, aimeId };
      case "face":
        return { kind: "avatar", accId: value, category: 3, aimeId };
      case "skin":
        return { kind: "avatar", accId: value, category: 4, aimeId };
      case "item":
        return { kind: "avatar", accId: value, category: 5, aimeId };
      case "front":
        return { kind: "avatar", accId: value, category: 6, aimeId };
      case "back":
        return { kind: "avatar", accId: value, category: 7, aimeId };
    }
  }

  USER.me().then((u) => {
    if (u) {
      user = u;
      const card = user.cards.length > 0 ? user.cards[0].luid : "";

      if (card) {
        fetchData(card);
      } else {
        loading = false;
      }
    }
  });
</script>

{#if !loading && !error}
  <div class="outer-container">
    <nav>
      {#each tabs as tabName, i}
        <div
          transition:slide={{ axis: "x" }}
          class:active={tab === i}
          on:click={() => {
            tab = i;

            // Set url params
            window.history.pushState({}, "", `/settings?tab=${tab}`);
          }}
          on:keydown={(e) => e.key === "Enter" && (tab = i)}
          role="button"
          tabindex="0"
        >
          {ts(`userbox.tabs.${tabName}`)}
        </div>
      {/each}
    </nav>
    {#if tab === 0}
      <div class="container" out:fade={FADE_OUT} in:fade={FADE_IN}>
        <div class="fields">
          {#each userBoxFields as { key, label, kind }, i (key)}
            <div class="field">
              <label for={key}>{label}</label>
              <div>
                <select
                  bind:value={values[key]}
                  id={key}
                  on:change={() => {
                    changed = [...changed, key];
                  }}
                >
                  {#each availableOptions[key] as option (option)}
                    <option value={option.id}
                      >{option.label ||
                        `${key} ${option.id.toString()}`}</option
                    >
                  {/each}
                </select>
                {#if changed.includes(key)}
                  <button
                    transition:slide={{ axis: "x" }}
                    on:click={() => {
                      const newValue = values[key];

                      if (newValue === undefined) return;

                      submit(generateBodyFromKind(key, newValue, aimeId), key);
                    }}
                  >
                    {#if submitting === key}
                      <Icon icon="line-md:loading-twotone-loop" />
                    {:else}
                      {t("settings.profile.save")}
                    {/if}
                  </button>
                {/if}
              </div>
            </div>
          {/each}
        </div>
        {#if HAS_USERBOX_ASSETS}
          <div class="preview">
            <h2>{t("userbox.preview.ui")}</h2>
            <!-- Frame -->
            {#if values.frame}
              <img
                src={`${DATA_HOST}/d/chu3/frame/${values.frame}.png`}
                alt="Preview"
              />
            {/if}

            <div class="secondrow">
              <!-- Map Icon -->
              {#if values.mapicon}
                <div class="mapicon">
                  <img
                    src={`${DATA_HOST}/d/chu3/mapicon/${values.mapicon}.png`}
                    alt="Preview"
                  />
                </div>
              {/if}

              <!-- System voice -->
              {#if values.voice}
                <div>
                  <img
                    src={`${DATA_HOST}/d/chu3/systemVoice/${values.voice}.png`}
                    alt="Preview"
                  />
                </div>
              {/if}
            </div>

            <h2>{t("userbox.preview.nameplate")}</h2>
            <!-- Nameplate -->
            {#if values.nameplate}
              <div class="nameplate">
                <img
                  src={`${DATA_HOST}/d/chu3/nameplate/${values.nameplate}.png`}
                  alt="Preview"
                />
                <p class="trophy">
                  {availableOptions.trophy.find((x) => x.id === values.trophy)
                    ?.label}
                </p>
                <div class="username">
                  <p>
                    {user.displayName}
                  </p>
                </div>
              </div>
            {/if}

            <h2>{t("userbox.preview.avatar")}</h2>
            <div class="avatar">
              <div>
                <img
                  src={`${DATA_HOST}/d/chu3/avatarAccessory/${values.wear}.png`}
                  alt="Preview"
                />
              </div>
              <div>
                <img
                  src={`${DATA_HOST}/d/chu3/avatarAccessory/${values.head}.png`}
                  alt="Preview"
                />
              </div>
              <div>
                <img
                  src={`${DATA_HOST}/d/chu3/avatarAccessory/${values.face}.png`}
                  alt="Preview"
                />
              </div>
              <div>
                <img
                  src={`${DATA_HOST}/d/chu3/avatarAccessory/${values.skin}.png`}
                  alt="Preview"
                />
              </div>
              <div>
                <img
                  src={`${DATA_HOST}/d/chu3/avatarAccessory/${values.item}.png`}
                  alt="Preview"
                />
              </div>
              <div>
                <img
                  src={`${DATA_HOST}/d/chu3/avatarAccessory/${values.front}.png`}
                  alt="Preview"
                />
              </div>
              <div>
                <img
                  src={`${DATA_HOST}/d/chu3/avatarAccessory/${values.back}.png`}
                  alt="Preview"
                />
              </div>
            </div>
          </div>
        {/if}
      </div>
    {:else}
      <div>
        <p>WIP</p>
      </div>
    {/if}
  </div>
{/if}
<StatusOverlays {error} {loading} />

<style lang="sass">
    @import "../vars"

    .outer-container
        display: flex
        flex-direction: column
        gap: 1rem

        nav
            display: flex
            gap: 1rem

            div
                padding: 0.5rem 1rem
                border-radius: 0.4rem
                cursor: pointer
                transition: background-color 0.2s
                font-weight: 500

                &.active
                    color: $c-main
  
    img 
        width: 100%
        height: auto

    .container
      display: flex
      flex-direction: row
      gap: 3rem

      @media (max-width: $w-max)
        flex-direction: column

    .preview
        display: flex
        flex-direction: column
        gap: 1rem
        width: 50%

        @media (max-width: $w-max)
            width: 100%

        .secondrow
            display: flex
            gap: 1rem
            justify-content: space-between

            div
                width: 40%
                flex-grow:0

        .avatar
            display: grid
            grid-template-columns: repeat(3, 1fr)
            grid-template-rows: repeat(3, 1fr)
            gap: 1rem

            div
                border: 1px solid white
                aspect-ratio:1
                overflow: hidden
                display: flex
                align-items: center
                justify-content: center
                border-radius: 0.4rem

                img
                    width: auto
                    height: 100%
                

        .nameplate
            position: relative
            width: 400px
            
            > .trophy
                position: absolute
                top: 10px
                left: 150px
                width: 220px
                background-color: #fff
                color: black
                border-radius: 0.4rem
                text-align: center
                font-weight:500
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.5)
                font-size: .8rem

            > .username
                position: absolute
                top: 50px
                left: 150px
                width: 220px
                height: 50px
                background-color: #fff
                color: black
                border-radius: 0.2rem
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.5)
                display: flex

                > p
                    margin: .2rem .5rem
                    font-size: 1.2rem

    .mapicon
        height: 100px
        display: flex
        justify-content: center

        img
            width: auto
            height: 100%

    .fields
      display: flex
      flex-direction: column
      gap: 12px
      width: 100%
      flex-grow: 0
  
      label
        display: flex
        flex-direction: column
  
    .field
      display: flex
      flex-direction: column
  
      label
        max-width: max-content
  
      > div:not(.bool)
        display: flex
        align-items: center
        gap: 1rem
        margin-top: 0.5rem
  
        > select
          flex: 1

</style>
