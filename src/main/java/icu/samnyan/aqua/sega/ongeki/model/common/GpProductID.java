package icu.samnyan.aqua.sega.ongeki.model.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public enum GpProductID {
    A_Credit1(0),
    A_Credit2(1),
    A_Credit3(2),
    B_Credit1(3),
    B_Credit2(4),
    B_Credit3(5),
    End(6);

    private int value;

    GpProductID(int i) { this.value = i; }

    @JsonValue
    public int getValue() {
        return this.value;
    }

}
