package dao;

import conexion.BaseDatos;
import proyecto_zooa.Reptil;
import java.sql.*;
import java.util.ArrayList;

public class ReptilDAO {

    public void insertar(Reptil r) {
        
        String sql = "INSERT INTO reptil(idAnimal, nombre, edad, consumoDiario) VALUES (?, ?, ?, ?)";

        try {

            Connection conn = BaseDatos.getConexion();

            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setLong(1, r.getIdAnimal());
            ps.setString(2, r.getNombre());
            ps.setInt(3, r.getEdad());
            ps.setDouble(4, r.getConsumoDiario());

            ps.executeUpdate();

            System.out.println("Reptil insertado");

            conn.close();

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    public ArrayList<Reptil> consultar() {

        ArrayList<Reptil> lista = new ArrayList<>();

        String sql = "SELECT * FROM reptil";

        try {

            Connection conn = BaseDatos.getConexion();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Reptil m = new Reptil(
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

    public void actualizar(Reptil r) {

        String sql = "UPDATE reptil SET nombre=?, edad=? WHERE idAnimal=?";

        try {

            Connection conn = BaseDatos.getConexion();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, r.getNombre());
            ps.setInt(2, r.getEdad());
            ps.setLong(3, r.getIdAnimal());

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

        String sql = "DELETE FROM reptil WHERE idAnimal=?";

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
    public Reptil buscarPorId(long idBuscar) {
    String sql = "SELECT * FROM reptil WHERE idAnimal = ?";
    try {
        Connection conn = BaseDatos.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, idBuscar);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Reptil(
                rs.getLong("idAnimal"),
                rs.getString("nombre"),
                rs.getInt("edad"),
                rs.getDouble("consumoDiario")
            );
        }
        conn.close();
    } catch (SQLException e) {
        System.out.println("Error al buscar Reptil: " + e.getMessage());
    }
    return null;
}
}
