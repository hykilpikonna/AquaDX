package icu.samnyan.aqua.sega.diva.util;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class DivaStringUtils {

    public static String arrToCsv(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i :
                arr) {
            sb.append(i).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String getDummyString(String content, int length) {
        StringBuilder sb = new StringBuilder();
        sb.append((content + ",").repeat(length));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
