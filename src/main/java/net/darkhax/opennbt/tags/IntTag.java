package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A tag for holding an integer.
 */
public class IntTag extends Tag {
    
    /**
     * The int value held by the tag.
     */
    private int value;
    
    /**
     * Creates a tag with the specified name.
     *
     * @param name The name of the tag.
     */
    public IntTag(String name) {
        
        this(name, 0);
    }
    
    /**
     * Creates a tag with the specified name and value.
     *
     * @param name The name of the tag.
     * @param value The value of the tag.
     */
    public IntTag(String name, int value) {
        
        super(name);
        this.value = value;
    }
    
    @Override
    public Integer getValue () {
        
        return this.value;
    }
    
    /**
     * Sets the value of this tag.
     *
     * @param value New value of this tag.
     */
    public void setValue (int value) {
        
        this.value = value;
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        this.value = in.readInt();
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        out.writeInt(this.value);
    }
    
    @Override
    public IntTag clone () {
        
        return new IntTag(this.getName(), this.getValue());
    }
}
