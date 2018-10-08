package Model;

import java.util.List;

public class MainDish {
    private String name;
    private int favourite;
    private int id;
    private List<Component> components;
    private String recipe;

    public MainDish(int id) {
        this.id = id;
        this.favourite = -1;
    }

    public MainDish(String name) {
        this.name = name;
        this.favourite = -1;
    }

    public MainDish(int id, String name) {
        this(name);
        this.id = id;
    }

    public MainDish(int id, String name, List<Component> components) {
        this(id, name);
        this.components = components;
    }

    public MainDish(int id, String name, List<Component> components, int favourite) {
        this(id, name, components);
        this.favourite = favourite;
    }

    public MainDish(int id, String name, int favourite) {
        this(id, name);
        this.favourite = favourite;
    }

    public MainDish(int id, String name, int favourite, String recipe) {
        this(id, name, favourite);
        this.recipe = recipe;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    @Override
    public boolean equals(Object obj) {
        MainDish md = (MainDish) obj;
        if(this.name.equals(md.name) && this.favourite == md.favourite) {
            return true;
        } else {
            return false;
        }
    }
}
