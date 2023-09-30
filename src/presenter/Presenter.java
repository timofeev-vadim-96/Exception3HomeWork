package presenter;

import dao.LocalDataBase;
import localExceptions.WhiteSpaceQuantityException;
import model.User;
import view.View;

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
    String DATA_REGEX = "(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[0-2])[.](19[0-9][0-9]|20[0-9][0-9])";

    public void createUser() {
        String newUser = view.createUser();
        isCorrectUser(newUser);
        String[] toParseUser = newUser.split(" ");
        String surName = toParseUser[0];
        String name = toParseUser[1];
        String patronymic = toParseUser[2];
        util.Date birthDay = convertDate(toParseUser[3]);
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
        } else if (!userArray[3].matches(DATA_REGEX)) {
            throw new IllegalArgumentException("Дата рождения не соответствует формату \"dd.mm.yyyy!\"");
        } else if (!userArray[4].matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException("Номер телефона должен быть целым беззнаковым числом");
        } else if (!userArray[5].equals("f") && !userArray[5].equals("m")) {
            throw new IllegalArgumentException("Пол может быть лишь \"f\" или \"m\"");
        }
        return true;
    }

    private util.Date convertDate(String date) {
        String[] birthDayArray = date.split("\\.");
        int day = Integer.parseInt(birthDayArray[0]);
        int month = Integer.parseInt(birthDayArray[1]);
        int year = Integer.parseInt(birthDayArray[2]);
        util.Date result = new util.Date(day, month, year);
        return result;
    }
}
