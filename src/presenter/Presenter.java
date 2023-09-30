package presenter;

import dao.LocalDataBase;
import localExceptions.WhiteSpaceQuantityException;
import model.User;
import view.View;

import java.time.LocalDateTime;
import java.util.Date;

public class Presenter {
    View view;
    LocalDataBase DB;

    public Presenter() {
        this.view = new View();
        this.DB = new LocalDataBase();
    }

    private static final String NUMBER_REGEX = "(\\d*\\.)?\\d+";
    private static final String ENG_REGEX = "[a-zA-Z]+";
    private static final String CYRILLIC_REGEX = "[а-яёА-ЯЁ]+";

    public void createUser() {
        String newUser = view.createUser();
        isCorrectUser(newUser);
        String[] toParseUser = newUser.split(" ");
        String name = toParseUser[0];
        String surName = toParseUser[1];
        String patronymic = toParseUser[2];
        Date birthDay = convertDate(toParseUser[3]);
        String phoneNumber = toParseUser[4];
        char gender = toParseUser[5].charAt(0);
        User user = new User(name, surName, patronymic, birthDay, phoneNumber, gender);
        recordUser(user);
    }

    private void recordUser(User user) {
        DB.recordUser(user);
    }

    private boolean isCorrectUser(String newUser) {
        int quantityOfElements = 6;
        String[] userArray = newUser.split(" ");
        if (userArray.length != quantityOfElements) {
            throw new WhiteSpaceQuantityException("Вы ввели неверное количество аргументов", new Date());
        } else if (!(userArray[0].matches(ENG_REGEX) || userArray[0].matches(CYRILLIC_REGEX))) {
            throw new IllegalArgumentException("Фамилия не может содержать символы, кроме букв!");
        } else if (!(userArray[1].matches(ENG_REGEX) || userArray[1].matches(CYRILLIC_REGEX))) {
            throw new IllegalArgumentException("Имя не может содержать символы, кроме букв!");
        } else if (!(userArray[2].matches(ENG_REGEX) || userArray[2].matches(CYRILLIC_REGEX))) {
            throw new IllegalArgumentException("Отчество не может содержать символы, кроме букв!");
        } else if (!(isDate(userArray[3]))) {
            throw new IllegalArgumentException("Дата рождения не соответствует формату \"dd.mm.yyyy!\"");
        } else if (!userArray[4].matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException("Номер телефона должен быть целым беззнаковым числом");
        } else if (!userArray[5].equals("f") && !userArray[5].equals("m")) {
            throw new IllegalArgumentException("Пол может быть лишь \"f\" или \"m\"");
        }
        return true;
    }

    private boolean isDate(String birthDay) {
        String[] birthDayArray = birthDay.split("\\.");
        if (birthDayArray.length != 3) return false;
        else if (!(birthDayArray[0].matches(NUMBER_REGEX) && Integer.parseInt(birthDayArray[0]) <= 31)) {
            return false;
        } else if (!(birthDayArray[1].matches(NUMBER_REGEX) && Integer.parseInt(birthDayArray[1]) <= 12)) {
            return false;
        } else if (!(birthDayArray[2].matches(NUMBER_REGEX) && Integer.parseInt(birthDayArray[2]) <= LocalDateTime.now().getYear())) { //г.р. не может быть
//            больше, чем текущий
            return false;
        }
        return true;
    }

    private Date convertDate(String date) {
        String[] birthDayArray = date.split("\\.");
        int day = Integer.parseInt(birthDayArray[0]);
        int month = Integer.parseInt(birthDayArray[1]);
        int year = Integer.parseInt(birthDayArray[2]) - 1900;
        Date result = new Date(year, month, day);
        return result;
    }
}
