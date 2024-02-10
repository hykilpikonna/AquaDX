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