package controller;

import model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserRegister extends BaseRegister {
    static List<Person> users = new ArrayList<>();

    public UserRegister() {
        super("/home/kirill/OOP/Project Regist/src/main/resources/List of users.txt");
        for (String line : getAllString()) {
            String[] params = line.split("/");
            Person user;
            switch (params[1]) {
                case "participant":
                    user = new Participant(line);
                    break;
                case "professor":
                    user = new Professor(line);
                    break;
                case "admin":
                    user = new Admin(params[0]);
                    break;
                default:
                    user = null;
                    break;
            }
            if (user != null) {
                users.add(user);
            } else {
                System.out.println("User doesn't found");
            }
        }
    }

    public static Person getUser(String name) {
        for (Person usr : users) {
            if (usr.getName().equals(name)) {
                return usr;
            }
        }
        return null;
    }

    public void printUsers() {
        System.out.println("name | status | course/projects | rating/projects | skills/projects");
        for (Person user : users) {
            StringBuilder sb = new StringBuilder();
            sb.append(user.getInfo().replace("/", "   "));
            System.out.println(sb.toString());
        }
    }

    public void addPerson(String person) {
        String[] params = person.split("/");
        Person user;
        switch (params[1]) {
            case "participant":
                user = new Participant(person);
                break;
            case "professor":
                user = new Professor(person);
                break;
            case "admin":
                user = new Admin(params[0]);
                break;
            default:
                user = null;
                break;
        }
        users.add(user);
    }

    public void deletePerson(String person) {
        Person usr = null;
        for (Person user : users) {
            if (user.getName().equals(person)) {
                switch (user.permission) {
                    case ADMIN:
                        System.out.println("Нельзя удалить админа");
                        return;
                    case PROFESSOR:
                        System.out.println("Нельзя удалить профессора");
                        return;
                    case PARTICIPANT:
                        for (Project project : ProjectRegister.projects) {
                            if (project.participants.contains(person)) {
                                project.deleteParticipants(person);
                            }
                        }
                        System.out.println("Пользователь удалён");
                        usr = user;
                        break;
                }
                break;
            }
        }
        if (usr == null) {
            System.out.println("Пользователь не найден");
        } else {
            users.remove(usr);
        }
    }

    public static void search(String fragment) {
        for (Person person : users) {
            if (person.getInfo().toLowerCase().contains(fragment)) {
                System.out.println(person.getInfo().replace("/", "  "));
            }
        }
    }

    public static void sortByRating() {
        users.sort(Comparator.comparingInt(Person::getRating).reversed());
        System.out.println("Отстортировали");
    }

    public static void changeRating(String input) {
        String[] params = input.split(" ");
        for (Person person : users) {
            if (person.getName().equals(params[0])) {
                if (person.permission == Person.Permission.PARTICIPANT) {
                    ((Participant) person).changeRating(Integer.parseInt(params[1]));
                    System.out.println("Рейтинг изменен");
                    return;
                } else {
                    System.out.println("У профессоров и админов нет рейтинга");
                }
            }
        }
    }

    @Override
    public void rewrite() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()))) {
            try {
                for (Person person : users) {
                    writer.write(person.getInfo() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
