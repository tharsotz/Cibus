package cibus.com.cibus;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Postos {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nome;
    private String x;
    private String y;
    private String bandeira;
    private String preco;

    public Postos(String nome, String x, String y, String bandeira, String preco) {
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.bandeira = bandeira;
        this.preco = preco;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Nome: "+ nome + "\n"
                + "Bandeira: " + bandeira + "\n"
                + "Preco: " + preco + "\n" + id;
    }

}
