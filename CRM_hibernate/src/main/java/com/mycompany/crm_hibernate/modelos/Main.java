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
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import models.Actividad;
import models.Contacto;
import models.Oportunidad;
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
        //Inicializacion de la varible de confirmacion de seguimiento
        Boolean salir = false;
        //Inicializacion de la sesion de Hibernate
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            //Inicializaicon de los objetos de gestion de datos
            AdminActividades adAc = new AdminActividades(s);
            AdminContactos adCo = new AdminContactos(s);
            AdminOportunidades adOp = new AdminOportunidades(s);
            //Mensaje de bienvenida
            System.out.println("Bienvenido al CRM con Hibernate. ");
            System.out.println("¿Que desea hacer en el dia de hoy? ");
            do {
                //Menu Principal
                System.out.println("1 -> Listados");
                System.out.println("2 -> Estadisticas");
                System.out.println("3 -> Añadir nuevos datos");
                System.out.println("4 -> Editar datos");
                System.out.println("5 -> Borrar datos");
                System.out.println("----------------------------------------");
                System.out.println("Introduzca un numero: ");
                //Inicializacion de la lectura de teclado
                Scanner sc = new Scanner(System.in);
                int op = sc.nextInt();
                switch (op) {
                    case 1:
                        //Menu de eleccion de listados
                        System.out.println("1 -> Listados Clientes");
                        System.out.println("2 -> Listados Oportunidades");
                        System.out.println("3 -> Listados Actividades");
                        System.out.println("4 -> Listado general");
                        System.out.println("5 -> Listado oportunidades por cliente");
                        System.out.println("6 -> Listado actividades por oportunidad");
                        System.out.println("----------------------------------------");
                        System.out.println("Introduzca un numero: ");
                        int op1 = sc.nextInt();
                        switch (op1) {
                            case 1:
                                //Muestra una tabla con todos los clientes
                                Tabla_contactos(adCo.mostrarContactos());
                                salir = comprobarSalir(salir);
                                break;
                            case 2:
                                //Muestra una tabla con todas las oportunidades
                                Tabla_oportunidades(adOp.mostrarOportunidades());
                                salir = comprobarSalir(salir);
                                break;
                            case 3:
                                //Muestra una tabla con todas las actividades
                                Tabla_actividades(adAc.mostrarActividades());
                                salir = comprobarSalir(salir);
                                break;
                            case 4:
                                //Muestra todas las tablas con los datos anteriores
                                Tabla_contactos(adCo.mostrarContactos());
                                System.out.println("");
                                Tabla_oportunidades(adOp.mostrarOportunidades());
                                System.out.println("");
                                Tabla_actividades(adAc.mostrarActividades());
                                salir = comprobarSalir(salir);
                                break;
                            case 5:
                                //Muestra una tabla con todas las oportunidades de un contacto seleccionado
                                System.out.println("Elija un id Contacto");
                                Tabla_contactos(adCo.mostrarContactos());
                                System.out.println("Introduzca un id");
                                int id_cont = sc.nextInt();
                                Tabla_Oportunidades_Contacto(adCo.mostrarContacto(id_cont));
                                salir = comprobarSalir(salir);
                                break;
                            case 6:
                                //Muestra una tabla con todas las actividades de una oportunidad seleccionada
                                System.out.println("Elija un id Oportunidad");
                                Tabla_oportunidades(adOp.mostrarOportunidades());
                                System.out.println("Introduzca un id");
                                int id_op = sc.nextInt();
                                Tabla_Actividades_Oportunidad(adOp.mostrarOportunidad(id_op));
                                salir = comprobarSalir(salir);
                                break;
                        }
                        break;
                    case 2:
                        //Menu de eleccion de estadisticas
                        System.out.println("1 -> Clientes totales");
                        System.out.println("----------------------------------------");
                        System.out.println("Introduzca un numero: ");
                        int op2 = sc.nextInt();
                        switch (op2) {
                            case 1:
                                //Inicializacion de las variables
                                List<Contacto> contactos = adCo.mostrarContactos();
                                int numContactos = contactos.size();
                                String strNumContactos = "Numero de Contactos: " + numContactos;
                                int tamano = strNumContactos.length();
                                //bucle para ajustar el borde superior al tamaño con un pequeño margen
                                for (int i = 0; i < tamano + 4; i++) {
                                    System.out.print("=");
                                }
                                System.out.println("");
                                //Muestra el numero de contactos en la base de datos
                                System.out.println("* " + strNumContactos + " *");
                                //bucle para ajustar el borde inferior al tamaño con un pequeño margen
                                for (int i = 0; i < tamano + 4; i++) {
                                    System.out.print("=");
                                }
                                System.out.println("");
                                salir = comprobarSalir(salir);
                                break;
                        }
                        break;
                    case 3:
                        //Menu de eleccion de añadir
                        System.out.println("1 -> Añadir Cliente");
                        System.out.println("2 -> Añadir Oportunidad");
                        System.out.println("3 -> Añadir Actividad");
                        System.out.println("----------------------------------------");
                        System.out.println("Introduzca un numero: ");
                        int op3 = sc.nextInt();
                        switch (op3) {
                            case 1:
                                //insercion de los datos para los atributos de un nuevo contacto
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
                                //creacion de un nuevo contacto
                                adCo.nuevoContacto(nombre, apellidos, empresa, email, telefono);
                                System.out.println("Se inserto el cliente correctamente");
                                salir = comprobarSalir(salir);
                                break;
                            case 2:
                                //insercion de los datos para los atributos de una nueva oportunidad
                                System.out.println("Introduzca la descripcion");
                                String limpiador1 = sc.nextLine();
                                String descripcionOp = sc.nextLine();
                                System.out.println("Introduzca el valor");
                                BigDecimal valor = sc.nextBigDecimal();
                                System.out.println("Introduzca el nivel");
                                String limpiador2 = sc.nextLine();
                                String nivel = sc.nextLine();
                                //Menu de eleccion de un estado de una oportunidad
                                System.out.println("Elija un estado");
                                System.out.println("1 -> Nuevo");
                                System.out.println("2 -> Cualificado");
                                System.out.println("3 -> Propuesta");
                                System.out.println("4 -> Negociacion");
                                System.out.println("5 -> Ganado");
                                System.out.println("6 -> Perdido");
                                System.out.println("Introduzca un numero");
                                int opEstado = sc.nextInt();
                                String estado = "";
                                switch (opEstado) {
                                    case 1:
                                        estado = "Nuevo";
                                        break;
                                    case 2:
                                        estado = "Cualificado";
                                        break;
                                    case 3:
                                        estado = "Propuesta";
                                        break;
                                    case 4:
                                        estado = "Negociacion";
                                        break;
                                    case 5:
                                        estado = "Ganado";
                                        break;
                                    case 6:
                                        estado = "Perdido";
                                        break;
                                }
                                System.out.println("Introduzca la fecha (yyyy-mm-dd)");
                                String limpiador3 = sc.nextLine();
                                String str = sc.nextLine();
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                                Date fecha = formatter.parse(str);
                                //eleccion del cliente asociado a la oportunidad
                                System.out.println("Elija un id Cliente");
                                Tabla_contactos(adCo.mostrarContactos());
                                System.out.println("Introduzca un id");
                                int id_cont = sc.nextInt();
                                //insercion de los datos para los atributos de una nueva oportunidad
                                adOp.nuevaOportunidad(descripcionOp, valor, fecha, nivel, estado, id_cont);
                                System.out.println("Se inserto la oportunidad correctamente");
                                salir = comprobarSalir(salir);
                                break;

                            case 3:
                                //insercion de los datos para los atributos de una nueva oportunidad
                                System.out.println("Introduzca el tipo");
                                String limpiador4 = sc.nextLine();
                                String tipo = sc.nextLine();
                                System.out.println("Introduzca la descripcion");
                                String descripcionAc = sc.nextLine();
                                System.out.println("Introduzca la fecha (yyyy-mm-dd)");
                                String str1 = sc.nextLine();
                                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-mm-dd");
                                Date fecha1 = formatter1.parse(str1);
                                //eleccion de la oportunidad asociada a la actividad
                                System.out.println("Elija un id Oportunidad");
                                Tabla_oportunidades(adOp.mostrarOportunidades());
                                System.out.println("Introduzca un id");
                                int id_op = sc.nextInt();
                                //insercion de los datos para los atributos de una nueva actividad
                                adAc.nuevaActividad(descripcionAc, tipo, fecha1, id_op);
                                salir = comprobarSalir(salir);
                                break;
                        }
                        break;
                    case 4:
                        //Menu de eleccion de edicion
                        System.out.println("1 -> Editar Cliente");
                        System.out.println("2 -> Editar Oportunidad");
                        System.out.println("3 -> Editar Actividad");
                        System.out.println("----------------------------------------");
                        System.out.println("Introduzca un numero: ");
                        int op4 = sc.nextInt();
                        switch (op4) {
                            case 1:
                                //Menu de eleccion de edicion de un contacto
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
                                        //edicion del nombre de un contacto seleccionado
                                        System.out.println("Elija un id Contacto");
                                        Tabla_contactos(adCo.mostrarContactos());
                                        System.out.println("Introduzca un id");
                                        int id_cont = sc.nextInt();
                                        System.out.println("Introduzca el nombre");
                                        String limpiador = sc.nextLine();
                                        String nombre = sc.nextLine();
                                        //actualizacion del objeto
                                        adCo.editarNombre(nombre, id_cont);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 2:
                                        //edicion de los apellidos de un contacto seleccionado                                        System.out.println("Elija un id Contacto");
                                        Tabla_contactos(adCo.mostrarContactos());
                                        System.out.println("Introduzca un id");
                                        int id_cont1 = sc.nextInt();
                                        System.out.println("Introduzca los apellidos");
                                        String limpiador1 = sc.nextLine();
                                        String apellidos = sc.nextLine();
                                        //actualizacion del objeto
                                        adCo.editarApellido(apellidos, id_cont1);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 3:
                                        //edicion de la empresa de un contacto seleccionado
                                        System.out.println("Elija un id Contacto");
                                        Tabla_contactos(adCo.mostrarContactos());
                                        System.out.println("Introduzca un id");
                                        int id_cont2 = sc.nextInt();
                                        System.out.println("Introduzca la empresa");
                                        String limpiador2 = sc.nextLine();
                                        String empresa = sc.nextLine();
                                        //actualizacion del objeto
                                        adCo.editarEmpresa(empresa, id_cont2);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 4:
                                        //edicion del telefono de un contacto seleccionado
                                        System.out.println("Elija un id Contacto");
                                        Tabla_contactos(adCo.mostrarContactos());
                                        System.out.println("Introduzca un id");
                                        int id_cont3 = sc.nextInt();
                                        System.out.println("Introduzca el telefono");
                                        String limpiador3 = sc.nextLine();
                                        String telefono = sc.nextLine();
                                        //actualizacion del objeto
                                        adCo.editarTelefono(telefono, id_cont3);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 5:
                                        //edicion del email de un contacto seleccionado
                                        System.out.println("Elija un id Contacto");
                                        Tabla_contactos(adCo.mostrarContactos());
                                        System.out.println("Introduzca un id");
                                        int id_cont4 = sc.nextInt();
                                        System.out.println("Introduzca el email");
                                        String limpiador4 = sc.nextLine();
                                        String email = sc.nextLine();
                                        //actualizacion del objeto
                                        adCo.editarEmail(email, id_cont4);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 6:
                                        //edicion de todos los atributos de un contacto seleccionado
                                        System.out.println("Elija un id Contacto");
                                        Tabla_contactos(adCo.mostrarContactos());
                                        System.out.println("Introduzca un id");
                                        int id_cont5 = sc.nextInt();
                                        System.out.println("Introduzca el nombre");
                                        String limpiador5 = sc.nextLine();
                                        String nombre1 = sc.nextLine();
                                        System.out.println("Introduzca los apellidos");
                                        String apellidos1 = sc.nextLine();
                                        System.out.println("Introduzca la empresa");
                                        String empresa1 = sc.nextLine();
                                        System.out.println("Introduzca el telefono");
                                        String telefono1 = sc.nextLine();
                                        System.out.println("Introduzca el email");
                                        String email1 = sc.nextLine();
                                        //actualizacion del objeto
                                        adCo.editarContacto(nombre1, apellidos1, empresa1, email1, telefono1, id_cont5);
                                        salir = comprobarSalir(salir);
                                        break;
                                }
                                break;
                            case 2:
                                //Menu de eleccion de edicion de una oportunidad
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
                                        //edicion de la descripcion de una oportunidad seleccionada
                                        System.out.println("Elija un id Oportunidad");
                                        Tabla_oportunidades(adOp.mostrarOportunidades());
                                        System.out.println("Introduzca un id");
                                        int id_op = sc.nextInt();
                                        System.out.println("Introduzca la descripcion");
                                        String limpiador = sc.nextLine();
                                        String descripcionOp = sc.nextLine();
                                        //actualizacion del objeto
                                        adOp.editarDescripcion(descripcionOp, id_op);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 2:
                                        //edicion del valor de una oportunidad seleccionada
                                        System.out.println("Elija un id Oportunidad");
                                        Tabla_oportunidades(adOp.mostrarOportunidades());
                                        System.out.println("Introduzca un id");
                                        int id_op1 = sc.nextInt();
                                        System.out.println("Introduzca el valor");
                                        BigDecimal valor = sc.nextBigDecimal();
                                        //actualizacion del objeto
                                        adOp.editarValor(valor, id_op1);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 3:
                                        //edicion del nivel de una oportunidad seleccionada
                                        System.out.println("Elija un id Oportunidad");
                                        Tabla_oportunidades(adOp.mostrarOportunidades());
                                        System.out.println("Introduzca un id");
                                        int id_op2 = sc.nextInt();
                                        System.out.println("Introduzca el nivel");
                                        String limpiador1 = sc.nextLine();
                                        String nivel = sc.nextLine();
                                        //actualizacion del objeto
                                        adOp.editarNivel(nivel, id_op2);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 4:
                                        //edicion del estado de una oportunidad seleccionada
                                        System.out.println("Elija un id Oportunidad");
                                        Tabla_oportunidades(adOp.mostrarOportunidades());
                                        System.out.println("Introduzca un id");
                                        int id_op3 = sc.nextInt();
                                        //Menu de eleccion de estado de una oportunidad
                                        System.out.println("Elija un estado");
                                        System.out.println("1 -> Nuevo");
                                        System.out.println("2 -> Cualificado");
                                        System.out.println("3 -> Propuesta");
                                        System.out.println("4 -> Negociacion");
                                        System.out.println("5 -> Ganado");
                                        System.out.println("6 -> Perdido");
                                        System.out.println("Introduzca un numero");
                                        int opEstado = sc.nextInt();
                                        String estado = "";
                                        switch (opEstado) {
                                            case 1:
                                                estado = "Nuevo";
                                                break;
                                            case 2:
                                                estado = "Cualificado";
                                                break;
                                            case 3:
                                                estado = "Propuesta";
                                                break;
                                            case 4:
                                                estado = "Negociacion";
                                                break;
                                            case 5:
                                                estado = "Ganado";
                                                break;
                                            case 6:
                                                estado = "Perdido";
                                                break;
                                        }
                                        //actualizacion del objeto
                                        adOp.editarEstado(estado, id_op3);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 5:
                                        //edicion del contacto asociado de una oportunidad seleccionada
                                        System.out.println("Elija un id Oportunidad");
                                        Tabla_oportunidades(adOp.mostrarOportunidades());
                                        System.out.println("Introduzca un id");
                                        int id_op4 = sc.nextInt();
                                        System.out.println("Elija un id Contacto");
                                        Tabla_contactos(adCo.mostrarContactos());
                                        System.out.println("Introduzca un id");
                                        int id_cont = sc.nextInt();
                                        //actualizacion del objeto
                                        adOp.editarContacto(id_cont, id_op4);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 6:
                                        //edicion de todos los atributos de una oportunidad seleccionada
                                        System.out.println("Elija un id Oportunidad");
                                        Tabla_oportunidades(adOp.mostrarOportunidades());
                                        System.out.println("Introduzca un id");
                                        int id_op5 = sc.nextInt();
                                        System.out.println("Introduzca la descripcion");
                                        String limpiador3 = sc.nextLine();
                                        String descripcionOp1 = sc.nextLine();
                                        System.out.println("Introduzca el valor");
                                        BigDecimal valor1 = sc.nextBigDecimal();
                                        System.out.println("Introduzca el nivel");
                                        String nivel1 = sc.nextLine();
                                        System.out.println("Introduzca el estado");
                                        String estado1 = sc.nextLine();
                                        System.out.println("Elija un id Contacto");
                                        Tabla_contactos(adCo.mostrarContactos());
                                        System.out.println("Introduzca un id");
                                        int id_cont1 = sc.nextInt();
                                        //actualizacion del objeto
                                        adOp.editarOportunidad(descripcionOp1, estado1, nivel1, valor1, id_op5, id_cont1);
                                        salir = comprobarSalir(salir);
                                        break;
                                }
                                break;
                            case 3:
                                //Menu de eleccion de edicion de actividades
                                System.out.println("1 -> Editar Tipo");
                                System.out.println("2 -> Editar Descripcion");
                                System.out.println("3 -> Editar Todo");
                                System.out.println("----------------------------------------");
                                System.out.println("Introduzca un numero: ");
                                int op8 = sc.nextInt();
                                switch (op8) {
                                    case 1:
                                        //edicion de el tipo de una actividad seleccionada
                                        System.out.println("Elija un id Actividad");
                                        Tabla_oportunidades(adOp.mostrarOportunidades());
                                        System.out.println("Introduzca un id");
                                        int id_ac = sc.nextInt();
                                        System.out.println("Introduzca el tipo");
                                        String limpiador = sc.nextLine();
                                        String tipo = sc.nextLine();
                                        //actualizacion del objeto
                                        adAc.editarTipo(tipo, id_ac);
                                        break;
                                    case 2:
                                        //edicion de la descripcion de una actividad seleccionada
                                        System.out.println("Elija un id Actividad");
                                        Tabla_oportunidades(adOp.mostrarOportunidades());
                                        System.out.println("Introduzca un id");
                                        int id_ac1 = sc.nextInt();
                                        System.out.println("Introduzca la descripcion");
                                        String limpiador1 = sc.nextLine();
                                        String descripcionAc = sc.nextLine();
                                        adAc.editarDescripcion(descripcionAc, id_ac1);
                                        //actualizacion del objeto
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 3:
                                        //edicion de la oportunidad enlazada de una actividad seleccionada
                                        System.out.println("Elija un id Actividad");
                                        Tabla_oportunidades(adOp.mostrarOportunidades());
                                        System.out.println("Introduzca un id");
                                        int id_ac2 = sc.nextInt();
                                        System.out.println("Elija un id Oportunidad");
                                        Tabla_oportunidades(adOp.mostrarOportunidades());
                                        System.out.println("Introduzca un id");
                                        int id_op = sc.nextInt();
                                        //actualizacion del objeto
                                        adAc.editarOportunidad(id_op, id_ac2);
                                        salir = comprobarSalir(salir);
                                        break;
                                    case 4:
                                        //edicion de todos los atributos de una actividad seleccionada
                                        System.out.println("Elija un id Actividad");
                                        Tabla_oportunidades(adOp.mostrarOportunidades());
                                        System.out.println("Introduzca un id");
                                        int id_ac3 = sc.nextInt();
                                        System.out.println("Introduzca el tipo");
                                        String limpiador3 = sc.nextLine();
                                        String tipo1 = sc.nextLine();
                                        System.out.println("Introduzca la descripcion");
                                        String descripcionAc1 = sc.nextLine();
                                        System.out.println("Elija un id Oportunidad");
                                        Tabla_oportunidades(adOp.mostrarOportunidades());
                                        System.out.println("Introduzca un id");
                                        int id_op1 = sc.nextInt();
                                        //actualizacion del objeto
                                        adAc.editarActividad(descripcionAc1, tipo1, id_ac3, id_op1);
                                        salir = comprobarSalir(salir);
                                        break;
                                }
                                break;
                        }
                        break;
                    case 5:
                        //Menu de eleccion de borrado
                        System.out.println("1 -> Borrar Cliente");
                        System.out.println("2 -> Borrar Oportunidad");
                        System.out.println("3 -> Borrar Actividad");
                        System.out.println("----------------------------------------");
                        System.out.println("Introduzca un numero: ");
                        int op5 = sc.nextInt();
                        switch (op5) {
                            case 1:
                                //elejimos un contacto y lo borramos
                                System.out.println("Elija un id Contacto");
                                Tabla_contactos(adCo.mostrarContactos());
                                System.out.println("Introduzca un id");
                                int id_cont = sc.nextInt();
                                adCo.borrarContacto(id_cont);
                                salir = comprobarSalir(salir);
                                break;
                            case 2:
                                //elejimos una oportunidad y la borramos
                                System.out.println("Elija un id Oportunidad");
                                Tabla_oportunidades(adOp.mostrarOportunidades());
                                System.out.println("Introduzca un id");
                                int id_op = sc.nextInt();
                                adOp.borrarOportunidad(id_op);
                                salir = comprobarSalir(salir);
                                break;
                            case 3:
                                //elejimos una actividad y la borramos
                                System.out.println("Elija un id Actividad");
                                Tabla_oportunidades(adOp.mostrarOportunidades());
                                System.out.println("Introduzca un id");
                                int id_ac = sc.nextInt();
                                adAc.borrarActividad(id_ac);
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
//metodo para comprobar si queremos seguir realizando operaciones en el crm

    public static boolean comprobarSalir(boolean salir) {
        System.out.println("¿Desea intentar otra operacion?");
        System.out.println("1 -> si");
        System.out.println("2 -> no");
        Scanner sc = new Scanner(System.in);
        int ops = sc.nextInt();
        if (ops == 2) {
            salir = true;
        }
        System.out.println("----------------------------------------");
        return salir;
    }
//metodo para mostrar los contactos con un formato marco

    public static void Tabla_contactos(List<Contacto> contactos) {
        //Inicializacion de variables
        int tamano = contactos.get(1).toString().length();
        //bucle para ajustar el margen superior al tamaño con un pequeño margen
        for (int i = 0; i < tamano + 4; i++) {
            System.out.print("=");
        }
        System.out.println("");
        //bucle para mostrar los datos con parte de los bordes laterales
        for (int i = 0; i < contactos.size(); i++) {
            Contacto c1 = contactos.get(i);
            System.out.println("* " + c1.toString() + " *");
        }
        //bucle para ajustar el borde inferior al tamaño con un pequeño margen
        for (int i = 0; i < tamano + 4; i++) {
            System.out.print("=");
        }
        System.out.println("");
    }
//metodo para mostrar las oportunidades con un formato marco

    public static void Tabla_oportunidades(List<Oportunidad> oportunidades) {
        //Inicializacion de variables
        int tamano = oportunidades.get(1).toString().length();
        //bucle para ajustar el borde superior al tamaño con un pequeño margen
        for (int i = 0; i < tamano + 4; i++) {
            System.out.print("=");
        }
        System.out.println("");
        //bucle para mostrar los datos con parte de los bordes laterales
        for (int i = 0; i < oportunidades.size(); i++) {
            Oportunidad op = oportunidades.get(i);
            System.out.println("* " + op.toString() + " *");
        }
        //bucle para ajustar el borde inferior al tamaño con un pequeño margen
        for (int i = 0; i < tamano + 4; i++) {
            System.out.print("=");
        }
        System.out.println("");
    }
//metodo para mostrar las actividades con un formato marco

    public static void Tabla_actividades(List<Actividad> actividades) {
        //Inicializacion de variables
        int tamano = actividades.get(1).toString().length();
        //bucle para ajustar el borde superior al tamaño con un pequeño margen
        for (int i = 0; i < tamano + 4; i++) {
            System.out.print("=");
        }
        System.out.println("");
        //bucle para mostrar los datos con parte de los bordes laterales
        for (int i = 0; i < actividades.size(); i++) {
            Actividad ac = actividades.get(i);
            System.out.println("* " + ac.toString() + " *");
        }
        //bucle para ajustar el borde inferior al tamaño con un pequeño margen
        for (int i = 0; i < tamano + 4; i++) {
            System.out.print("=");
        }
        System.out.println("");
    }
//metodo para mostrar las oportunidades de un contacto en un formato con marco

    public static void Tabla_Oportunidades_Contacto(Set<Oportunidad> opCont) {
        //Inicializacion de variables
        Iterator it = opCont.iterator();
        String oportunidad = it.next().toString();
        int tamano = oportunidad.length();
        //bucle para ajustar el borde superior al tamaño con un pequeño margen
        for (int i = 0; i < tamano + 4; i++) {
            System.out.print("=");
        }
        //pre-inicializacion del bucle while
        System.out.println("");
        System.out.println("* " + oportunidad + " *");
        //bucle para mostrar los datos con parte de los bordes laterales
        while (it.hasNext()) {
            oportunidad = it.next().toString();
            System.out.println("* " + oportunidad + " *");
        }
        //bucle para ajustar el borde inferior al tamaño con un pequeño margen
        for (int i = 0; i < tamano + 4; i++) {
            System.out.print("=");
        }
        System.out.println("");
    }
//metodo para mostrar las actividades de una oportunidad en un formato con marco

    public static void Tabla_Actividades_Oportunidad(Set<Actividad> acOp) {
        //Inicializacion de variables
        Iterator it = acOp.iterator();
        String actividad = it.next().toString();
        int tamano = actividad.length();
        //bucle para ajustar el borde superior al tamaño con un pequeño margen
        for (int i = 0; i < tamano + 4; i++) {
            System.out.print("=");
        }
        System.out.println("");
        //pre-inicializacion del bucle while
        System.out.println("* " + actividad + " *");
        //bucle para mostrar los datos con parte de los bordes laterales
        while (it.hasNext()) {
            actividad = it.next().toString();
            System.out.println("* " + actividad + " *");
        }
        //bucle para ajustar el borde inferior al tamaño con un pequeño margen
        for (int i = 0; i < tamano + 4; i++) {
            System.out.print("=");
        }
        System.out.println("");
    }
}
