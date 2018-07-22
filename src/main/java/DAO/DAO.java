package DAO;

import java.util.List;

public interface DAO<E> {


    public void add(E Entity);
    public void update(E Entity);
    public void delete(E Entity);
    public E get(int ID);
    public List<E> get();

}
