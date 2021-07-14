/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.correntista.entidade;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Fabio
 */
@Entity
@Table(name = "gato")
@PrimaryKeyJoinColumn(name = "id_animal")
public class Gato extends Animal{
   
    @Column(nullable = false)
    private boolean fiv;
    @Column(nullable = false)
    private boolean felv;

    public Gato() {
    }

    public Gato(String nome, String sexo, String observacao, Date nascimento, double peso,
    boolean fiv, boolean felv) {
        super(nome, sexo, observacao, nascimento, peso);
        this.fiv = fiv;
        this.felv = felv;
    }

    public boolean isFiv() {
        return fiv;
    }

    public void setFiv(boolean fiv) {
        this.fiv = fiv;
    }

    public boolean isFelv() {
        return felv;
    }

    public void setFelv(boolean felv) {
        this.felv = felv;
    }
    
    
    
}
