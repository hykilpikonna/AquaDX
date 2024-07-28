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
  isGhost: boolean
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
  detailedRanks: { [key: number]: { [key: string]: number } }
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
  favSongs?: number[]
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

export interface GameOption {
  key: string
  value: any
  type: 'Boolean'
}

export interface UserBox {
  userName:string,
  level:number,
  exp:string,
  point:number,
  totalPoint:number,
  playerRating:number,
  highestRating:number,
  nameplateId:number,
  frameId:number,
  characterId:number,
  trophyId:number,
  totalMapNum:number,
  totalHiScore: number,
  totalBasicHighScore:number,
  totalAdvancedHighScore:number,
  totalExpertHighScore:number,
  totalMasterHighScore:number,
  totalUltimaHighScore:number,
  friendCount:number,
  firstPlayDate:Date,
  lastPlayDate:Date,
  courseClass:number,
  overPowerPoint:number,
  overPowerRate:number,
  mapIconId:number,
  voiceId:number,
  avatarWear: number,
  avatarHead: number,
  avatarFace: number,
  avatarSkin: number,
  avatarItem: number,
  avatarFront: number,
  avatarBack: number,
}

// Assign a number to each kind of user box item with an enum
export enum UserBoxItemKind {
  nameplate = 1,
  frame = 2,
  trophy = 3,
  mapicon = 8,
  sysvoice = 9,
  avatar = 11,
}

// Define type only with the keys
export type UserBoxItemKindStr = keyof typeof UserBoxItemKind;

type ChangePlateReq = {kind:'plate', nameplateId:number}
type ChangeFrameReq = {kind:'frame', frameId:number}
type ChangeTrophyReq = {kind:'trophy',trophyId:number}
type ChangeMapIconReq = {kind:'mapicon',mapiconid:number}
type ChangeVoiceReq = {kind:'sysvoice',voiceId:number}
type ChangeAvatarReq = {
  kind:'avatar',
  accId:number,
  category:number
}

export type ChangeUserBoxReq = {aimeId:string} & (ChangePlateReq | ChangeFrameReq | ChangeTrophyReq | ChangeMapIconReq | ChangeVoiceReq | ChangeAvatarReq);

