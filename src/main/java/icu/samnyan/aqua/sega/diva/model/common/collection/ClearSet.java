package icu.samnyan.aqua.sega.diva.model.common.collection;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@NoArgsConstructor
public class ClearSet {
    private int clear = 0;
    private int great = 0;
    private int excellent = 0;
    private int perfect = 0;

    public void addClear() {
        this.clear += 1;
    }

    public void addGreat() {
        this.clear += 1;
        this.great += 1;
    }

    public void addExcellent() {
        this.clear += 1;
        this.great += 1;
        this.excellent += 1;
    }

    public void addPerfect() {
        this.clear += 1;
        this.great += 1;
        this.excellent += 1;
        this.perfect += 1;
    }
}
