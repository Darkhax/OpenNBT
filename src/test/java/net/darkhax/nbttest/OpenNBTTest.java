package net.darkhax.nbttest;

import java.util.Arrays;

import net.darkhax.opennbt.NBTHelper;
import net.darkhax.opennbt.tags.CompoundTag;

public class OpenNBTTest {
    
    /**
     * A string which represents the system specific newline character.
     */
    public static final String NEW_LINE = System.getProperty("line.separator");
    
    public static void main (String[] args) {
        
        runIOTest();
        runBooleanTest();
    }
    
    /**
     * Performs a basic IO test. The goal of this test is to successfully write several
     * properties and then read them back from the data. For the test to be considered a
     * success, the output must be the same as the input.
     */
    private static void runIOTest () {
        
        System.out.println("Starting basic IO test");
        
        // Creates a new CompoundTag with various data stored.
        CompoundTag tag = new CompoundTag("TestTag");
        tag.setInt("TestInteger", 1337);
        tag.setString("TestString", "Hello World!");
        tag.setIntArray("TestIntegerArray", new int[] { 200, 200, 208, 208, 203, 205, 203, 205, 48, 30 });
        
        // Writes the tag to a new file called NBTExample.nbt
        NBTHelper.writeFile(tag, "NBTExample.nbt");
        
        // Reads the NBT data from the NBTExample.nbt file.
        tag = NBTHelper.readFile("NBTExample.nbt");
        
        // Results for the test
        System.out.println("TestInteger: " + tag.getInt("TestInteger") + NEW_LINE + "TestString: " + tag.getString("TestString") + NEW_LINE + "TestIntegerArray: " + Arrays.toString(tag.getIntArray("TestIntegerArray")));
        System.out.println("Tag Dump: " + tag.toString());
        System.out.println("The IO test was " + ((tag.tagEquals("TestInteger", 1337) && tag.tagEquals("TestString", "Hello World!") && Arrays.equals(tag.getIntArray("TestIntegerArray"), new int[] { 200, 200, 208, 208, 203, 205, 203, 205, 48, 30 })) ? "successful!" : "not successful"));
    }
    
    /**
     * Performs a test on the IO of booleans. Booleans are not directly supported by OpenNBT,
     * and the read/write methods convert the data from boolean values to byte values, which is
     * similar to how MC does it. This test is specifically to ensure that this conversion
     * process doesn't get messed up.
     */
    private static void runBooleanTest () {
        
        System.out.println("Starting boolean test");
        
        // Creates a new CompoundTag and sets various boolean properties to it.
        CompoundTag tag = new CompoundTag("BooleanTag");
        tag.setBoolean("TestTrue", true);
        tag.setBoolean("TestFalse", false);
        tag.setBooleanArray("TestBooleanArray", new boolean[] { true, false, false, true });
        
        // Writes the tag to a new file called NBTExample.nbt
        NBTHelper.writeFile(tag, "NBTExample.nbt");
        
        // Reads the NBT data from the NBTExample.nbt file.
        tag = NBTHelper.readFile("NBTExample.nbt");
        
        // Results for the test
        System.out.println("Tag Dump: " + tag.toString());
        System.out.println("The IO test was " + ((tag.getBoolean("TestTrue") && !tag.getBoolean("TestFalse") && Arrays.equals(tag.getBooleanArray("TestBooleanArray"), new boolean[] { true, false, false, true })) ? "successful!" : "not successful!"));
    }
}