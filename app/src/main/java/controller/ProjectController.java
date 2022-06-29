/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionFactory;
import model.Project;

/**
 *
 * @author andre
 */
public class ProjectController {
    
    private Connection conn = null;
    private PreparedStatement st = null;
    private ResultSet rs;
    
    public void save(Project project) throws SQLException{
        String sql = "INSERT INTO projects (name"
                + ", description"
                + ", createdAt"
                + ", updatedAt) VALUES (?,?,?,?)";
        try{
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(sql);
            st.setString(1, project.getName());
            st.setString(2, project.getDescription());
            st.setDate(3, new Date(project.getCreatedAt().getTime()));
            st.setDate(4, new Date(project.getUpdatedAt().getTime()));
            st.execute();
        } catch(SQLException e){
            throw new RuntimeException("Erro ao salvar novo projeto. ", e);
        } finally{
            ConnectionFactory.closeConnection(conn, st);
        }
        
    }
    
    public void update(Project project) throws SQLException{
        String sql = "UPDATE projects SET "
                + ", name = ?"
                + ", description = ?"
                + ", createdAt = ?"
                + ", updatedAt = ?"
                + "WHERE id = ?";
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(sql);            
            st.setString(1, project.getName());
            st.setString(2, project.getDescription());            
            st.setDate(3, new Date(project.getCreatedAt().getTime()));
            st.setDate(4, new Date(project.getUpdatedAt().getTime()));
            st.setInt(5, project.getId());
            st.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar registro. ", e);
        } finally {
        }
    }
    
    public void removeById(int id) throws SQLException{
        String sql = "DELETE FROM projects WHERE id = ?";
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa! " + e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(conn, st);
        }
    }
    
    public List<Project> getAll(){
        String sql = "SELECT * FROM projects";
        List<Project> projects = new ArrayList<>();
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Project p = new Project();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setCreatedAt(rs.getDate("createdAt"));
                p.setUpdatedAt(rs.getDate("updatedAt"));
                projects.add(p);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao recuperar registros do banco de dados. " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, st, rs);
        }
        return projects;
    }
}
