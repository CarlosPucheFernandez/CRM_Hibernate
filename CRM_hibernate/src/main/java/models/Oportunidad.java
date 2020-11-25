package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Carlo
 */
public class Oportunidad implements Serializable {
//definicion de variables

    private Integer id;
    private String descripcion;
    private BigDecimal valor;
    private Date fecha;
    private String nivel;
    private String estado;

    private models.Contacto contacto;

    private Set<models.Actividad> actividades;

//constructor sin parametros
    public Oportunidad() {
    }
//constructor con parametros

    public Oportunidad(models.Contacto cliente, String descripcion, BigDecimal valor, Date fecha, String nivel) {
        this.contacto = cliente;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
        this.nivel = nivel;
        this.estado = "NUEVA";
        this.actividades = new HashSet(0);

    }
//Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Set<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }
//Formato de salida del objeto

    @Override
    public String toString() {
//      Establecimiento de formatos a los atributos
        String idf = String.format("%-5s", id);
        String descripcionf = String.format("%-30s", descripcion);
        String estadof = String.format("%-14s", estado);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String fechaAdapter = formatter.format(fecha);
        String fechaf = String.format("%-14s", fechaAdapter);
        String nivelf = String.format("%-14s", nivel);
        String valorf = String.format("%-10s", valor);
        String idc = contacto.getNombre();
        String idcf = String.format("%-12s", idc);
//      Salida de los datos ya formateados
        return "»" + " Id:" + idf + "Contacto:" + idcf + " Descripcion:" + descripcionf + " Nivel:" + nivelf + " Valor:" + valorf + " Fecha:" + fechaf + " Estado:" + estadof;
    }

}
