/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author andre
 */
public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/todo";
    public static final String USER = "root";
    public static final String PASS = "12345678";
    
    //tenta fazer a conexão com DB
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão com DB", e);
        }
    }
    
    //fecha a conexão com DB
    public static void closeConnection(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao encerrar a conexão com DB", e);
        }
    }
    
    //fecha a conexão com DB
    public static void closeConnection(Connection connection, PreparedStatement st){
        try {
            if (connection != null){
                connection.close();
            }
            
            if (st != null){
                st.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao encerrar a conexão com DB", e);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement st, ResultSet rs){
        try {
            if (connection != null){
                connection.close();
            }
            
            if (st != null){
                st.close();
            }
            if (rs != null){
                rs.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao encerrar a conexão com DB", e);
        }
    }
}
