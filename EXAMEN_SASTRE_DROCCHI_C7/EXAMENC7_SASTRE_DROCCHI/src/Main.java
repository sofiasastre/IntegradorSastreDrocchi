import DAO.impl.OdontologoArray;
import DAO.impl.OdontologoDAO;
import DAO.impl.PacienteDAO;
import Model.Odontologo;
import Model.Paciente;
import Service.OdontologoService;
import Service.PacienteService;

import java.time.LocalDate;

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
        //os.buscarTodos();
        //Odontologo o3 = new Odontologo(2,11, "Anibal","Picapiedras");
        //os.modificar(o3);
        //System.out.println("ESTE ES EL MODIFICADO " + o2);
        //os.buscarTodos();
        Paciente p = new Paciente("Esteban","Camejo","Treinta y Tres 123",44444444, LocalDate.of(2021,01,01));
        PacienteService pservice = new PacienteService(new PacienteDAO());

        pservice.guardar(p);



    }
}