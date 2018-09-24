package Model;

public class SoupComponent {
    private int soupId;
    private int componentId;
    private int id;
    private int count;
    private Unit unit;

    public SoupComponent(int componentId, int soupId) {
        this.soupId = soupId;
        this.componentId = componentId;
    }

    public SoupComponent(int id, int componentId, int soupId) {
        this(componentId,soupId);
        this.id = id;
    }

    public SoupComponent(int soupId, int componentId, int count, Unit unit) {
        this.soupId = soupId;
        this.componentId = componentId;
        this.count = count;
        this.unit = unit;
    }

    public SoupComponent(int id, int soupId, int componentId, int count, Unit unit) {
        this(soupId, componentId, count, unit);
        this.id = id;
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
