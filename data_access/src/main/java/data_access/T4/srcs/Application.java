package data_access.T4.srcs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {
  private static Connection conexion;

  public static void abrirConexion(String bd, String servidor, String usuario,
      String password) {
    try {
      String url = String.format("jdbc:mariadb://%s:3306/%s", servidor, bd);
      // Establecemos la conexión con la BD
      conexion = DriverManager.getConnection(url, usuario, password);
      if (conexion != null) {
        System.out.println("Conectado a " + bd + " en " + servidor);
      } else {
        System.out.println("No conectado a " + bd + " en " + servidor);
      }
    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getLocalizedMessage());
      System.out.println("SQLState: " + e.getSQLState());
      System.out.println("Código error: " + e.getErrorCode());
    }
  }

  public static void cerrarConexion() {
    try {
      conexion.close();
    } catch (SQLException e) {
      System.out.println("Error al cerrar la conexión: " + e.getLocalizedMessage());
    }
  }

  public static void consultarJugadores() {
    try (Statement stm = conexion.createStatement()) {
      ResultSet result = stm.executeQuery("SELECT * FROM jugadores_celta");
      while (result.next()) {
        System.out.println(result.getInt("dorsal"));
        System.out.println(result.getString("nombre"));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void mostrarNombreEdad() {
    try (Statement stm = conexion.createStatement()) {
      ResultSet result = stm.executeQuery("SELECT * FROM jugadores_celta WHERE edad > 30");
      while (result.next()) {
        System.out.println(result.getString("nombre"));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void insertarManuel() {
    try (Statement stm = conexion.createStatement()) {
      stm.executeUpdate(String.format(
          "INSERT INTO jugadores_celta(dorsal, nombre, posicion, edad, nacionalidad, convocado, partidos_jugados,goles,minutos_jugados) VALUES (%d,'%s','%s',%d,'%s',%d,%d,%d,%d)",
          99, "Manuel", "Entrenador", 19, "Sanxenxo", 0, 0, 0, 0));
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void Guaita() {
    try (Statement stm = conexion.createStatement()) {
      stm.executeQuery(
          "UPDATE jugadores_celta SET nombre = 'Radu', edad = '27', nacionalidad = 'Raluka' WHERE nombre = 'Vicente Guaita'");
    } catch (Exception e) {
    }
  }

  public static void deleteFromBD(int dorsal) {
    try (Statement stm = conexion.createStatement()) {
      stm.executeUpdate("DELETE FROM jugadores_celta WHERE dorsal=" + dorsal);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void consultar(int dorsal, int edad) throws SQLException {
    String query = "SELECT * FROM jugadores_celta where dorsal= ? AND edad = ?";
    PreparedStatement ps = conexion.prepareStatement(query);
    ps.setInt(1, dorsal);
    ps.setInt(2, edad);
    ResultSet result = ps.executeQuery();
    result.next();
    System.out.println(result.getString("nombre"));
  }

  public static void main(String[] args) throws SQLException {
    abrirConexion("celta", "localhost", "root", "");
    // deleteFromBD(1);
    // mostrarNombreEdad();
    // insertarManuel();
    // Guaita();
    // consultarJugadores();
    consultar(99, 19);
    cerrarConexion();
  }
}
