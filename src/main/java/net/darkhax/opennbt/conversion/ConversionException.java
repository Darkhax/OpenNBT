package net.darkhax.opennbt.conversion;

/**
 * An exception thrown when an error occurs while converting something.
 */
@SuppressWarnings("serial")
public class ConversionException extends RuntimeException {
    
    /**
     * Constructs a new ConversionException with no additional information.
     */
    public ConversionException() {
        
        super();
    }
    
    /**
     * Constructs a new ConversionException with an additional message.
     * 
     * @param message A string message to add to the exception.
     */
    public ConversionException(String message) {
        
        super(message);
    }
    
    /**
     * Constructs a new ConversionException with an additional cause.
     * 
     * @param cause The additional cause to add to the exception.
     */
    public ConversionException(Throwable cause) {
        
        super(cause);
    }
    
    /**
     * Constructs a new ConversionException with an additional message and cause.
     * 
     * @param message A string message to add to the exception.
     * @param cause The additional cause to add to the exception.
     */
    public ConversionException(String message, Throwable cause) {
        
        super(message, cause);
    }
}
