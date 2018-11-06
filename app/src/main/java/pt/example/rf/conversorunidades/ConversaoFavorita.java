package pt.example.rf.conversorunidades;

/**
 * Created by ricardo fernandes on 13/07/2017.
 */

public class ConversaoFavorita {

    private int id;
    private String codigoConversao;
    private String nomeConversao;

    public ConversaoFavorita() {
        this.setNomeConversao(getNomeConversao());
    }

    public ConversaoFavorita(int id, String codigoConversao, String nomeConversao) {
        this.setId(id);
        this.setCodigoConversao(codigoConversao);
        this.setNomeConversao(nomeConversao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoConversao() {
        return codigoConversao;
    }

    public void setCodigoConversao(String codigoConversao) {
        this.codigoConversao = codigoConversao;
    }

    public String getNomeConversao() {
        return nomeConversao;
    }

    public void setNomeConversao(String nomeConversao) {
        this.nomeConversao = nomeConversao;
    }

}
