package data_access.T4.srcs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Alumnos {
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


    public static void alumnosWithChars(String chars){
        try (Statement stm = conexion.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT * FROM alumnos WHERE nombre like " + "'%" + chars + "%'");
            while(result.next()){
                System.out.println(result.getString("nombre"));
            }
        } catch (SQLException e) {
        }
    }
    
    public static void crearAlumno(String nombre, String apellidos, int altura, int aula){
        try (Statement stm = conexion.createStatement()) {
            stm.executeUpdate(String.format("INSERT INTO alumnos (nombre, apellidos, altura, aula) VALUES ('%s','%s',%d,%d)", nombre, apellidos, altura, aula));
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
        abrirConexion("add", "localhost", "root", "");
        // alumnosWithChars("a");
        crearAlumno("Diego", "Costa", 150, 0);
        cerrarConexion();
    }
}

// Un método que permita consultar alumnos que contengan una cadena de
// caracteres en su nombre. Además deberá visualizar el número de resultados
// obtenidos.