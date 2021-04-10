package exceptions;

/**
 * No such member found in login stage
 */
public class NoMemberException extends Exception{
    private static final long serialVersionUID = -5117034905277605155L;

    public NoMemberException(String message){
        super(message);
    }
}
