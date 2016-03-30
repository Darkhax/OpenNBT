package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;

/**
 * The foundation for all NBT tag objects.
 */
public abstract class Tag implements Cloneable {
    
    /**
     * The name of the tag.
     */
    private final String name;
    
    /**
     * Constructs a new Tag with the specified name.
     * 
     * @param name The name of the tag.
     */
    public Tag(String name) {
        
        this.name = name;
    }
    
    /**
     * Gets the name of the tag.
     *
     * @return String The name of the tag.
     */
    public final String getName () {
        
        return this.name;
    }
    
    /**
     * Gets the value of this tag.
     *
     * @return Object The value of this tag.
     */
    public abstract Object getValue ();
    
    /**
     * Reads the tag from an input stream.
     *
     * @param in Stream to write to.
     * @throws IOException If an I/O error occurs.
     */
    public abstract void read (DataInputStream in) throws IOException;
    
    /**
     * Writes this tag to an output stream.
     *
     * @param out Stream to write to.
     * @throws IOException If an I/O error occurs.
     */
    public abstract void write (DataOutputStream out) throws IOException;
    
    @Override
    public abstract Tag clone ();
    
    @Override
    public boolean equals (Object object) {
        
        if (!(object instanceof Tag))
            return false;
            
        final Tag tag = (Tag) object;
        
        if (!this.getName().equals(tag.getName()))
            return false;
            
        if (this.getValue() == null)
            return tag.getValue() == null;
            
        else if (tag.getValue() == null)
            return false;
            
        if (this.getValue().getClass().isArray() && tag.getValue().getClass().isArray()) {
            
            final int length = Array.getLength(this.getValue());
            
            if (Array.getLength(tag.getValue()) != length)
                return false;
                
            for (int index = 0; index < length; index++) {
                
                final Object objectValue = Array.get(this.getValue(), index);
                final Object other = Array.get(tag.getValue(), index);
                
                if (objectValue == null && other != null || objectValue != null && !objectValue.equals(other))
                    return false;
            }
            
            return true;
        }
        
        return this.getValue().equals(tag.getValue());
    }
    
    @Override
    public String toString () {
        
        final String name = this.getName() != null && !this.getName().equals("") ? "(" + this.getName() + ")" : "";
        String value = "";
        
        if (this.getValue() != null) {
            
            value = this.getValue().toString();
            
            if (this.getValue().getClass().isArray()) {
                
                final StringBuilder build = new StringBuilder();
                build.append("[");
                
                for (int index = 0; index < Array.getLength(this.getValue()); index++) {
                    
                    if (index > 0)
                        build.append(", ");
                        
                    build.append(Array.get(this.getValue(), index));
                }
                
                build.append("]");
                value = build.toString();
            }
        }
        
        return this.getClass().getSimpleName() + name + " { " + value + " }";
    }
}
