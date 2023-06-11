package DAO.impl;

import DAO.IDao;
import Model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class OdontologoDAO implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDAO.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'EXAMENC7_SASTRE_DROCCHI/resource/create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";
    private final static String SQL_INSERT = "INSERT INTO ODONTOLOGOS(MATRICULA,NOMBRE,APELLIDO)" + "VALUES(?,?,?)";
    private final static String SQL_BUSCAR_TODO = "SELECT * FROM ODONTOLOGOS";
    private final static String SQL_UPDATE ="update ODONTOLOGOS set MATRICULA=?, NOMBRE=?, APELLIDO=? WHERE ID=?; ";
    private final static String SQL_DELETE = "DELETE FROM ODONTOLOGOS WHERE ID = ?;";
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
    public Odontologo guardar(Odontologo o) {
        // Connection connection = null;
        //PreparedStatement preparedStatement = null;
        Connection connection = con();
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, o.getMatricula());
            ps.setString(2, o.getNombre());
            ps.setString(3, o.getApellido());
            ps.executeUpdate();
            logger.debug("Se está guardando un odontologo en la base");
            logger.debug("Se guardan los datos Matricula: " + o.getMatricula() + ", Nombre: " + o.getNombre() + ", Apellido: " + o.getApellido());

            ResultSet keys = ps.getGeneratedKeys();
            while (keys.next()) {
                o.setId(keys.getInt(1));
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
        return o;
    }

    @Override
    public ArrayList<Odontologo> listarTodos() {
        logger.info("......Preparando el listado de odontologos.....");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Odontologo> odontologos = new ArrayList<>();
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
                int matricula = result.getInt("matricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                Odontologo odontologo = new Odontologo(id, matricula, nombre, apellido);
                System.out.println(odontologo);
                odontologos.add(odontologo);
            }
            logger.debug(odontologos);
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return odontologos;
    }

    @Override
    public void modificar(Odontologo odontologo) {
        Connection connection = con();
        try {
            PreparedStatement pst = connection.prepareStatement(SQL_UPDATE);
            pst.setInt(1, odontologo.getMatricula());
            pst.setString(2, odontologo.getNombre());
            pst.setString(3, odontologo.getApellido());
            pst.setInt(4, odontologo.getId());
            pst.executeUpdate();

            ResultSet keys = pst.getGeneratedKeys();
            while (keys.next()) {
                odontologo.setId(keys.getInt(1));
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
