package net.darkhax.opennbt.tags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.darkhax.opennbt.NBTHelper;

/**
 * A compound tag which contains a bunch of other tags.
 */
public class CompoundTag extends Tag implements Iterable<Tag> {
    
    /**
     * A map containing tagged values.
     */
    private Map<String, Tag> value;
    
    /**
     * Creates a tag with the specified name.
     *
     * @param name The name of the tag.
     */
    public CompoundTag(String name) {
        
        this(name, new LinkedHashMap<String, Tag>());
    }
    
    /**
     * Creates a tag with the specified name.
     *
     * @param name The name of the tag.
     * @param value The value of the tag.
     */
    public CompoundTag(String name, Map<String, Tag> value) {
        
        super(name);
        this.value = new LinkedHashMap<String, Tag>(value);
    }
    
    @Override
    public Map<String, Tag> getValue () {
        
        return new LinkedHashMap<String, Tag>(this.value);
    }
    
    /**
     * Sets the value of this tag.
     *
     * @param value New value of this tag.
     */
    public void setValue (Map<String, Tag> value) {
        
        this.value = new LinkedHashMap<String, Tag>(value);
    }
    
    /**
     * Checks whether the compound tag is empty.
     *
     * @return boolean Whether the compound tag is empty.
     */
    public boolean isEmpty () {
        
        return this.value.isEmpty();
    }
    
    /**
     * Checks whether the compound tag contains a tag with the specified name.
     *
     * @param tagName Name of the tag to check for.
     * @return boolean Whether the compound tag contains a tag with the specified name.
     */
    public boolean hasTag (String tagName) {
        
        return this.value.containsKey(tagName);
    }
    
    /**
     * Gets the tag with the specified name.
     *
     * @param tagName Name of the tag.
     * @return Tag The tag with the specified name.
     */
    public <T extends Tag> T getTag (String tagName) {
        
        return (T) this.value.get(tagName);
    }
    
    /**
     * Puts the tag into this compound tag.
     *
     * @param tag Tag to put into this compound tag.
     * @return Tag The previous tag associated with its name, or null if there wasn't one.
     */
    public <T extends Tag> T setTag (T tag) {
        
        return (T) this.value.put(tag.getName(), tag);
    }
    
    /**
     * Gets a byte from the Compound Tag. If no tag is found with the given name, 0 will be
     * returned.
     * 
     * @param name The name of the byte tag.
     * @return byte The stored byte.
     */
    public byte getByte (String name) {
        
        return (this.value.containsKey(name)) ? (byte) this.value.get(name).getValue() : 0;
    }
    
    /**
     * Sets a byte to the Compound Tag.
     * 
     * @param name The name to store the byte under.
     * @param value The byte value to store.
     */
    public void setByte (String name, byte value) {
        
        this.value.put(name, new ByteTag(name, value));
    }
    
    /**
     * Gets a short from the Compound Tag. If no tag is found with the given name, 0 will be
     * returned.
     * 
     * @param name The name of the short tag.
     * @return short The stored short.
     */
    public short getShort (String name) {
        
        return (this.value.containsKey(name)) ? (short) this.value.get(name).getValue() : 0;
    }
    
    /**
     * Sets a short to the Compound Tag.
     * 
     * @param name The name to store the short under.
     * @param value The short value to store.
     */
    public void setShort (String name, short value) {
        
        this.value.put(name, new ShortTag(name, value));
    }
    
    /**
     * Gets an int from the Compound Tag. If no tag is found with the given name, 0 will be
     * returned.
     * 
     * @param name The name of the int tag.
     * @return int The stored int.
     */
    public int getInt (String name) {
        
        return (this.value.containsKey(name)) ? (int) this.value.get(name).getValue() : 0;
    }
    
    /**
     * Sets an int to the Compound Tag.
     * 
     * @param name The name to store the int under.
     * @param value The int value to store.
     */
    public void setInt (String name, int value) {
        
        this.value.put(name, new IntTag(name, value));
    }
    
    /**
     * Get a long from the Compound Tag. If no tag is found with the given name, 0 will be
     * returned.
     * 
     * @param name The name of the long tag.
     * @return long The stored long.
     */
    public long getLong (String name) {
        
        return (this.value.containsKey(name)) ? (long) this.value.get(name).getValue() : 0;
    }
    
    /**
     * Sets a long to the Compound Tag.
     * 
     * @param name The name to store the long under.
     * @param value The long to store.
     */
    public void setLong (String name, long value) {
        
        this.value.put(name, new LongTag(name, value));
    }
    
    /**
     * Gets a float from the Compound Tag. If no tag is found with the given name, 0f will be
     * returned.
     * 
     * @param name The name of the float tag.
     * @return float The stored float.
     */
    public float getFloat (String name) {
        
        return (this.value.containsKey(name)) ? (float) this.value.get(name).getValue() : 0;
    }
    
    /**
     * Sets a float to the Compound Tag.
     * 
     * @param name The name to store the float under.
     * @param value The float value to store.
     */
    public void setFloat (String name, float value) {
        
        this.value.put(name, new FloatTag(name, value));
    }
    
    /**
     * Gets a double from the Compound Tag. If no tag is found with the given name, 0d will be
     * returned.
     * 
     * @param name The name of the double tag.
     * @return double The stored double.
     */
    public double getDouble (String name) {
        
        return (this.value.containsKey(name)) ? (double) this.value.get(name).getValue() : 0d;
    }
    
    /**
     * Sets a double to the Compound Tag.
     * 
     * @param name The name to store the double under.
     * @param value The double value to store.
     */
    public void setDouble (String name, double value) {
        
        this.value.put(name, new DoubleTag(name, value));
    }
    
    /**
     * Gets a byte array from the Compound Tag. If no tag is found with the given name, an
     * empty array will be returned.
     * 
     * @param name The name of the byte array tag.
     * @return byte[] The stored byte array.
     */
    public byte[] getByteArray (String name) {
        
        return (this.value.containsKey(name)) ? (byte[]) this.value.get(name).getValue() : new byte[0];
    }
    
    /**
     * Sets a byte array to the Compound Tag.
     * 
     * @param name The name to store the byte array under.
     * @param value The byte array to store.
     */
    public void setByteArray (String name, byte[] value) {
        
        this.value.put(name, new ByteArrayTag(name, value));
    }
    
    /**
     * Gets a String from the Compound Tag. If no tag is found with the given name, an empty
     * string will be returned.
     * 
     * @param name The name of the String tag.
     * @return String The stored String.
     */
    public String getString (String name) {
        
        return (this.value.containsKey(name)) ? (String) this.value.get(name).getValue() : "";
    }
    
    /**
     * Sets a String to the Compound Tag.
     * 
     * @param name The name to store the String under.
     * @param value The String to store.
     */
    public void setString (String name, String value) {
        
        this.value.put(name, new StringTag(name, value));
    }
    
    /**
     * Gets a List<Tag> from the Compound Tag. If no tag is found with the given name, and
     * empty List will be returned.
     * 
     * @param name The name of the ListTag tag.
     * @return List<Tag> The stored ListTag.
     */
    
    public List<Tag> getTagList (String name) {
        
        return (this.value.containsKey(name)) ? (List<Tag>) this.value.get(name).getValue() : new ArrayList<Tag>();
    }
    
    /**
     * Sets a List<Tag> to the Compound Tag.
     * 
     * @param name The name to store the List<Tag> under.
     * @param value The Tag List to store.
     */
    public void setTagList (String name, List<Tag> value) {
        
        this.value.put(name, new ListTag(name, value));
    }
    
    /**
     * Gets a CompoundTag from the CompoundTag. If no CompoundTag is found with the given name,
     * null will be returned.
     * 
     * @param name The name of the CompoundTag tag.
     * @return CompoundTag The stored CompoundTag.
     */
    public CompoundTag getCompoundTag (String name) {
        
        return (this.value.containsKey(name)) ? (CompoundTag) this.value.get(name) : null;
    }
    
    /**
     * Sets a CompoundTag to the CompoundTag.
     * 
     * @param name The name to store the CompoundTag under.
     * @param value The CompoundTag to store.
     */
    public void setCompoundTag (String name, CompoundTag value) {
        
        this.value.put(name, value);
    }
    
    /**
     * Gets an int array from the CompoundTag. If no Tag is found with the given name, an empty
     * array will be returned.
     * 
     * @param name The name of the int array tag.
     * @return int[] The stored int array.
     */
    public int[] getInArray (String name) {
        
        return (this.value.containsKey(name)) ? (int[]) this.value.get(name).getValue() : new int[0];
    }
    
    /**
     * Sets an int array to the CompoundTag.
     * 
     * @param name The name to store the int array under.
     * @param value The int array to store.
     */
    public void setIntArray (String name, int[] value) {
        
        this.value.put(name, new IntArrayTag(name, value));
    }
    
    /**
     * Gets a double array from the CompoundTag. If no Tag is found with the given name, an
     * empty array will be returned.
     * 
     * @param name The name of the double array tag.
     * @return double[] The stored double array.
     */
    public double[] getDoubleArray (String name) {
        
        return (this.value.containsKey(name)) ? (double[]) this.value.get(name).getValue() : new double[0];
    }
    
    /**
     * Sets a double array to the CompoundTag.
     * 
     * @param name The name to store the double array under.
     * @param value The double array to store.
     */
    public void setDoubleArray (String name, double[] value) {
        
        this.value.put(name, new DoubleArrayTag(name, value));
    }
    
    /**
     * Gets a float array from the CompoundTag. If no Tag is found with the given name, an
     * empty array will be returned.
     * 
     * @param name The name of the float array tag.
     * @return float[] The stored float array.
     */
    public float[] getFloatArray (String name) {
        
        return (this.value.containsKey(name)) ? (float[]) this.value.get(name).getValue() : new float[0];
    }
    
    /**
     * Sets a float array to the CompoundTag.
     * 
     * @param name The name to store the float array under.
     * @param value The float array to store.
     */
    public void setFloatArray (String name, float[] value) {
        
        this.value.put(name, new FloatArrayTag(name, value));
    }
    
    /**
     * Gets a long array from the CompoundTag. If no Tag is found with the given name, an empty
     * array will be returned.
     * 
     * @param name The name of the long array tag.
     * @return long[] The stored long array.
     */
    public long[] getLongArray (String name) {
        
        return (this.value.containsKey(name)) ? (long[]) this.value.get(name).getValue() : new long[0];
    }
    
    /**
     * Sets a long array to the CompoundTag.
     * 
     * @param name The name to store the long array under.
     * @param value The double long to store.
     */
    public void setLongArray (String name, long[] value) {
        
        this.value.put(name, new LongArrayTag(name, value));
    }
    
    /**
     * Gets a serializable array from the CompoundTag. If no Tag is found with the given name,
     * an empty array will be returned.
     * 
     * @param name The name of the serializable array tag.
     * @return Serializable[] The stored serializable array.
     */
    public Serializable[] getSerializableArray (String name) {
        
        return (this.value.containsKey(name)) ? (Serializable[]) this.value.get(name).getValue() : new Serializable[0];
    }
    
    /**
     * Sets a serializable array to the CompoundTag.
     * 
     * @param name The name to store the serializable array under.
     * @param value The serializable array to store.
     */
    public void setSerializableArray (String name, Serializable[] value) {
        
        this.value.put(name, new SerializableArrayTag(name, value));
    }
    
    /**
     * Gets a serializable object from the CompoundTag. If no Tag is found with the given name,
     * an empty array will be returned.
     * 
     * @param name The name of the serializable object tag.
     * @return Serializable The stored serializable object.
     */
    public Serializable getSerializable (String name) {
        
        return (this.value.containsKey(name)) ? (Serializable) this.value.get(name) : null;
    }
    
    /**
     * Sets a serializable object to the CompoundTag.
     * 
     * @param name The name to store the serializable object under.
     * @param value The serializable object to store.
     */
    public void setSerializableTag (String name, Serializable value) {
        
        this.value.put(name, new SerializableTag(name, value));
    }
    
    /**
     * Gets a short array from the CompoundTag. If no Tag is found with the given name, an
     * empty array will be returned.
     * 
     * @param name The name of the short array tag.
     * @return short[] The stored short array.
     */
    public short[] getShortArray (String name) {
        
        return (this.value.containsKey(name)) ? (short[]) this.value.get(name).getValue() : new short[0];
    }
    
    /**
     * Sets a short array to the CompoundTag.
     * 
     * @param name The name to store the short array under.
     * @param value The short array to store.
     */
    public void setShortArray (String name, short[] value) {
        
        this.value.put(name, new ShortArrayTag(name, value));
    }
    
    /**
     * Gets a string array from the CompoundTag. If no Tag is found with the given name, an
     * empty array will be returned.
     * 
     * @param name The name of the string array tag.
     * @return string[] The stored string array.
     */
    public String[] getStringArray (String name) {
        
        return (this.value.containsKey(name)) ? (String[]) this.value.get(name).getValue() : new String[0];
    }
    
    /**
     * Sets a string array to the CompoundTag.
     * 
     * @param name The name to store the string array under.
     * @param value The string array to store.
     */
    public void setStringArray (String name, String[] value) {
        
        this.value.put(name, new StringArrayTag(name, value));
    }
    
    /**
     * Gets a boolean from the CompoundTag. If no tag is found with the name, false will be
     * returned.
     * 
     * @param name The name of the boolean tag.
     * @return boolean The stored boolean.
     */
    public boolean getBoolean (String name) {
        
        return this.getByte(name) != 0;
    }
    
    /**
     * Sets a boolean to the CompoundTag.
     * 
     * @param name The name to store the boolean under.
     * @param value The boolean value to store.
     */
    public void setBoolean (String name, boolean value) {
        
        this.setByte(name, (byte) (value ? 1 : 0));
    }
    
    /**
     * Removes a tag from this compound tag.
     *
     * @param tagName Name of the tag to remove.
     * @return Tag The removed tag.
     */
    public <T extends Tag> T removeTag (String tagName) {
        
        return (T) this.value.remove(tagName);
    }
    
    /**
     * Gets a set of keys in this compound tag.
     *
     * @return Set<String> The compound tag's key set.
     */
    public Set<String> keySet () {
        
        return this.value.keySet();
    }
    
    /**
     * Gets a collection of tags in this compound tag.
     *
     * @return Collection<Tag> This compound tag's tags.
     */
    public Collection<Tag> values () {
        
        return this.value.values();
    }
    
    /**
     * Gets the number of tags in this compound tag.
     *
     * @return int This compound tag's size.
     */
    public int size () {
        
        return this.value.size();
    }
    
    /**
     * Clears all tags from this compound tag.
     */
    public void clear () {
        
        this.value.clear();
    }
    
    @Override
    public Iterator<Tag> iterator () {
        
        return this.values().iterator();
    }
    
    @Override
    public void read (DataInputStream in) throws IOException {
        
        List<Tag> tags = new ArrayList<Tag>();
        
        try {
            
            Tag tag;
            
            while ((tag = NBTHelper.readTag(in)) != null)
                tags.add(tag);
        }
        catch (EOFException e) {
            
            throw new IOException("Closing EndTag was not found!");
        }
        
        tags.forEach(tag -> this.setTag(tag));
    }
    
    @Override
    public void write (DataOutputStream out) throws IOException {
        
        for (Tag tag : this.value.values())
            NBTHelper.writeTag(out, tag);
            
        out.writeByte(0);
    }
    
    @Override
    public CompoundTag clone () {
        
        Map<String, Tag> newMap = new LinkedHashMap<String, Tag>();
        this.value.entrySet().forEach(entry -> newMap.put(entry.getKey(), entry.getValue().clone()));
        
        return new CompoundTag(this.getName(), newMap);
    }
}
