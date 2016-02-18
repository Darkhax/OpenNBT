package net.darkhax.opennbt.conversion;

import net.darkhax.opennbt.tags.DoubleArrayTag;

/**
 * A converter that converts between DoubleArrayTag and double[].
 */
public class DoubleArrayTagConverter implements TagConverter<DoubleArrayTag, double[]> {
    
    @Override
    public double[] convert (DoubleArrayTag tag) {
        
        return tag.getValue();
    }
    
    @Override
    public DoubleArrayTag convert (String name, double[] value) {
        
        return new DoubleArrayTag(name, value);
    }
}
