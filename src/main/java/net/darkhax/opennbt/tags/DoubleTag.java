package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A tag for holding a doubel value.
 */
public class DoubleTag extends Tag {
    
    /**
     * The double value held by the tag.
     */
    private double value;
    
    /**
     * Creates a tag with the specified name.
     *
     * @param name The name of the tag.
     */
    public DoubleTag(String name) {
        
        this(name, 0);
    }
    
    /**
     * Creates a tag with the specified name and value.
     *
     * @param name The name of the tag.
     * @param value The value of the tag.
     */
    public DoubleTag(String name, double value) {
        
        super(name);
        this.value = value;
    }
    
    @Override
    public Double getValue () {
        
        return this.value;
    }
    
    /**
     * Sets the value of this tag.
     *
     * @param value New value of this tag.
     */
    public void setValue (double value) {
        
        this.value = value;
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        this.value = in.readDouble();
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        out.writeDouble(this.value);
    }
    
    @Override
    public DoubleTag clone () {
        
        return new DoubleTag(this.getName(), this.getValue());
    }
}
