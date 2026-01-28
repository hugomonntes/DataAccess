package data_access.T4.srcs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
    public static void añadirColumna(String nombreTabla, String nombreCampo, int tipoDato, String propiedades) {
        String query = String.format("ALTER TABLE %s ADD %s %d %s", nombreTabla, nombreCampo, tipoDato, propiedades);
        try (Statement stm = conexion.createStatement()) {
            stm.executeUpdate(query);
        } catch (Exception e) {
        }
    }

    // 10. Queremos obtener los siguientes datos de las columnas devueltas por la
    // consulta "select *, nombre as non from alumnos": Nombre de la columna, alias
    // de la columna, nombre del tipo de dato usado en la columna, si es
    // autoincrementado y si permite nulos.
    public static void mostrarDatos() {
        try (Statement stm = conexion.createStatement()) {
            String query = "select *, nombre as non from alumnos";
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i < rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i) + " - " + rsmd.getColumnTypeName(i) + " - "
                        + rsmd.isAutoIncrement(i) + " - " + rsmd.isNullable(i));
            }
        } catch (Exception e) {
        }
    }

    // 12. Queremos insertar un grupo de alumnos garantizando que, si alguna
    // inserción
    // falla, la base de datos quede en el estado inicial. ¿Cómo podernos realizar
    // esta
    // tarea?. Indica dos ejemplo que utilice esta facilidad (comprobando que el
    // SGBD
    // la soporta): Uno que su ejecución no produzca error y otro que si y, en este
    // caso, se deshagan todas las modificaciones de se hayan realizado a la base de
    // datos y se indique el código de error generado. ¿Conoces alguna forma que
    // evitar tener que deshacer todos los cambios?
    public static void insertarGrupoAlumnos() {
        try {
            conexion.setAutoCommit(false);
            try (Statement stm = conexion.createStatement()) {
                stm.executeUpdate(
                        "INSERT INTO alumnos (nombre, apellidos, altura, aula) VALUES ('Ana', 'Lopez', 165, 1)");
                stm.executeUpdate(
                        "INSERT INTO alumnos (nombre, apellidos, altura, aula) VALUES ('Luis', 'Garcia', 175, 2)");
                stm.executeUpdate(
                        "INSERT INTO alumnos (nombre, apellidos, altura, aula, edad) VALUES ('Marta', 'Sanchez', 160, 1, 20)");
                conexion.commit();
            } catch (SQLException e) {
                conexion.rollback();
            }
        } catch (SQLException e) {
        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
            }
        }
    }

    // 9. Mediante DatabaseMetaData (y métodos similares) queremos obtener cierta
    // información de la base de datos y de las tablas que contiene la base de
    // datos:
    // a. Obtén los siguientes datos de la base de datos: Nombre del driver, versión
    // del driver, url de conexión, usuario con el que estamos conectados a la base
    // de datos, el nombre del SGBD, versión del SGBD y las palabras reservadas
    // que tienen el SGBD.
    public static void getInfo(String bd) {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            System.out.println("Nombre del driver: " + dbmd.getDriverName());
            System.out.println("Versión del driver: " + dbmd.getDriverVersion());
            System.out.println("URL de conexión: " + dbmd.getURL());
            System.out.println("Usuario conectado: " + dbmd.getUserName());
            System.out.println("Nombre del SGBD: " + dbmd.getDatabaseProductName());
            System.out.println("Versión del SGBD: " + dbmd.getDatabaseProductVersion());
            System.out.println("Palabras reservadas: " + dbmd.getSQLKeywords());
        } catch (SQLException e) {
            System.out.println("Error al obtener la información de la base de datos: " + e.getLocalizedMessage());
        }
    }

    // b. Obtén todas las bases de datos (Catalogs) del SGBD.
    public static void getCatalogs() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getCatalogs();
            System.out.println("Bases de datos disponibles:");
            while (rs.next()) {
                String catalog = rs.getString("TABLE_CAT");
                System.out.println("- " + catalog);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las bases de datos: " + e.getLocalizedMessage());
        }
    }

    // c. Para todas las tablas de la base datos ADD obtén: el nombre de las tabla y
    // el tipo de tabla.
    public static void getTables() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getTables("add", null, "%", null);
            System.out.println("Tablas en la base de datos ADD:");
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                String tableType = rs.getString("TABLE_TYPE");
                System.out.println(tableName + " (" + tableType + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error las tablas: " + e.getLocalizedMessage());
        }
    }

    // d. Repite el ejercicio anterior pero solo mostrando las vistas.
    public static void getViews() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getTables("add", null, "%", new String[] { "VIEW" });
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                String tableType = rs.getString("TABLE_TYPE");
                System.out.println(tableName + " (" + tableType + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error las tablas: " + e.getLocalizedMessage());
        }
    }

    // e. Combina en uno solo los ejercicios b y c.
    public static void getCatalogsAndTables() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet catalogs = dbmd.getCatalogs();
            System.out.println("Bases de datos y sus tablas:");
            while (catalogs.next()) {
                String catalog = catalogs.getString("TABLE_CAT");
                System.out.println("Base de datos: " + catalog);
                ResultSet tables = dbmd.getTables(catalog, null, "%", null);
                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");
                    String tableType = tables.getString("TABLE_TYPE");
                    System.out.println(" - " + tableName + " (" + tableType + ")");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error bases de datos y tablas: " + e.getLocalizedMessage());
        }
    }

    // f. Obtén todos los procedimientos almacenados de la base de datos ADD.
    public static void getStoredProcedures() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getProcedures("add", null, "%");
            System.out.println("Procedimientos almacenados en la base de datos ADD:");
            while (rs.next()) {
                String procedureName = rs.getString("PROCEDURE_NAME");
                System.out.println("- " + procedureName);
            }
        } catch (SQLException e) {
            System.out.println("Error procedimientos almacenados: " + e.getLocalizedMessage());
        }
    }

    // g. Mediante getColumns obtén de las tablas de la base de datos ADD que
    // comiencen por 'a' los siguientes datos: posición de la columna, base de
    // datos, tabla, nombre de la columna, nombre del tipo de dato de la columna,
    // tamaño de la columna y si permite nulos. Indica también si has encontrado
    // alguna tabla con un campo autoincrementado.
    public static void getColumnsInfo() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet tables = dbmd.getTables("add", null, "a%", null);
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Tabla: " + tableName);
                ResultSet columns = dbmd.getColumns("add", null, tableName, "%");
                while (columns.next()) {
                    int position = columns.getInt("ORDINAL_POSITION");
                    String columnName = columns.getString("COLUMN_NAME");
                    String typeName = columns.getString("TYPE_NAME");
                    int columnSize = columns.getInt("COLUMN_SIZE");
                    String isNullable = columns.getString("IS_NULLABLE");
                    String isAutoIncrement = columns.getString("IS_AUTOINCREMENT");
                    System.out.println(String.format(
                            " - Posición: %d, Columna: %s, Tipo: %s, Tamaño: %d, Permite nulos: %s, Autoincrementado: %s",
                            position, columnName, typeName, columnSize, isNullable, isAutoIncrement));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la información de las columnas: " + e.getLocalizedMessage());
        }
    }

    // 13. Queremos leer y almacenar objetos binarios en una base de datos. Para
    // ello
    // vamos a obtener y guardar imágenes en la tabla imágenes de la base de datos
    // AD.
    // a. Vamos a obtener una imagen desde la base de datos y almacenarla en el
    // disco duro. Para ello usamos el método getBinaryStream. Este método
    // devuelve un objeto de tipo InputStream. Del cual tendremos que ir leyendo
    // bytes y almacenándolos en un archivo binario del disco duro.
    public static void almacenarImagen() {
        try (Statement st = conexion.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM imagenes WHERE nombre = 'escritor1.jpg'");
            while (rs.next()) {
                InputStream is = rs.getBinaryStream("imagen");
                try (FileOutputStream fos = new FileOutputStream("C:\\imagenes\\imagen.jpg")) {
                    int i;
                    byte[] buffer = new byte[1024];
                    while ((i = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, i);
                    }
                    is.close();
                } catch (IOException e) {

                }
            }
        } catch (SQLException e) {
        }
    }

    // b. Vamos ahora a almacenar una imagen que está guardada en el disco duro en
    // la base de datos. Para ello creamos una sentencia preparada para insertar
    // datos en la tabla imágenes. Para establecer el elemento binario usamos el
    // método setBinaryStream con los siguientes argumentos: posición del campo
    // imagen, objeto de tipo FileImputStream (que apunta a la imagen que
    // queremos insertar) y número de bytes que vamos a escribir.
    public static void insertarImagen() {
        String query = "INSERT INTO imagenes (nombre, imagen) VALUES (?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            File file = new File("C:\\imagenes\\escritor1.jpg");
            try (FileInputStream fis = new FileInputStream(file)) {
                ps.setString(1, "escritor1.jpg");
                ps.setBinaryStream(2, fis, (int) file.length());
                ps.executeUpdate();
            } catch (IOException e) {
            }
        } catch (SQLException e) {
        }
    }

    // 15. Crea un método que ejecute el procedimiento almacenado getAulas y la
    // función
    // Suma de la base de datos Add. Visualiza los datos que devuelven.
    public static void ejecutarProcedimientoYFuncion() {
        try (Statement stm = conexion.createStatement()) {
            ResultSet rs = stm.executeQuery("CALL getAulas()");
            System.out.println("Aulas:");
            while (rs.next()) {
                System.out.println("- " + rs.getString("nombreAula"));
            }
            rs = stm.executeQuery("SELECT Suma(5, 10) AS resultado");
            if (rs.next()) {
                System.out.println("Resultado de la función Suma: " + rs.getInt("resultado"));
            }
        } catch (SQLException e) {
        }
    }

    // 16. Realiza un método que permita buscar una cadena de texto en cualquier
    // columna de tipo char o varchar de cualquier tabla de una base datos dada.
    // Debe
    // indicar la base de datos, tabla y columna donde se encontró la coincidencia y
    // el
    // texto completo del campo
    public static void buscarCadenaEnBaseDatos(String cadenaBuscada) {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet tables = dbmd.getTables(null, null, "%", new String[] { "TABLE" });
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                ResultSet columns = dbmd.getColumns(null, null, tableName, "%");
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String typeName = columns.getString("TYPE_NAME");
                    if (typeName.equalsIgnoreCase("CHAR") || typeName.equalsIgnoreCase("VARCHAR")) {
                        String query = String.format("SELECT * FROM %s WHERE %s LIKE ?", tableName, columnName);
                        try (PreparedStatement ps = conexion.prepareStatement(query)) {
                            ps.setString(1, "%" + cadenaBuscada + "%");
                            ResultSet rs = ps.executeQuery();
                            while (rs.next()) {
                                System.out.println(String.format("Base de datos: %s, Tabla: %s, Columna: %s, Valor: %s",
                                        conexion.getCatalog(), tableName, columnName,
                                        rs.getString(columnName)));
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
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
        // ArrayList<Object> tiempos = calcularTiempos();
        // for (Object tiempo : tiempos) {
        // System.out.println(tiempo + "ms");
        // }
        // mostrarDatos();
        // insertarGrupoAlumnos();
        // getTables();
        getViews();
        cerrarConexion();
        ejecutarProcedimientoYFuncion();
        buscarCadenaEnBaseDatos("a");
    }
}

// Dar de baja alumnos y asignaturas.