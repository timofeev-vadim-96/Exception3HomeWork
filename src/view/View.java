package view;

import java.util.Scanner;

public class View {

    public String createUser() {
        String newUser = prompt("Введите фамилию, имя, отчество - строки, дату рождения - строка формата dd.mm.yyyy, "
                + "номер телефона - целое беззнаковое число без" + " форматирования, \nпол - символ латиницей f или m, "
                + "через пробел:");
        return newUser;
    }

    private String prompt(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }
}
