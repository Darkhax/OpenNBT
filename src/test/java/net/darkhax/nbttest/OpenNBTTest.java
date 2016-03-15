package net.darkhax.nbttest;

import java.util.Arrays;

import net.darkhax.opennbt.NBTHelper;
import net.darkhax.opennbt.tags.CompoundTag;
import net.darkhax.opennbt.tags.StringTag;

public class OpenNBTTest {
    
    /**
     * A string which represents the system specific newline character.
     */
    public static final String NEW_LINE = System.getProperty("line.separator");
    
    public static void main (String[] args) {
        
        runIOTest();
        runBooleanConversionTest();
        runMassIOTest();
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
        System.out.println("The IO test was " + ((tag.tagEquals("TestInteger", 1337) && tag.tagEquals("TestString", "Hello World!") && Arrays.equals(tag.getIntArray("TestIntegerArray"), new int[] { 200, 200, 208, 208, 203, 205, 203, 205, 48, 30 })) ? "successful!" : "not successful") + NEW_LINE);
    }
    
    /**
     * Performs a test on the IO of booleans. Booleans are not directly supported by OpenNBT,
     * and the read/write methods convert the data from boolean values to byte values, which is
     * similar to how MC does it. This test is specifically to ensure that this conversion
     * process doesn't get messed up.
     */
    private static void runBooleanConversionTest () {
        
        System.out.println("Starting boolean conversion test");
        
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
        System.out.println("The boolean conversion test was " + ((tag.getBoolean("TestTrue") && !tag.getBoolean("TestFalse") && Arrays.equals(tag.getBooleanArray("TestBooleanArray"), new boolean[] { true, false, false, true })) ? "successful!" : "not successful!") + NEW_LINE);
    }
    
    /**
     * Performs an IO test on all of the non-complex tag types. The goal of the test is to
     * write one tag of almost every type to another tag, and then see if it survives being
     * written and read to the disk. This test does not check serializable objects.
     */
    private static void runMassIOTest () {
        
        System.out.println("Starting mass IO test");
        
        // Sets all of the non-complex data to the test tag.
        CompoundTag tag = new CompoundTag("TestTag");
        tag.setByte("Byte", Byte.MIN_VALUE);
        tag.setShort("Short", Short.MAX_VALUE);
        tag.setInt("Int", Integer.MIN_VALUE + 100000);
        tag.setLong("Long", Long.MAX_VALUE);
        tag.setFloat("Float", Float.MIN_VALUE + 2.29034f);
        tag.setDouble("Double", Double.MAX_VALUE - 22.9213d);
        tag.setByteArray("ByteArray", new byte[] { Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MIN_VALUE });
        tag.setString("String", "duivqe;idveu");
        tag.setDoubleArray("DoubleArray", new double[] { Double.MAX_VALUE, Double.MIN_VALUE, Double.MAX_VALUE });
        tag.setFloatArray("FloatArray", new float[] { 234f, 32f, 9432f });
        tag.setLongArray("LongArray", new long[] { Long.MIN_VALUE, Long.MIN_VALUE, Long.MAX_VALUE });
        tag.setShortArray("ShortArray", new short[] { Short.MAX_VALUE, Short.MIN_VALUE, Short.MIN_VALUE });
        tag.setStringArray("StringArray", new String[] { "wefqreqgq", "ij3erfo2j", "feiioqvfl" });
        tag.setTagList("TagList", Arrays.asList(new StringTag("Tag1", "Value1"), new StringTag("Tag2", "Value2"), new StringTag("Tag3", "Value3")));
        
        // Writes the tag to a new file called NBTExample.nbt
        NBTHelper.writeFile(tag, "NBTExample.nbt");
        
        // Reads the NBT data from the NBTExample.nbt file.
        CompoundTag readTag = NBTHelper.readFile("NBTExample.nbt");
        
        // A check to see if the two tags have the same data.
        System.out.println("Initial Tag Dump: " + tag.toString());
        System.out.println("Read Tag Dump: " + readTag.toString());
        System.out.println("The mass IO test was " + ((tag.equals(readTag)) ? "successful!" : "not successful!") + NEW_LINE);
    }
}