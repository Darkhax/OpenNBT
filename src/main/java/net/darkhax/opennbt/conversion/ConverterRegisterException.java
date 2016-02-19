package net.darkhax.opennbt.conversion;

/**
 * An exception thrown when an error occurs while registering a converter.
 */
@SuppressWarnings("serial")
public class ConverterRegisterException extends RuntimeException {
    
    /**
     * Constructs a new ConverterRegisterException without any additional info.
     */
    public ConverterRegisterException() {
        
        super();
    }
    
    /**
     * Constructs a new ConverterRegisterException with an additional message.
     * 
     * @param message An additional string message.
     */
    public ConverterRegisterException(String message) {
        
        super(message);
    }
    
    /**
     * Constructs a new ConverterRegisterException with an additional cause.
     * 
     * @param cause An additional cause to add to the exception.
     */
    public ConverterRegisterException(Throwable cause) {
        
        super(cause);
    }
    
    /**
     * Constructs a new ConverterRegisterException with an additional message and cause.
     * 
     * @param message An additional string message.
     * @param cause An additional cause to add to the exception.
     */
    public ConverterRegisterException(String message, Throwable cause) {
        
        super(message, cause);
    }
}
