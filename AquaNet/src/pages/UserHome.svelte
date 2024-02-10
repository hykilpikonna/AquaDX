<script lang="ts">
  import {registerChart, title} from "../libs/ui";
  import {getMaimai, getMaimaiTrend} from "../libs/maimai";
  import type {MaiUserPreviewData} from "../libs/maimaiTypes";
  import type {TrendEntry} from "../libs/generalTypes";
  import {data_host} from "../libs/config";
  // @ts-ignore
  import SvelteHeatmap from "svelte-heatmap";
  import { Line } from 'svelte-chartjs';
  import moment from "moment";
  import 'chartjs-adapter-moment';

  registerChart()

  export let userId: any;
  userId = +userId

  title(`User ${userId}`)

  let d: {
    user: MaiUserPreviewData,
    trend: TrendEntry[]
  } | null = null

  // Get user details
  Promise.all([
    getMaimai('GetUserPreviewApi', {userId}),
    getMaimaiTrend(userId)
  ]).then(([user, trend]) => {
    console.log(user)
    console.log(trend)

    d = {user, trend}
  })
</script>

<main>
  {#if d}
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
      }}} />
    </div>

    <div class="heatmap">
      <SvelteHeatmap data={d.trend.map(it => {return {date: it.date, value: it.plays}})}
                     startDate={moment().subtract(1, 'year').toDate()}
                     endDate={moment().toDate()}
                     fontColor="#b3c6ff"
                     emptyColor="#2d333b"
                     colors={['#0e4429', '#006d32', '#26a641', '#39d353']}
      />
    </div>
  {:else}
    <p>Loading...</p>
  {/if}
</main>