package net.darkhax.opennbt.conversion;

import net.darkhax.opennbt.tags.ByteArrayTag;

/**
 * A converter that converts between ByteArrayTag and byte[].
 */
public class ByteArrayTagConverter implements TagConverter<ByteArrayTag, byte[]> {
    
    @Override
    public byte[] convert (ByteArrayTag tag) {
        
        return tag.getValue();
    }
    
    @Override
    public ByteArrayTag convert (String name, byte[] value) {
        
        return new ByteArrayTag(name, value);
    }
}
