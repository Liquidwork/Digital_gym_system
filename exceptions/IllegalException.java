package exceptions;

/**
 * Input is illegal
 */
public class IllegalException extends Exception{
    private static final long serialVersionUID = -732927241524770292L;

    public IllegalException(String message){
        super(message);
    }
}
