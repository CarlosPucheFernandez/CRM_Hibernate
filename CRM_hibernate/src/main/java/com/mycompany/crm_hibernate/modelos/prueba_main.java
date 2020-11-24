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
public class prueba_main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Query query = s.createQuery("FROM Contacto");
        List<Contacto> listaResultados = query.list();

        for (int i = 0; i < listaResultados.size(); i++) {
            Contacto c1 = listaResultados.get(i);
            System.out.println(c1.toString());
        }

    }

}
