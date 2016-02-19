package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A Tag which contains an array of String.
 */
public class StringArrayTag extends Tag {
    
    /**
     * The value stored by this String array.
     */
    private String[] value;
    
    /**
     * Constructs a new StringArrayTag with no value.
     * 
     * @param name The name of the tag.
     */
    public StringArrayTag(String name) {
        
        this(name, new String[0]);
    }
    
    /**
     * Constructs a new StringArrayTag with a name and a value.
     * 
     * @param name The name of the tag.
     * @param value The String array value for the tag.
     */
    public StringArrayTag(String name, String[] value) {
        
        super(name);
        this.value = value;
    }
    
    @Override
    public String[] getValue () {
        
        return this.value.clone();
    }
    
    /**
     * Sets the value of the tag.
     * 
     * @param value The value to store in the tag.
     */
    public void setValue (String[] value) {
        
        if (value == null)
            return;
            
        this.value = value.clone();
    }
    
    /**
     * Gets a value from the stored String array based on the index.
     * 
     * @param index The index of the value within the stored array.
     * @return String The value stored at the index in the array.
     */
    public String getValue (int index) {
        
        return this.value[index];
    }
    
    /**
     * Sets a value to the stored String array at the given index.
     * 
     * @param index The index to store a new String value at.
     * @param value The new String to store in the array.
     */
    public void setValue (int index, String value) {
        
        this.value[index] = value;
    }
    
    /**
     * Gets the length of the array stored in the tag.
     * 
     * @return int The length of the stored array.
     */
    public int length () {
        
        return this.value.length;
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        this.value = new String[in.readInt()];
        
        for (int index = 0; index < this.value.length; index++)
            this.value[index] = in.readUTF();
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        out.writeInt(this.value.length);
        
        for (int index = 0; index < this.value.length; index++)
            out.writeUTF(this.value[index]);
    }
    
    @Override
    public StringArrayTag clone () {
        
        return new StringArrayTag(this.getName(), this.getValue());
    }
}
