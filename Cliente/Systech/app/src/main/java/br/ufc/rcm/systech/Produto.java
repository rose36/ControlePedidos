package br.ufc.rcm.systech;

/**
 * Created by Garen on 13/12/2015.
 */
public class Produto {
    private String descricao, grupo;
    private int id;
    private Double preco;

    public Produto(){

    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Produto(String descricao, String grupo, int id, Double preco) {
        this.descricao = descricao;
        this.grupo = grupo;
        this.id = id;
        this.preco = preco;

    }

    public String getDescricao() {
        return descricao;
    }

    public String getGrupo() {
        return grupo;
    }

    public int getId() {
        return id;
    }

    public Double getPreco() {
        return preco;

    }
}
