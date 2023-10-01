package localExceptions;

public class WrongPhoneNumberFormatException extends IllegalArgumentException{
    public WrongPhoneNumberFormatException(){
        super("Номер телефона должен быть целым беззнаковым числом!");
    }
}
