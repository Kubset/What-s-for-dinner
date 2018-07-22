package Model;

public class Component {

    private int id;
    private String name;

    public Component(String name) {
        this.name = name;
    }

    public Component(int id, String name) {
        this.id = id;
        this.name = name;
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
