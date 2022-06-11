package Helpers;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class PriceFormatter {

    public static String format(float num) {
        BigDecimal trieu = new BigDecimal(num * 1000000);
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat fmoney = NumberFormat.getCurrencyInstance(vietnam);

        return fmoney.format(trieu);
    }
}
