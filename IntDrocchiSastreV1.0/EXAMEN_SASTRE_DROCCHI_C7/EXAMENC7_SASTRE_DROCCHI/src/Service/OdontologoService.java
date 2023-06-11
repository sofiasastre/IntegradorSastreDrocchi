package Service;

import DAO.IDao;
import Model.Odontologo;

import java.util.ArrayList;

public class OdontologoService {
    private ArrayList<Odontologo> odontologos = new ArrayList<>();
    public OdontologoService(IDao<Odontologo> miDao){
        this.miDao = miDao;
    }
    private IDao<Odontologo> miDao;
    public IDao<Odontologo> getMiDao(){return miDao;}
    public void setMiDao(IDao<Odontologo> miDao){this.miDao = miDao;}
    public Odontologo guardar(Odontologo odontologo){
        return miDao.guardar(odontologo);
    }
    public ArrayList<Odontologo> buscarTodos() {
        return miDao.listarTodos();
    }
    public void eliminar(int id) {
        miDao.eliminar(id);
    }

    public void modificar(Odontologo odontologo){
        miDao.modificar(odontologo);
    }
}
