package net.darkhax.nbttest;

import net.darkhax.opennbt.NBTIO;
import net.darkhax.opennbt.tags.CompoundTag;
import net.darkhax.opennbt.tags.IntArrayTag;
import net.darkhax.opennbt.tags.IntTag;
import net.darkhax.opennbt.tags.StringTag;

public class OpenNBTTest {
    
    public static void main (String[] args) {
        
        // Creates a new CompoundTag with various data stored.
        CompoundTag tag = new CompoundTag("testTag");
        tag.setTag(new IntTag("TestInteger", 1337));
        tag.setTag(new StringTag("TestString", "Hellow World!"));
        tag.setTag(new IntArrayTag("TestIntegerArray", new int[] { 200, 200, 208, 208, 203, 205, 203, 205, 48, 30 }));
        
        // Writes the tag to a new file called NBTExample.nbt
        NBTIO.writeFile(tag, "NBTExample.nbt");
        
        // Reads the NBT data from the NBTExample.nbt file. 
        tag = NBTIO.readFile("NBTExample.nbt");
        
        System.out.println(tag.toString());
    }
}