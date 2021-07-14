/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.correntista.controle;

import br.com.correntista.dao.DonoDao;
import br.com.correntista.dao.DonoDaoImpl;
import br.com.correntista.dao.HibernateUtil;
import br.com.correntista.entidade.Dono;
import br.com.correntista.entidade.Endereco;
import br.com.correntista.webservice.WebServiceEndereco;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

/**
 *
 * @author fabio
 */
@ManagedBean(name = "donoC")
@ViewScoped
public class DonoControle {
    private Endereco endereco;
    private Dono dono;
    private DonoDao donoDao;
    Session sessao;
    private List<Dono> donos;
    private DataModel<Dono> modeldonos;
    private int aba;

    public DonoControle() {
        donoDao = new DonoDaoImpl();
    }

    public void salvar() {
        donoDao = new DonoDaoImpl();
        sessao = HibernateUtil.abrirSessao();
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage message = null;
        try {
            donoDao.salvarOuAlterar(dono, sessao);
            message = new FacesMessage("Salvo com sucesso!", "");
        } catch (HibernateException e) {
            message = new FacesMessage("Erro ao Salvar!", "");

        } finally {
            sessao.close();
            contexto.addMessage(null, message);
        }

    }

    public void pesquisarPorNome() {
        sessao = HibernateUtil.abrirSessao();
        try {
            donos = donoDao.pesquisarPorNome(dono.getNome(), sessao);
            modeldonos = new ListDataModel<>(donos);
            dono.setNome(null);

        } catch (HibernateException e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            sessao.close();
        }
    }
   
    public void excluir() {
        dono = modeldonos.getRowData();
        sessao = HibernateUtil.abrirSessao();
        try {
           
            donoDao.excluir(dono, sessao);
            dono = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Excluído com Sucesso", null));
            modeldonos = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Existe animais vinculados a este dono!", ""));
        } finally {
            sessao.close();
        }
    }
     public void buscarCep() {
        WebServiceEndereco webService = new WebServiceEndereco();
        endereco = webService.pesquisarCep(endereco.getCep());
        if (endereco == null) {
            endereco = new Endereco();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Não existe nenhum Cep com esse valor!", null));
        }
    }

    public void prepararAlterar() {
        dono = modeldonos.getRowData();
        aba = 1;
    }

    
    
    public Dono getDono() {
        if (dono == null) {
            dono = new Dono();
        }
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public DataModel<Dono> getModeldonos() {
        return modeldonos;
    }

    public int getAba() {
        return aba;
    }

    public Endereco getEndereco() {
         if (endereco == null) {
            endereco = new Endereco();
        
        }
         return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
}
