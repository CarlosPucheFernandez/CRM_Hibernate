package models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Carlo
 */
public class Contacto implements Serializable {
//definicion de variables

    private Integer id;
    private String nombre;
    private String apellidos;
    private String empresa;
    private String telefono;
    private String mail;

    private Set<models.Oportunidad> oportunidades;
//constructor sin parametros

    public Contacto() {
    }
//constructor con parametros

    public Contacto(String nombre, String apellidos, String empresa, String telefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.empresa = empresa;
        this.telefono = telefono;
        this.mail = email;
        this.oportunidades = new HashSet(0);
    }
//Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<Oportunidad> getOportunidades() {
        return oportunidades;
    }

    public void setOportunidades(Set<Oportunidad> oportunidades) {
        this.oportunidades = oportunidades;
    }
//Formato de salida del objeto

    @Override
    public String toString() {
        //      Establecimiento de formatos a los atributos
        String idf = String.format("%-5s", id);
        String nombref = String.format("%-12s", nombre);
        String apellidosf = String.format("%-20s", apellidos);
        String empresaf = String.format("%-23s", empresa);
        String telefonof = String.format("%-13s", telefono);
        String mailf = String.format("%-30s", mail);
        //      Salida de los datos ya formateados
        return "»" + " Id:" + idf + " Nombre:" + nombref + " Apellidos:" + apellidosf + " Empresa:" + empresaf + " Telefono:" + telefonof + " E-mail:" + mailf;
    }

}
