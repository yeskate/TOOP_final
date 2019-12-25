package model;

import java.util.ArrayList;

public class Professor extends Person {
    public ArrayList<String> projects;
    public final int RATING = Integer.MAX_VALUE;

    public Professor(String line) {
        super(line.split("/")[0], Permission.PROFESSOR);
        String[] params = line.split("/");
        if (params.length == 2) {
            this.projects = new ArrayList<>();
        } else {
            String[] projects = params[2].split(";");
            ArrayList<String> tmp = new ArrayList<>();
            for (String pr : projects) {
                tmp.add(pr);
            }
            this.projects = tmp;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getRating() {
        return this.RATING;
    }

    public void addProject(String project) {
        this.projects.add(project);
    }

    public void deleteProject(String project) {
        this.projects.remove(project);
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
}
