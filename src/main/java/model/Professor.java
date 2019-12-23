package model;

import java.util.List;

public class Professor extends Person {


    public Professor(String firstName, String secondName) {
        super(firstName, secondName);
    }

    @Override
    List<Project> getProjects() {
        return null;
    }

    void deleteProject(Project project){

    }

    void addProject(Project project) {

    }
}
