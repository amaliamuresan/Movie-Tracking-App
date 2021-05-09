package com.client.webClient.beans;

public class CastMember {
    private String name;
    private String profile_path;
    private String character;
    private String known_for_department;
    private int order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
