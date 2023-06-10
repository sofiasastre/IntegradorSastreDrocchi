import DAO.impl.OdontologoArray;
import DAO.impl.OdontologoDAO;
import Model.Odontologo;
import Service.OdontologoService;

public class Main {
    public static void main(String[] args) {
        Odontologo o = new Odontologo(11,"Pedro","García");
        Odontologo o2 = new Odontologo(22,"Maria","Gutierrex");

        OdontologoService os = new OdontologoService(new OdontologoDAO());
        //Guardo en H2
        //os.guardar(o);
        //os.guardar(o2);
        //Busco en base
        //os.buscarTodos();
        //Cambio el almacenamiento
        //os.setMiDao(new OdontologoArray());
        //Guardo en Memoria
       // os.guardar(o);
       // os.guardar(o2);
        //os.eliminar(26);
        os.buscarTodos();
        Odontologo o3 = new Odontologo(1,11, "Pedro","Picapiedras");
        os.modificar(o3);
        System.out.println("ESTE ES EL MODIFICADO " + o2);
        os.buscarTodos();



    }
}