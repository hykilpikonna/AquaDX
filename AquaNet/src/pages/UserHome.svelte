<script lang="ts">
  import { CHARTJS_OPT, coverNotFound, pfpNotFound, registerChart, renderCal, title } from "../libs/ui";
  import type { GenericGamePlaylog, GenericGameSummary, MusicMeta, TrendEntry } from "../libs/generalTypes";
  import {DATA_HOST} from "../libs/config";
  import 'cal-heatmap/cal-heatmap.css';
  import { Line } from 'svelte-chartjs';
  import moment from "moment";
  import 'chartjs-adapter-moment';
  import { DATA, GAME } from "../libs/sdk";
  import { type GameName, getMult } from "../libs/scoring";
  import StatusOverlays from "../components/StatusOverlays.svelte";

  registerChart()

  export let username: string;
  export let game: GameName
  game = game || "mai2"
  let calElement: HTMLElement
  let error: string | null;
  title(`User ${username}`)

  interface MusicAndPlay extends MusicMeta, GenericGamePlaylog {}

  let d: {
    user: GenericGameSummary,
    trend: TrendEntry[]
    recent: MusicAndPlay[]
  } | null = null

  Promise.all([
    GAME.userSummary(username, game),
    GAME.trend(username, game),
    DATA.allMusic(game)
  ]).then(([user, trend, music]) => {
    console.log(user)
    console.log(trend)

    d = {user, trend, recent: user.recent.map(it => {return {...music[it.musicId], ...it}})}
    localStorage.setItem("tmp-user-details", JSON.stringify(d))
    renderCal(calElement, trend.map(it => {return {date: it.date, value: it.plays}}))
  }).catch((e) => error = e.message);

  const games = {chu3: 'Chuni', mai2: 'Mai', ongeki: 'Ongeki'}
  const titleText = games[game]
</script>

<main id="user-home" class="content">
  {#if d}
    <div class="user-pfp">
      <img src={`${DATA_HOST}/d/${game}/assetbundle/icon/${d.user.iconId.toString().padStart(6, "0")}.png`} alt="" class="pfp" on:error={pfpNotFound}>
      <h2>{d.user.name}</h2>
      <nav>
        {#each Object.entries(games) as [g, name]}
          <a href={`/u/${username}/${g}`} class:active={game === g}>{name}</a>
        {/each}
      </nav>
    </div>

    <div>
      <h2>{titleText} Statistics</h2>
      <div class="scoring-info">
        <div class="chart">
          <div class="info-top">
            <div class="rating">
              <span>{game === 'mai2' ? 'DX Rating' : 'Rating'}</span>
              <span>{d.user.rating.toLocaleString()}</span>
            </div>

            <div class="rank">
              <span>Server Rank</span>
              <span>#{+d.user.serverRank.toLocaleString() + 1}</span>
            </div>
          </div>

          <div class="trend">
            <!-- ChartJS cannot be fully responsive unless there is a parent div that's independent from its size and helps it determine its size -->
            <div class="chartjs-box-reference">
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
            <span>{(d.user.accuracy).toFixed(2)}%</span>
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
            <span>{game === 'mai2' ? 'DX Score' : 'Total Score'}</span>
            <span>{d.user.totalScore.toLocaleString()}</span>
          </div>
        </div>
      </div>
    </div>

    <div>
      <h2>Play Activity</h2>
      <div class="activity-info">
        <div id="cal-heatmap" bind:this={calElement} />

        <div class="info-bottom">
          <div class="plays">
            <span>Plays</span>
            <span>{d.user.plays}</span>
          </div>

          <div class="time">
            <span>Play Time</span>
            <span>{(d.user.totalPlayTime / 60).toFixed(1)} hr</span>
          </div>

          <div class="first-play">
            <span>First Seen</span>
            <span>{moment(d.user.joined).format("YYYY-MM-DD")}</span>
          </div>

          <div class="last-play">
            <span>Last Seen</span>
            <span>{moment(d.user.lastSeen).format("YYYY-MM-DD")}</span>
          </div>

          <div class="last-version">
            <span>Last Version</span>
            <span>{d.user.lastVersion}</span>
          </div>
        </div>
      </div>
    </div>

    <div class="recent">
      <h2>Recent Scores</h2>
      <div class="scores">
        {#each d.recent as r, i}
          <div class:alt={i % 2 === 0}>
            <img src={`${DATA_HOST}/d/${game}/music/00${r.musicId.toString().padStart(6, '0').substring(2)}.png`} alt="" on:error={coverNotFound} />
            <div class="info">
              <div>{r.name ??"Unable find music"}</div>
              <div>
                <span class={`lv level-${r.level === 10 ? 3 : r.level}`}>
                  { r.notes?.[r.level === 10 ? 0 : r.level]?.lv?.toFixed(1) ?? r.level ?? '0'}
                </span>
                <span class={"rank-" + ("" + getMult(r.achievement, game)[2])[0]}>
                  <span class="rank-text">{("" + getMult(r.achievement, game)[2]).replace("p", "+")}</span>
                  <span class="rank-num">{(r.achievement / 10000).toFixed(2)}%</span>
                </span>
                {#if game === 'mai2'}
                  <span class:increased={r.afterRating - r.beforeRating > 0} class="dx-change">
                    {r.afterRating - r.beforeRating}
                  </span>
                {/if}
              </div>
            </div>
          </div>
        {/each}
      </div>
    </div>
  {/if}

  <StatusOverlays {error} loading={!d} />
</main>

<style lang="sass">
@import "../vars"

$gap: 20px

#user-home
  .user-pfp
    display: flex
    align-items: flex-end
    gap: $gap
    margin-top: -72px
    position: relative

    h2
      font-size: 2rem
      margin: 0
      white-space: nowrap

    nav
      position: absolute
      display: flex
      flex-direction: row
      gap: 10px
      top: 4px
      right: 0

  .pfp
    width: 100px
    height: 100px
    border-radius: 12px
    object-fit: cover

  @media (max-width: $w-mobile)
    .user-pfp
      margin-top: -68px
      h2
        font-size: 1.5rem

    .pfp
      width: 80px
      height: 80px

  .info-bottom, .info-top, .other-info
    display: flex
    gap: $gap

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
    gap: $gap
    max-height: 250px

    .chart
      flex: 0 1 790px
      display: flex
      flex-direction: column

    .other-info
      flex: 1 0 100px
      flex-direction: column
      gap: 0
      justify-content: space-between

    .trend
      height: 300px
      width: 100%
      max-width: 790px

      position: relative

      > .chartjs-box-reference
        position: absolute
        inset: 0

    @media (max-width: $w-mobile)
      flex-direction: column
      max-height: unset

      .chart
        flex: 0

        .trend
          max-height: 130px

      .other-info
        > div
          flex-direction: row
          justify-content: space-between

      .info-bottom
        justify-content: space-between

  .activity-info
    display: flex
    flex-direction: column
    gap: $gap

    #cal-heatmap
      overflow-x: auto

    @media (max-width: $w-mobile)
      #cal-heatmap
        width: 100%

      .info-bottom
        flex-direction: column
        gap: 0

        > div
          flex-direction: row
          justify-content: space-between

  // Recent Scores section
  .recent
    .scores
      display: flex
      flex-direction: column
      flex-wrap: wrap
      gap: $gap

      > div.alt
        background-color: rgba(white, 0.03)
        border-radius: 10px

      // Image and song info
      > div
        display: flex
        align-items: center
        gap: $gap
        padding-right: 16px
        max-width: 100%
        box-sizing: border-box

        img
          width: 50px
          height: 50px
          border-radius: 10px
          object-fit: cover

        // Song info and score
        > div.info
          flex: 1
          display: flex
          justify-content: space-between
          overflow: hidden

          // Limit song name to one line
          > div:first-child
            flex: 1
            min-width: 0
            overflow: hidden
            text-overflow: ellipsis
            white-space: nowrap

          // Make song score and rank not wrap
          > div:last-child
            white-space: nowrap

          @media (max-width: $w-mobile)
            flex-direction: column
            gap: 0

            .rank-text
              text-align: left

        .rank-S
          // Gold green gradient on text
          background: linear-gradient(90deg, #ffee94, #ffb798, #ffa3e5, #ebff94)
          -webkit-background-clip: text
          color: transparent

        .rank-A
          color: #ff8a8a

        .rank-B
          color: #6ba6ff

        .lv
          min-width: 30px
          text-align: center
          background: rgba(var(--lv-color), 0.6)
          padding: 0 6px
          border-radius: 10px
          margin-right: 10px

        span
          display: inline-block
          text-align: right

        // Vertical table-like alignment
        span.rank-text
          min-width: 40px
        span.rank-num
          min-width: 60px
        span.dx-change
          min-width: 30px

      span.increased
        &:before
          content: "+"
        color: $c-good
</style>
