package net.darkhax.nbttest;

import java.util.Arrays;

import net.darkhax.opennbt.NBTIO;
import net.darkhax.opennbt.tags.CompoundTag;

public class OpenNBTTest {
    
    public static void main (String[] args) {
        
        // Creates a new CompoundTag with various data stored.
        CompoundTag tag = new CompoundTag("TestTag");
        tag.setInt("TestInteger", 1337);
        tag.setString("TestString", "Hello World!");
        tag.setIntArray("TestIntegerArray", new int[] { 200, 200, 208, 208, 203, 205, 203, 205, 48, 30 });
        
        // Writes the tag to a new file called NBTExample.nbt
        NBTIO.writeFile(tag, "NBTExample.nbt");
        
        // Reads the NBT data from the NBTExample.nbt file.
        tag = NBTIO.readFile("NBTExample.nbt");
        
        // Prints the result of the tag's toString method to see if the data was written and
        // read properly.
        System.out.println("TestInteger: " + tag.getInt("TestInteger"));
        System.out.println("TestString: " + tag.getString("TestString"));
        System.out.println("TestIntegerArray: " + Arrays.toString(tag.getInArray("TestIntegerArray")));
        System.out.println("Tag Dump: " + tag.toString());
    }
}