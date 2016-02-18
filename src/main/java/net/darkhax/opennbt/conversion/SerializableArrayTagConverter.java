package net.darkhax.opennbt.conversion;

import java.io.Serializable;

import net.darkhax.opennbt.tags.SerializableArrayTag;

/**
 * A converter that converts between SerializableArrayTag and Serializable[].
 */
public class SerializableArrayTagConverter implements TagConverter<SerializableArrayTag, Serializable[]> {
    
    @Override
    public Serializable[] convert (SerializableArrayTag tag) {
        
        return tag.getValue();
    }
    
    @Override
    public SerializableArrayTag convert (String name, Serializable[] value) {
        
        return new SerializableArrayTag(name, value);
    }
}
