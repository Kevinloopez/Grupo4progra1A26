package dao;

import conexion.BaseDatos;
import proyecto_zooa.Mamifero;
import java.sql.*;
import java.util.ArrayList;

public class MamiferoDAO {

    public void insertar(Mamifero m) {
        
        String sql = "INSERT INTO mamifero(idAnimal, nombre, edad, consumoDiario) VALUES (?,?, ?, ?)";

        try {

            Connection conn = BaseDatos.getConexion();

            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setLong(1, m.getIdAnimal());
            ps.setString(2, m.getNombre());
            ps.setInt(3, m.getEdad());
            ps.setDouble(4, m.getConsumoDiario());

            ps.executeUpdate();

            System.out.println("Mamifero insertado");

            conn.close();

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    public ArrayList<Mamifero> consultar() {

        ArrayList<Mamifero> lista = new ArrayList<>();

        String sql = "SELECT * FROM mamifero";

        try {

            Connection conn = BaseDatos.getConexion();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Mamifero m = new Mamifero(
                        rs.getLong("idAnimal"),
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

    public void actualizar(Mamifero m) {

        String sql = "UPDATE mamifero SET nombre=?, edad=? WHERE idAnimal=?";

        try {

            Connection conn = BaseDatos.getConexion();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getEdad());
            ps.setLong(3, m.getIdAnimal());

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

        String sql = "DELETE FROM mamifero WHERE idAnimal=?";

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
    
    public Mamifero buscarPorId(long idBuscar) {
    String sql = "SELECT * FROM mamifero WHERE idAnimal = ?";
    try {
        Connection conn = BaseDatos.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, idBuscar);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Mamifero(
                rs.getLong("idAnimal"),
                rs.getString("nombre"),
                rs.getInt("edad"),
                rs.getDouble("consumoDiario")
            );
        }
        conn.close();
    } catch (SQLException e) {
        System.out.println("Error al buscar Mamifero: " + e.getMessage());
    }
    return null;
}
}