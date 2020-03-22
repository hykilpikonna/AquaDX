package icu.samnyan.aqua.sega.ongeki.model.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public enum IdListType {
    Invalid,
    NgMusic,
    Recommend;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
