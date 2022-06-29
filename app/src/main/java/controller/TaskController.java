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
import model.Tasks;

/**
 *
 * @author andre
 */
public class TaskController {
    
    private Connection conn = null;
    private PreparedStatement st = null;
    private ResultSet rs;
    
    public void save(Tasks task) throws SQLException{
        String sql = "INSERT INTO tasks (idProject"
                + ", name"
                + ", description"
                + ", completed"
                + ", notes"
                + ", deadline"
                + ", createdAt"
                + ", updatedAt) VALUES (?,?,?,?,?,?,?,?)";
        try{
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(sql);
            st.setInt(1, task.getIdProject());
            st.setString(2, task.getName());
            st.setString(3, task.getDescription());
            st.setBoolean(4, task.isCompleted());
            st.setString(5, task.getNotes());
            st.setDate(6, new Date(task.getDeadline().getTime()));
            st.setDate(7, new Date(task.getCreatedAt().getTime()));
            st.setDate(8, new Date(task.getUpdatedAt().getTime()));
            st.execute();
        } catch(SQLException e){
            throw new SQLException("Erro ao salvar nova tarefa. " + e.getMessage(), e);
        } finally{
            ConnectionFactory.closeConnection(conn, st);
        }
        
    }
    
    public void update(Tasks task) throws SQLException{
        String sql = "UPDATE tasks SET "
                + "idProject = ?"
                + ", name = ?"
                + ", description = ?"
                + ", completed = ?"
                + ", notes = ?"
                + ", deadline = ?"
                + ", createdAt = ?"
                + ", updatedAt = ?"
                + "WHERE id = ?";
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(sql);
            st.setInt(1, task.getIdProject());
            st.setString(2, task.getName());
            st.setString(3, task.getDescription());
            st.setBoolean(4, task.isCompleted());
            st.setString(5, task.getNotes());
            st.setDate(6, new Date(task.getDeadline().getTime()));
            st.setDate(7, new Date(task.getCreatedAt().getTime()));
            st.setDate(8, new Date(task.getUpdatedAt().getTime()));
            st.setInt(9, task.getId());
            st.execute();
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar registro. " + e.getMessage());
        } finally {
        }
    }
    
    public void removeById(int taskId) throws SQLException{
        String sql = "DELETE FROM tasks WHERE id = ?";
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(sql);
            st.setInt(1, taskId);
            st.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa!", e);
        } finally{
            ConnectionFactory.closeConnection(conn, st);
        }
    }
    
    public List<Tasks> getAll(int idProject){
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        List<Tasks> tasks = new ArrayList<>();
        try {
            conn = ConnectionFactory.getConnection();
            st = conn.prepareStatement(sql);
            st.setInt(1, idProject);
            rs = st.executeQuery();
            while(rs.next()){
                Tasks t = new Tasks();
                t.setId(rs.getInt("id"));
                t.setIdProject(rs.getInt("idProject"));
                t.setName(rs.getString("name"));
                t.setDescription(rs.getString("description"));
                t.setNotes(rs.getString("notes"));
                t.setCompleted(rs.getBoolean("completed"));
                t.setDeadline(rs.getDate("deadline"));
                t.setCreatedAt(rs.getDate("createdAt"));
                t.setUpdatedAt(rs.getDate("updatedAt"));
                tasks.add(t);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao recuperar registros do banco de dados. " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, st, rs);
        }
        return tasks;
    }
}
