/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denuncia.service.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import denuncia.dao.UsuarioDao;
import denuncia.entity.Usuario;
import denuncia.service.UsuarioServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "UsuarioServicio")
public class UsuarioServicioImpl extends GenericServiceImpl<Usuario, Long>
        implements UsuarioServicio {

    @EJB
    private UsuarioDao usuarioDao;

    @Override
    public GenericDao<Usuario, Long> getDao() {
        return usuarioDao;
    }

    @Override
    public Usuario findByDocumento(String documento) {
        return usuarioDao.findByDocumento(documento);
    }

    @Override
    public List<Usuario> listarUsuariosInternos() {
        return usuarioDao.listarUsuariosInternos();
    }
}
