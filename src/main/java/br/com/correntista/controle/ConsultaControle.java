/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.correntista.controle;

import br.com.correntista.dao.ConsultaDao;
import br.com.correntista.dao.ConsultaDaoImpl;
import br.com.correntista.dao.HibernateUtil;
import br.com.correntista.entidade.Animal;
import br.com.correntista.entidade.Consulta;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author fabio
 */
@ManagedBean(name = "ConsultaC")
@ViewScoped
public class ConsultaControle {
    private Animal animal;        
    private Consulta consulta;
    private ConsultaDao consultadao;
    private Session sessao;
    
    
    public void salvar() {
        consultadao = new ConsultaDaoImpl();
        sessao = HibernateUtil.abrirSessao();
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage message = null;
        try {
            consulta.setAnimal(animal);
            consultadao.salvarOuAlterar(consulta, sessao);
            message = new FacesMessage("Salvo com sucesso!", "");
        } catch (HibernateException e) {
            message = new FacesMessage("Erro ao Salvar!", "");

        } finally {
            sessao.close();
            contexto.addMessage(null, message);
        }

    }

    public Consulta getConsulta() {
        if(consulta == null){
            consulta = new Consulta();
        }
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public ConsultaDao getConsultadao() {
        return consultadao;
    }

    public void setConsultadao(ConsultaDao consultadao) {
        this.consultadao = consultadao;
    }
    
    
}
