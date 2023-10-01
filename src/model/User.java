package model;

import util.Date;

public class User {
    private String name;
    private String surname;
    private String patronymic;
    private Date birthDay;
    private String phoneNumber;
    private char gender;

    public User(String name, String surname, String patronymic, Date birthDay, String phoneNumber, char gender) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("<%s><%s><%s><%s><%s><%s>\n", this.surname, this.name, this.patronymic, this.phoneNumber
                , this.birthDay, this.gender);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public char getGender() {
        return gender;
    }
}
