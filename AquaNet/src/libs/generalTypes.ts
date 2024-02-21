export interface TrendEntry {
  date: string
  rating: number
  plays: number
}

export interface Card {
  luid: string
  registerTime: string
  accessTime: string
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