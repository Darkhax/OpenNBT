package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A Tag for holding an array of Serializable objects.
 */
public class SerializableArrayTag extends Tag {
    
    /**
     * The array of Serializable objects held by the tag.
     */
    private Serializable[] value;
    
    /**
     * Constructs a new SerializableArrayTag with no value.
     * 
     * @param name The name of the tag.
     */
    public SerializableArrayTag(String name) {
        
        this(name, new Serializable[0]);
    }
    
    /**
     * Constructs a new SerializableArrayTag with a name and a value.
     * 
     * @param name The name of the tag.
     * @param value The Serializable Array for the tag.
     */
    public SerializableArrayTag(String name, Serializable[] value) {
        
        super(name);
        this.value = value;
    }
    
    @Override
    public Serializable[] getValue () {
        
        return this.value.clone();
    }
    
    /**
     * Sets the value of the tag.
     * 
     * @param value The new value for the tag.
     */
    public void setValue (Serializable[] value) {
        
        if (value == null)
            return;
            
        this.value = value.clone();
    }
    
    /**
     * Gets the Serializable object stored at the specified index within the array.
     * 
     * @param index The index of the value being searched for.
     * @return Serializable The value stored at the specified index.
     */
    public Serializable getValue (int index) {
        
        return this.value[index];
    }
    
    /**
     * Sets the value at the specified index.
     * 
     * @param index The index to set the value at.
     * @param value The value to store.
     */
    public void setValue (int index, Serializable value) {
        
        this.value[index] = value;
    }
    
    /**
     * Gets the length of the tags array.
     *
     * @return int The tag's array length.
     */
    public int length () {
        
        return this.value.length;
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        this.value = new Serializable[in.readInt()];
        final ObjectInputStream str = new ObjectInputStream(in);
        
        for (int index = 0; index < this.value.length; index++)
            try {
                
                this.value[index] = (Serializable) str.readObject();
            }
            
            catch (final ClassNotFoundException e) {
                
                throw new IOException("Class not found while reading SerializableArrayTag!", e);
            }
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        out.writeInt(this.value.length);
        final ObjectOutputStream str = new ObjectOutputStream(out);
        
        for (final Serializable element : this.value)
            str.writeObject(element);
    }
    
    @Override
    public SerializableArrayTag clone () {
        
        return new SerializableArrayTag(this.getName(), this.getValue());
    }
}
