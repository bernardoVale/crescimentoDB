package lb2.com.br.main;

import lb2.com.br.core.DataLineParser;
import lb2.com.br.core.Engine;
import lb2.com.br.excel.Export;
import lb2.com.br.model.DataLine;
import lb2.com.br.util.Builder;
import org.apache.poi.hssf.usermodel.HSSFCell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: bernardovale
 * Date: 14/06/13
 * Time: 17:01
 */public class ImportFile {

    private static Engine engine;

    public static void main(String[] args) throws IOException {

        String pathToTxt = args[0];//"/home/bernardovale/Documentos/LB2/clientes/sercontel/2013-Historico_tablespaces_ST.txt";
        //args[0];
        String ignoreTS;
        String fileName;
        String month;
        if(args.length==3){     //Default
            ignoreTS = "TEMP;UNDOTBS1";
            fileName = args[1];
            month = args[2];
        }else{
            fileName = args[1];
            ignoreTS = Builder.readFileAsString(args[3]);
            month = args[2];

        }
        String size = "263485M";
         //args[0]; //Default path args[1];
        //fileName = "/home/bernardovale/Documentos/LB2/clientes/sercontel/sercontel";//args[2];//
        List<DataLine> linesArray;

        try {
            // txt File turn to String
            String data = Builder.readFileAsString(pathToTxt);
            //
            engine = new Engine(data);
            linesArray = engine.extractUseful();
            DataLineParser parser = new DataLineParser(linesArray,ignoreTS,";",fileName,size,month);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
