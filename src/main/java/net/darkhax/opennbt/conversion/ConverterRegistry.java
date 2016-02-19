package net.darkhax.opennbt.conversion;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.darkhax.opennbt.tags.ByteArrayTag;
import net.darkhax.opennbt.tags.ByteTag;
import net.darkhax.opennbt.tags.CompoundTag;
import net.darkhax.opennbt.tags.DoubleArrayTag;
import net.darkhax.opennbt.tags.DoubleTag;
import net.darkhax.opennbt.tags.FloatArrayTag;
import net.darkhax.opennbt.tags.FloatTag;
import net.darkhax.opennbt.tags.IntArrayTag;
import net.darkhax.opennbt.tags.IntTag;
import net.darkhax.opennbt.tags.ListTag;
import net.darkhax.opennbt.tags.LongArrayTag;
import net.darkhax.opennbt.tags.LongTag;
import net.darkhax.opennbt.tags.SerializableArrayTag;
import net.darkhax.opennbt.tags.SerializableTag;
import net.darkhax.opennbt.tags.ShortArrayTag;
import net.darkhax.opennbt.tags.ShortTag;
import net.darkhax.opennbt.tags.StringArrayTag;
import net.darkhax.opennbt.tags.StringTag;
import net.darkhax.opennbt.tags.Tag;
import net.darkhax.opennbt.tags.TagRegisterException;

/**
 * A registry mapping tags and value types to converters.
 */
public class ConverterRegistry {
    
    /**
     * A Map of tag converters.
     */
    private static final Map<Class<? extends Tag>, TagConverter<? extends Tag, ?>> tagToConverter = new HashMap<Class<? extends Tag>, TagConverter<? extends Tag, ?>>();
    
    /**
     * A Map of type converters.
     */
    private static final Map<Class<?>, TagConverter<? extends Tag, ?>> typeToConverter = new HashMap<Class<?>, TagConverter<? extends Tag, ?>>();
    
    static {
        
        register(ByteTag.class, Byte.class, new ByteTagConverter());
        register(ShortTag.class, Short.class, new ShortTagConverter());
        register(IntTag.class, Integer.class, new IntTagConverter());
        register(LongTag.class, Long.class, new LongTagConverter());
        register(FloatTag.class, Float.class, new FloatTagConverter());
        register(DoubleTag.class, Double.class, new DoubleTagConverter());
        register(ByteArrayTag.class, byte[].class, new ByteArrayTagConverter());
        register(StringTag.class, String.class, new StringTagConverter());
        register(ListTag.class, List.class, new ListTagConverter());
        register(CompoundTag.class, Map.class, new CompoundTagConverter());
        register(IntArrayTag.class, int[].class, new IntArrayTagConverter());
        
        register(DoubleArrayTag.class, double[].class, new DoubleArrayTagConverter());
        register(FloatArrayTag.class, float[].class, new FloatArrayTagConverter());
        register(LongArrayTag.class, long[].class, new LongArrayTagConverter());
        register(SerializableArrayTag.class, Serializable[].class, new SerializableArrayTagConverter());
        register(SerializableTag.class, Serializable.class, new SerializableTagConverter());
        register(ShortArrayTag.class, short[].class, new ShortArrayTagConverter());
        register(StringArrayTag.class, String[].class, new StringArrayTagConverter());
    }
    
    /**
     * Registers a converter with the converter registry. Will throw an exception if the tag
     * has already been registered.
     *
     * @param tag: Tag type class to register the converter to.
     * @param type: Value type class to register the converter to.
     * @param converter: Converter to register.
     * @throws ConverterRegisterException: If an error occurs while registering the converter.
     */
    public static <T extends Tag, V> void register (Class<T> tag, Class<V> type, TagConverter<T, V> converter) throws ConverterRegisterException {
        
        if (tagToConverter.containsKey(tag))
            throw new TagRegisterException("Type conversion to tag " + tag.getName() + " is already registered.");
            
        if (typeToConverter.containsKey(type))
            throw new TagRegisterException("Tag conversion to type " + type.getName() + " is already registered.");
            
        tagToConverter.put(tag, converter);
        typeToConverter.put(type, converter);
    }
    
    /**
     * Converts the given tag to a value.
     *
     * @param tag: Tag to convert.
     * @return V: The converted value.
     * @throws ConversionException: If a suitable converter could not be found.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Tag, V> V convertToValue (T tag) throws ConversionException {
        
        if (tag == null || tag.getValue() == null)
            return null;
            
        if (!tagToConverter.containsKey(tag.getClass()))
            throw new ConversionException("Tag type " + tag.getClass().getName() + " has no converter.");
            
        TagConverter<T, ?> converter = (TagConverter<T, ?>) tagToConverter.get(tag.getClass());
        return (V) converter.convert(tag);
    }
    
    /**
     * Converts the given value to a tag.
     *
     * @param name: Name of the resulting tag.
     * @param value: Value to convert.
     * @return Tag: The converted tag.
     * @throws ConversionException: If a suitable converter could not be found.
     */
    @SuppressWarnings("unchecked")
    public static <V, T extends Tag> T convertToTag (String name, V value) throws ConversionException {
        
        if (value == null)
            return null;
            
        TagConverter<T, V> converter = (TagConverter<T, V>) typeToConverter.get(value.getClass());
        
        if (converter == null) {
            
            for (Class<?> clazz : getAllClasses(value.getClass())) {
                
                if (typeToConverter.containsKey(clazz)) {
                    
                    try {
                        
                        converter = (TagConverter<T, V>) typeToConverter.get(clazz);
                        break;
                    }
                    
                    catch (ClassCastException e) {
                    
                    }
                }
            }
        }
        
        if (converter == null)
            throw new ConversionException("Value type " + value.getClass().getName() + " has no converter.");
            
        return converter.convert(name, value);
    }
    
    /**
     * Gets all classes and super classes from a class.
     * 
     * @param clazz: The class to grab classes from.
     * @return Set<Class>: A Set of classes.
     */
    private static Set<Class<?>> getAllClasses (Class<?> clazz) {
        
        Set<Class<?>> set = new LinkedHashSet<Class<?>>();
        Class<?> currentClass = clazz;
        
        while (currentClass != null) {
            
            set.add(currentClass);
            set.addAll(getAllSuperInterfaces(currentClass));
            currentClass = currentClass.getSuperclass();
        }
        
        // Make sure Serializable is at the end to avoid mix-ups.
        if (set.contains(Serializable.class)) {
            
            set.remove(Serializable.class);
            set.add(Serializable.class);
        }
        
        return set;
    }
    
    /**
     * Gets all interfaces from a class.
     * 
     * @param clazz: The class to grab interfaces from.
     * @return Set<Class>: A set of interface classes.
     */
    private static Set<Class<?>> getAllSuperInterfaces (Class<?> clazz) {
        
        Set<Class<?>> set = new HashSet<Class<?>>();
        
        for (Class<?> c : clazz.getInterfaces()) {
            
            set.add(c);
            set.addAll(getAllSuperInterfaces(c));
        }
        
        return set;
    }
}
