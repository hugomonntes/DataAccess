import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

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

    public static void alumnosWithChars(String chars) {
        try (Statement stm = conexion.createStatement()) {
            ResultSet result = stm.executeQuery("SELECT * FROM alumnos WHERE nombre like " + "'%" + chars + "%'");
            while (result.next()) {
                System.out.println(result.getString("nombre"));
            }
        } catch (SQLException e) {
        }
    }

    public static int crearAlumno(String nombre, String apellidos, int altura, int aula) {
        try (Statement stm = conexion.createStatement()) {
            return stm.executeUpdate(
                    String.format("INSERT INTO alumnos (nombre, apellidos, altura, aula) VALUES ('%s','%s',%d,%d)",
                            nombre, apellidos, altura, aula));
        } catch (SQLException e) {
        }
        return 0;
    }

    public static int eliminarAlumno(int id) {
        try (Statement stm = conexion.createStatement()) {
            return stm.executeUpdate(String.format("DELETE FROM alumnos WHERE codigo = %d", id));
        } catch (SQLException e) {
        }
        return 0;
    }

    public static int crearAsinatura(String nombreAsignatura) {
        try (Statement stm = conexion.createStatement()) {
            return stm.executeUpdate(String.format("INSERT INTO asignaturas (nombre) VALUES ('%s')", nombreAsignatura));
        } catch (SQLException e) {
        }
        return 0;
    }

    public static int eliminarAsinatura(int id) {
        try (Statement stm = conexion.createStatement()) {
            return stm.executeUpdate(String.format("DELETE FROM asignaturas WHERE cod = %d", id));
        } catch (SQLException e) {
        }
        return 0;
    }

    // 4. Modificar alumnos y asignaturas.
    public static void modificarAlumno(int id, String nombre, String apellidos, int altura) {
        try (Statement stm = conexion.createStatement()) {
            stm.executeUpdate(
                    String.format("UPDATE alumnos SET nombre = '%s', apellidos = '%s', altura = %d WHERE codigo = %d",
                            nombre, apellidos, altura, id));
        } catch (SQLException e) {
        }
    }

    public static void modificarAsignatura(int id, String nombre) {
        try (Statement stm = conexion.createStatement()) {
            stm.executeUpdate(String.format("UPDATE asignaturas SET nombre = '%s' WHERE cod = %d", nombre, id));
        } catch (SQLException e) {
        }
    }

    // 5. Realiza las siguientes consultas:
    // Ø Nombres de las aulas con alumnos
    // Ø Nombre de los alumnos, de las asignaturas y notas de aquellos alumnos que
    // han aprobado alguna asignatura.
    // Ø Nombre de las asignaturas sin alumnos.
    public static void consultarAulasConAlumnos() {
        try (Statement stm = conexion.createStatement()) {
            ResultSet result = stm
                    .executeQuery("SELECT nombreAula FROM aulas WHERE numero NOT IN (SELECT aula FROM alumnos)");
            while (result.next()) {
                System.out.println(result.getString("nombreAula"));
            }
        } catch (SQLException e) {
        }
    }

    public static void consultarSegunda() {
        try (Statement stm = conexion.createStatement()) {
            stm.executeQuery(
                    "SELECT alumnos.nombre, asignaturas.COD, notas.nota FROM alumnos JOIN alumnos JOIN asignaturas JOIN notas ON alumnos.codigo = notas.alumno");
        } catch (Exception e) {
        }
    }

    // 6. Realizar un método que consulte que el nombre de un alumno contenga cierto
    // patrón y que la altura sea mayor que un valor. Realízalo con y sin sentencias
    // preparadas, pasando los valores de los criterios como parámetros.
    public static void consultaNombreAlumno(String patron, int alturaMin) throws SQLException {
        PreparedStatement ps = null;
        String query = "SELECT * FROM alumnos WHERE nombre LIKE ? AND altura > ?";
        if (ps == null) {
            ps = conexion.prepareStatement(query);
        }
        ps.setString(1, patron);
        ps.setInt(2, alturaMin);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            System.out.println(result.getString("nombre"));
        }
    }

    public static void consultaNombreAlumno2(String patron, int alturaMin) throws SQLException {
        try (Statement stm = conexion.createStatement()) {
            String query = "SELECT * FROM alumnos WHERE nombre LIKE '%" + patron + "%' AND altura >" + alturaMin;
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                System.out.println(result.getString("nombre"));
            }
        } catch (Exception e) {
        }
    }

    // 7. Ejecuta los métodos anteriores, calculando el tiempo de ejecución, dentro
    // de un
    // bucle: 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000 veces. ¿Qué
    // conclusión extraes?
    public static ArrayList<Object> calcularTiempos() throws SQLException {
        ArrayList<Integer> tiempo = new ArrayList<>(Arrays.asList(1, 10, 100, 1000, 100000, 1000000, 1000000));
        ArrayList<Object> tiemposRegistrados = new ArrayList<>();
        for (int i = 0; i < tiempo.size(); i++) {
            for (int j = 0; j < tiempo.get(i); j++) {
                long startTime = System.currentTimeMillis();
                consultarAulasConAlumnos();
                consultaNombreAlumno("%o%", 182);
                consultaNombreAlumno2("o", 180);
                long endTime = System.currentTimeMillis();
                tiemposRegistrados.add(endTime - startTime);
            }
        }
        return tiemposRegistrados;
    }

    // 8. Quememos crear un método que pasándole cuatro parámetros (tabla, nombre
    // de campo, tipo de dato, propiedades) nos permita añadir una columna a una
    // tabla.
    public static void añadirColumna(String nombreTabla, String nombreCampo, int tipoDato, String propiedades){
        
    }

    public static void main(String[] args) throws SQLException {
        abrirConexion("add", "localhost", "root", "");
        // alumnosWithChars("a");
        // System.out.println(crearAlumno("Diego", "Costa", 150, 20));
        // System.out.println(crearAsinatura("Brasileño"));
        // System.out.println(eliminarAlumno(16));
        // System.out.println(eliminarAsinatura(9));
        // getInfo("add");
        // modificarAlumno(1, "Hugo", "Montes", 183);
        // modificarAsignatura(1, "Aleman");
        // consultarAulasConAlumnos();
        // consultaNombreAlumno("%o%", 182);
        // consultaNombreAlumno2("o", 180);
        // System.out.println(calcularTiempos() + "ms");
        ArrayList<Object> tiempos = calcularTiempos();
        for (Object tiempo : tiempos) {
            System.out.println(tiempo + "ms");
        }
        cerrarConexion();
    }
}

// Dar de baja alumnos y asignaturas.