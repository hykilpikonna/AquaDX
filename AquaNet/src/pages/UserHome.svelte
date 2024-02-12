<script lang="ts">
  import {registerChart, title} from "../libs/ui";
  import {getMaimai, getMaimaiTrend} from "../libs/maimai";
  import type {MaiUserPreviewData} from "../libs/maimaiTypes";
  import type {TrendEntry} from "../libs/generalTypes";
  import {data_host} from "../libs/config";
  // @ts-ignore
  import CalHeatmap from 'cal-heatmap';
  // @ts-ignore
  import Tooltip from 'cal-heatmap/plugins/Tooltip';
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
    const cal = new CalHeatmap();
    cal.paint({
      itemSelector: calElement,
      domain: {
        type: 'month',
        label: { text: 'MMM', textAlign: 'start', position: 'top' },
      },
      subDomain: {
        type: 'ghDay',
        radius: 2, width: 11, height: 11, gutter: 4
      },
      range: 12,
      data: {source: d.trend, x: 'date', y: 'plays'},
      scale: {
        color: {
          type: 'linear',
          range: ['#14432a', '#4dd05a'],
          domain: [0, d.trend.reduce((a, b) => Math.max(a, b.plays), 0)]
        },
      },
      date: {start: moment().subtract(1, 'year').add(1, 'month').toDate()},
      theme: "dark",
    }, [
      [Tooltip, {text: (_: Date, v: number, d: any) =>
        `${v ?? "No"} songs played on ${d.format('MMMM D, YYYY')}`
      }]
    ]);
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
      }}} />
    </div>

    <div id="cal-heatmap" bind:this={calElement} />
  {:else}
    <p>Loading...</p>
  {/if}
</main>