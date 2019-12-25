package controller;

import model.Professor;
import model.Project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRegister extends BaseRegister {
    static List<Project> projects = new ArrayList<>();

    public ProjectRegister() {
        super("src/main/resources/List of projects.txt");
        for (String line : lines) {
            String[] param = line.split("/");
            ArrayList<String> participants = new ArrayList<>();
            for (String name : param[5].split(",")) {
                participants.add(name);
            }
            ArrayList<String> tags = new ArrayList<>();
            for (String tag : param[6].split(",")) {
                tags.add(tag);
            }
            Professor pr = (Professor) UserRegister.getUser(param[4]);
            Project tmp = new Project(param[0], param[1], param[2], param[3], pr, participants, tags);
            projects.add(tmp);
        }
    }

    public void addProject(String project) {
        String[] params = project.split("/");
        if (params.length == 0){
            System.out.println("Неверные входные данные\n");
            return;
        }
        Professor professor = (Professor) UserRegister.getUser(params[4]);
        professor.addProject(params[0]);
        ArrayList<String> participants = new ArrayList<>();
        for (String name : params[5].split(",")) {
            participants.add(name);
        }
        ArrayList<String> tags = new ArrayList<>();
        for (String tag : params[6].split(",")) {
            tags.add(tag);
        }
        projects.add(new Project(params[0], params[1], params[2], params[3], professor,
                participants, tags));
        System.out.println("Проект добавлен в систему\n");
    }

    public void deleteProject(String projectName) {
        Project tmp = null;
        for (Project project : projects) {
            if (projectName.equals(project.nameOfProject)) {
                project.supervisor.deleteProject(project.nameOfProject);
                tmp = project;
            }
        }
        if (tmp == null) {
            System.out.println("Данного проекта не существует\n");
        } else {
            projects.remove(tmp);
        }
        System.out.println("Проект удален из системы\n");
    }

    public void printProjects() {
        System.out.println("Список проектов\n");
        for (Project project : projects) {
            StringBuilder sb = new StringBuilder();
            sb.append(project.getInfo().replace("/", "\n"));
            System.out.println(sb.toString());
            System.out.println("\n");
        }
    }

    public void search(String tag) {
        for (Project project : projects) {
            if (project.tags.contains(tag)) {
                System.out.println("Подходящие проекты\n");
                System.out.println(project.getInfo().replace("/", "\n"));
                System.out.println("\n");
            }
        }
    }

    public void addParticipant(String input) {
        String[] params = input.split("/");
        if (params.length == 0){
            System.out.println("Неверные входные данные\n");
            return;
        }
        for (Project project : projects) {
            if (project.nameOfProject.equals(params[0])) {
                project.addParticipants(params[1]);
                System.out.println("Участник добавлен в проект\n");
                return;
            }
        }
    }

    public static void deleteParticipant(String input) {
        String[] params = input.split("/");
        for (Project project : projects) {
            if (project.nameOfProject.equals(params[0])) {
                project.deleteParticipants(params[1]);
                System.out.println("Участник удален из проекта\n");
                return;
            }
        }
    }

    @Override
    public void rewrite() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()))) {
            try {
                for (Project project : projects) {
                    writer.write(project.getInfo() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
