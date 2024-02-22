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

export interface UserMe {
  username: string
  email: string
  displayName: string
  country: string
  lastLogin: number
  regTime: number
  profileLocation: string
  profileBio: string
  emailConfirmed: boolean
  ghostCard: Card
  cards: Card[]
  computedName: string
}

export interface CardSummaryGame {
  name: string
  rating: number
  lastLogin: string
}

export interface CardSummary {
  maimai: CardSummaryGame | null
  maimai2: CardSummaryGame | null
  chusan: CardSummaryGame | null
  chunithm: CardSummaryGame | null
  ongeki: CardSummaryGame | null
  diva: CardSummaryGame | null
}