package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A tag holding an array of bytes.
 */
public class ByteArrayTag extends Tag {
    
    /**
     * The Byte array held by the tag.
     */
    private byte[] value;
    
    /**
     * Creates a tag with the specified name.
     *
     * @param name The name of the tag.
     */
    public ByteArrayTag(String name) {
        
        this(name, new byte[0]);
    }
    
    /**
     * Creates a tag with the specified name and values.
     *
     * @param name The name of the tag.
     * @param value The value of the tag.
     */
    public ByteArrayTag(String name, byte[] value) {
        super(name);
        this.value = value;
    }
    
    @Override
    public byte[] getValue () {
        
        return this.value.clone();
    }
    
    /**
     * Sets the value of this tag.
     *
     * @param value New value of this tag.
     */
    public void setValue (byte[] value) {
        
        if (value == null)
            return;
            
        this.value = value.clone();
    }
    
    /**
     * Gets a value in this tag's array.
     *
     * @param index Index of the value.
     * @return byte The value at the given index.
     */
    public byte getValue (int index) {
        
        return this.value[index];
    }
    
    /**
     * Sets a value in this tag's array.
     *
     * @param index Index of the value.
     * @param value Value to set.
     */
    public void setValue (int index, byte value) {
        
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
        
        this.value = new byte[in.readInt()];
        in.readFully(this.value);
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        out.writeInt(this.value.length);
        out.write(this.value);
    }
    
    @Override
    public ByteArrayTag clone () {
        
        return new ByteArrayTag(this.getName(), this.getValue());
    }
}