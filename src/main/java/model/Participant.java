package model;

import java.util.Arrays;
import java.util.List;

public class Participant extends Person {
    int course;
    int rating;
    List<String> skills;

    public Participant(String line) {
        super(line.split("/")[0], Permission.PARTICIPANT);
        String[] params = line.split("/");
        this.course = Integer.parseInt(params[2]);
        this.rating = Integer.parseInt(params[3]);
        String[] skills = params[4].split(",");
        this.skills = Arrays.asList(skills);
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("/");
        sb.append("participant/");
        sb.append(course).append("/");
        sb.append(rating).append("/");
        for (String skill : skills) {
            sb.append(skill).append(",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    @Override
    public int getRating(){
        return this.rating;
    }

    public void changeRating(int deltaRating) {
        this.rating += deltaRating;
    }

}
