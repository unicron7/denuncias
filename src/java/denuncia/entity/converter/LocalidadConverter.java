/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denuncia.entity.converter;

import denuncia.entity.Localidad;
import denuncia.service.LocalidadServicio;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author mejiaw
 */
@ManagedBean
@RequestScoped
public class LocalidadConverter implements Converter {
    @EJB
    private LocalidadServicio localidadServicio;
    
    /**
     * Creates a new instance of LocalidadConverter
     */
    public LocalidadConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null) {
            try {
                return localidadServicio.findByPk(Long.parseLong(value));
            } catch(Exception ex) {
                System.out.println("Ocurrio un error al convertir Localidad a Objeto");
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null) {
            return String.valueOf(((Localidad) value).getCodigoLocalidad());
        }
        return null;
    }
    
}
