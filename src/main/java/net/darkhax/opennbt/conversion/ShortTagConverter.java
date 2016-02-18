package net.darkhax.opennbt.conversion;

import net.darkhax.opennbt.tags.ShortTag;

/**
 * A converter that converts between ShortTag and short.
 */
public class ShortTagConverter implements TagConverter<ShortTag, Short> {
    
    @Override
    public Short convert (ShortTag tag) {
        
        return tag.getValue();
    }
    
    @Override
    public ShortTag convert (String name, Short value) {
        
        return new ShortTag(name, value);
    }
}
