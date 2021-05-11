package exceptions;

/**
 * This exception is thrown if operating time has excessed.
 */
public class OutOfTimeException extends RuntimeException{
    
    public OutOfTimeException(String message){
        super(message);
    }
}
