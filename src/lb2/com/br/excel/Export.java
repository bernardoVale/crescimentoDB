package lb2.com.br.excel;

import lb2.com.br.model.TableSpace;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bernardovale
 * Date: 18/06/13
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
public class Export {

    private HSSFWorkbook workbook = new HSSFWorkbook();
    private HSSFSheet sheet;
    private FileOutputStream fos = null;
    private double total;
    private String nomeArquivo;
    private List<TableSpace> data;

    public Export(String nomeArquivo,List<TableSpace> data,double total) {
        sheet = workbook.createSheet("crescimentoTs");
        this.nomeArquivo = nomeArquivo;
        this.data = data;
        this.total = total;
    }

    /**
     * Populates all tablespaces names into the first Row
     * also add the dates
     */
    public void header(){
        HSSFRow cabecalho = sheet.createRow(0);
        int i = 1;
        cabecalho.createCell(0).setCellValue("ATENDIMENTO");
        for(TableSpace ts : data){
            cabecalho.createCell(i).setCellValue(ts.getNome());
            i++;
        }
    }
    /**
     *
     * @param rows
     */
    private void instantiateTable(int rows){
        int cont = 0;
        for (int i=1;i<=rows;i++){
            sheet.createRow(i).createCell(0).setCellValue(
                    data.get(0).getDatasAtendimento().get(cont)
            );
            cont++;
        }
        HSSFCell cel1 = sheet.createRow(cont + 2).createCell(0);
        cel1.setCellValue("TOTAL TABLESPACE");
        sheet.createRow(cont+3).createCell(0).setCellValue("TOTAL");
        sheet.createRow(cont+4).createCell(0).setCellValue(total);
    }
    public void exportTablespaces() throws IOException {
        try {
            fos = new FileOutputStream(new File(nomeArquivo));
            instantiateTable(data.get(0).getUtilizado().size());
            header();
            int row = 1;
            int column = 1;
            for(TableSpace ts : data){
                for(Double value : ts.getUtilizado()){
                    sheet.getRow(row).createCell(column).setCellValue(value);
                    row++;
                }
                sheet.getRow(row+1).createCell(column).setCellValue(ts.getTotal());
                row = 1;
                column++;
            }
            workbook.write(fos);

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
            fos.flush();
            fos.close();
        }
        }
    }
