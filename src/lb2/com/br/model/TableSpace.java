package lb2.com.br.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bernardovale
 * Date: 15/06/13
 * Time: 15:21
 * To change this template use File | Settings | File Templates.
 */
public class TableSpace {

    private String nome;
    private List<String> datasAtendimento;
    private List<Double> utilizado;

    public TableSpace() {
    }

    public TableSpace(String nome, List<String> datasAtendimento, List<Double> utilizado) {
        this.nome = nome;
        this.datasAtendimento = datasAtendimento;
        this.utilizado = utilizado;
    }

    public TableSpace(String nome) {
        this.nome = nome;
    }

    public List<Double> getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(String utilizado) {
        if(this.utilizado==null){
            this.utilizado = new ArrayList<Double>();
        }
        this.utilizado.add(Double.parseDouble(utilizado));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getDatasAtendimento() {
        return datasAtendimento;
    }

    public void setDatasAtendimento(String datasAtendimento) {
        if(this.datasAtendimento==null){
            this.datasAtendimento = new ArrayList<String>();
        }
        this.datasAtendimento.add(datasAtendimento);
    }
}
