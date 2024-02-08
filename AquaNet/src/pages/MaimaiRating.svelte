<script lang="ts">
  import {data_host} from "../libs/config";

  const data = {
    username: "AZA☆",
    rating: "701:2:19011:1006030,11528:2:23500:1006452,836:2:19910:995577,572:2:18021:997469,757:2:19507:993536,839:2:19911:1005278,606:2:18019:982216,11517:2:23014:993709,625:2:18503:993160,11408:2:22501:1005514,771:2:19511:993191,734:2:19500:989036,11479:2:23011:988050,11594:2:23503:1002051,682:2:19004:1000732,797:2:19900:982911,772:2:19511:987791,10204:2:23506:1001532,841:2:19912:992283,11565:3:23500:983334,11489:2:23000:997830,709:2:19013:986791,11469:2:23012:985848,11514:2:23014:1003829,720:2:19006:1001675,11565:2:23500:1001386,11003:3:20000:984282,11168:3:21004:983648,11588:2:23504:1000833,11410:3:22501:1000243,63:3:10000:1000119,11587:2:23504:998203,11236:2:21508:997255,375:3:14000:981309,510:2:19505:1004313",
    ratingNew: "11642:2:24006:1003839,11580:3:24000:972621,11640:2:24006:985702,11633:2:24000:986507,11634:3:24000:985023,10411:2:24000:977957,11624:2:24000:966136,11578:2:24000:963522,11643:2:24006:962392,11629:3:24000:966908,11619:2:24005:939345,11640:3:24006:904054,11641:3:24006:903064,11642:3:24006:875413,11634:2:24000:922194"
  }

  const multTable = [
      [100.5, 22.4, "SSSp"],
      [100, 21.6, "SSS"],
      [99.5, 21.1, "SSp"],
      [99, 20.8, "SS"],
      [98, 20.3, "Sp"],
      [97, 20, "S"],
      [94, 16.8, "AAA"],
      [90, 15.2, "AA"],
      [80, 13.6, "A"]
  ]

  function getMult(achievement: number) {
    achievement /= 10000
    for (let i = 0; i < multTable.length; i++) {
      if (achievement >= (multTable[i][0] as number)) {
        return multTable[i]
      }
    }
    return [0, 0, 0]
  }

  // Parse rating
  function parseRatings(rating: string, musicInfo: any) {
    const arr = rating.split(',').map(x => x.split(':').map(x => parseInt(x)))
    return arr.map(x => {
      const music = musicInfo[x[0]]

      if (!music) {
        console.error(`Music not found: ${x[0]}`)
        return null
      }

      music.note = music.notes[x[1]]
      const mult = getMult(x[3])
      return {
        music: music,
        musicId: x[0],
        difficulty: x[1],
        todo: x[2],  // TODO: Figure out what this is
        achievement: x[3],
        calc: (mult[1] as number) * music.note.lv,
        rank: mult[2]
      }
    }).filter(x => x)
  }

  interface ParsedRating {
    music: {
      name: string,
      composer: string,
      bpm: number,
      ver: number,
      note: {
        lv: number
        designer: string
        lv_id: number
        notes: number
      }
    },
    musicId: number,
    difficulty: number,
    achievement: number,
    calc: number,
    rank: string
  }

  let parsedRatings: {
    old: ParsedRating[],
    new: ParsedRating[]
  } | null = null

  // Get music information from data server
  fetch(`${data_host}/maimai/meta/00/all-music.json`).then(it => it.json()).then(musicInfo => {
    parsedRatings = {
      old: parseRatings(data.rating, musicInfo),
      new: parseRatings(data.ratingNew, musicInfo)
    }

    console.log(parsedRatings)
  })
</script>

<!-- Display all parsed ratings -->
{#if parsedRatings}
  {#each [{title: "Old", data: parsedRatings.old}, {title: "New", data: parsedRatings.new}] as section}
    <h2>{section.title}</h2>
    <div class="rating-cards">
      {#each section.data as rating}
        <div class="level-{rating.difficulty}">
          <img class="cover" src={`${data_host}/maimai/assetbundle/jacket_s/00${rating.musicId.toString().padStart(6, '0').substring(2)}.png`} alt="">

          <div class="detail">
            <span class="name">{rating.music.name}</span>
            <span class="rating">
              <span>{(rating.achievement / 10000).toFixed(2)}%</span>
              <img class="rank" src={`${data_host}/maimai/sprites/rankimage/UI_GAM_Rank_${rating.rank}.png`} alt="">
            </span>
            <span>{rating.calc.toFixed(1)}</span>
          </div>
          <img class="ver" src={`${data_host}/maimai/sprites/tab/title/UI_CMN_TabTitle_MaimaiTitle_Ver${rating.music.ver.toString().substring(0, 3)}.png`} alt="">
          <div class="lv">{rating.music.note.lv}</div>
        </div>
      {/each}
    </div>
  {/each}
{/if}

<style lang="sass">
.rating-cards
  display: grid
  gap: 2rem
  width: 100%

  // Fill as many columns as possible
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr))

  // Center the cards
  justify-items: center
  align-items: center

  // Style each card
  > div
    $border-radius: 20px
    width: 200px
    height: 200px
    border-radius: $border-radius

    display: flex
    position: relative

    // Difficulty border
    border: 5px solid var(--lv-color, #60aaff)
    &.level-1
      --lv-color: #aaff60
    &.level-2
      --lv-color: #f25353
    &.level-3
      --lv-color: #e881ff

    img
      object-fit: cover
      pointer-events: none

    img.cover
      width: 100%
      height: 100%
      border-radius: calc($border-radius - 3px)

    img.ver
      position: absolute
      top: -20px
      left: -30px
      height: 50px

    // Information
    .detail
      position: absolute
      bottom: 0
      left: 0
      right: 0
      padding: 10px
      background: rgba(0, 0, 0, 0.5)
      border-radius: 0 0 calc($border-radius - 3px) calc($border-radius - 3px)

      // Blur
      backdrop-filter: blur(3px)

      display: flex
      flex-direction: column
      text-align: left

      > span
        // Disable text wrapping, max 2 lines
        overflow: hidden
        text-overflow: ellipsis
        white-space: nowrap

      .name
        font-size: 1.2em
        font-weight: bold

      .rating
        display: flex
        img
          height: 1.5em

    .lv
      position: absolute
      bottom: 0
      right: 0
      padding: 5px 10px
      background: var(--lv-color)
      // Top left border radius
      border-radius: 10px 0

      font-size: 1.3em

      &:before
        content: "Lv"
        font-size: 0.8em

  // Mobile
  @media (max-width: 500px)
    margin-left: -1rem
    margin-right: -1rem
    width: calc(100% + 2rem)
    grid-template-columns: repeat(auto-fill, minmax(130px, 1fr))
    font-size: 0.8em
    > div
      width: 150px
      height: 150px

      img.ver
        height: 45px
        left: -20px
</style>