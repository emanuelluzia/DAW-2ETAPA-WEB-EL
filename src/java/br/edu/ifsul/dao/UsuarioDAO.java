package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.persistence.Query;

/**
 *
 * @author Emanuel Luzia
 *
 */
public class UsuarioDAO<TIPO> extends DAOGenerico<Usuario> implements Serializable {
    
    public UsuarioDAO(){
        super();
        classePersistente = Usuario.class;        
    }
    
    public boolean login(String usuario, String senha){
        Query query = em.createQuery("from Usuario where upper(Apelido) = :pusuario "
                + " and upper(senha) = :psenha and ativo = true");
        query.setParameter("pusuario", usuario.toUpperCase());
        query.setParameter("psenha", senha.toUpperCase());
        if(!query.getResultList().isEmpty()){
            return true;
        } else {
            return false;
        }        
    }
    
    public Usuario localizaUsuarioPorNomeUsuario(String usuario){
        Query query = em.createQuery("from Usuario where upper(Apelido) = :pusuario");
        query.setParameter("pusuario", usuario.toUpperCase());
        Usuario obj = (Usuario) query.getSingleResult();
        return obj;
    }

}
