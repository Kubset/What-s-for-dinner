package DAO;

import Criteria.SqlCriteria;

import java.util.List;

public interface DAO<E> {


    public void add(E Entity);
    public void update(E Entity);
    public void delete(E Entity);
    public List<E> get(SqlCriteria sqlCriteria);

}
