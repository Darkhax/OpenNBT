package net.darkhax.opennbt;

import java.util.Comparator;

import net.darkhax.opennbt.tags.Tag;

public final class NBTComparator implements Comparator<Tag> {
    
    @Override
    public int compare (Tag firstTag, Tag secondTag) {
        
        return (firstTag != null && !firstTag.equals(secondTag)) ? 1 : (secondTag != null) ? -1 : 0;
    }
}