package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A tag for holding a Byte.
 */
public class ByteTag extends Tag {
    
    /**
     * The byte value held by the tag.
     */
    private byte value;
    
    /**
     * Creates a tag with the specified name.
     *
     * @param name The name of the tag.
     */
    public ByteTag(String name) {
        
        this(name, (byte) 0);
    }
    
    /**
     * Creates a tag with the specified name and value.
     *
     * @param name The name of the tag.
     * @param value The value of the tag.
     */
    public ByteTag(String name, byte value) {
        
        super(name);
        this.value = value;
    }
    
    @Override
    public Byte getValue () {
        
        return this.value;
    }
    
    /**
     * Sets the value of this tag.
     *
     * @param value New value of this tag.
     */
    public void setValue (byte value) {
        
        this.value = value;
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        this.value = in.readByte();
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        out.writeByte(this.value);
    }
    
    @Override
    public ByteTag clone () {
        
        return new ByteTag(this.getName(), this.getValue());
    }
}