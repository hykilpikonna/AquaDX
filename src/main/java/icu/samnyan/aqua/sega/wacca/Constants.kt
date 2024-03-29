package icu.samnyan.aqua.sega.wacca

enum class WaccaGrades(val value: Int) {
    D(1),
    C(2),
    B(3),
    A(4),
    AA(5),
    AAA(6),
    S(7),
    SS(8),
    SSS(9),
    MASTER(10),
    S_PLUS(11),
    SS_PLUS(12),
    SSS_PLUS(13),
}

enum class WaccaDifficulty(val value: Int) {
    NORMAL(1),
    HARD(2),
    EXPERT(3),
    INFERNO(4),
}

val WACCA_ITEM_TYPES = mapOf(
    "xp" to 1,
    "wp" to 2,
    "music_unlock" to 3,
    "music_difficulty_unlock" to 4,
    "title" to 5,
    "icon" to 6,
    "trophy" to 7,
    "skill" to 8,
    "ticket" to 9,
    "note_color" to 10,
    "note_sound" to 11,
    "baht_do_not_send" to 12,
    "boost_badge" to 13,
    "gate_point" to 14,
    "navigator" to 15,
    "user_plate" to 16,
    "touch_effect" to 17,
)

enum class WaccaOptionType(val id: Int, val default: Int) {
    NOTE_SPEED(1, 5), // 1.0 - 6.0
    FIELD_MASK(2, 0), // 0-4
    NOTE_SOUND(3, 105001), // ID
    NOTE_COLOR(4, 203001), // ID
    BGM_VOLUME(5, 10), // 0-100 increments of 10
    BG_VIDEO(7, 0), // ask, on, or off
    MIRROR(101, 0), // none or left+right swap
    JUDGE_DISPLAY_POS(102, 0), // center, under, over, top or off
    JUDGE_DETAIL_DISPLAY(103, 0), // on or off
    MEASURE_GUIDELINES(105, 1), // on or off
    GUIDELINE_MASK(106, 1), // 0 - 5
    JUDGE_LINE_TIMING_ADJUST(108, 10), // -10 - 10
    NOTE_DESIGN(110, 3), // 1 - 5
    BONUS_EFFECT(114, 1), // on or off
    CHARA_VOICE(115, 1), // "usually" or none
    SCORE_DISPLAY_METHOD(116, 0), // add or subtract
    GIVE_UP(117, 0), // off, no touch, can't achieve s, ss, sss, pb
    GUIDELINE_SPACING(118, 1), // none, or a-g type
    CENTER_DISPLAY(119, 1), // none, combo, score add, score sub, s ss sss pb boarder
    RANKING_DISPLAY(120, 1), // on or off
    STAGE_UP_ICON_DISPLAY(121, 1), // on or off
    RATING_DISPLAY(122, 1), // on or off
    PLAYER_LEVEL_DISPLAY(123, 1), // on or off
    TOUCH_EFFECT(124, 1), // on or off
    GUIDE_SOUND_VOL(125, 30), // 0-100 increments of 10
    TOUCH_NOTE_VOL(126, 80), // 0-100 increments of 10
    HOLD_NOTE_VOL(127, 80), // 0
    SLIDE_NOTE_VOL(128, 80), // 0
    SNAP_NOTE_VOL(129, 80), // 0
    CHAIN_NOTE_VOL(130, 80), // 0
    BONUS_NOTE_VOL(131, 80), // 0
    GATE_SKIP(132, 0), // on
    KEY_BEAM_DISPLAY(133, 1), // on
    LEFT_SLIDE_NOTE_COLOR(201, 4), // red
    RIGHT_SLIDE_NOTE_COLOR(202, 3), // red
    FORWARD_SLIDE_NOTE_COLOR(203, 1), // red
    BACK_SLIDE_NOTE_COLOR(204, 2), // red
    MASTER_VOL(1001, 3), // 0
    SET_TITLE_ID(1002, 104001), // ID
    SET_ICON_ID(1003, 102001), // ID
    SET_NAV_ID(1004, 210001), // ID
    SET_PLATE_ID(1005, 211001), // ID
}