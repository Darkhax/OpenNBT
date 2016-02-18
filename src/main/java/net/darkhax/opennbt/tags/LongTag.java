package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A Tag for holding a Long value.
 */
public class LongTag extends Tag {
    
    /**
     * The Long value held by the tag.
     */
    private long value;
    
    /**
     * Constructs a new LongTag with no value.
     * 
     * @param name: The name of the tag.
     */
    public LongTag(String name) {
        
        this(name, 0);
    }
    
    /**
     * Constructs a new LongTag with a name and a value.
     * 
     * @param name: The name of the tag.
     * @param value: The Long value for the tag.
     */
    public LongTag(String name, long value) {
        
        super(name);
        this.value = value;
    }
    
    /**
     * Sets the value of the tag.
     * 
     * @param value: The value to store in the tag.
     */
    public void setValue (long value) {
        
        this.value = value;
    }
    
    @Override
    public Long getValue () {
        
        return this.value;
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        this.value = in.readLong();
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        out.writeLong(this.value);
    }
    
    @Override
    public LongTag clone () {
        
        return new LongTag(this.getName(), this.getValue());
    }
}
