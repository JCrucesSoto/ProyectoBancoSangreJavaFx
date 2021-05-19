/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import conexiondb.ConexionMySQL;
import java.sql.*;

/**
 *
 * @author J
 */
public class InsertarYConsultar {
    
        public boolean insertar(String actualiza) {


        boolean seRealizaronInserciones = false;

        try {


            ConexionMySQL conexion = new ConexionMySQL("localhost", "proy3TE6", "root", "root");
            
            
            conexion.ejecutarInstruccion(actualiza);

            conexion.cerrarConexion();
            
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return seRealizaronInserciones;
    }

    public ResultSet consulta(String consulta) throws SQLException {
        

        ConexionMySQL conexion = new ConexionMySQL("localhost", "proy3TE6", "root", "root");
        
        conexion.ejecutarConsulta(consulta);
        
        ResultSet resultadoConsultas = conexion.getResultSet();
        


        //conexion.cerrarConexion();

        return resultadoConsultas;
        
    }
    
}
