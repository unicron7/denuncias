/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denuncia.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import denuncia.entity.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface UsuarioDao extends GenericDao<Usuario, Long> {

    public Usuario findByDocumento(String documento);

    public List<Usuario> listarUsuariosInternos();
    
}
