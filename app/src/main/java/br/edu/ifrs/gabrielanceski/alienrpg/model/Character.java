package br.edu.ifrs.gabrielanceski.alienrpg.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Map;

@Entity
public class Character {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private Career career;

    @TypeConverters
    private Map<Attribute, Integer> attributes;

    @TypeConverters
    private Map<Ability, Integer> abilities;

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

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public Map<Attribute, Integer> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Attribute, Integer> attributes) {
        this.attributes = attributes;
    }

    public Map<Ability, Integer> getAbilities() {
        return abilities;
    }

    public void setAbilities(Map<Ability, Integer> abilities) {
        this.abilities = abilities;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", career=" + career +
                ", attributes=" + attributes +
                ", abilities=" + abilities +
                '}';
    }
}
