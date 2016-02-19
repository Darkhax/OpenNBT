package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A Tag for holding a Serializable object.
 */
public class SerializableTag extends Tag {
    
    /**
     * The Serializable object held by the tag.
     */
    private Serializable value;
    
    /**
     * Constructs a new SerializableTag with no value.
     * 
     * @param name The name of the tag.
     */
    public SerializableTag(String name) {
        
        this(name, 0);
    }
    
    /**
     * Constructs a new SerializableTag with a name and a value.
     * 
     * @param name The name of the tag.
     * @param value The Serializable object for the tag.
     */
    public SerializableTag(String name, Serializable value) {
        
        super(name);
        this.value = value;
    }
    
    /**
     * Sets the value of the tag.
     * 
     * @param value The value to store in the tag.
     */
    public void setValue (Serializable value) {
        
        this.value = value;
    }
    
    @Override
    public Serializable getValue () {
        
        return this.value;
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        ObjectInputStream str = new ObjectInputStream(in);
        
        try {
            
            this.value = (Serializable) str.readObject();
        }
        
        catch (ClassNotFoundException e) {
            
            throw new IOException("Class not found while reading SerializableTag!", e);
        }
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        ObjectOutputStream str = new ObjectOutputStream(out);
        str.writeObject(this.value);
    }
    
    @Override
    public SerializableTag clone () {
        
        return new SerializableTag(this.getName(), this.getValue());
    }
}
