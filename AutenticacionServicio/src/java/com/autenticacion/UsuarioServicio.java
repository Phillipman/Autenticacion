/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autenticacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author felipe
 */
@WebService(serviceName = "UsuarioServicio")
public class UsuarioServicio {

    private Connection bd;
    
    public UsuarioServicio() {
        iniciar();
    }
    
    private Connection iniciar() {
        if (bd == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                bd = DriverManager.getConnection("jdbc:mysql://localhost:3306/AutenticacionBD?user=requerimientos&password=requerimientos");
            } catch (Exception e) {
            }
        }
        return bd;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "autenticar")
    public Object[] autenticar(@WebParam(name = "nombre") String nombre, @WebParam(name = "clave") String clave) {
        Object[] valores = null;
        try {
            Statement instruccion = bd.createStatement();
            String consulta = "SELECT u.idUsuario, u.nombre, u.apellido1, u.apellido2 " +
                              "FROM AutenticacionBD.Usuario u " +
                              "WHERE u.nombreUsuario = '" + nombre + "' AND u.clave = '" + clave + "';";
            ResultSet filas = instruccion.executeQuery(consulta);
            while (filas.next()) {
                valores = new Object[5];
                valores[0] = filas.getInt(1);
                valores[1] = filas.getString(2);
                valores[2] = filas.getString(3);
                valores[3] = filas.getString(4);
                valores[4] = nombre;
            }
        } catch (Exception e) {
        }
        return valores;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "crear")
    public boolean crear(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellido1") String apellido1, @WebParam(name = "apellido2") String apellido2, @WebParam(name = "nombreUsuario") String nombreUsuario, @WebParam(name = "clave") String clave, @WebParam(name = "idRolLF") int idRolLF) {
        try {
            bd.setAutoCommit(false);
            Statement instruccion = bd.createStatement();
            instruccion.executeUpdate("INSERT INTO AutenticacionBD.Usuario (nombre, apellido1, apellido2, nombreUsuario, clave) " +
                                      "VALUES ('" + nombre + "', '" + apellido1 + "', '" + apellido2 + "', '" + nombreUsuario + "', '" + clave + "');");
            instruccion.executeUpdate("INSERT INTO AutenticacionBD.UsuariosRoles (Usuario_idUsuario, Rol_idRol) " +
                                      "VALUES (LAST_INSERT_ID(), 2)");
            bd.commit();
            bd.setAutoCommit(true);
            return true;
        } catch (Exception e) {
            try {
                bd.rollback();
                bd.setAutoCommit(true);
            } catch (Exception ex) {
            }
            return false;
        }
    }
}
