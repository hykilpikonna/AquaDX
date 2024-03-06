export interface TrendEntry {
  date: string
  rating: number
  plays: number
}

export interface Card {
  luid: string
  registerTime: string
  accessTime: string
  linked: boolean
  ghost: boolean
}

export interface AquaNetUser {
  username: string
  email: string
  displayName: string
  country: string
  lastLogin: number
  regTime: number
  profileLocation: string
  profileBio: string
  profilePicture: string
  emailConfirmed: boolean
  ghostCard: Card
  cards: Card[]
  computedName: string,
}

export interface CardSummaryGame {
  name: string
  rating: number
  lastLogin: string
}

export interface CardSummary {
  mai2: CardSummaryGame | null
  chu3: CardSummaryGame | null
  ongeki: CardSummaryGame | null
  diva: CardSummaryGame | null
}


export interface ConfirmProps {
  title: string
  message: string
  confirm: () => void
  cancel?: () => void
  dangerous?: boolean
}

export interface GenericGamePlaylog {
  musicId: number
  level: number
  playDate: string
  achievement: number
  maxCombo: number
  totalCombo: number
  afterRating: number
  beforeRating: number
}

export interface GenericRanking {
  name: string
  username: string
  rank: number
  accuracy: number
  rating: number
  fullCombo: number
  allPerfect: number
  lastSeen: string
}

export interface RankCount {
  name: string
  count: number
}

export interface GenericGameSummary {
  name: string
  iconId: number
  aquaUser?: AquaNetUser
  serverRank: number
  accuracy: number
  rating: number
  ratingHighest: number
  ranks: RankCount[]
  maxCombo: number
  fullCombo: number
  allPerfect: number
  totalScore: number
  plays: number
  totalPlayTime: number
  joined: string
  lastSeen: string
  lastVersion: string
  ratingComposition: { [key: string]: any }
  recent: GenericGamePlaylog[]
}

export interface MusicMeta {
  name: string,
  composer: string,
  bpm: number,
  ver: number,
  notes: {
    lv: number
    designer: string
    lv_id: number
    notes: number
  }[]
}

export type AllMusic = { [key: string]: MusicMeta }
