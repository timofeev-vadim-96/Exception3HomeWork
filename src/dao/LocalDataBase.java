package dao;

import model.User;

import java.io.FileWriter;
import java.io.IOException;

public class LocalDataBase {

    public void recordUser(User user) {
        String fileName = user.getSurname() + ".txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
