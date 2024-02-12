<script lang="ts">
  import {CHARTJS_OPT, registerChart, renderCal, title} from "../libs/ui";
  import {getMaimaiTrend, getMaimaiUser} from "../libs/maimai";
  import type {MaimaiUserSummaryEntry} from "../libs/maimaiTypes";
  import type {TrendEntry} from "../libs/generalTypes";
  import {data_host} from "../libs/config";
  import 'cal-heatmap/cal-heatmap.css';
  import { Line } from 'svelte-chartjs';
  import moment from "moment";
  import 'chartjs-adapter-moment';

  registerChart()

  export let userId: any;
  userId = +userId
  let calElement: HTMLElement

  title(`User ${userId}`)

  let d: {
    user: MaimaiUserSummaryEntry,
    trend: TrendEntry[]
  } | null = null

  Promise.all([
    getMaimaiUser(userId),
    getMaimaiTrend(userId)
  ]).then(([user, trend]) => {
    console.log(user)
    console.log(trend)

    d = {user, trend}
    localStorage.setItem("tmp-user-details", JSON.stringify(d))
    renderCal(calElement, trend.map(it => {return {date: it.date, value: it.plays}}))
  })
</script>

<main id="user-home">
  {#if d !== null}
    <div class="user-pfp">
      <img src={`${data_host}/maimai/assetbundle/icon/${d.user.iconId.toString().padStart(6, "0")}.png`} alt="" class="pfp">
      <h1>{d.user.name}</h1>
    </div>

    <div class="scoring-info">
      <div class="chart">
        <div class="info-top">
          <div class="rating">
            <span>DX Rating</span>
            <span>{d.user.rating.toLocaleString()}</span>
          </div>

          <div class="rank">
            <span>Server Rank</span>
            <span>#{d.user.serverRank.toLocaleString()}</span>
          </div>
        </div>

        <div class="trend">
          <Line data={{
            datasets: [
              {
                label: 'Rating',
                data: d.trend.map(it => {return {x: Date.parse(it.date), y: it.rating}}),
                borderColor: '#646cff',
                tension: 0.1,

                // TODO: Set X axis span to 3 months
              }
            ]
          }} options={CHARTJS_OPT} />
        </div>

        <div class="info-bottom">
          {#each d.user.ranks as r}
            <div>
              <span>{r.name}</span>
              <span>{r.count}</span>
            </div>
          {/each}
        </div>
      </div>

      <div class="other-info">
        <div class="accuracy">
          <span>Accuracy</span>
          <span>{(d.user.accuracy / 10000).toFixed(2)}%</span>
        </div>

        <div class="max-combo">
          <span>Max Combo</span>
          <span>{d.user.maxCombo}</span>
        </div>

        <div class="full-combo">
          <span>Full Combo</span>
          <span>{d.user.fullCombo}</span>
        </div>

        <div class="all-perfect">
          <span>All Perfect</span>
          <span>{d.user.allPerfect}</span>
        </div>

        <div class="total-dx-score">
          <span>DX Score</span>
          <span>{d.user.totalDxScore.toLocaleString()}</span>
        </div>
      </div>
    </div>

    <div class="activity-info">
      <div id="cal-heatmap" bind:this={calElement} />

      <div class="info-bottom">
        <div class="plays">
          <span>Plays</span>
          <span>{d.user.plays}</span>
        </div>

        <div class="time">
          <span>Play Time</span>
          <span>{d.user.totalPlayTime}</span>
        </div>

        <div class="first-play">
          <span>Joined</span>
          <span>{moment(d.user.joined).format("YYYY-MM-DD HH:mm:ss")}</span>
        </div>

        <div class="last-play">
          <span>Last Seen</span>
          <span>{moment(d.user.lastSeen).format("YYYY-MM-DD HH:mm:ss")}</span>
        </div>

        <div class="last-version">
          <span>Last Version</span>
          <span>{d.user.lastVersion}</span>
        </div>
      </div>
    </div>

  {:else}
    <p>Loading...</p>
  {/if}
</main>

<style lang="sass">
@import "../vars"

#user-home
  display: flex
  flex-direction: column
  gap: 20px
  padding: 0 32px

  .pfp
    width: 100px
    height: 100px
    border-radius: 5px
    object-fit: cover

  .info-bottom, .info-top, .other-info
    display: flex
    gap: 20px

    > div
      display: flex
      flex-direction: column

      > span:first-child
        font-weight: bold
        font-size: 0.8rem

        // character spacing
        letter-spacing: 0.1em
        color: $c-main

  .info-top > div > span:last-child
    font-size: 1.5rem

  .scoring-info
    display: flex
    gap: 20px
    max-height: 250px

    .chart
      flex: 1
      display: flex
      flex-direction: column

    .other-info
      flex: 0 0 100px
      flex-direction: column
      gap: 0
      justify-content: space-between


    .trend
      height: 300px
      width: 100%



</style>