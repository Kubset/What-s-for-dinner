package Model;

public class DishComponent {
    private int dishId;
    private int componentId;
    private int id;

    public DishComponent(int dishId, int componentId) {
        this.dishId = dishId;
        this.componentId = componentId;
    }

    public DishComponent(int id, int componentId, int dishId) {
        this(dishId,componentId);
        this.id = id;
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
