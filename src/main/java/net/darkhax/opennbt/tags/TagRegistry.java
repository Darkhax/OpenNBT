package net.darkhax.opennbt.tags;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * A registry which maps tag classes and their ids.
 */
public class TagRegistry {
    
    /**
     * A map which handles ID to tag class conversions.
     */
    private static final Map<Integer, Class<? extends Tag>> idToTag = new HashMap<Integer, Class<? extends Tag>>();
    
    /**
     * A map which handles tag class to ID conversions.
     */
    private static final Map<Class<? extends Tag>, Integer> tagToId = new HashMap<Class<? extends Tag>, Integer>();
    
    /**
     * The last ID to be found by the getNextID method.
     */
    private static int lastID = 80;
    
    static {
        
        register(1, ByteTag.class);
        register(2, ShortTag.class);
        register(3, IntTag.class);
        register(4, LongTag.class);
        register(5, FloatTag.class);
        register(6, DoubleTag.class);
        register(7, ByteArrayTag.class);
        register(8, StringTag.class);
        register(9, ListTag.class);
        register(10, CompoundTag.class);
        register(11, IntArrayTag.class);
        register(12, LongArrayTag.class);
        
        register(60, DoubleArrayTag.class);
        register(61, FloatArrayTag.class);
        register(63, SerializableArrayTag.class);
        register(64, SerializableTag.class);
        register(65, ShortArrayTag.class);
        register(66, StringArrayTag.class);
    }
    
    /**
     * Registers a tag class into the system. If the ID or Class has already been used, an
     * exception will be thrown.
     * 
     * @param id The ID to register the tag under.
     * @param tag The Tag class to register.
     * @throws TagRegisterException Thrown if the ID or Tag has already been used.
     */
    public static void register (int id, Class<? extends Tag> tag) throws TagRegisterException {
        
        if (idToTag.containsKey(id))
            throw new TagRegisterException("Tag ID " + id + " is already in use by " + idToTag.get(id).getSimpleName() + ".");
            
        if (tagToId.containsKey(tag))
            throw new TagRegisterException("Tag " + tag.getSimpleName() + " is already registered.");
            
        idToTag.put(id, tag);
        tagToId.put(tag, id);
    }
    
    /**
     * Gets a class that is associated with a tag ID.
     * 
     * @param id The tag ID to search for.
     * @return Class The Class of the Tag associated with the ID.
     */
    public static Class<? extends Tag> getClassFor (int id) {
        
        if (!idToTag.containsKey(id))
            return null;
            
        return idToTag.get(id);
    }
    
    /**
     * Gets an ID from its associated Tag class.
     * 
     * @param clazz The Class to search for.
     * @return int The ID associated with the Tag class. -1 means the class has no associated
     *         ID.
     */
    public static int getIdFor (Class<? extends Tag> clazz) {
        
        if (!tagToId.containsKey(clazz))
            return -1;
            
        return tagToId.get(clazz);
    }
    
    /**
     * Creates a new instance of the tag with the specified ID using the basic string
     * constructor.
     * 
     * @param id The ID of the tag to create.
     * @param tagName The name to give this tag.
     * @return Tag The newly created Tag.
     * @throws TagCreateException Thrown if an error occurred while constructing the tag.
     */
    public static Tag createInstance (int id, String tagName) throws TagCreateException {
        
        final Class<? extends Tag> clazz = getClassFor(id);
        
        try {
            
            final Constructor<? extends Tag> constructor = clazz.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            return constructor.newInstance(tagName);
        }
        
        catch (final Exception exception) {
            
            throw new TagCreateException("Failed to create instance of tag " + clazz.getSimpleName() + ".", exception);
        }
    }
    
    /**
     * Attempts to get an unused ID for the tag registry. While negative id values can be used
     * (except for -1), this method will only try to generate a positive ID. If there are no
     * positive ID values remain, an exception will be thrown. That is extremely unlikely, as
     * there are 2147483647 positive ID values.
     * 
     * @return int An unused ID value to use for the tag registry.
     */
    public static int getNextID () {
        
        for (int id = lastID; lastID < Integer.MAX_VALUE; id++)
            if (!idToTag.containsKey(id)) {
                
                lastID = id++;
                return id;
            }
            
        throw new TagIdException();
    }
}
