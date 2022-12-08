package br.edu.ifrs.gabrielanceski.alienrpg.model;

public enum Career {
    COLONIAL_MARINE(Attribute.STRENGTH, "Fuzileiro Colonial"),
    COLONIAL_MARSHAL(Attribute.WITS, "Xerife Colonial"),
    COMPANY_AGENT(Attribute.WITS, "Agente da Companhia"),
    KID(Attribute.AGILITY, "Criança"),
    MEDIC(Attribute.EMPATHY, "Médico"),
    OFFICER(Attribute.EMPATHY, "Oficial"),
    PILOT(Attribute.AGILITY, "Piloto"),
    ROUGHNECK(Attribute.STRENGTH, "Operário"),
    SCIENTIST(Attribute.WITS, "Cientista");

    private final Attribute mainAttribute;
    private final String name;

    Career(Attribute mainAttribute, String name) {
        this.mainAttribute = mainAttribute;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Attribute getMainAttribute() {
        return mainAttribute;
    }
}