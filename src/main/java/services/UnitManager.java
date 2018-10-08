package services;

import Criteria.AllUnits;
import Criteria.UnitById;
import DAO.UnitDAO;
import Model.Unit;

import java.util.List;

public class UnitManager implements Service<Unit> {

    private UnitDAO unitDAO;

    public UnitManager() {
        this.unitDAO = new UnitDAO();
    }

    public Unit get(int id) {
        List<Unit> units = unitDAO.get(new UnitById(id));
        if(units.size() > 0) {
            return units.get(0);
        } else {
            return null;
        }
    }

    public List<Unit> getAll() {
        return unitDAO.get(new AllUnits());
    }

    public void edit(Unit unit) {
        unitDAO.update(unit);

    }

    public void create(Unit unit) {
        unitDAO.add(unit);

    }

    public void delete(Unit unit) {
        unitDAO.delete(unit);
    }

    public void deleteAll() {
        List<Unit> units = unitDAO.get(new AllUnits());
        for(Unit unit : units) {
            unitDAO.delete(unit);
        }
    }
}
