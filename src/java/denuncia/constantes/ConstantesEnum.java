/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denuncia.constantes;

/**
 *
 * @author Javier Coronel
 */
public enum ConstantesEnum {
    
    GRAN_MINERIA("GRAMIN", "G", null), MED_MINERIA("MEDMIN", "M", null), PEQ_MINERIA("PEQMIN", "P", null), REG_GENERAL("REGGENL", "T", null),
    EST_TRAMITE("ESTENTRA", "En trámite", 241L), EST_OTORGADO("ESTOTOR", "OTORGADA", 242L), EST_INSCRITO("ESTINSC", "INSCRITA", 243L),
    EST_SUSPENDIDO("ESTSUSP", "SUSPENDIDO", 244L), EST_ARCHIVADA("ESTARCHIV", "ARCHIVADA", 246L), EST_ACUMULADA("ACMLDA19", "ACUMULADA", 1459L),
    EST_SUBSANACION("ESTNOTOR", "SUBSANACION", 755L),
    
    //PARA ENVIO DE CORREO ELECTRONICO
    IP_SERVIDOR_CORREO("IPMAILSRV", "10.10.6.6", null),
    PORT_SERVIDOR_CORREO("PORTMAILSRV", "25", null),
    REMITENTE_CORREO_VACACIONES("REMITENTE", "denuncias@controlminero.gob.ec", null);
    
    private String nemonico;
    private String descripcion;
    private Long codigo;
    
    private ConstantesEnum(String nemonico, String descripcion, Long codigo) {
        this.nemonico = nemonico;
        this.descripcion = descripcion;
        this.codigo = codigo;
    }
    
    public String getNemonico() {
        return nemonico;
    }
    
    public void setNemonico(String nemonico) {
        this.nemonico = nemonico;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Long getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
}
