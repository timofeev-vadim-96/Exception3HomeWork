package localExceptions;

public class WrongGenderFormatException extends IllegalArgumentException{
    public WrongGenderFormatException(){
        super("Пол может быть лишь \"f\" или \"m\"");
    }
}
