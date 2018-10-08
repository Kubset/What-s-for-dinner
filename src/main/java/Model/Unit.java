package Model;

public class Unit {
    private String name;
    private Integer id;

    public Unit(Integer id) {
        this.id = id;
    }

    public Unit(String name) {
        this.name = name;
    }

    public Unit(String name, int id) {
        this(name);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
