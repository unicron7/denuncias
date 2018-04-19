/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denuncia.controller;

import denuncia.dto.DenunciaDto;
import denuncia.entity.Localidad;
import denuncia.entity.Usuario;
import denuncia.mail.MailSender;
import denuncia.service.LocalidadServicio;
import denuncia.service.UsuarioServicio;
import denuncia.util.DateUtil;
import denuncia.util.FacesUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author mejiaw
 */
@ManagedBean
@ViewScoped
public class DenunciaController {
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private LocalidadServicio localidadServicio;
    
    private boolean showMineriaIlegal;
    private boolean showInfraccion;
    private String tipoDenuncia;
    private List<Usuario> funcionarios;
    private Usuario funcionario;
    private List<UploadedFile> archivosParaCargar;
    private DenunciaDto denuncia;
    private List<Localidad> provincias;
    private List<Localidad> cantones;
    private List<Localidad> parroquias;
    
    private Localidad provincia;
    private Localidad canton;
    private Localidad parroquia;

    /**
     * Creates a new instance of DenunciaController
     */
    public DenunciaController() {
    }
    
    @PostConstruct
    public void init() {
        showMineriaIlegal= false;
        showInfraccion= false;
        denuncia= new DenunciaDto();
    }

    public boolean isShowMineriaIlegal() {
        return showMineriaIlegal;
    }

    public void setShowMineriaIlegal(boolean showMineriaIlegal) {
        this.showMineriaIlegal = showMineriaIlegal;
    }

    public boolean isShowInfraccion() {
        return showInfraccion;
    }

    public void setShowInfraccion(boolean showInfraccion) {
        this.showInfraccion = showInfraccion;
    }

    public String getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(String tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario) {
        this.funcionario = funcionario;
    }

    public DenunciaDto getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(DenunciaDto denuncia) {
        this.denuncia = denuncia;
    }
    
    public List<Usuario> getFuncionarios() {
        if (funcionarios == null) {
            funcionarios = usuarioServicio.listarUsuariosInternos();
        }
        return funcionarios;
    }

    public List<UploadedFile> getArchivosParaCargar() {
        return archivosParaCargar;
    }

    public void setArchivosParaCargar(List<UploadedFile> archivosParaCargar) {
        this.archivosParaCargar = archivosParaCargar;
    }

    public void showFields() {
        if(tipoDenuncia.equals("Mineria ilegal")) {
            showMineriaIlegal= true;
            showInfraccion= false;
        } else {
            showMineriaIlegal= false;
            showInfraccion= true;
        }
    }

    public Localidad getProvincia() {
        return provincia;
    }

    public void setProvincia(Localidad provincia) {
        this.provincia = provincia;
    }

    public Localidad getCanton() {
        return canton;
    }

    public void setCanton(Localidad canton) {
        this.canton = canton;
    }

    public Localidad getParroquia() {
        return parroquia;
    }

    public void setParroquia(Localidad parroquia) {
        this.parroquia = parroquia;
    }
    
    public List<Localidad> getProvincias() {
        if (provincias == null) {
            Localidad catalogoProvincia = localidadServicio.findByNemonico("EC").get(0);
            provincias = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(catalogoProvincia.getCodigoLocalidad()));
        }
        return provincias;
    }
    
    public List<Localidad> getCantones() {
        if (cantones == null) {
            cantones = new ArrayList();
            if (provincia == null) {
                return cantones;
            }
            cantones = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(provincia.getCodigoLocalidad()));
        }
        return cantones;
    }
    
    public List<Localidad> getParroquias() {
        if (parroquias == null) {
            parroquias = new ArrayList();
            if (canton == null) {
                return parroquias;
            }
            parroquias = localidadServicio.findByLocalidadPadre(BigInteger.valueOf(canton.getCodigoLocalidad()));
        }
        return parroquias;
    }
    
    public void cargaCantones() {
        cantones = null;
        parroquias = null;
        getCantones();
        getParroquias();
    }
    
    public void cargaParroquias() {
        parroquias = null;
        getParroquias();
    }
    
    public void addArchivos(FileUploadEvent event) {
        boolean existe= false;
        if(archivosParaCargar==null) {
            archivosParaCargar= new ArrayList<UploadedFile>();
        }
        for(UploadedFile f : archivosParaCargar) {
            if(f.getFileName().equals(event.getFile().getFileName())) {
                existe= true;
            }
        }
        if(!existe) {
            archivosParaCargar.add(event.getFile());
        } else {
            FacesUtil.showErrorMessage("Error", "El archivo ya se encuentra en la lista");
        }
        RequestContext.getCurrentInstance().execute("PF('archivosfrmwg').hide();");
    }
    
    public void sendDenuncia() {
        denuncia.setTipoDenuncia(tipoDenuncia);
        if(tipoDenuncia.compareTo("Mineria ilegal")==0) {
            denuncia.setProvincia(provincia.getNombre());
            denuncia.setCanton(canton.getNombre());
            denuncia.setParroquia(parroquia.getNombre());
            denuncia.setNombreDenunciado("");
        } else {
            denuncia.setProvincia("");
            denuncia.setCanton("");
            denuncia.setParroquia("");
            denuncia.setSector("");
            denuncia.setNombreDenunciado(funcionario.getNombresCompletos());
        }
        
        List<String> lista= new ArrayList<String>();
        if(archivosParaCargar!=null) {
            for(UploadedFile f : archivosParaCargar) {
                lista.add(f.getFileName());
            }
        }
        denuncia.setAdjuntos(lista);
        denuncia.setFechaHoraEnvio(DateUtil.obtenerFechaHoraConFormato(Calendar.getInstance().getTime()));
        sendMail(denuncia);
        RequestContext.getCurrentInstance().showMessageInDialog(
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Denuncia enviada", "Su denuncia ha sido enviada correctamente"));
        reset();
    }
    
    private void reset() {
        denuncia= new DenunciaDto();
        tipoDenuncia= "";
        showMineriaIlegal= false;
        showInfraccion= false;
        funcionario= null;
        funcionarios= null;
        provincia= null;
        canton= null;
        parroquia= null;
        provincias= null;
        cantones= null;
        parroquias= null;
        archivosParaCargar= null;
        
    }
    
    private void sendMail(DenunciaDto d) {
        sendNewDenunciaMsg("victor_aumala@controlminero.gob.ec", d);
    }
    
    private void sendNewDenunciaMsg(String destinatario, DenunciaDto d) {
        MailSender ms= new MailSender();
        try {
            ms.sendMailHTML("Notificación nueva denuncia", ms.getNewDenunciaMsg(d), destinatario);
        } catch(Exception ex) {
            System.out.println("Ocurrio un error al enviar el correo: " + ex.toString());
        }
    }
    
    public StreamedContent getPhoto(String numeroDocumento) {
        StreamedContent sc= null;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String ruta= "/resources/images/empleados/" + numeroDocumento + ".jpg";
        sc= new DefaultStreamedContent(externalContext.getResourceAsStream(ruta), "image/jpg");
        return sc;
    }
}
