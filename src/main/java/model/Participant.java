package model;

import java.util.Arrays;
import java.util.List;

public class Participant extends Person {
    int course;
    List<String> skills;

    public Participant(String line) {
        super(line.split("/")[0], Permission.PARTICIPANT);
        String[] params = line.split("/");
        this.course = Integer.parseInt(params[2]);
        String[] skills = params[3].split(",");
        this.skills = Arrays.asList(skills);
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("/");
        sb.append("participant/");
        sb.append(course).append("/");
        for (String skill : skills) {
            sb.append(skill).append(",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

}
