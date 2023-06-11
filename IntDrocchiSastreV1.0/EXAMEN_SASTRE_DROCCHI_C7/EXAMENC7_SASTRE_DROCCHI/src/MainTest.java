import DAO.impl.OdontologoArray;
import DAO.impl.OdontologoDAO;
import Model.Odontologo;
import Service.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MainTest {
    OdontologoService os = new OdontologoService(new OdontologoDAO());
    ArrayList<Odontologo> lista;
    @BeforeEach
    void doBefore(){
        lista = new ArrayList<>();
    }
    @Test
    void testGuardarEnH2(){
        lista = os.buscarTodos();
        Odontologo o = new Odontologo(11,"Pedro","García");
        os.guardar(o);
        //Se testea que lo obtenido sea lo equivalente a lo guardado en la base
        assertEquals(lista.size()+1, os.buscarTodos().size());
    }
    @Test
    void testGuardarEnMemoria(){
        os.setMiDao(new OdontologoArray());
        Odontologo o = new Odontologo(11,"Pedro","García");
        Odontologo o2 = new Odontologo(22,"Maria","Gutierrex");
        //Guardo en Memoria
        os.guardar(o);
        os.guardar(o2);

        lista = os.buscarTodos();
        //Se testea que lo obtenido sea lo equivalente a lo guardado en la memoria
        assertEquals(2, lista.size());
    }
}