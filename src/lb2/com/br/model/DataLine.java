package lb2.com.br.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bernardovale
 * Date: 14/06/13
 * Time: 17:30
 * To change this template use File | Settings | File Templates.
 */
public class DataLine {

    private String data;
    private String nome;
    private String total;
    private String utilizado;
    private String livre;
    private String porcentagem;

    public DataLine(String data, String nome, String total, String utilizado, String livre) {
        this.data = data;
        this.nome = nome;
        this.total = total;
        this.utilizado = utilizado;
        this.livre = livre;
    }

    public DataLine(String porcentagem, String livre, String utilizado, String total, String nome, String data) {
        this.porcentagem = porcentagem;
        this.livre = livre;
        this.utilizado = utilizado;
        this.total = total;
        this.nome = nome;
        this.data = data;
    }

    public DataLine(List<String> dadosSeparados) {
        this.data = dadosSeparados.get(0);
        this.nome = dadosSeparados.get(1);
        this.total = dadosSeparados.get(2);
        this.utilizado = dadosSeparados.get(3);
        this.livre = dadosSeparados.get(4);
        this.porcentagem = dadosSeparados.get(5);
    }

    @Override
    public String toString() {
        return "Dia:"+data+"\n" +
                "Nome:"+nome+"\n" +
                "Total:"+total+"\n" +
                "Utilizado:"+utilizado+"\n" +
                "Livre:"+livre+"\n" +
                "Porcentagem:"+porcentagem+"";
    }

    public String getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(String porcentagem) {
        this.porcentagem = porcentagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(String utilizado) {
        this.utilizado = utilizado;
    }

    public String getLivre() {
        return livre;
    }

    public void setLivre(String livre) {
        this.livre = livre;
    }
}
