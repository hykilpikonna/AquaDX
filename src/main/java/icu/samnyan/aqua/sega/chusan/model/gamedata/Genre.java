package icu.samnyan.aqua.sega.chusan.model.gamedata;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public enum Genre {
    POPS_ANIME("POPS & ANIME"),
    GAME("GAME"),
    NICONICO("niconico"),
    TOUHOU("東方Project"),
    RESERVE2("Reserve2"),
    ORIGINAL("Original"),
    VARIETY("Variety"),
    IRODORI("イロドリミドリ"),
    KOTONOHA("言ノ葉Project"),
    GEKICHUMA("ゲキマイ");

    private String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
