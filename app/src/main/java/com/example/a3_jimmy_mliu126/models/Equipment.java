package com.example.a3_jimmy_mliu126.models;

import java.util.ArrayList;
import java.util.List;

public class Equipment {
    private String name;
    private int attack;
    private int defense;
    private String description;
    private String image;
    private int id;
    private String category;
    private List<String> common_locations;

    public Equipment(String name, int attack, int defense, String description, String image, int id, String category, ArrayList<String> common_locations) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.description = description;
        this.image = image;
        this.id = id;
        this.category = category;
        this.common_locations = common_locations;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getCommon_locations() {
        return common_locations;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defense=" + defense +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", id=" + id +
                ", category='" + category + '\'' +
                ", common_locations=" + common_locations +
                '}';
    }
}
