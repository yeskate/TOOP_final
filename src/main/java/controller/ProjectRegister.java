package controller;

import model.Professor;
import model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectRegister extends BaseRegister {
    List<Project> projects = new ArrayList<>();

    public ProjectRegister(){
        //todo убрать лишние вызовы регистров, надо чтобы вызывалось по 1 разу
        super("/home/kirill/OOP/Project Regist/src/main/resources/List of projects.txt");
        for (String line : lines) {
            String[] param = line.split("/");
            List<String> participants = new ArrayList<>();

            for (String name : param[5].split(",")) {
                participants.add(name);
            }
            List<String> tags = new ArrayList<>();
            for (String tag : param[6].split(",")) {
                tags.add(tag);
            }

            Professor pr = (Professor) UserRegister.getUser(param[4]);
            Project tmp = new Project(param[0], param[1], param[2], param[3], pr, participants, tags);
            projects.add(tmp);
        }
    }

    void addProject(Project project) {
        addString(project.getInfo());
    }

    public void printProjects(){
        System.out.println("Name | Purpose of project | tasks | deadline | supervisor | participants | tags");
        for (Project project: projects){
            StringBuilder sb = new StringBuilder();
            sb.append(project.getInfo().replace("/", "   "));
            System.out.println(sb.toString());
        }
    }

    public void search(String tag){
        for (Project project: projects){
            if (project.tags.contains(tag)){
                System.out.println(project.getInfo().replace("/", "  "));
            }
        }
    }
}
