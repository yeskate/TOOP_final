package model;

import java.util.List;

public class Admin extends Person {
    public Admin(String name) {
        super(name,Permission.ADMIN);
    }


    @Override
    public String getInfo() {
        return name;
    }

    void deleteProject(Project project) {
    }

    void addProject(Project project) {

    }
}
