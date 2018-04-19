/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denuncia.dao;

import com.saviasoft.persistence.util.dao.GenericDao;
import denuncia.entity.Localidad;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Javier Coronel
 */

@Local
public interface LocalidadDao extends GenericDao<Localidad, Long> {
    
    Localidad findByPk(Long id);
    
    List<Localidad> findByNemonico(String nemonico);
    
    Localidad findByCodInternacional(String codInternacional);

    List<Localidad> findByLocalidadPadre(BigInteger localidadPadre);
    
}
