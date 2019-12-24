package view;

import controller.ProjectRegister;
import controller.UserRegister;
import model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static controller.UserRegister.getUser;


public class Main {
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
                projectRegister.printProjects();
                break;
            case 1:
                userRegister.printUsers();
                break;
            case 2:
                System.out.println("Введите тэг для поиска проектов");
                String tag = in.readLine().trim();
                projectRegister.search(tag);
                break;
            case 3:
                System.out.println("Введите подстроку для поиска пользователя");
                String fragment = in.readLine().trim().toLowerCase();
                UserRegister.search(fragment);
                break;
            case 4:
                try {
                    if (user.permission == Person.Permission.PROFESSOR || user.permission == Person.Permission.ADMIN) {
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
            case 5:
                try {
                    if (user.permission == Person.Permission.PROFESSOR || user.permission == Person.Permission.ADMIN) {
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
            case 6:
                input = in.readLine().trim();
                projectRegister.deleteProject(input);
                break;
            case 7:
                input = in.readLine().trim();
                projectRegister.addParticipant(input);
                break;
            case 8:
                input = in.readLine().trim();
                projectRegister.deleteParticipant(input);
                break;
            case 9:
                authorization();
                break;
            case 10:
                return;
            default:
                System.out.println("Такой команды не существует");
                workWithProjects();
                break;
        }
        workWithProjects();
        //todo сортировка проектов, людей
        //todo добавление проекта, человека
        //todo оценивание
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
