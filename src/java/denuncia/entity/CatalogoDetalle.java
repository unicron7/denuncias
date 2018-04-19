/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denuncia.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Javier Coronel
 */
@Entity
@Table(name = "catalogo_detalle", catalog = "arcom_catmin", schema = "catmin")
@NamedQueries({
    @NamedQuery(name = "CatalogoDetalle.findAll", query = "SELECT c FROM CatalogoDetalle c")})
public class CatalogoDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_catalogo_detalle")
    private Long codigoCatalogoDetalle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 400)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nemonico")
    private String nemonico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "valor")
    private String valor;
    @Column(name = "estado_registro")
    private Boolean estadoRegistro;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "usuario_creacion")
    private BigInteger usuarioCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Column(name = "usuario_modificacion")
    private BigInteger usuarioModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoLocalidad")
    private Collection<Localidad> localidadCollection;
    @JoinColumn(name = "codigo_catalogo", referencedColumnName = "codigo_catalogo")
    @ManyToOne(optional = false)
    private Catalogo codigoCatalogo;

    public CatalogoDetalle() {
    }

    public CatalogoDetalle(Long codigoCatalogoDetalle) {
        this.codigoCatalogoDetalle = codigoCatalogoDetalle;
    }

    public CatalogoDetalle(Long codigoCatalogoDetalle, String nombre, String nemonico, String valor) {
        this.codigoCatalogoDetalle = codigoCatalogoDetalle;
        this.nombre = nombre;
        this.nemonico = nemonico;
        this.valor = valor;
    }

    public Long getCodigoCatalogoDetalle() {
        return codigoCatalogoDetalle;
    }

    public void setCodigoCatalogoDetalle(Long codigoCatalogoDetalle) {
        this.codigoCatalogoDetalle = codigoCatalogoDetalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNemonico() {
        return nemonico;
    }

    public void setNemonico(String nemonico) {
        this.nemonico = nemonico;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Boolean getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(Boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(BigInteger usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public BigInteger getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(BigInteger usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Collection<Localidad> getLocalidadCollection() {
        return localidadCollection;
    }

    public void setLocalidadCollection(Collection<Localidad> localidadCollection) {
        this.localidadCollection = localidadCollection;
    }

    public Catalogo getCodigoCatalogo() {
        return codigoCatalogo;
    }

    public void setCodigoCatalogo(Catalogo codigoCatalogo) {
        this.codigoCatalogo = codigoCatalogo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCatalogoDetalle != null ? codigoCatalogoDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoDetalle)) {
            return false;
        }
        CatalogoDetalle other = (CatalogoDetalle) object;
        if ((this.codigoCatalogoDetalle == null && other.codigoCatalogoDetalle != null) || (this.codigoCatalogoDetalle != null && !this.codigoCatalogoDetalle.equals(other.codigoCatalogoDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.migracion.modelo.CatalogoDetalle[ codigoCatalogoDetalle=" + codigoCatalogoDetalle + " ]";
    }
    
}
