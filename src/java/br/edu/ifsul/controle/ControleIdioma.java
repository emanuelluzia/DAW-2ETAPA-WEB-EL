package br.edu.ifsul.controle;

import br.edu.ifsul.util.Util;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.modelo.Idioma;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "controleIdioma")
@SessionScoped
public class ControleIdioma implements Serializable {
    private IdiomaDAO<Idioma> dao;
    private Idioma objeto;
    
    public ControleIdioma(){
        dao = new IdiomaDAO();
    }
    
    public String listar(){
        return "/privado/idioma/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Idioma();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        boolean persistiu;
        
        if(objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        
        if(persistiu){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remove(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public IdiomaDAO getDao() {
        return dao;
    }

    public void setDao(IdiomaDAO dao) {
        this.dao = dao;
    }

    public Idioma getObjeto() {
        return objeto;
    }

    public void setObjeto(Idioma objeto) {
        this.objeto = objeto;
    }
}
