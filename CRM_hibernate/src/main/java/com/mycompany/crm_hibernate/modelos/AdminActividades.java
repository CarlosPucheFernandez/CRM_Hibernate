/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crm_hibernate.modelos;

import java.util.Date;
import models.Actividad;
import models.Oportunidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author carlo
 */
public class AdminActividades {

    Session s;
    Transaction t;

    public AdminActividades(Session s) {
        this.s = s;
        this.t = null;
    }

    public void nuevaActividad(String descripcion, String tipo, Date fecha, int id_op) {
        comprobarTransaccion();
        models.Actividad ac = new models.Actividad();
        models.Oportunidad op = s.get(Oportunidad.class, id_op);
        ac.setTipo(tipo);
        ac.setDescripcion(descripcion);
        ac.setFecha(fecha);
        ac.setOportunidad(op);
        s.save(ac);
        t.commit();
    }

    public void editarActividad(String descripcion, String tipo, int id, int id_op) {
        comprobarTransaccion();
        models.Actividad ac = s.get(Actividad.class, id);
        models.Oportunidad op = s.get(Oportunidad.class, id_op);
        ac.setDescripcion(descripcion);
        ac.setTipo(tipo);
        ac.setOportunidad(op);
        s.update(ac);
        t.commit();
    }

    public void editarDescripcion(String descripcion, int id) {
        comprobarTransaccion();
        models.Actividad ac = s.get(Actividad.class, id);
        ac.setDescripcion(descripcion);
        s.update(ac);
        t.commit();
    }

    public void editarTipo(String tipo, int id) {
        comprobarTransaccion();
        models.Actividad op = s.get(Actividad.class, id);
        op.setTipo(tipo);
        s.update(op);
        t.commit();
    }

    public void editarOportunidad(int id_op, int id) {
        comprobarTransaccion();
        models.Actividad ac = s.get(Actividad.class, id);
        models.Oportunidad op = s.get(Oportunidad.class, id);
        ac.setOportunidad(op);
        s.update(ac);
        t.commit();
    }

    public void borrarActividad(int id) {
        comprobarTransaccion();
        Query borrarsql = s.createQuery("DELETE actividad where id=:id_borrar");
        borrarsql.setParameter("id_borrar", id);
        if (borrarsql.executeUpdate() != 0) {
            System.out.println("Todo correcto");
        } else {
            System.out.println("No se elimino nada");
        }
        t.commit();
    }

    public void mostrarActividad() {

    }

    public void comprobarTransaccion() {
        if (t == null) {
            t = s.beginTransaction();
        } else if (!this.t.isActive()) {
            t = s.beginTransaction();
        }
    }
}
