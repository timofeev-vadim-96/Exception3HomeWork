package validator;

import localExceptions.WhiteSpaceQuantityException;
import localExceptions.WrongDataFormatException;
import localExceptions.WrongGenderFormatException;
import localExceptions.WrongPhoneNumberFormatException;

import java.util.Date;

public class Validator {
    private static final String NUMBER_REGEX = "(\\d*\\.)?\\d+";
    private static final String ENG_REGEX = "[a-zA-Z]+";
    private static final String CYRILLIC_REGEX = "[а-яёА-ЯЁ]+";
    private static final String DATA_REGEX = "(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[0-2])[.](19[0-9][0-9]|20[0-9][0-9])";

    public boolean isCorrectUser(String newUser) {
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
            throw new WrongDataFormatException();
        } else if (!userArray[4].matches(NUMBER_REGEX)) {
            throw new WrongPhoneNumberFormatException();
        } else if (!userArray[5].equals("f") && !userArray[5].equals("m")) {
            throw new WrongGenderFormatException();
        }
        return true;
    }
}
