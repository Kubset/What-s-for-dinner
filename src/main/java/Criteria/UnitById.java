package Criteria;

public class UnitById extends GetById {

    private static final String QUERY = "SELECT * FROM units WHERE unit_id=?;";

    public UnitById(int id) {
        super(QUERY, id);
    }
}
