package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A tag for holding an integer array.
 */
public class IntArrayTag extends Tag {
    
    /**
     * The int array value held by the tag.
     */
    private int[] value;
    
    /**
     * Creates a tag with the specified name.
     *
     * @param name The name of the tag.
     */
    public IntArrayTag(String name) {
        
        this(name, new int[0]);
    }
    
    /**
     * Creates a tag with the specified name and values.
     *
     * @param name The name of the tag.
     * @param value The value of the tag.
     */
    public IntArrayTag(String name, int[] value) {
        
        super(name);
        this.value = value;
    }
    
    @Override
    public int[] getValue () {
        
        return this.value.clone();
    }
    
    /**
     * Sets the value of this tag.
     *
     * @param value The new value of this tag.
     */
    public void setValue (int[] value) {
        
        if (value == null)
            return;
            
        this.value = value.clone();
    }
    
    /**
     * Gets a value in this tag's array.
     *
     * @param index Index of the value.
     * @return int The value at the given index.
     */
    public int getValue (int index) {
        
        return this.value[index];
    }
    
    /**
     * Sets a value in this tag's array.
     *
     * @param index Index of the value.
     * @param value Value to set.
     */
    public void setValue (int index, int value) {
        
        this.value[index] = value;
    }
    
    /**
     * Gets the length of this tag's array.
     *
     * @return int This tag's array length.
     */
    public int length () {
        
        return this.value.length;
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        this.value = new int[in.readInt()];
        
        for (int index = 0; index < this.value.length; index++)
            this.value[index] = in.readInt();
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        out.writeInt(this.value.length);
        
        for (final int element : this.value)
            out.writeInt(element);
    }
    
    @Override
    public IntArrayTag clone () {
        
        return new IntArrayTag(this.getName(), this.getValue());
    }
}