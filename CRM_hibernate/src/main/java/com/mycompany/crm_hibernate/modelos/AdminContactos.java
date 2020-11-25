/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crm_hibernate.modelos;

import java.util.List;
import java.util.Set;
import models.Contacto;
import models.Oportunidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author carlo
 */
public class AdminContactos {
//definicion de variables

    Session s;
    Transaction t;
//Inicializador

    public AdminContactos(Session s) {
        this.s = s;
        this.t = null;
    }
//metodo para crear una nueva instancia de contacto en la base de datos

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

    //metodo para editar todos los parametros de un contacto
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
//metodo para editar el nombre de un contacto

    public void editarNombre(String nombre, int id) {
        comprobarTransaccion();
        models.Contacto c1 = s.get(Contacto.class, id);
        c1.setNombre(nombre);
        s.update(c1);
        t.commit();
    }
//metodo para editar el apellido de un contacto

    public void editarApellido(String apellido, int id) {
        comprobarTransaccion();
        models.Contacto c1 = s.get(Contacto.class, id);
        c1.setApellidos(apellido);
        s.update(c1);
        t.commit();
    }
//metodo para editar la empresa de un contacto

    public void editarEmpresa(String empresa, int id) {
        comprobarTransaccion();
        models.Contacto c1 = s.get(Contacto.class, id);
        c1.setEmpresa(empresa);
        s.update(c1);
        t.commit();
    }
//metodo para editar el telefono de un contacto

    public void editarTelefono(String telefono, int id) {
        comprobarTransaccion();
        models.Contacto c1 = s.get(Contacto.class, id);
        c1.setTelefono(telefono);
        s.update(c1);
        t.commit();
    }
//metodo para editar el email de un contacto

    public void editarEmail(String email, int id) {
        comprobarTransaccion();
        models.Contacto c1 = s.get(Contacto.class, id);
        c1.setMail(email);
        s.update(c1);
        t.commit();
    }
//metodo para borrar un contacto

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
//metodo para recoger todos los contactos de la base de datos

    public List mostrarContactos() {
        List<Contacto> Contactos = s.createQuery("from Contacto").list();
        return Contactos;
    }
//metodo para mostrar todas las oportunidades de un contacto

    public Set<Oportunidad> mostrarContacto(int id) {
        Contacto c1 = s.get(Contacto.class, id);
        Set<Oportunidad> opCont = c1.getOportunidades();
        return opCont;
    }
//metodo para comprobar si la transaccion de hibernate esta abierta o no y activarla

    public void comprobarTransaccion() {
        if (t == null) {
            t = s.beginTransaction();
        } else if (!this.t.isActive()) {
            t = s.beginTransaction();
        }
    }
}
