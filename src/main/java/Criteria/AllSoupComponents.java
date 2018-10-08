package Criteria;

public class AllSoupComponents extends GetAll {

    private final static String QUERY = "SELECT * FROM soup_components;";

    public AllSoupComponents() {
        super(QUERY);
    }
}
