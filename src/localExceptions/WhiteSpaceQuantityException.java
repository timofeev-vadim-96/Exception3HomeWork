//Ошибка количества пробелов
package localExceptions;

import java.util.Date;

public class WhiteSpaceQuantityException extends IllegalArgumentException{
    private Date startDate;
    public WhiteSpaceQuantityException(String message, Date startDate){
        super(message); //во всех исключениях есть конструктор message + exception
        this.startDate = startDate;
    }
}
