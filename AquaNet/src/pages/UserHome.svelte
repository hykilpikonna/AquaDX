<script lang="ts">
  import {registerChart, renderCal, title} from "../libs/ui";
  import {getMaimai, getMaimaiTrend} from "../libs/maimai";
  import type {MaiUserPreviewData} from "../libs/maimaiTypes";
  import type {TrendEntry} from "../libs/generalTypes";
  import {data_host} from "../libs/config";
  // @ts-ignore
  import CalHeatmap from 'cal-heatmap';
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
    user: MaiUserPreviewData,
    trend: TrendEntry[]
  } | null = null

  Promise.all([
    getMaimai('GetUserPreviewApi', {userId}),
    getMaimaiTrend(userId)
  ]).then(([user, trend]) => {
    console.log(user)
    console.log(trend)

    d = {user, trend}
    localStorage.setItem("tmp-user-details", JSON.stringify(d))
    renderCal(calElement, trend.map(it => {return {date: it.date, value: it.plays}}))
  })
</script>

<main>
  {#if d !== null}
    <img src={`${data_host}/maimai/assetbundle/icon/${d.user.iconId.toString().padStart(6, "0")}.png`} alt="" class="pfp">
    <h1>{d.user.userName}</h1>

    <div class="trend">
      <Line data={{
        datasets: [
          {
            label: 'Rating',
            data: d.trend.map(it => {return {x: Date.parse(it.date), y: it.rating}}),
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
          }
        ]
      }} options={{
        scales: {
          xAxis: {
            type: 'time'
          }
        },
        plugins: {
          legend: {
            display: false
          },
          tooltip: {
            mode: "index",
            intersect: false
          }
        },
      }} />
    </div>

    <div id="cal-heatmap" bind:this={calElement} />
  {:else}
    <p>Loading...</p>
  {/if}
</main>