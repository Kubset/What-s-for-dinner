package Model;

public class DishComponent {
    private int dishId;
    private int componentId;
    private int id;

    public DishComponent(int id, int componentId, int dishId) {
        this.id = id;
        this.componentId = componentId;
        this.dishId = dishId;
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
