package localExceptions;

public class WrongDataFormatException extends IllegalArgumentException{
    public WrongDataFormatException(){
        super("Дата рождения не соответствует формату \"dd.mm.yyyy!\"");
    }
}
