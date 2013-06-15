package lb2.com.br.main;

import lb2.com.br.core.DataLineParser;
import lb2.com.br.core.Engine;
import lb2.com.br.model.DataLine;
import lb2.com.br.util.Builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bernardovale
 * Date: 14/06/13
 * Time: 17:01
 * To change this template use File | Settings | File Templates.
 */
public class ImportFile {


    public static void main(String[] args) {

        String pathToTxt = "/home/bernardovale/Documentos/LB2/clientes" +
                "/flessak/teste.txt";//args[0]; //Default path
        Engine engine = new Engine();
        List<String> tableSpace = new ArrayList<String>();
        List<DataLine> linesArray = new ArrayList<DataLine>();

        try {
            // txt File turn to String
            String data = Builder.readFileAsString(pathToTxt);
            linesArray = engine.extractUseful(data);
            DataLineParser parser = new DataLineParser(linesArray);
         /*   for(DataLine tb : tablesFromInstance){
                int i = tableSpacesName.indexOf(tb.getNome());
                if(i==-1){

                    System.out.println(tb.getNome());
                    tableSpacesName.add(tb.getNome());
                }
            }
            for(String name : tableSpacesName){
                for(DataLine tb :tablesFromInstance){
                    if(tb.getNome().equals(name)){

                    }
                }
            }   */


        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
