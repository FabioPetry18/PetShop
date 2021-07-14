/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.correntista.entidade;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Fabio
 */
@Entity
@Table(name = "cachorro")
@PrimaryKeyJoinColumn(name = "id_animal")
public class Cachorro extends Animal {

    private boolean treinado;

    public Cachorro() {
    }

    public Cachorro(String nome, String sexo, String observacao, Date nascimento, double peso,
            boolean treinado) {
        super(nome, sexo, observacao, nascimento, peso);
        this.treinado = treinado;
    }

    public boolean isTreinado() {
        return treinado;
    }

    public void setTreinado(boolean treinado) {
        this.treinado = treinado;
    }

}
