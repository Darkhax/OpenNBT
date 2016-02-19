package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A tag for holding a float.
 */
public class FloatTag extends Tag {
    
    /**
     * The float value held by the tag.
     */
    private float value;
    
    /**
     * Creates a tag with the specified name.
     *
     * @param name The name of the tag.
     */
    public FloatTag(String name) {
        
        this(name, 0);
    }
    
    /**
     * Creates a tag with the specified name and float.
     *
     * @param name The name of the tag.
     * @param value The value of the tag.
     */
    public FloatTag(String name, float value) {
        
        super(name);
        this.value = value;
    }
    
    @Override
    public Float getValue () {
        
        return this.value;
    }
    
    /**
     * Sets the value of this tag.
     *
     * @param value New value of this tag.
     */
    public void setValue (float value) {
        
        this.value = value;
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        this.value = in.readFloat();
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        out.writeFloat(this.value);
    }
    
    @Override
    public FloatTag clone () {
        
        return new FloatTag(this.getName(), this.getValue());
    }
}
