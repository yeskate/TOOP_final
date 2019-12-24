package model;

public abstract class Person {
    String name;
    public Permission permission;

    Person(String name, Permission permission) {
        this.name = name;
        this.permission = permission;
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
