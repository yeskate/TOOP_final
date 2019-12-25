package model;

public class Admin extends Person {
    public final int RATING = Integer.MAX_VALUE;

    public Admin(String name) {
        super(name, Permission.ADMIN);
    }

    @Override
    public int getRating() {
        return this.RATING;
    }

    @Override
    public String getInfo() {
        return name;
    }

}
