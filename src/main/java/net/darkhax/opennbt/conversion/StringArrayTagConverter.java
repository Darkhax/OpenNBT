package net.darkhax.opennbt.conversion;

import net.darkhax.opennbt.tags.StringArrayTag;

/**
 * A converter that converts between StringArrayTag and String[].
 */
public class StringArrayTagConverter implements TagConverter<StringArrayTag, String[]> {
    
    @Override
    public String[] convert (StringArrayTag tag) {
        
        return tag.getValue();
    }
    
    @Override
    public StringArrayTag convert (String name, String[] value) {
        
        return new StringArrayTag(name, value);
    }
}
