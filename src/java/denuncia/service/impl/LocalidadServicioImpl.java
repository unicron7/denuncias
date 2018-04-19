/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denuncia.service.impl;

import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import denuncia.dao.LocalidadDao;
import denuncia.entity.Localidad;
import denuncia.service.LocalidadServicio;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Coronel
 */
@Stateless(name = "LocalidadServicio")
public class LocalidadServicioImpl extends GenericServiceImpl<Localidad, Long>
        implements LocalidadServicio {
    
    @EJB
    private LocalidadDao localidadDao;

    @Override
    public GenericDao<Localidad, Long> getDao() {
        return localidadDao;
    }

    @Override
    public List<Localidad> findByNemonico(String nemonico) {
        return localidadDao.findByNemonico(nemonico);
    }

    @Override
    public Localidad findByCodInternacional(String codInternacional) {
        return localidadDao.findByCodInternacional(codInternacional);
    }

    @Override
    public List<Localidad> findByLocalidadPadre(BigInteger localidadPadre) {
        return localidadDao.findByLocalidadPadre(localidadPadre);
    }
    
}
