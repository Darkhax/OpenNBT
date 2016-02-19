package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A Tag for holding a String value.
 */
public class StringTag extends Tag {
    
    /**
     * The string value held by this tag.
     */
    private String value;
    
    /**
     * Constructs a new StringTag with no value.
     * 
     * @param name The name of the tag.
     */
    public StringTag(String name) {
        
        this(name, "");
    }
    
    /**
     * Constructs a new StringTag with a name and a value.
     * 
     * @param name The name of the tag.
     * @param value The String value for the tag.
     */
    public StringTag(String name, String value) {
        
        super(name);
        this.value = value;
    }
    
    /**
     * Sets the value of the tag.
     *
     * @param value The value to store in the tag.
     */
    public void setValue (String value) {
        
        this.value = value;
    }
    
    @Override
    public String getValue () {
        
        return this.value;
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        this.value = in.readUTF();
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        out.writeUTF(this.value);
    }
    
    @Override
    public StringTag clone () {
        
        return new StringTag(this.getName(), this.getValue());
    }
}
