package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A Tag for holding an array of shorts.
 */
public class ShortArrayTag extends Tag {
    
    /**
     * The short array value held by this tag.
     */
    private short[] value;
    
    /**
     * Construct a new ShortArrayTag with no value.
     * 
     * @param name The name of the tag.
     */
    public ShortArrayTag(String name) {
        
        this(name, new short[0]);
    }
    
    /**
     * Constructs a new ShortArrayTag with a name and a value.
     * 
     * @param name The name of the tag.
     * @param value The Short Array value for the tag.
     */
    public ShortArrayTag(String name, short[] value) {
        
        super(name);
        this.value = value;
    }
    
    /**
     * Sets the value of the array.
     * 
     * @param value The short array to store in the tag.
     */
    public void setValue (short[] value) {
        
        if (value == null)
            return;
            
        this.value = value.clone();
    }
    
    @Override
    public short[] getValue () {
        
        return this.value.clone();
    }
    
    /**
     * Gets the value at the index within the stored array.
     * 
     * @param index The index of the value to retrieve within the stored array.
     * @return short The stored value.
     */
    public short getValue (int index) {
        
        return this.value[index];
    }
    
    /**
     * Sets the value at the indexed position in the stored array.
     * 
     * @param index The index to store the value at.
     * @param value The value to store in the array.
     */
    public void setValue (int index, short value) {
        
        this.value[index] = value;
    }
    
    /**
     * Gets the length of the stored short array.
     * 
     * @return int The length of the stored array.
     */
    public int length () {
        
        return this.value.length;
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        this.value = new short[in.readInt()];
        for (int index = 0; index < this.value.length; index++)
            this.value[index] = in.readShort();
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        out.writeInt(this.value.length);
        for (final short element : this.value)
            out.writeShort(element);
    }
    
    @Override
    public ShortArrayTag clone () {
        
        return new ShortArrayTag(this.getName(), this.getValue());
    }
}
