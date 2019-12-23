package model;

import java.util.List;

public class Admin extends Person {
    public Admin(String firstName, String secondName) {
        super(firstName, secondName);
    }

    @Override
    List<Project> getProjects() {
        return null;
    }

    void deleteProject(Project project) {
    }

    void addProject(Project project) {

    }
}
