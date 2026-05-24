package dao;

import conexion.BaseDatos;
import proyecto_zooa.Ave;
import java.sql.*;
import java.util.ArrayList;

public class AveDAO {

    public void insertar(Ave a) {
        
        String sql = "INSERT INTO ave(idAnimal, nombre, edad, consumoDiario) VALUES (?, ?, ?, ?)";

        try {

            Connection conn = BaseDatos.getConexion();

            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setLong(1, a.getIdAnimal());
            ps.setString(2, a.getNombre());
            ps.setInt(3, a.getEdad());
            ps.setDouble(4, a.getConsumoDiario());

            ps.executeUpdate();

            System.out.println("Ave insertada");

            conn.close();

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    public ArrayList<Ave> consultar() {

        ArrayList<Ave> lista = new ArrayList<>();

        String sql = "SELECT * FROM ave";

        try {

            Connection conn = BaseDatos.getConexion();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Ave m = new Ave(
                        rs.getLong("idanimal"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getDouble("consumoDiario")
                );

                lista.add(m);
            }

            conn.close();

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
        }

        return lista;
    }

    public void actualizar(Ave a) {

        String sql = "UPDATE ave SET nombre=?, edad=? WHERE idAnimal=?";

        try {

            Connection conn = BaseDatos.getConexion();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, a.getNombre());
            ps.setInt(2, a.getEdad());
            ps.setLong(3, a.getIdAnimal());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Registro actualizado");
            } else {
                System.out.println("No existe el ID");
            }

            conn.close();

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    public void eliminar(long id) {

        String sql = "DELETE FROM ave WHERE idAnimal=?";

        try {

            Connection conn = BaseDatos.getConexion();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, (int) id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Registro eliminado");
            } else {
                System.out.println("No existe el ID");
            }

            conn.close();

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public Ave buscarPorId(long idBuscar) {
    String sql = "SELECT * FROM ave WHERE idAnimal = ?";
    try {
        Connection conn = BaseDatos.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, idBuscar);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Ave(
                rs.getLong("idAnimal"),
                rs.getString("nombre"),
                rs.getInt("edad"),
                rs.getDouble("consumoDiario")
            );
        }
        conn.close();
    } catch (SQLException e) {
        System.out.println("Error al buscar Ave: " + e.getMessage());
    }
    return null;
}
    
}
