package view;

import controller.ProjectRegister;
import controller.UserRegister;
import model.Admin;
import model.Participant;
import model.Person;
import model.Professor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static controller.UserRegister.getUser;
import static controller.UserRegister.printUsers;


public class Main {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static Person user;
    public static UserRegister userRegister = new UserRegister();
    public static ProjectRegister projectRegister = new ProjectRegister();

    private static void authorization() throws IOException {
        String username = in.readLine();
        Person tmp = getUser(username);
        if (tmp==null){
            System.out.println("Неверный логин");
            authorization();
        } else {
            System.out.println("Вход выполнен");
            user = getUser(username);
        }
    }

    private static void workWithProjects() throws IOException {
        int i = Integer.parseInt(in.readLine());
        switch (i){
            case 0:
                projectRegister.printProjects();
                break;
            case 1:
                printUsers();
                break;
            case 2:
                System.out.println("Введите тэг для поиска");
                String tag = in.readLine().trim();
                projectRegister.search(tag);
                break;
            case 3:
                if (user.permission == Person.Permission.PROFESSOR){
                    System.out.println("Введите данные пользователя");
                    String input = in.readLine().trim();
                    userRegister.addPerson(input);
                } else {
                    System.out.println("У вас нет доступа");
                }
                break;
            case 8:
                authorization();
                break;
            case 9:
                return;

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
    }
}
