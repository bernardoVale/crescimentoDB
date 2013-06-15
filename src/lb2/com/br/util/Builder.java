package lb2.com.br.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: bernardovale
 * Date: 14/06/13
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
public class Builder {

    /**
     * Recieve a path on filesystem to a txt file and return a String of it
     * @param filePath
     * @return
     * @throws java.io.IOException
     */
    public static String readFileAsString(String filePath) throws java.io.IOException{
        byte[] buffer = new byte[(int) new File(filePath).length()];
        BufferedInputStream f = null;
        try {
            f = new BufferedInputStream(new FileInputStream(filePath));
            f.read(buffer);
            if (f != null) try { f.close(); } catch (IOException ignored) { }
        } catch (IOException ignored) { System.out.println("File not found or invalid path.");}
        return new String(buffer);
    }
}
