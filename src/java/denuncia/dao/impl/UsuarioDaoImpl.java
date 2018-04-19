/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denuncia.dao.impl;

import com.saviasoft.persistence.util.dao.eclipselink.GenericDaoEjbEl;
import denuncia.dao.UsuarioDao;
import denuncia.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "UsuarioDao")
public class UsuarioDaoImpl extends GenericDaoEjbEl<Usuario, Long> implements
        UsuarioDao {

    public UsuarioDaoImpl() {
        super(Usuario.class);
    }

    @Override
    public Usuario findByDocumento(String documento) {
        try {
            Query query= em.createQuery("Select u from Usuario u where u.estadoRegistro= :estado and u.numeroDocumento= :documento");
            query.setParameter("estado", true);
            query.setParameter("documento", documento);
            return (Usuario) query.getResultList().get(0);
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
    
    @Override
    public List<Usuario> listarUsuariosInternos() {
        try {
            Query query= em.createQuery("select u from Usuario u, UsuarioRol ur, Rol r where u.codigoUsuario = ur.usuario.codigoUsuario "
                    + "and r.codigoRol = ur.rol.codigoRol "
                    + "and ur.estadoRegistro = true "
                    + "and r.nemonico not in ('UEXT', 'ABGSRMN', 'ADMIN', 'GADADMI', 'ADMINGPS', 'AGEADU', 'GADAUDI', 'JEFETRANS', 'PROGPS', 'GADRECE', 'RCSBGN', 'GADRESP', 'SNACON', 'SNDESA', 'SUBSECREGION', 'USUARIO') "
                    + "and u.estadoRegistro = true order by u.nombre ASC");
            return query.getResultList();
        } catch(Exception ex) {
           System.out.println(ex.toString());
        }
        return null;
    }
}
