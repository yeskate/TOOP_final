package view;

import controller.ProjectRegister;
import controller.UserRegister;
import model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static controller.ProjectRegister.deleteParticipant;
import static controller.UserRegister.getUser;


public class Main {
    //todo пофиксить абсолютные пути
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static Person user;
    public static UserRegister userRegister = new UserRegister();
    public static ProjectRegister projectRegister = new ProjectRegister();

    private static void authorization() throws IOException {
        String username = in.readLine();
        Person tmp = getUser(username);
        if (tmp == null) {
            System.out.println("Неверный логин");
            authorization();
        } else {
            System.out.println("Вход выполнен");
            user = getUser(username);
        }
    }

    private static void workWithProjects() throws IOException {
        int i = -1;
        String input = null;
        try {
            i = Integer.parseInt(in.readLine());
        } catch (Exception e) {
            workWithProjects();
        }
        switch (i) {
            case 0:
                writeCommands();
                break;
            case 1:
                projectRegister.printProjects();
                break;
            case 2:
                userRegister.printUsers();
                break;
            case 3:
                System.out.println("Введите тэг для поиска проектов");
                String tag = in.readLine().trim();
                projectRegister.search(tag);
                break;
            case 4:
                UserRegister.sortByRating();
                break;
            case 5:
                System.out.println("Введите подстроку для поиска пользователя");
                String fragment = in.readLine().trim().toLowerCase();
                UserRegister.search(fragment);
                break;
            case 6:
                if (user.permission != Person.Permission.PARTICIPANT) {
                    input = in.readLine().trim();
                    projectRegister.addParticipant(input);
                } else {
                    System.out.println("У вас нет доступа");
                }
                break;
            case 7:
                if (user.permission != Person.Permission.PARTICIPANT) {
                    input = in.readLine().trim();
                    deleteParticipant(input);
                } else {
                    System.out.println("У вас нет доступа");
                }
                break;
            case 8:
                if (user.permission != Person.Permission.PARTICIPANT) {
                    input = in.readLine().trim();
                    UserRegister.changeRating(input);
                } else {
                    System.out.println("У вас нет доступа");
                }
                break;
            case 9:
                try {
                    if (user.permission == Person.Permission.ADMIN) {
                        System.out.println("Введите данные пользователя");
                        input = in.readLine().trim();
                        userRegister.addPerson(input);
                    } else {
                        System.out.println("У вас нет доступа");
                    }
                } catch (Exception e) {
                    System.out.println("Oops");
                }
                break;
            case 10:
                if (user.permission == Person.Permission.ADMIN) {
                    System.out.println("Введите имя студента, которого надо удалить");
                    input = in.readLine().trim();
                    userRegister.deletePerson(input);
                }
                break;
            case 11:
                try {
                    if (user.permission == Person.Permission.ADMIN) {
                        System.out.println("Введите данные о проекте");
                        input = in.readLine().trim();
                        projectRegister.addProject(input);
                    } else {
                        System.out.println("У вас нет доступа");
                    }
                } catch (Exception e) {

                    System.out.println("Oops");
                }
                break;
            case 12:
                if (user.permission == Person.Permission.ADMIN) {
                    input = in.readLine().trim();
                    projectRegister.deleteProject(input);
                } else {
                    System.out.println("У вас нет доступа");
                }
                break;
            case 13:
                authorization();
                break;
            case 14:
                return;
            default:
                System.out.println("Такой команды не существует");
                break;
        }
        workWithProjects();
    }

    private static void writeCommands(){
        StringBuilder sb = new StringBuilder();
        sb.append("0 - вывод всех команд;").append("\n");
        sb.append("1 - вывод всех проектов (доступно всем);").append("\n");
        sb.append("2 - вывод всех пользователей (доступно всем);").append("\n");
        sb.append("3 - поиск проекта по тэгам (доступно всем);").append("\n");
        sb.append("4 - сортировка участников по рейтингу (доступно всем);").append("\n");
        sb.append("5 - поиск участника по тэгу (доступно всем);").append("\n");
        sb.append("6 - добавление участника в проект (доступно администратору, профессору);").append("\n");
        sb.append("7 - удаление участника из проекта (доступно администратору, профессору);").append("\n");
        sb.append("8 - изменение рейтинга участника (доступно администратору, профессору);").append("\n");
        sb.append("9 - добавление пользователя в систему (доступно администратору);").append("\n");
        sb.append("10 - удаление пользователя из системы (доступно администратору);").append("\n");
        sb.append("11 - добавления проекта в системы (доступно администратору);").append("\n");
        sb.append("12 - удаление проекта из системы (доступно администратору);").append("\n");
        sb.append("13 - сменить пользователя;").append("\n");
        sb.append("14 - завершение работы;").append("\n");
        System.out.println(sb);
    }
    public static void main(String[] args) {
        System.out.println("");
        /**
         * Приветсвие
         */
        //вход в систему
        try {
            //TODO приветсвие и информация о входе
            authorization();
        } catch (IOException e) {
            System.out.println("Не удалось подключить файл с данными о пользователях");
        }
        //работа с проектами
        try {
            writeCommands();
            workWithProjects();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
         * Прощание
         */
        userRegister.rewrite();
        projectRegister.rewrite();
    }
}
