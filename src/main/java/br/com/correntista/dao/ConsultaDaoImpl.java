/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.correntista.dao;

import br.com.correntista.entidade.Consulta;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Fabio
 */
public class ConsultaDaoImpl extends BaseDaoImpl<Consulta, Long> implements ConsultaDao, Serializable {

    @Override
    public Consulta pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Consulta) sessao.get(Consulta.class, id);
    }

    @Override
    public List<Consulta> pesquisarPorData(Date dia, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Consulta where Date(dia) = :dia");
        consulta.setParameter("dia", dia);
        return consulta.list();
    }

   
}
