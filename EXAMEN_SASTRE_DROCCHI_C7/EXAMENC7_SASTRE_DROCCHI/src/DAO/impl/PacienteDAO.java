package DAO.impl;

import DAO.IDao;
import Model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PacienteDAO implements IDao<Paciente> {
    private static final Logger logger = Logger.getLogger(PacienteDAO.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'EXAMENC7_SASTRE_DROCCHI/resource/create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";
    private final static String SQL_INSERT = "INSERT INTO PACIENTES(NOMBRE,APELLIDO,DOMICILIO,DNI,FECHAALTA)" + "VALUES(?,?,?,?,?)";
    private final static String SQL_BUSCAR_TODO = "SELECT * FROM PACIENTES";
    private final static String SQL_UPDATE ="update PACIENTES set NOMBRE=?, APELLIDO=?, DOMICILIO=?, DNI=?, FECHAALTA=? WHERE ID=?; ";
    private final static String SQL_DELETE = "DELETE FROM PACIENTES WHERE ID = ?;";
    public static Connection con() {
        Connection c = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        // Connection connection = null;
        //PreparedStatement preparedStatement = null;
        Connection connection = con();
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDomicilio());
            ps.setInt(4, paciente.getDNI());
            LocalDate fechaAlta = paciente.getFechaAlta();
            java.sql.Date fechaAltaSQL = java.sql.Date.valueOf(fechaAlta);
            ps.setDate(5, fechaAltaSQL);
            ps.executeUpdate();
            logger.debug("Se está guardando un paciente en la base");
            logger.debug("Se guardan los datos Nombre: " + paciente.getNombre() + ", Apellido: " + paciente.getApellido() + ", Domicilio: " + paciente.getDomicilio() + ", DNI: " + paciente.getDNI() + ", Fecha de Alta: " + paciente.getFechaAlta());

            ResultSet keys = ps.getGeneratedKeys();
            while (keys.next()) {
                paciente.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public ArrayList<Paciente> listarTodos() {
        logger.info("......Preparando el listado de pacientes.....");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Paciente> pacientes = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            connection = con();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement(SQL_BUSCAR_TODO);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                int id = result.getInt("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                String direccion = result.getString("direccion");
                int dni = result.getInt("dni");
                java.sql.Date fechaAltaSQL = result.getDate("fechaalta");
                LocalDate fechaAlta = fechaAltaSQL.toLocalDate();

                Paciente paciente = new Paciente(id, nombre, apellido,direccion,dni,fechaAlta);
                System.out.println(paciente);
                pacientes.add(paciente);
            }
            logger.debug(pacientes);
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    @Override
    public void modificar(Paciente paciente) {
        Connection connection = con();
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDomicilio());
            ps.setInt(4, paciente.getDNI());
            LocalDate fechaAlta = paciente.getFechaAlta();
            java.sql.Date fechaAltaSQL = java.sql.Date.valueOf(fechaAlta);
            ps.setDate(5, fechaAltaSQL);
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            while (keys.next()) {
                paciente.setId(keys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(int id) {
        Connection conection = con();
        try {
            PreparedStatement pst = conection.prepareStatement(SQL_DELETE);
            pst.setInt(1, id);
            pst.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                conection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        };
    }
}
