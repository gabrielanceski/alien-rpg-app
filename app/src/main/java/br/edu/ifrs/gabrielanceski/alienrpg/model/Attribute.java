package br.edu.ifrs.gabrielanceski.alienrpg.model;

public enum Attribute {
    STRENGTH("FORÇA"), AGILITY("AGILIDADE"), EMPATHY("EMPATIA"), WITS("PERSPICÁCIA");

    private final String name;

    Attribute(String name) {
        this.name = name;
    }

    public static Attribute fromPortuguese(String string) {
        for (Attribute attribute : values())
            if (attribute.getName().equalsIgnoreCase(string)) return attribute;
        return STRENGTH;
    }

    public String getName() {
        return name;
    }
}
