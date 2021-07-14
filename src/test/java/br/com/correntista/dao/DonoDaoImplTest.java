/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.correntista.dao;

import br.com.correntista.entidade.Dono;
import br.com.correntista.entidade.Endereco;
import static br.com.correntista.util.UtilGerador.*;
import br.com.correntista.webservice.WebServiceEndereco;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fabio
 */
public class DonoDaoImplTest {

    private Dono dono;
    private DonoDao donoDao;
    private Session sessao;

    public DonoDaoImplTest() {
        donoDao = new DonoDaoImpl();
    }

   // @Test
    public void testSalvar() {
        System.out.println("Salvar");
        dono = new Dono(gerarNome(), gerarCpf(), gerarTelefoneFixo(), gerarEmail());
         
        WebServiceEndereco webService = new WebServiceEndereco();
        Endereco endereco = webService.pesquisarCep("01001000");
        endereco.setNumero(gerarNumero(3));
        endereco.setComplemento(gerarCaracter(7));
        dono.setEndereco(endereco);  
        endereco.setDono(dono);
              
        
        
        sessao = HibernateUtil.abrirSessao();
        donoDao.salvarOuAlterar(dono, sessao);
        sessao.close();
        assertNotNull(dono.getId());
    }
    


  // @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarDonoBD();
        sessao = HibernateUtil.abrirSessao();
        List<Dono> donos = donoDao.pesquisarPorNome(dono.getNome().substring(0, 4), sessao);
        sessao.close();
        
        assertTrue(!donos.isEmpty());
        assertNotNull(donos.get(0).getAnimais());
    }
    
    public Dono buscarDonoBD(){
        sessao = HibernateUtil.abrirSessao();
        Query consulta = sessao.createQuery("from Dono");
        List<Dono> donos = consulta.list();
        sessao.close();
        if(donos.isEmpty()){
            testSalvar();
        }else{
            dono = donos.get(0);
        }
        return dono;
    }

}
