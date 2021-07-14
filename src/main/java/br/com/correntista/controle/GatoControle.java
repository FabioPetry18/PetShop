/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.correntista.controle;


import br.com.correntista.dao.ComportamentoDao;
import br.com.correntista.dao.ComportamentoDaoImpl;
import br.com.correntista.dao.DonoDao;
import br.com.correntista.dao.DonoDaoImpl;
import br.com.correntista.dao.GatoDao;
import br.com.correntista.dao.GatoDaoImpl;
import br.com.correntista.dao.HibernateUtil;
import br.com.correntista.entidade.Comportamento;
import br.com.correntista.entidade.Dono;
import br.com.correntista.entidade.Gato;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

/**
 *
 * @author fabio
 */
@ManagedBean(name = "gatoC")
@ViewScoped
public class GatoControle {

    private Dono dono;
    private Comportamento comportamento;
    private Gato gato;
    private  GatoDao gatoDao;
    private Session sessao;
    private List<Gato> gatos;
    private List<SelectItem> comboDonos;
    private List<SelectItem> comboComportamento;
    private DataModel<Gato> modelgatos;
    private int aba;

    public GatoControle() {
        gatoDao = new GatoDaoImpl();
    }

    public void salvar() {

        sessao = HibernateUtil.abrirSessao();
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage message = null;
        try {
            gato.setDono(dono);
            gatoDao.salvarOuAlterar(gato, sessao);
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
            System.out.println(gatos);
          gatos = gatoDao.pesquisarPorNome(gato.getNome(), sessao);
            modelgatos = new ListDataModel<>(gatos);
            dono.setNome(null);
            
        } catch (HibernateException e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void excluir() {
        gato = modelgatos.getRowData();
        sessao = HibernateUtil.abrirSessao();
        try {
            gatoDao.excluir(gato, sessao);
            gato = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Excluído com Sucesso", null));
            modelgatos = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao Excluir", ""));
        } finally {
            sessao.close();
        }
    }

    public void prepararAlterar() {
        gato = modelgatos.getRowData();
        aba = 1;
    }

    public void carregarComboDono() {
        sessao = HibernateUtil.abrirSessao();
        DonoDao donoDao = new DonoDaoImpl();
        try {
            List<Dono> donos = donoDao.pesquisarTodo(sessao);
            comboDonos = new ArrayList<>();
            for (Dono don : donos) {
                comboDonos.add(new SelectItem(don.getId(), don.getNome()));
            }

        } catch (HibernateException e) {
            System.out.println("Erro ao carregar combobox dono " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

   

    public void carregarComboComportamento() {
        sessao = HibernateUtil.abrirSessao();
        ComportamentoDao comportamentoDao = new ComportamentoDaoImpl();
        try {
            List<Comportamento> comport = comportamentoDao.pesquisarTodo(sessao);
            comboComportamento = new ArrayList<>();
            for (Comportamento comp : comport) {
                comboComportamento.add(new SelectItem(comp.getId(), comp.getTipo()));
            }

        } catch (HibernateException e) {
            System.out.println("Erro ao carregar combobox Comportamento " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getTitle().equals("Novo")) {
            carregarComboComportamento();
            carregarComboDono();
        }
    }

    public void onTabClose(TabCloseEvent event) {

    }

    //get/set
    public Gato getGato() {
        if (gato == null) {
            gato = new Gato();
        }
        return gato;
    }

    public void setGato(Gato gato) {
        this.gato = gato;
    }

    public List<SelectItem> getComboDonos() {
        return comboDonos;
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

    public int getAba() {
        return aba;
    }

    public Comportamento getComportamento() {
        if (comportamento == null) {
            comportamento = new Comportamento();
        }
        return comportamento;
    }

    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    public List<SelectItem> getComboComportamento() {
        return comboComportamento;
    }

    public DataModel<Gato> getModelgatos() {
        return modelgatos;
    }
    
}
