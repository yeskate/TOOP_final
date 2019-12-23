package view;

import controller.UserRegister;
import model.Admin;
import model.Participant;
import model.Person;
import model.Professor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static Person.Permission permission = Person.Permission.UNKNOWN;

    private static void authorization() throws IOException {
        //TODO приветсвие и информация о входе
        String username = in.readLine();
        UserRegister userRegister = new UserRegister("resources/List of users.txt");
        String answer = userRegister.getString(username);
        if (answer == null) {
            System.out.println("Пользователь не найден");
            authorization();
        } else {
            System.out.println("Выполнен вход под" + username);
            switch (answer.split(":")[1]) {
                case "participant":
                    permission = Person.Permission.PARTICIPANT;
                    System.out.println("Вы работаете как студент");
                    break;
                case "professor":
                    permission = Person.Permission.PROFESSOR;
                    System.out.println("Вы работаете как профессор");
                    break;
                case "admin":
                    permission = Person.Permission.ADMIN;
                    System.out.println("Вы работаете как админ");
                    break;
                default:
                    break;
            }
        }
    }

    private static void workWithProjects() {

    }

    public static void main(String[] args){
        System.out.println("");
        /**
         * Приветсвие
         */
        //вход в систему
        try {
            authorization();
        } catch (IOException e) {
            System.out.println("Не удалось подключить файл с данными о пользователях");
        }
        //работа с проектами
        workWithProjects();
        /**
         * Прощание
         */
    }
}
