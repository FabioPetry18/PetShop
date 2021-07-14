/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.correntista.dao;

import br.com.correntista.entidade.Cachorro;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Fabio
 */
public interface CachorroDao extends BaseDao<Cachorro, Long> {

    List<Cachorro> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
    
}
