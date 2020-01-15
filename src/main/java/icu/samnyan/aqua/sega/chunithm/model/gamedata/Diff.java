package icu.samnyan.aqua.sega.chunithm.model.gamedata;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public enum Diff {

    BASIC("BASIC"),
    ADVANCED("ADVANCED"),
    EXPERT("EXPERT"),
    MASTER("MASTER"),
    WE("WORLD'S END");

    private String displayName;

    Diff(String displayName) {
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
