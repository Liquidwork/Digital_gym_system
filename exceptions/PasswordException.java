package exceptions;

/**
 * Wrong password in login phase
 */
public class PasswordException extends Exception{
    private static final long serialVersionUID = -7155756365679768314L;

    public PasswordException(String message){
        super(message);
    }
}
