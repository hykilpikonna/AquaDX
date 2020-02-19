package icu.samnyan.aqua.sega.diva.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
@AllArgsConstructor
public class ContestProgress {
    int pvId;
    int hardness;
    int edition;
    int stars;
    int scores;
    int version;
}
