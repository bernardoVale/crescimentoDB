package lb2.com.br.core;

import lb2.com.br.model.DataLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bernardovale
 * Date: 14/06/13
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */
public class Engine {

    private List<String> realData = new ArrayList<String>();
    private List<DataLine> dtLine = new ArrayList<DataLine>();

    public List<DataLine> extractUseful(String data){
        String [] linesArray = data.split("\n");
        //First remove the garbage lines
        for(String line : linesArray){
            //Split for blank space and add to another array just the
            //real information
            String [] lineColumns = line.split(" ");
            for(String column : lineColumns){
                if(!column.equals("")){//Just if its not garbage
                    realData.add(column);
                }
            }
            clearTempAndPopulate();
        }
        return dtLine;
    }

    /**
     * Populate real data if, and only if,
     * the temporary array was not empty
     */
    private void clearTempAndPopulate(){
        if(realData.size()!=0){ //Check if this is not a blank line
            dtLine.add(new DataLine(realData));//If not add a new Dataline
        }
        realData.clear();//Clear this shit
    }
}
