package Model;

public class SoupComponent {
    private int soupId;
    private int componentId;
    private int id;

    public SoupComponent(int id, int componentId, int soupId) {
        this.id = id;
        this.componentId = componentId;
        this.soupId = soupId;
    }

    public int getSoupId() {
        return soupId;
    }

    public void setSoupId(int soupId) {
        soupId = soupId;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        componentId = componentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
