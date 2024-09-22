<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { t } from "../../libs/i18n";
  import CommunityCard from "../../components/CommunityCard.svelte";
  import { DISCORD_INVITE, QQ_INVITE, TELEGRAM_INVITE } from "../../libs/config";
</script>

<div class="setup-instructions">
  <h2>{t('home.join-community')}</h2>
  <div class="grid cols-3 gap-4">
    <CommunityCard color="82, 93, 233" icon="ic:baseline-discord" on:click={() => window.location.href = DISCORD_INVITE}>
      <h3>Discord</h3>
    </CommunityCard>
    <CommunityCard color="46, 163, 224" icon="mingcute:telegram-fill" on:click={() => window.location.href = TELEGRAM_INVITE}>
      <h3>Telegram</h3>
    </CommunityCard>
    <CommunityCard color="226, 60, 68" icon="ri:qq-fill" on:click={() => window.location.href = QQ_INVITE}>
      <h3>QQ</h3>
    </CommunityCard>
  </div>
</div>

<style lang="sass">

</style>
