package Criteria;

public class AllDishComponents extends GetAll {

    private final static String QUERY = "SELECT * FROM dish_components;";

    public AllDishComponents() {
        super(QUERY);
    }
}
