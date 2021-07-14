/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.correntista.dao;

import br.com.correntista.entidade.Cachorro;
import br.com.correntista.entidade.Dono;
import br.com.correntista.entidade.Gato;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fabio
 */
public class CachorroDaoImplTest {

    private Cachorro cachorro;
    private CachorroDao cachorroDao;
    private Session sessao;

    public CachorroDaoImplTest() {
        cachorroDao = new CachorroDaoImpl();
    }

    //@Test
    public void testSalvar() {
        System.out.println("salvar");
        DonoDaoImplTest donoTeste = new DonoDaoImplTest();
        Dono dono = donoTeste.buscarDonoBD();

        cachorro = new Cachorro("Dog Kiko", "Macho", "bla, bla...", new Date(), 4, false);
        cachorro.setDono(dono);
        sessao = HibernateUtil.abrirSessao();
        cachorroDao.salvarOuAlterar(cachorro, sessao);
        sessao.close();
        assertNotNull(cachorro.getId());
    }

  // @Test
    public void testAlterar() {
        System.out.println("alterar");
        buscarCachorroBD();
        cachorro.setNome("Dog kiko alterada");
        sessao = HibernateUtil.abrirSessao();
        cachorroDao.salvarOuAlterar(cachorro, sessao);
        sessao.close();

        sessao = HibernateUtil.abrirSessao();
        Cachorro cachorroAlt = cachorroDao.pesquisarPorId(cachorro.getId(), sessao);
        sessao.close();
        assertEquals(cachorro.getNome(), cachorroAlt.getNome());
    }

   // @Test
    public void testExcluir() {
        System.out.println("excluir");
        buscarCachorroBD();
        sessao = HibernateUtil.abrirSessao();
        cachorroDao.excluir(cachorro, sessao);

        Cachorro cachorroExc = cachorroDao.pesquisarPorId(cachorro.getId(), sessao);
        sessao.close();
        assertNull(cachorroExc);
    }

   // @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarCachorroBD();
        sessao = HibernateUtil.abrirSessao();
        List<Cachorro> cachorros = cachorroDao.pesquisarPorNome(cachorro.getNome(), sessao);
        sessao.close();
        assertTrue(cachorros.size() >= 1);
    }

    public Cachorro buscarCachorroBD() {
        sessao = HibernateUtil.abrirSessao();
        Query consulta = sessao.createQuery("from Cachorro");
        List<Cachorro> dogs = consulta.list();
        sessao.close();
        if (dogs.isEmpty()) {
            testSalvar();
        } else {
            cachorro = dogs.get(0);
        }
        return cachorro;
    }
}
