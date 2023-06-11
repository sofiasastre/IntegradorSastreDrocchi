package DAO.impl;

import DAO.IDao;
import Model.Odontologo;

import java.util.ArrayList;

public class OdontologoArray implements IDao<Odontologo> {
    private ArrayList<Odontologo> odontologos;
    private int id = 0;

    public OdontologoArray() {
        this.odontologos = new ArrayList<>();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        this.id++;
        odontologo.setId(id);
        odontologos.add(odontologo);
        System.out.println("Odontólogo guardado en colección: " + odontologo);
        return odontologo;
    }

    @Override
    public ArrayList listarTodos() {
        return odontologos;
    }

    @Override
    public void modificar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(int id) {

    }
}
