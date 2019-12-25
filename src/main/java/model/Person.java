package model;

public abstract class Person {
    String name;
    int rating;
    public Permission permission;

    Person(String name, Permission permission) {
        this.name = name;
        this.permission = permission;
    }

    public int getRating() {
        return this.rating;
    }

    public String getName() {
        return name;
    }

    public abstract String getInfo();

    public enum Permission {
        PARTICIPANT,
        PROFESSOR,
        ADMIN
    }
}
