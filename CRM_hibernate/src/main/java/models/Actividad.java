
package models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author carlo
 */
public class Actividad implements Serializable{
    private Integer id;
    private models.Oportunidad oportunidad;
    private String tipo;
    private String descripcion;
    private Date fecha;

    public Actividad() {
    }

    public Actividad(models.Oportunidad oportunidad, String tipo, String descripcion, Date fecha) {
        this.oportunidad = oportunidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Oportunidad getOportunidad() {
        return oportunidad;
    }

    public void setOportunidad(Oportunidad oportunidad) {
        this.oportunidad = oportunidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        String idf = String.format("%-5s", id);
        String descripcionf = String.format("%-30s", descripcion);
        String tipof = String.format("%-6s", tipo);
        String fechaf = String.format("%-14s", fecha);
        Integer ido = oportunidad.getId();
        String idof = String.format("%-10s", ido);
        return "»" + " Id:" + idf + "Oportunidad Id:" + idof + " Descripcion:" + descripcionf + " Tipo:" + tipof + " Fecha:" + fechaf;
    }

}
