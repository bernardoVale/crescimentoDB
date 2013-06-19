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

    public static void main(String[] args) {

        String pathToTxt = args[0];//"/home/bernardovale/Documentos/LB2/clientes" +
                //"/agroBrasil/2013-Historico_tablespaces_ORCL_AB.txt";
       /* String pathToTxt = "/home/bernardovale/Documentos/LB2/clientes" +
                "/agroBrasil/teste2.txt";        */
        String ignoreTS = args[1];//"TEMP;UNDOTBS1";//args[0]; //Default path
        String fileName = args[2];//"abORCL";
        List<DataLine> linesArray;

        try {
            // txt File turn to String
            String data = Builder.readFileAsString(pathToTxt);
            ignoreTS = Builder.readFileAsString(ignoreTS);
            engine = new Engine(data);
            linesArray = engine.extractUseful();
            DataLineParser parser = new DataLineParser(linesArray,ignoreTS,";",fileName);
            parser.checkAverageTSGrowth();
            System.out.println("Crescimento total:"+parser.checkTotalGrowth());
            //parser.checkAverageTSGrowth();
            //parser.checkAverageGrowthMonth();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
