/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.correntista.dao;

import br.com.correntista.entidade.Gato;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Fabio
 */
public class GatoDaoImpl extends BaseDaoImpl<Gato, Long> implements GatoDao, Serializable{

    @Override
    public Gato pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Gato) sessao.get(Gato.class, id);
    }

    @Override
    public List<Gato> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Gato where nome = :nome");
        consulta.setParameter("nome", nome);
        return consulta.list();
    }
    
}
