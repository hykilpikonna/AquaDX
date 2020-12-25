package icu.samnyan.aqua.sega.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sam_nya (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionInfo {
    private Integer majorVersion;
    private Integer minorVersion;
    private Integer releaseVersion;
}
