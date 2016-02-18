package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A Tag for holding a long array.
 */
public class LongArrayTag extends Tag {
    
    /**
     * The long array value held by the tag.
     */
    private long[] value;
    
    /**
     * Creates a tag with the specified name.
     *
     * @param name: The name of the tag.
     */
    public LongArrayTag(String name) {
        this(name, new long[0]);
    }
    
    /**
     * Creates a tag with the specified name and value.
     *
     * @param name: The name of the tag.
     * @param value: The value of the tag.
     */
    public LongArrayTag(String name, long[] value) {
        super(name);
        this.value = value;
    }
    
    @Override
    public long[] getValue () {
        
        return this.value.clone();
    }
    
    /**
     * Sets the value of the tag.
     *
     * @param value: The new value of the tag.
     */
    public void setValue (long[] value) {
        
        if (value == null)
            return;
            
        this.value = value.clone();
    }
    
    /**
     * Gets a value in the tag's array.
     *
     * @param index: Index of the value.
     * @return long: The value at the given index.
     */
    public long getValue (int index) {
        
        return this.value[index];
    }
    
    /**
     * Sets a value in the tag's array.
     *
     * @param index: Index of the value.
     * @param value: Value to set.
     */
    public void setValue (int index, long value) {
        
        this.value[index] = value;
    }
    
    /**
     * Gets the length of the tag's array.
     *
     * @return int: The tag's array length.
     */
    public int length () {
        
        return this.value.length;
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        this.value = new long[in.readInt()];
        for (int index = 0; index < this.value.length; index++) {
            this.value[index] = in.readLong();
        }
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        out.writeInt(this.value.length);
        for (int index = 0; index < this.value.length; index++) {
            out.writeLong(this.value[index]);
        }
    }
    
    @Override
    public LongArrayTag clone () {
        
        return new LongArrayTag(this.getName(), this.getValue());
    }
}
