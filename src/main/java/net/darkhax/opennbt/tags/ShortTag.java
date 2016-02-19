package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A Tag which holds a short value.
 */
public class ShortTag extends Tag {
    
    /**
     * The short value held by this tag.
     */
    private short value;
    
    /**
     * Constructs a new ShortTag with no value.
     * 
     * @param name The name of the tag.
     */
    public ShortTag(String name) {
        
        this(name, (short) 0);
    }
    
    /**
     * Constructs a new ShortTag with a name and a value.
     * 
     * @param name The name of the tag.
     * @param value The short value for the tag.
     */
    public ShortTag(String name, short value) {
        
        super(name);
        this.value = value;
    }
    
    /**
     * Sets the short value of the tag.
     * 
     * @param value The value to store in the tag.
     */
    public void setValue (short value) {
        
        this.value = value;
    }
    
    @Override
    public Short getValue () {
        
        return this.value;
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        this.value = in.readShort();
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        out.writeShort(this.value);
    }
    
    @Override
    public ShortTag clone () {
        
        return new ShortTag(this.getName(), this.getValue());
    }
}
