/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denuncia.service;

import com.saviasoft.persistence.util.service.GenericService;
import denuncia.entity.Localidad;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */
@Local
public interface LocalidadServicio extends GenericService<Localidad, Long> {
    
    List<Localidad> findByNemonico(String nemonico);
    
    Localidad findByCodInternacional(String codInternacional);

    List<Localidad> findByLocalidadPadre(BigInteger localidadPadre);
    
}
