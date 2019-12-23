package model;

import java.util.List;

public abstract class Project {
    String nameOfProject;
    String purposeOfProject;
    String tasks;
    String deadline;
    String supervisor;
    List<Participant> participants;
    List<String> tags;

    public Project(String name, String purpose, String tasks, String deadline, String supervisor) {
        this.nameOfProject = name;
        this.purposeOfProject = purpose;
        this.tasks = tasks;
        this.deadline = deadline;
        this.supervisor = supervisor;
    }

    List<Participant> printParticipants() {
        if (participants.isEmpty()) {
            throw new NullPointerException("Users doesn't pointed");
        }
        return participants;
    }

    void addParticipants(Participant participant) {
        participants.add(participant);
    }

    void deleteParticipants(Participant participant) {
        if (!participants.remove(participant)) {
            System.out.println("Student doesn't found");
        }
    }

    int getCountOfParticipants(){
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
