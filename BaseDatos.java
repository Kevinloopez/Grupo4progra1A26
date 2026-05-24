package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {
    
    public static Connection getConexion() {
        
        Connection con = null;
        
        try {
            
            String url = "jdbc:mariadb://localhost:3306/zoologico";
            String usuario = "root";
            String password = "";
            
            con = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion exitosa");
            
        } catch (SQLException e) {
            System.out.println("Error de conexion");
            System.out.println(e.getMessage());
        }
        return con;
    }
}
