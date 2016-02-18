package net.darkhax.opennbt.conversion;

import net.darkhax.opennbt.tags.StringTag;

/**
 * A converter that converts between StringTag and String.
 */
public class StringTagConverter implements TagConverter<StringTag, String> {
    
    @Override
    public String convert (StringTag tag) {
        
        return tag.getValue();
    }
    
    @Override
    public StringTag convert (String name, String value) {
        
        return new StringTag(name, value);
    }
}