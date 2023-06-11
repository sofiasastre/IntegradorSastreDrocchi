package Service;

import DAO.IDao;
import Model.Paciente;

import java.util.ArrayList;

public class PacienteService {
    private ArrayList<Paciente> pacientes = new ArrayList<>();
    public PacienteService(IDao<Paciente> miDao){
        this.miDao = miDao;
    }
    private IDao<Paciente> miDao;
    public IDao<Paciente> getMiDao(){return miDao;}
    public void setMiDao(IDao<Paciente> miDao){this.miDao = miDao;}
    public Paciente guardar(Paciente odontologo){
        return miDao.guardar(odontologo);
    }
    public ArrayList<Paciente> buscarTodos() {
        return miDao.listarTodos();
    }
    public void eliminar(int id) {
        miDao.eliminar(id);
    }

    public void modificar(Paciente paciente){
        miDao.modificar(paciente);
    }
}
