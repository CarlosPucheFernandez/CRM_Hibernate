/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crm_hibernate.modelos;

import java.math.BigDecimal;
import java.util.Date;
import models.Contacto;
import models.Oportunidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author carlo
 */
public class AdminOportunidades {

    Session s;
    Transaction t;

    public AdminOportunidades(Session s) {
        this.s = s;
        this.t = null;
    }

    public void nuevaOportunidad(String descripcion, BigDecimal valor, Date fecha, String nivel, String estado, int id_cont) {
        comprobarTransaccion();
        models.Oportunidad op1 = new models.Oportunidad();
        models.Contacto c1 = s.get(Contacto.class, id_cont);
        op1.setDescripcion(descripcion);
        op1.setEstado(estado);
        op1.setFecha(fecha);
        op1.setNivel(nivel);
        op1.setValor(valor);
        op1.setContacto(c1);

        s.save(op1);
        t.commit();
    }

    public void editarOportunidad(String descripcion, String estado, String nivel, BigDecimal valor, int id, int id_cont) {
        comprobarTransaccion();
        models.Oportunidad op = s.get(Oportunidad.class, id);
        models.Contacto c1 = s.get(Contacto.class, id_cont);
        op.setDescripcion(descripcion);
        op.setEstado(estado);
        op.setNivel(nivel);
        op.setValor(valor);
        op.setContacto(c1);
        s.update(op);
        t.commit();
    }

    public void editarDescripcion(String descripcion, int id) {
        comprobarTransaccion();
        models.Oportunidad op = s.get(Oportunidad.class, id);
        op.setDescripcion(descripcion);
        s.update(op);
        t.commit();
    }

    public void editarEstado(String estado, int id) {
        comprobarTransaccion();
        models.Oportunidad op = s.get(Oportunidad.class, id);
        op.setEstado(estado);
        s.update(op);
        t.commit();
    }

    public void editarNivel(String nivel, int id) {
        comprobarTransaccion();
        models.Oportunidad op = s.get(Oportunidad.class, id);
        op.setNivel(nivel);
        s.update(op);
        t.commit();
    }

    public void editarValor(BigDecimal valor, int id) {
        comprobarTransaccion();
        models.Oportunidad op = s.get(Oportunidad.class, id);
        op.setValor(valor);
        s.update(op);
        t.commit();
    }

    public void editarContacto(int id_cont, int id) {
        comprobarTransaccion();
        models.Contacto c1 = s.get(Contacto.class, id_cont);
        models.Oportunidad op = s.get(Oportunidad.class, id);
        op.setContacto(c1);
        s.update(op);
        t.commit();
    }

    public void borrarOportunidad(int id) {
        comprobarTransaccion();
        Query borrarsql = s.createQuery("DELETE oportunidad where id=:id_borrar");
        borrarsql.setParameter("id_borrar", id);
        if (borrarsql.executeUpdate() != 0) {
            System.out.println("Todo correcto");
        } else {
            System.out.println("No se elimino nada");
        }
        t.commit();
    }

    public void mostrarOportunidad() {

    }

    public void comprobarTransaccion() {
        if (t == null) {
            t = s.beginTransaction();
        } else if (!this.t.isActive()) {
            t = s.beginTransaction();
        }
    }
}
