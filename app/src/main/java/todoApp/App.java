/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package todoApp;

import controller.ProjectController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import model.Project;
import util.ConnectionFactory;

public class App {
    public static void main(String[] args) throws SQLException{
        
        //Project p = new Project("Futebol", "teste banco de dados 2", new Date(), new Date());
        
        ProjectController controller = new ProjectController();
       
        
        controller.getAll();
    }
}
