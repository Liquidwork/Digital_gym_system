package exceptions;

/**
 * Member existed in user file
 */
public class MemberExistedException extends Exception{
    private static final long serialVersionUID = -5403877162854027266L;

    public MemberExistedException(String message){
        super(message);
    }
}
