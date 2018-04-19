/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denuncia.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mejiaw
 */
public class DateUtil {
    public static String obtenerFechaConFormato(Date fecha) {
        return obtenerFechaConFormato("dd-MM-yyyy", fecha);
    }
    
    public static String obtenerFechaConFormato(String formato, Date fecha) {
        SimpleDateFormat sdf= new SimpleDateFormat(formato);
        if(fecha!=null) {
            return sdf.format(fecha);
        }
        return "";
    }
    
    public static String obtenerFechaHoraConFormato(Date fecha) {
        return obtenerFechaConFormato("yyyy-MM-dd HH:mm:ss", fecha);
    }
}
