package mapper;
import util.Date;

public class DataConverter {
    public Date convertDate(String date) {
        String[] birthDayArray = date.split("\\.");
        int day = Integer.parseInt(birthDayArray[0]);
        int month = Integer.parseInt(birthDayArray[1]);
        int year = Integer.parseInt(birthDayArray[2]);
        Date result = new Date(day, month, year);
        return result;
    }
}
