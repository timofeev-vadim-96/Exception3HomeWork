package presenter;

import dao.LocalDataBase;
import mapper.DataConverter;
import model.User;
import validator.Validator;
import view.View;

import util.Date;

public class Presenter {
    View view;
    LocalDataBase DB;
    DataConverter dataConverter;
    Validator validator;

    public Presenter() {
        this.view = new View();
        this.DB = new LocalDataBase();
        this.dataConverter = new DataConverter();
        this.validator = new Validator();
    }

    public void createUser() {
        String newUser = view.createUser();
        validator.isCorrectUser(newUser);
        String[] toParseUser = newUser.split(" ");
        String surName = toParseUser[0];
        String name = toParseUser[1];
        String patronymic = toParseUser[2];
        Date birthDay = dataConverter.convertDate(toParseUser[3]);
        String phoneNumber = toParseUser[4];
        char gender = toParseUser[5].charAt(0);
        User user = new User(name, surName, patronymic, birthDay, phoneNumber, gender);
        DB.recordUser(user);
    }
}
