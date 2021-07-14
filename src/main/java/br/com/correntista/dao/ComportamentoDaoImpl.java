/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.correntista.dao;

import br.com.correntista.entidade.Comportamento;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Fabio
 */
public class ComportamentoDaoImpl implements ComportamentoDao, Serializable {

    @Override
    public List<Comportamento> pesquisarTodo(Session sessao) throws HibernateException {
       return sessao.createQuery("from Comportamento").list();
    }
 
}
