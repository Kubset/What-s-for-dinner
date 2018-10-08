package services;

import java.util.List;

public interface Service<E> {

    public E get(int id);

    public List<E> getAll();

    public void edit(E entity);

    public void create(E entity);

    public void delete(E entity);

    public void deleteAll();
}
