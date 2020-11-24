/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crm_hibernate.modelos;

import java.util.List;
import models.Contacto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author carlo
 */
public class AdminContactos {

    Session s;
    Transaction t;

    public AdminContactos(Session s) {
        this.s = s;
        this.t = null;
    }

    public void nuevoContacto(String nombre, String apellidos, String empresa, String email, String telefono) {
        comprobarTransaccion();
        models.Contacto c1 = new models.Contacto();
        c1.setNombre(nombre);
        c1.setApellidos(apellidos);
        c1.setEmpresa(empresa);
        c1.setMail(email);
        c1.setTelefono(telefono);
        s.save(c1);
        t.commit();
    }

    public void editarContacto(String nombre, String apellidos, String empresa, String email, String telefono, int id) {
        comprobarTransaccion();
        models.Contacto c1 = s.get(Contacto.class, id);
        c1.setNombre(nombre);
        c1.setApellidos(apellidos);
        c1.setEmpresa(empresa);
        c1.setMail(email);
        c1.setTelefono(telefono);
        s.update(c1);
        t.commit();
    }

    public void editarNombre(String nombre, int id) {
        comprobarTransaccion();
        models.Contacto c1 = s.get(Contacto.class, id);
        c1.setNombre(nombre);
        s.update(c1);
        t.commit();
    }

    public void editarApellido(String apellido, int id) {
        comprobarTransaccion();
        models.Contacto c1 = s.get(Contacto.class, id);
        c1.setApellidos(apellido);
        s.update(c1);
        t.commit();
    }

    public void editarEmpresa(String empresa, int id) {
        comprobarTransaccion();
        models.Contacto c1 = s.get(Contacto.class, id);
        c1.setEmpresa(empresa);
        s.update(c1);
        t.commit();
    }

    public void editarTelefono(String telefono, int id) {
        comprobarTransaccion();
        models.Contacto c1 = s.get(Contacto.class, id);
        c1.setTelefono(telefono);
        s.update(c1);
        t.commit();
    }

    public void editarEmail(String email, int id) {
        comprobarTransaccion();
        models.Contacto c1 = s.get(Contacto.class, id);
        c1.setMail(email);
        s.update(c1);
        t.commit();
    }

    public void borrarContacto(int id) {
        comprobarTransaccion();
        Query borrarsql = s.createQuery("DELETE Contacto where id=:id_borrar");
        borrarsql.setParameter("id_borrar", id);
        if (borrarsql.executeUpdate() != 0) {
            System.out.println("Todo correcto");
        } else {
            System.out.println("No se elimino nada");
        }
        t.commit();
    }

    public List mostrarContacto() {
        List<Contacto> Contactos = s.createQuery("from contacto").list();
        return Contactos;
    }

    public void comprobarTransaccion() {
        if (t == null) {
            t = s.beginTransaction();
        } else if (!this.t.isActive()) {
            t = s.beginTransaction();
        }
    }
}
