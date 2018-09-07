package Model;

public class SoupComponent {
    private int soupId;
    private int componentId;
    private int id;

    public SoupComponent(int componentId, int soupId) {
        this.soupId = soupId;
        this.componentId = componentId;
    }

    public SoupComponent(int id, int componentId, int soupId) {
        this(componentId,soupId);
        this.id = id;
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
