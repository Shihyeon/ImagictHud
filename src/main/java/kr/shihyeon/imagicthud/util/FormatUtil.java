package kr.shihyeon.imagicthud.util;

import java.text.DecimalFormat;

public class FormatUtil {

    private static final DecimalFormat numberFormat = new DecimalFormat("#,###.#");

    public static String formatHealthFloat(float value) {
        if (value % 1 <= 0.01) {
            return numberFormat.format(Math.round(value));
        } else {
            return numberFormat.format(value);
        }
    }
}
