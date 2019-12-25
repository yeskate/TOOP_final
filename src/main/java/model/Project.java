package model;

import java.util.ArrayList;

public class Project {
    public String nameOfProject;
    public String purposeOfProject;
    public String tasks;
    public String deadline;
    public Professor supervisor;
    public ArrayList<String> participants;
    public ArrayList<String> tags;

    public Project(String name, String purpose, String tasks, String deadline, Professor supervisor,
                   ArrayList<String> participants, ArrayList<String> tags) {
        this.nameOfProject = name;
        this.purposeOfProject = purpose;
        this.tasks = tasks;
        this.deadline = deadline;
        this.supervisor = supervisor;
        this.tags = tags;
        this.participants = participants;
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(nameOfProject).append("/");
        sb.append(purposeOfProject).append("/");
        sb.append(tasks).append("/");
        sb.append(deadline).append("/");
        sb.append(supervisor.name).append("/");
        for (String p : participants) {
            sb.append(p).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("/");
        for (String t : tags) {
            sb.append(t).append(",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public void addParticipants(String participant) {
        participants.add(participant);
    }

    public void deleteParticipants(String participant) {
        if (!participants.remove(participant)) {
            System.out.println("Участник не найден");
        }
    }
}
