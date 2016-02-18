package net.darkhax.opennbt.conversion;

import java.io.Serializable;

import net.darkhax.opennbt.tags.SerializableTag;

/**
 * A converter that converts between SerializableTag and Serializable.
 */
public class SerializableTagConverter implements TagConverter<SerializableTag, Serializable> {
    
    @Override
    public Serializable convert (SerializableTag tag) {
        
        return tag.getValue();
    }
    
    @Override
    public SerializableTag convert (String name, Serializable value) {
        
        return new SerializableTag(name, value);
    }
}
