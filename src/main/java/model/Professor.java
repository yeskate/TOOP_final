package model;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Person {
    List<String> projects;

    public Professor(String line) {
        super(line.split("/")[0],Permission.PROFESSOR);
        String[] params = line.split("/");
        String[] projects = params[2].split(";");
        List<String> tmp = new ArrayList<>();
        for (String project : projects) {
            tmp.add(project);
        }
        this.projects = tmp;
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("/");
        sb.append("professor/");
        for (String project : projects) {
            sb.append(project).append(";");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    void deleteProject(Project project) {
        if (projects.contains(project)) {
            projects.remove(project);
        } else {
            System.out.println("Project doesn't found");
        }
    }

    void addProject(Project project) {
        projects.add(project.nameOfProject);
    }
}
