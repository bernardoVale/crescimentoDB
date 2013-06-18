package lb2.com.br.core;

import lb2.com.br.excel.Export;
import lb2.com.br.model.DataLine;
import lb2.com.br.model.TableSpace;
import lb2.com.br.util.TablespaceUtil;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Bernardo Vale
 * Date: 15/06/13
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */
public class DataLineParser {

    private List<DataLine> datalines;
    private List<TableSpace> tableSpaces;
    private List<String> tbNames;
    private String ignore;
    private List<String> ignoredList = new ArrayList<String>();
    private Export exp;
    public DataLineParser(List<DataLine> dataLines,String ignore,String regex) {
        this.datalines =  dataLines;
        tbNames = new ArrayList<String>();
        tableSpaces = new ArrayList<TableSpace>();
        this.ignore = ignore;
        parse();
        parseIgnored(regex);
        parseTotals();
        parseToExport();
    }

    /**
     * Add total field for all tablespaces
     */
    private void parseTotals() {
        for(TableSpace ts : tableSpaces){
            ts.setTotal(checkGrowthByTS(ts));
        }
    }

    /**
     * Export tablespaces to MS Excel
     */
    private void parseToExport() {
        double total = checkTotalGrowth();
        exp = new Export("/home/bernardovale/Documentos/LB2/export/"+String.valueOf(System.currentTimeMillis())+".xls"
                ,tableSpaces,total);
        try {
            exp.exportTablespaces();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * Parse the ignore string and create an array of tablespace names that should not be
     * included in database growth calc
     * @param regex
     */
    private void parseIgnored(String regex) {
        String[] ignored = ignore.split(regex);//
        for(int i=0;i<ignored.length;i++){
            ignoredList.add(ignored[i]);
        }
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
    private boolean isIgnored(String tsName){
        return ignoredList.contains(tsName);
    }

    /**
     * Transform datalines data into Tablespace object data
     */
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

    /**
     * Return the average growth by tablespace
     */
    public void checkAverageTSGrowth(){
        for(TableSpace ts : tableSpaces){
            if(!isIgnored(ts.getNome())){//Only IF not ignored
                double growth = checkGrowthByTS(ts);
                double avg = growth/ts.getDatasAtendimento().size(); // media por instancia
                System.out.println("Tablespace:"+ts.getNome());
                System.out.println("Crescimento no periodo:"+growth);
                System.out.println("Crescimento Médio:"+avg);
            }
        }
    }

    /**
     * Check the total growth of all parsed tablespaces
     * @return
     */
    public double checkTotalGrowth(){
        double total = 0;
        for(TableSpace ts : tableSpaces){
            if(!isIgnored(ts.getNome())){
                double first = ts.getUtilizado().get(0);
                double last = ts.getUtilizado().get(ts.getUtilizado().size()-1);
                total = (last-first) + total;
            }
        }
        return total;
    }

    /**
     * Return the total growth of a Tablespace
     * @param ts
     * @return
     */
    public double checkGrowthByTS(TableSpace ts){
        return (ts.getUtilizado().get(ts.getUtilizado().size()-1)
                - ts.getUtilizado().get(0));
    }

    public void checkAverageGrowthMonth(){
        for(TableSpace ts : tableSpaces){
            double instance = numInstance(ts);
            double first = ts.getUtilizado().get(0);
            double last = ts.getUtilizado().get(ts.getUtilizado().size()-1);
            double avg = (last-first)/ts.getDatasAtendimento().size();
            double month = avg*instance;
            System.out.println("Tablespace:"+ts.getNome());
            System.out.println("Crescimento Médio:"+avg);
            System.out.println("Crescimento Médio Mensal:"+month);
        }
    }
    public void averagePerDay(){
        for(TableSpace ts : tableSpaces){
            double instance = numInstance(ts);
            double avg = checkGrowthByTS(ts)/ts.getDatasAtendimento().size();
            double day = avg/instance; //Crescimento diario
            double teste = checkGrowthByTS(ts);
            System.out.println("Tablespace:"+ts.getNome());
            System.out.println("Crescimento Médio:"+avg);
            System.out.println("Crescimento Médio Diário:"+avg);
            System.out.println("Crescimento Médio Mensal:"+day*30);
        }
    }
    private double numInstance(TableSpace ts){
        int dtMedia = 0;
        for (int i=0; i<ts.getDatasAtendimento().size()-1; i++){
            String dt1 = ts.getDatasAtendimento().get(i);
            String dt2 = ts.getDatasAtendimento().get(i+1);
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                java.util.Date date1 = formatter.parse(dt1);
                java.util.Date date2 = formatter.parse(dt2);
                Long dif = (date2.getTime() - date1.getTime())/1000/60/60/24;
                dtMedia = dif.intValue() + dtMedia;
            } catch (ParseException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return dtMedia/(ts.getDatasAtendimento().size()-1);
    }
}
