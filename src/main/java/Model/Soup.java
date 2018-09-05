package Model;

import java.util.List;

public class Soup {
    private String name;
    private int favourite;
    private int id;
    private List<Component> components;

    public Soup(String name) {
        this.name = name;
        this.favourite = -1;
    }

    public Soup(int id, String name) {
        this(name);
        this.id = id;
    }

    public Soup(int id, String name, List<Component> components) {
        this(id, name);
        this.components = components;
    }

    public Soup(int id, String name, List<Component> components, int favourite) {
        this(id, name, components);
        this.favourite = favourite;
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
        Soup s = (Soup) obj;
        if(this.name.equals(s.name) && this.favourite == s.favourite) {
            return true;
        } else {
            return false;
        }
    }
}
