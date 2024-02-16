export interface Rating {
    musicId: number
    level: number
    achievement: number
}

export interface ParsedRating extends Rating {
    music: MaimaiMusic,
    calc: number,
    rank: string
}

export interface MaimaiMusic {
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
    recent: MaimaiUserPlaylog[]
}

export interface MaimaiUserPlaylog {
    id: number;
    musicId: number;
    level: number;
    userPlayDate: string;
    trackNo: number;
    vsRank: number;
    achievement: number;
    deluxscore: number;
    scoreRank: number;
    maxCombo: number;
    totalCombo: number;
    maxSync: number;
    totalSync: number;
    tapCriticalPerfect: number;
    tapPerfect: number;
    tapGreat: number;
    tapGood: number;
    tapMiss: number;
    holdCriticalPerfect: number;
    holdPerfect: number;
    holdGreat: number;
    holdGood: number;
    holdMiss: number;
    slideCriticalPerfect: number;
    slidePerfect: number;
    slideGreat: number;
    slideGood: number;
    slideMiss: number;
    touchCriticalPerfect: number;
    touchPerfect: number;
    touchGreat: number;
    touchGood: number;
    touchMiss: number;
    breakCriticalPerfect: number;
    breakPerfect: number;
    breakGreat: number;
    breakGood: number;
    breakMiss: number;
    isTap: boolean;
    isHold: boolean;
    isSlide: boolean;
    isTouch: boolean;
    isBreak: boolean;
    isCriticalDisp: boolean;
    isFastLateDisp: boolean;
    fastCount: number;
    lateCount: number;
    isAchieveNewRecord: boolean;
    isDeluxscoreNewRecord: boolean;
    comboStatus: number;
    syncStatus: number;
    isClear: boolean;
    beforeRating: number;
    afterRating: number;
    beforeGrade: number;
    afterGrade: number;
    afterGradeRank: number;
    beforeDeluxRating: number;
    afterDeluxRating: number;
    isPlayTutorial: boolean;
    isEventMode: boolean;
    isFreedomMode: boolean;
    playMode: number;
    isNewFree: boolean;
    trialPlayAchievement: number;
    extNum1: number;
    extNum2: number;
}
