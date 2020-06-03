package utils;

import java.text.DecimalFormat;

public class Format {
    public static String longToString(long l){
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(l);
    }
}
