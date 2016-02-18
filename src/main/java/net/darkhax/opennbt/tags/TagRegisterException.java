package net.darkhax.opennbt.tags;

/**
 * An exception thrown when an error occurs while registering a tag.
 */
@SuppressWarnings("serial")
public class TagRegisterException extends RuntimeException {
    
    /**
     * Constructs a TagRegisterException without any additional information.
     */
    public TagRegisterException() {
        
        super();
    }
    
    /**
     * Constructs a TagRegisterException with a special message.
     * 
     * @param message: A special message for the exception.
     */
    public TagRegisterException(String message) {
        
        super(message);
    }
    
    /**
     * Constructs a TagRegisterException using a Throwable as a cause.
     * 
     * @param cause: The cause of the exception.
     */
    public TagRegisterException(Throwable cause) {
        
        super(cause);
    }
    
    /**
     * Constructs a TagRegisterException using a Throwable and a special message.
     * 
     * @param message: A special message for the exception.
     * @param cause: The cause of the exception.
     */
    public TagRegisterException(String message, Throwable cause) {
        
        super(message, cause);
    }
}
