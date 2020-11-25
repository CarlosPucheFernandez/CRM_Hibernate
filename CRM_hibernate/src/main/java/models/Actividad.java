package models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author carlo
 */
public class Actividad implements Serializable {
//definicion de variables

    private Integer id;
    private models.Oportunidad oportunidad;
    private String tipo;
    private String descripcion;
    private Date fecha;
//constructor sin parametros

    public Actividad() {
    }
//constructor con parametros

    public Actividad(models.Oportunidad oportunidad, String tipo, String descripcion, Date fecha) {
        this.oportunidad = oportunidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
//Getters y Setters

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
//Formato de salida del objeto

    @Override
    public String toString() {
        //      Establecimiento de formatos a los atributos
        String idf = String.format("%-5s", id);
        String descripcionf = String.format("%-30s", descripcion);
        String tipof = String.format("%-12s", tipo);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String fechaAdapter = formatter.format(fecha);
        String fechaf = String.format("%-14s", fechaAdapter);
        Integer ido = oportunidad.getId();
        String idof = String.format("%-10s", ido);
        //      Salida de los datos ya formateados
        return "»" + " Id:" + idf + "Oportunidad Id:" + idof + " Descripcion:" + descripcionf + " Tipo:" + tipof + " Fecha:" + fechaf;
    }

}
