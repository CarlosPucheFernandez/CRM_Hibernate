/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crm_hibernate.modelos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import models.Actividad;
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
//definicion de variables

    Session s;
    Transaction t;
//Inicializador

    public AdminOportunidades(Session s) {
        this.s = s;
        this.t = null;
    }
//metodo para crear una nueva instancia de oportunidad en la base de datos

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
//metodo para editar todos los parametros de una oportunidad

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
//metodo para editar la descripcion de una oportunidad

    public void editarDescripcion(String descripcion, int id) {
        comprobarTransaccion();
        models.Oportunidad op = s.get(Oportunidad.class, id);
        op.setDescripcion(descripcion);
        s.update(op);
        t.commit();
    }
//metodo para editar el estado de una oportunidad

    public void editarEstado(String estado, int id) {
        comprobarTransaccion();
        models.Oportunidad op = s.get(Oportunidad.class, id);
        op.setEstado(estado);
        s.update(op);
        t.commit();
    }
//metodo para editar el nivel de una oportunidad

    public void editarNivel(String nivel, int id) {
        comprobarTransaccion();
        models.Oportunidad op = s.get(Oportunidad.class, id);
        op.setNivel(nivel);
        s.update(op);
        t.commit();
    }
//metodo para editar el valor de una oportunidad

    public void editarValor(BigDecimal valor, int id) {
        comprobarTransaccion();
        models.Oportunidad op = s.get(Oportunidad.class, id);
        op.setValor(valor);
        s.update(op);
        t.commit();
    }
//metodo para editar el contacto asociado de una oportunidad

    public void editarContacto(int id_cont, int id) {
        comprobarTransaccion();
        models.Contacto c1 = s.get(Contacto.class, id_cont);
        models.Oportunidad op = s.get(Oportunidad.class, id);
        op.setContacto(c1);
        s.update(op);
        t.commit();
    }
//metodo para borrar una oportunidad

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
//metodo para recoger todas las oportunidades de la base de datos

    public List<Oportunidad> mostrarOportunidades() {
        List<Oportunidad> Oportunidades = s.createQuery("from Oportunidad").list();
        return Oportunidades;
    }
//metodo para mostrar todas las actividades de una oportunidad

    public Set<Actividad> mostrarOportunidad(int id) {
        Oportunidad op = s.get(Oportunidad.class, id);
        Set<Actividad> acOp = op.getActividades();
        return acOp;
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
