package br.edu.ifrs.gabrielanceski.alienrpg.model;

public enum Ability {
    HEAVY_MACHINERY(Attribute.STRENGTH, "Maquinário Pesado"), CLOSE_COMBAT(Attribute.STRENGTH, "Combate Corpo a Corpo"), STAMINA(Attribute.STRENGTH, "Resistência"),
    OBSERVATION(Attribute.WITS, "Observação"), SURVIVAL(Attribute.WITS, "Sobrevivência"), COMTECH(Attribute.WITS, "Tecnologia"),
    MEDICAL_AID(Attribute.EMPATHY, "Ajuda Médica"), MANIPULATION(Attribute.EMPATHY, "Manipulação"), COMMAND(Attribute.EMPATHY, "Comando"),
    PILOTING(Attribute.AGILITY, "Pilotagem"), MOBILITY(Attribute.AGILITY, "Mobilidade"), RANGED_COMBAT(Attribute.AGILITY, "Combate à Distância");

    private final Attribute attribute;
    private final String name;

    Ability(Attribute attribute, String name) {
        this.attribute = attribute;
        this.name = name;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public String getName() {
        return name;
    }

    public static Ability fromPortuguese(String string) {
        for (Ability ability : values())
            if (ability.getName().equalsIgnoreCase(string)) return ability;
        return HEAVY_MACHINERY;
    }

}
