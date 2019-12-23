package model;

import java.util.List;

public class Participant extends Person {
    int course;
    List<String> skills;

    public Participant(String firstName, String secondName, int course, List<String> skills) {
        super(firstName, secondName);
        this.course = course;
        this.skills = skills;
    }


    @Override
    List<Project> getProjects() {
        return null;
    }

}
