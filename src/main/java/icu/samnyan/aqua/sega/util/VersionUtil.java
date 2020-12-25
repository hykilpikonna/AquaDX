package icu.samnyan.aqua.sega.util;

public class VersionUtil {

    public static VersionInfo parseVersion(String version) {
        var vs = version.split("\\.");
        try {
            VersionInfo v = new VersionInfo();
            if (vs.length > 0) {
                v.setMajorVersion(Integer.parseInt(vs[0]));
            }
            if (vs.length > 1) {
                v.setMinorVersion(Integer.parseInt(vs[1]));
            }
            if (vs.length > 2) {
                v.setReleaseVersion(Integer.parseInt(vs[2]));
            }
            return v;
        } catch (Exception e) {
            return new VersionInfo(0, 0, 0);
        }

    }

    public static String getTargetVersion(String savedVersion, String currentVersion) {
        var v1s = savedVersion.split("\\.");
        var v2s = currentVersion.split("\\.");
        try {
            for (int i = 0; i < v1s.length; i++) {
                int v1n = Integer.parseInt(v1s[i]);
                int v2n = Integer.parseInt(v2s[i]);
                if (v1n > v2n) {
                    return currentVersion;
                }
                if (v1n < v2n) {
                    return savedVersion;
                }
            }
            // if all same
            return savedVersion;
        } catch (Exception e) {
            return savedVersion;
        }
    }
}
