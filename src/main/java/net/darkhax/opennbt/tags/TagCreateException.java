package net.darkhax.opennbt.tags;

/**
 * An exception that is thrown when an error occurs during tag instantiated.
 */
@SuppressWarnings("serial")
public class TagCreateException extends Exception {
    
    /**
     * Constructs a new TagCreateException without any additional information.
     */
    public TagCreateException() {
        
        super();
    }
    
    /**
     * Constructs a new TagCreateException with a special message.
     * 
     * @param message: A special message for the exception.
     */
    public TagCreateException(String message) {
        
        super(message);
    }
    
    /**
     * Constructs a new TagCreateException using a Throwable as a cause.
     * 
     * @param cause: The cause of the exception.
     */
    public TagCreateException(Throwable cause) {
        
        super(cause);
    }
    
    /**
     * Constructs a new TagCreateException using a Throwable cause and a special message.
     * 
     * @param message: A special message for the exception.
     * @param cause: The cause of the exception.
     */
    public TagCreateException(String message, Throwable cause) {
        
        super(message, cause);
    }
}