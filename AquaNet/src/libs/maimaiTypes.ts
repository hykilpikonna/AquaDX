export interface Rating {
    musicId: number
    level: number
    achievement: number
}

export interface ParsedRating extends Rating {
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
    calc: number,
    rank: string
}

export interface MaiUserPreviewData {
    userName: string
    userId: number
    trophyId: number
    totalAwake: number
    playerRating: number
    partnerId: number
    nameplateId: number
    lastRomVersion: string
    lastPlayDate: string
    lastLoginDate: string
    lastGameId: string
    lastDataVersion: string
    isNetMember: boolean
    isLogin: boolean
    iconId: number
    frameId: number
}

export interface MaimaiUserSummaryEntry {
    name: string
    iconId: number
    serverRank: number
    accuracy: number
    rating: number
    ratingHighest: number
    ranks: { name: string, count: number }[]
    maxCombo: number
    fullCombo: number
    allPerfect: number
    totalDxScore: number
    plays: number
    totalPlayTime: number
    joined: string
    lastSeen: string
    lastVersion: string
    best35: string
    best15: string
    recent: Rating[]
}