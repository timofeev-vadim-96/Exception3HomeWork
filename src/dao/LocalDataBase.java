package dao;

import model.User;

import java.io.FileWriter;
import java.io.IOException;

public class LocalDataBase {

    public void recordUser(User user) {
        String fileName = user.getName() + ".txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(user.toString());
        } catch (IOException | RuntimeException e) {
            System.out.println("caught exception: " + e.getClass().getSimpleName());
        }
    }
}
