package Model;

public class Component {

    private int id;
    private String name;
    private Integer count;
    private Unit unit;

    public Component(int id) {
        this.id = id;
    }

    public Component(String name) {
        this.name = name;
    }

    public Component(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Component(int id, String name, int count, Unit unit) {
        this(id, name);
        this.count = count;
        this.unit = unit;
    }

    public Component(String name, int count, Unit unit) {
        this(name);
        this.count = count;
        this.unit = unit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
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

    @Override
    public boolean equals(Object obj) {
        Component c = (Component) obj;
        if(this.name.equals(c.name)) {
            return true;
        } else {
            return false;
        }
    }
}
