package net.darkhax.opennbt.tags;

/**
 * An exception that is thrown when there are no available Tag IDs available.
 */
@SuppressWarnings("serial")
public class TagIdException extends RuntimeException {
    
    public TagIdException() {
        
        super("There are no positive tag IDs left. How in the world did that happen!");
    }
}
