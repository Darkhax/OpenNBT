package net.darkhax.opennbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.darkhax.opennbt.tags.CompoundTag;
import net.darkhax.opennbt.tags.Tag;
import net.darkhax.opennbt.tags.TagCreateException;
import net.darkhax.opennbt.tags.TagRegistry;

/**
 * A class containing various helpers and utilities.
 */
public class NBTHelper {
    
    /**
     * The current version of the library. Follows a Major-Minor-Build structure. A minor
     * update will only take place when there is a change which potentially breaks
     * functionality.
     */
    public static final String VERSION = "2.4.0";
    
    /**
     * A standard Comparator for comparing two tags. For the first tag to be greater than the
     * second, it must not be null, and the tags must not be equal. For the first tag to be
     * less than the second, it must not be greater than the second tag, and the second tag
     * must not be null. In all other cases they are equal.
     */
    public static final Comparator<Tag> NBT_COMPARATOR = new NBTComparator();
    
    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param path Path of the file.
     * @return CompoundTag The read compound tag.
     */
    public static CompoundTag readFile (String path) {
        
        return readFile(new File(path));
    }
    
    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param file File to read from.
     * @return CompoundTag The read compound tag.
     */
    public static CompoundTag readFile (File file) {
        
        return readFile(file, true);
    }
    
    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param path Path of the file.
     * @param compressed Whether the NBT file is compressed.
     * @return CompoundTag The read compound tag.
     */
    public static CompoundTag readFile (String path, boolean compressed) {
        
        return readFile(new File(path), compressed);
    }
    
    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param file File to read from.
     * @param compressed Whether the NBT file is compressed.
     * @return CompoundTag The read compound tag.
     */
    public static CompoundTag readFile (File file, boolean compressed) {
        
        try {
            
            InputStream in = new FileInputStream(file);
            
            if (compressed)
                in = new GZIPInputStream(in);
                
            Tag tag = readTag(new DataInputStream(in));
            
            if (!(tag instanceof CompoundTag))
                throw new IOException("Root tag is not a CompoundTag!");
                
            return (CompoundTag) tag;
        }
        
        catch (IOException e) {
            
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Writes the given root CompoundTag to the given file.
     *
     * @param tag Tag to write.
     * @param path Path to write to.
     */
    public static void writeFile (CompoundTag tag, String path) {
        
        writeFile(tag, new File(path));
    }
    
    /**
     * Writes the given root CompoundTag to the given file.
     *
     * @param tag Tag to write.
     * @param file File to write to.
     */
    public static void writeFile (CompoundTag tag, File file) {
        
        writeFile(tag, file, true);
    }
    
    /**
     * Writes the given root CompoundTag to the given file.
     *
     * @param tag Tag to write.
     * @param path Path to write to.
     * @param compressed Whether the NBT file should be compressed.
     */
    public static void writeFile (CompoundTag tag, String path, boolean compressed) {
        
        writeFile(tag, new File(path), compressed);
    }
    
    /**
     * Writes the given root CompoundTag to the given file.
     *
     * @param tag Tag to write.
     * @param file File to write to.
     * @param compressed Whether the NBT file should be compressed.
     */
    public static void writeFile (CompoundTag tag, File file, boolean compressed) {
        
        try {
            
            if (!file.exists()) {
                
                if (file.getParentFile() != null && !file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                    
                file.createNewFile();
            }
            
            OutputStream out = new FileOutputStream(file);
            
            if (compressed)
                out = new GZIPOutputStream(out);
                
            writeTag(new DataOutputStream(out), tag);
            out.close();
        }
        
        catch (IOException e) {
            
            e.printStackTrace();
        }
    }
    
    /**
     * Reads an NBT tag from a DataInputStream.
     *
     * @param in Input stream to read from.
     * @return Tag The read tag, or null if the tag is an end tag.
     * @throws IOException If an I/O error occurs.
     */
    public static Tag readTag (DataInputStream in) throws IOException {
        
        int id = in.readUnsignedByte();
        
        if (id == 0)
            return null;
            
        String name = in.readUTF();
        Tag tag;
        
        try {
            
            tag = TagRegistry.createInstance(id, name);
        }
        
        catch (TagCreateException e) {
            
            throw new IOException("Failed to create tag.", e);
        }
        
        tag.read(in);
        return tag;
    }
    
    /**
     * Writes a tag to an output stream.
     *
     * @param out Output stream to write to.
     * @param tag Tag to write.
     * @throws IOException If an I/O error occurs.
     */
    public static void writeTag (DataOutputStream out, Tag tag) throws IOException {
        
        out.writeByte(TagRegistry.getIdFor(tag.getClass()));
        out.writeUTF(tag.getName());
        tag.write(out);
    }
    
    /**
     * Uses an array of tag names, to try and dig through the layers of a CompoundTag and get a
     * specific tag. This is not more efficient, however it is cleaner then doing it all by
     * hand.
     * 
     * An example of where this could be used, is if you want to access a tag which is several
     * layers deep within another compound tag. Rather than retrieving each compound tag
     * individually, you can use this to loop through the tags automatically. The down side to
     * this approach is that it requires previous knowledge of the tag structure.
     * 
     * @param tag The CompoundTag to dig through.
     * @param steps An array of tag names to dig through.
     * @return CompoundTag The deepest tag that could be found using the provided steps.
     */
    public static CompoundTag getDeepTag (CompoundTag tag, String[] steps) {
        
        CompoundTag deepTag = tag;
        
        if (tag != null)
            for (String tagName : steps)
                if (deepTag.hasTag(tagName))
                    deepTag = deepTag.getCompoundTag(tagName);
                    
        return deepTag;
    }
}