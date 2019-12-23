package model;

import java.util.List;

public abstract class Person {
    String firstName;
    String secondName;

    Person(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    abstract List<Project> getProjects();

    public static enum  Permission{
        PARTICIPANT,
        PROFESSOR,
        ADMIN,
        UNKNOWN
    }
}
