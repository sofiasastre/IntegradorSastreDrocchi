package DAO;

import Model.Odontologo;

import java.util.ArrayList;

public interface IDao <T>{
    T guardar(T t);
    ArrayList<T> listarTodos();
    void modificar(T t);
    void eliminar(int id);
}
