package kr.shihyeon.imagicthud.util;

public class FormatUtil {

    public static String formatHealthFloat(float value) {
        if (value % 1 <= 0.01) {
            return String.format("%d", Math.round(value));
        } else {
            return String.format("%.1f", value);
        }
    }
}
