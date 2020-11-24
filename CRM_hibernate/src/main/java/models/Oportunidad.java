package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Carlo
 */
public class Oportunidad implements Serializable {

    private Integer id;
    private String descripcion;
    private BigDecimal valor;
    private Date fecha;
    private String nivel;
    private String estado;

    private models.Contacto contacto;

    private Set<models.Actividad> actividades;

    public Oportunidad() {
    }

    public Oportunidad(models.Contacto cliente, String descripcion, BigDecimal valor, Date fecha, String nivel) {
        this.contacto = cliente;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
        this.nivel = nivel;
        this.estado = "NUEVA";
        this.actividades = new HashSet(0);

    }

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

    @Override
    public String toString() {
        String idf = String.format("%-5s", id);
        String descripcionf = String.format("%-30s", descripcion);
        String estadof = String.format("%-6s", estado);
        String fechaf = String.format("%-14s", fecha);
        String nivelf = String.format("%-14s", nivel);
        String valorf = String.format("%-10s", valor);
        String idc = contacto.getNombre();
        String idcf = String.format("%-12s", idc);
        return "»" + " Id:" + idf + "Contacto:" + idcf + " Descripcion:" + descripcionf + " Nivel:" + nivelf + " Valor:" + valorf + " Fecha:" + fechaf + " Estado:" + estadof;
    }

}
