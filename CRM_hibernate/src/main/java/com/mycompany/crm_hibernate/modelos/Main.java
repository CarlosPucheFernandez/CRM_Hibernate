/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crm_hibernate.modelos;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import models.Contacto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author carlo
 */
public class Main {

    private static SessionFactory sessionFactory;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Boolean salir = false;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            AdminActividades adAc = new AdminActividades(s);
            AdminContactos adCo = new AdminContactos(s);
            AdminOportunidades adOp = new AdminOportunidades(s);
            System.out.println("Bienvenido al CRM con Hibernate. ");
            System.out.println("¿Que desea hacer en el dia de hoy? ");
            do {
                System.out.println("1 -> Listados");
                System.out.println("2 -> Estadisticas");
                System.out.println("3 -> Añadir nuevos datos");
                System.out.println("4 -> Editar datos");
                System.out.println("5 -> Borrar datos");
                System.out.println("----------------------------------------");
                System.out.println("Introduzca un numero: ");
                Scanner sc = new Scanner(System.in);
                int op = sc.nextInt();
                switch (op) {
                    case 1:
                        System.out.println("1 -> Listados Clientes");
                        System.out.println("2 -> Listados Oportunidades");
                        System.out.println("3 -> Listados Actividades");
                        System.out.println("4 -> Listado general");
                        System.out.println("----------------------------------------");
                        System.out.println("Introduzca un numero: ");
                        int op1 = sc.nextInt();
                        switch (op1) {
                            case 1:
                                //lista clientes
                                salir = comprobarSalir(salir);
                                break;
                            case 2:
                                //lista Oportunidades
                                salir = comprobarSalir(salir);
                                break;
                            case 3:
                                //lista actividades
                                salir = comprobarSalir(salir);
                                break;
                            case 4:
                                //lista todo
                                salir = comprobarSalir(salir);
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("1 -> Clientes totales");
                        System.out.println("2 -> Numero Oportunidades por cliente");
                        System.out.println("----------------------------------------");
                        System.out.println("Introduzca un numero: ");
                        int op2 = sc.nextInt();
                        switch (op2) {
                            case 1:
                                //Clientes totales
                                salir = comprobarSalir(salir);
                                break;
                            case 2:
                                //numero op * cliente
                                salir = comprobarSalir(salir);
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("1 -> Añadir Cliente");
                        System.out.println("2 -> Añadir Oportunidad");
                        System.out.println("3 -> Añadir Actividad");
                        System.out.println("----------------------------------------");
                        System.out.println("Introduzca un numero: ");
                        int op3 = sc.nextInt();
                        switch (op3) {
                            case 1:
                                System.out.println("Introduzca el nombre");
                                String limpiador = sc.nextLine();
                                String nombre = sc.nextLine();
                                System.out.println("Introduzca los apellidos");
                                String apellidos = sc.nextLine();
                                System.out.println("Introduzca la empresa");
                                String empresa = sc.nextLine();
                                System.out.println("Introduzca el telefono");
                                String telefono = sc.nextLine();
                                System.out.println("Introduzca el email");
                                String email = sc.nextLine();
                                adCo.nuevoContacto(nombre, apellidos, empresa, email, telefono);
                                System.out.println("Se inserto el cliente correctamente");
                                salir = comprobarSalir(salir);
                                break;
                            case 2:
                                System.out.println("Introduzca la descripcion");
                                String limpiador1 = sc.nextLine();
                                String descripcionOp = sc.nextLine();
                                System.out.println("Introduzca el valor");
                                BigDecimal valor = sc.nextBigDecimal();
                                System.out.println("Introduzca el nivel");
                                String limpiador2 = sc.nextLine();
                                String nivel = sc.nextLine();
                                System.out.println("Introduzca el estado");
                                String estado = sc.nextLine();
                                System.out.println("Introduzca la fecha (yyyy-mm-dd)");
                                String str = sc.nextLine();
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                Date fecha = formatter.parse(str);
                                int id_cont = 1;
                                adOp.nuevaOportunidad(descripcionOp, valor, fecha, nivel, estado, id_cont);
                                System.out.println("Se inserto la oportunidad correctamente");
                                salir = comprobarSalir(salir);
                                break;
                            case 3:
                                System.out.println("Introduzca el tipo");
                                String tipo = sc.nextLine();
                                System.out.println("Introduzca la descripcion");
                                String descripcionAc = sc.nextLine();
                                Date fecha1 = Date.valueOf(LocalDate.MAX);
                                int id_op = 1;
                                adAc.nuevaActividad(descripcionAc, tipo, fecha1, id_op);
                                salir = comprobarSalir(salir);
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("1 -> Editar Cliente");
                        System.out.println("2 -> Editar Oportunidad");
                        System.out.println("3 -> Editar Actividad");
                        System.out.println("----------------------------------------");
                        System.out.println("Introduzca un numero: ");
                        int op4 = sc.nextInt();
                        switch (op4) {
                            case 1:
                                System.out.println("1 -> Editar Nombre");
                                System.out.println("2 -> Editar Apellidos");
                                System.out.println("3 -> Editar Empresa");
                                System.out.println("4 -> Editar Telefono");
                                System.out.println("5 -> Editar Email");
                                System.out.println("6 -> Editar Todo");
                                System.out.println("----------------------------------------");
                                System.out.println("Introduzca un numero: ");
                                int op6 = sc.nextInt();
                                switch (op6) {
                                    case 1:
                                        System.out.println("Introduzca el nombre");
                                        String nombre = sc.nextLine();
                                        int id = 1;
                                        adCo.editarNombre(nombre, id);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 2:
                                        System.out.println("Introduzca los apellidos");
                                        String apellidos = sc.nextLine();
                                        int id2 = 1;
                                        adCo.editarApellido(apellidos, id2);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 3:
                                        System.out.println("Introduzca la empresa");
                                        String empresa = sc.nextLine();
                                        int id3 = 1;
                                        adCo.editarEmpresa(empresa, id3);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 4:
                                        System.out.println("Introduzca el telefono");
                                        String telefono = sc.nextLine();
                                        int id4 = 1;
                                        adCo.editarTelefono(telefono, id4);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 5:
                                        System.out.println("Introduzca el email");
                                        String email = sc.nextLine();
                                        int id5 = 1;
                                        adCo.editarEmail(email, id5);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 6:
                                        System.out.println("Introduzca el nombre");
                                        String nombre1 = sc.nextLine();
                                        System.out.println("Introduzca los apellidos");
                                        String apellidos1 = sc.nextLine();
                                        System.out.println("Introduzca la empresa");
                                        String empresa1 = sc.nextLine();
                                        System.out.println("Introduzca el telefono");
                                        String telefono1 = sc.nextLine();
                                        System.out.println("Introduzca el email");
                                        String email1 = sc.nextLine();
                                        int id6 = 1;
                                        adCo.editarContacto(nombre1, apellidos1, empresa1, email1, telefono1, id6);
                                        salir = comprobarSalir(salir);
                                        break;
                                }
                                break;
                            case 2:
                                System.out.println("1 -> Editar Descripcion");
                                System.out.println("2 -> Editar Valor");
                                System.out.println("3 -> Editar Nivel");
                                System.out.println("4 -> Editar Estado");
                                System.out.println("5 -> Editar Todo");
                                System.out.println("----------------------------------------");
                                System.out.println("Introduzca un numero: ");
                                int op7 = sc.nextInt();
                                switch (op7) {
                                    case 1:
                                        System.out.println("Introduzca la descripcion");
                                        String descripcionOp = sc.nextLine();
                                        int id = 1;
                                        adOp.editarDescripcion(descripcionOp, id);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 2:
                                        System.out.println("Introduzca el valor");
                                        BigDecimal valor = sc.nextBigDecimal();
                                        int id2 = 1;
                                        adOp.editarValor(valor, id2);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 3:
                                        System.out.println("Introduzca el nivel");
                                        String nivel = sc.nextLine();
                                        int id3 = 1;
                                        adOp.editarNivel(nivel, id3);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 4:
                                        System.out.println("Introduzca el estado");
                                        String estado = sc.nextLine();
                                        int id4 = 1;
                                        adOp.editarEstado(estado, id4);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 5:
                                        System.out.println("Introduzca el numero del contacto");
                                        int id_cont = sc.nextInt();
                                        int id5 = 1;
                                        adOp.editarContacto(id_cont, id5);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 6:
                                        System.out.println("Introduzca la descripcion");
                                        String descripcionOp1 = sc.nextLine();
                                        System.out.println("Introduzca el valor");
                                        BigDecimal valor1 = sc.nextBigDecimal();
                                        System.out.println("Introduzca el nivel");
                                        String nivel1 = sc.nextLine();
                                        System.out.println("Introduzca el estado");
                                        String estado1 = sc.nextLine();
                                        int id6 = 1;
                                        int id_cont1 = 1;
                                        adOp.editarOportunidad(descripcionOp1, estado1, nivel1, valor1, id6, id_cont1);
                                        salir = comprobarSalir(salir);
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println("1 -> Editar Tipo");
                                System.out.println("2 -> Editar Descripcion");
                                System.out.println("3 -> Editar Todo");
                                System.out.println("----------------------------------------");
                                System.out.println("Introduzca un numero: ");
                                int op8 = sc.nextInt();
                                switch (op8) {
                                    case 1:
                                        System.out.println("Introduzca el tipo");
                                        String tipo = sc.nextLine();
                                        int id = 1;
                                        adAc.editarTipo(tipo, id);
                                        break;
                                    case 2:
                                        System.out.println("Introduzca la descripcion");
                                        String descripcionAc = sc.nextLine();
                                        int id1 = 1;
                                        adAc.editarDescripcion(descripcionAc, id1);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 3:
                                        System.out.println("Introduzca el numero de la operacion");
                                        int id_op = sc.nextInt();
                                        int id2 = 1;
                                        adAc.editarOportunidad(id_op, id2);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 4:
                                        System.out.println("Introduzca el tipo");
                                        String tipo1 = sc.nextLine();
                                        System.out.println("Introduzca la descripcion");
                                        String descripcionAc1 = sc.nextLine();
                                        int id_op1 = sc.nextInt();
                                        int id3 = 1;
                                        adAc.editarActividad(descripcionAc1, tipo1, id3, id_op1);
                                        salir = comprobarSalir(salir);
                                        break;
                                }
                                break;
                        }
                        break;
                    case 5:
                        System.out.println("1 -> Borrar Cliente");
                        System.out.println("2 -> Borrar Oportunidad");
                        System.out.println("3 -> Borrar Actividad");
                        System.out.println("----------------------------------------");
                        System.out.println("Introduzca un numero: ");
                        int op5 = sc.nextInt();
                        switch (op5) {
                            case 1:
                                int id = 1;
                                adCo.borrarContacto(id);
                                salir = comprobarSalir(salir);
                                break;
                            case 2:
                                int id1 = 1;
                                adOp.borrarOportunidad(id1);
                                salir = comprobarSalir(salir);
                                break;
                            case 3:
                                int id2 = 1;
                                adAc.borrarActividad(id2);
                                salir = comprobarSalir(salir);
                                break;
                        }
                        break;

                }
            } while (salir != true);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static boolean comprobarSalir(boolean salir) {
        System.out.println("¿Desea intentar otra operacion?");
        System.out.println("1 -> si");
        System.out.println("2 -> no");
        Scanner sc = new Scanner(System.in);
        int ops = sc.nextInt();
        if (ops == 2) {
            salir = true;
        }
        return salir;
    }
}
