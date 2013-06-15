package lb2.com.br.core;

import lb2.com.br.model.DataLine;
import lb2.com.br.model.TableSpace;
import lb2.com.br.util.TablespaceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bernardovale
 * Date: 15/06/13
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */
public class DataLineParser {

    private List<DataLine> datalines;
    private List<TableSpace> tableSpaces;
    private List<String> tbNames;
    public DataLineParser(List<DataLine> dataLines) {
        this.datalines =  dataLines;
        tbNames = new ArrayList<String>();
        tableSpaces = new ArrayList<TableSpace>();
        parse();
    }

    /**
     * Parse and extract all tablespaces from datalines
     */
    private void extractTablespaces(){
        for(DataLine dt : datalines){
            int i = tbNames.indexOf(dt.getNome());
            if(i==-1){
                tbNames.add(dt.getNome());
                //Put in this array too parse easier the other information after
                tableSpaces.add(new TableSpace(dt.getNome()));
            }
        }
    }
    private void parse(){
        extractTablespaces();
        List<String> atendimentos = new ArrayList<String>();
        List<Double> utilizados = new ArrayList<Double>();
        for (DataLine dt : datalines){
            int index = TablespaceUtil.tbNameIndex(tableSpaces,dt.getNome());
            tableSpaces.get(index).setDatasAtendimento(dt.getData());
            tableSpaces.get(index).setUtilizado(dt.getUtilizado());
        }
    }
}
