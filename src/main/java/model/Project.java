package model;

import controller.UserRegister;

import java.util.ArrayList;
import java.util.List;

public class Project {
    public String nameOfProject;
    public String purposeOfProject;
    public String tasks;
    public String deadline;
    public Professor supervisor;
    public List<String> participants;
    public List<String> tags;

    public Project(String name, String purpose, String tasks, String deadline, Professor supervisor,
                   List<String> participants, List<String> tags) {
        this.nameOfProject = name;
        this.purposeOfProject = purpose;
        this.tasks = tasks;
        this.deadline = deadline;
        this.supervisor = supervisor;
        this.tags = tags;
        this.participants = participants;
    }


    List<String> printParticipants() {
        if (participants.isEmpty()) {
            throw new NullPointerException("Users doesn't pointed");
        }
        return participants;
    }

    public String getName() {
        return this.supervisor.name;
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
        sb.deleteCharAt(sb.length()-1);
        sb.append("/");
        for (String t : tags) {
            sb.append(t).append(",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    void addParticipants(String participant) {
        participants.add(participant);
    }

    void deleteParticipants(Participant participant) {
        if (!participants.remove(participant)) {
            System.out.println("Student doesn't found");
        }
    }

    int getCountOfParticipants() {
        return participants.size();
    }

    List<String> getTags() {
        if (tags.isEmpty()) {
            throw new NullPointerException("Tags doesn't pointed");
        }
        return tags;
    }

    void addTag(String tag) {
        tags.add(tag);
    }

    void deleteParticipants(String tag) {
        if (!tags.remove(tag)) {
            System.out.println("Tag doesn't found");
        }
    }


}
