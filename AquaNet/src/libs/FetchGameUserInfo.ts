import { DATA, GAME } from './sdk'
import moment from 'moment'
import type { GenericGameSummary, MusicMeta, TrendEntry } from './generalTypes'
import { type GameName } from './scoring'

interface MusicAndPlay extends MusicMeta {
}

export interface UserDetails {
  user: GenericGameSummary;
  trend: TrendEntry[];
  // @ts-ignore
  recent: MusicAndPlay[any];
}

export async function fetchUserDetails(username: string, game: GameName): Promise<UserDetails | null> {
  try {
    const [ user, trend, music ] = await Promise.all([
      GAME.userSummary(username, game),
      GAME.trend(username, game),
      DATA.allMusic(game),
    ])
    const minDate = moment().subtract(60, 'days').format('YYYY-MM-DD')
    const detailedTrend = trend.filter(it => it.date >= minDate)
    const recentPlays = user.recent.map(it => {
      return { ...music[it.musicId], ...it }
    })
    return {
      user,
      trend: detailedTrend,
      recent: recentPlays,
    }
  } catch (error) {
    return null
  }
}

