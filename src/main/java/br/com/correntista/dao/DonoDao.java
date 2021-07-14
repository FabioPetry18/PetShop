/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.correntista.dao;

import br.com.correntista.entidade.Dono;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Fabio
 */
public interface DonoDao extends BaseDao<Dono, Long> {

    Dono pesquisarPorCpfDono(String cpf, Session sessao) throws HibernateException;
    
    List<Dono> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
    
    List<Dono> pesquisarTodo(Session sessao) throws HibernateException;
}
