package preti.stock.fe.location;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PresentationTools {
    private static Locale ptBR = new Locale("pt", "BR");

    public static String formatDate(Date d) {
        return new SimpleDateFormat("dd/MM/yyyy").format(d);
    }
    
    public static Date parseDate(String d) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(d);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatCurrency(Number n) {
        return NumberFormat.getCurrencyInstance(ptBR).format(n);
    }

    public static String formatDoubleNumber(Number n) {
        return NumberFormat.getInstance(ptBR).format(n);
    }

}
