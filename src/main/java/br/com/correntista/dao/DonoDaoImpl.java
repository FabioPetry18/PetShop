/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.correntista.dao;

import br.com.correntista.entidade.Dono;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Fabio
 */
public class DonoDaoImpl extends BaseDaoImpl<Dono, Long> implements DonoDao, Serializable {

    @Override
    public Dono pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Dono) sessao.get(Dono.class, id);
    }

    @Override
    public Dono pesquisarPorCpfDono(String cpf, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Dono d join fetch d.animais where cpf = :cpf");
        consulta.setParameter("cpf", cpf);
        return (Dono) consulta.uniqueResult();
    }

    @Override
    public List<Dono> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Dono d join fetch d.animais a where d.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public List<Dono> pesquisarTodo(Session sessao) throws HibernateException {
       return sessao.createQuery("from Dono").list();
    }

}
