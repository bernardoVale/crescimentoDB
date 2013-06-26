package lb2.com.br.util;

/**
 * User: bernardovale
 * Date: 25/06/13
 * Time: 18:00
 */
public class DateUtil {

    /**
     * Check if a date its inside a @param month
     * @return
     */
    public static boolean insideMonth(String data,String month){
        String monYear = data.substring(3);
        return monYear.equalsIgnoreCase(month);
    }
}
