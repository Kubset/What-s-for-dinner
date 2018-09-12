package Model;

public class DishComponent {
    private int dishId;
    private int componentId;
    private int id;
    private int count;
    private Unit unit;

    public DishComponent(int dishId, int componentId) {
        this.dishId = dishId;
        this.componentId = componentId;
    }

    public DishComponent(int id, int componentId, int dishId) {
        this(dishId,componentId);
        this.id = id;
    }

    public DishComponent(int dishId, int componentId, int count, Unit unit) {
        this.dishId = dishId;
        this.componentId = componentId;
        this.count = count;
        this.unit = unit;
    }

    public DishComponent(int dishId, int componentId, int id, int count, Unit unit) {
        this(dishId, componentId, count, unit);
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

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
