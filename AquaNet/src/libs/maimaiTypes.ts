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