/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denuncia.service;

import com.saviasoft.persistence.util.service.GenericService;
import denuncia.entity.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface UsuarioServicio extends GenericService<Usuario, Long> {

    public Usuario findByDocumento(String trim);
    
    public List<Usuario> listarUsuariosInternos();
    
}
