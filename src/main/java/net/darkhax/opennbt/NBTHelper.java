package net.darkhax.opennbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.darkhax.opennbt.tags.CompoundTag;
import net.darkhax.opennbt.tags.Tag;
import net.darkhax.opennbt.tags.TagCreateException;
import net.darkhax.opennbt.tags.TagRegistry;

/**
 * A class containing methods for reading/writing NBT tags.
 */
public class NBTHelper {
    
    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param path: Path of the file.
     * @return CompoundTag: The read compound tag.
     */
    public static CompoundTag readFile (String path) {
        
        return readFile(new File(path));
    }
    
    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param file File: to read from.
     * @return CompoundTag: The read compound tag.
     */
    public static CompoundTag readFile (File file) {
        
        return readFile(file, true);
    }
    
    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param path: Path of the file.
     * @param compressed: Whether the NBT file is compressed.
     * @return CompoundTag: The read compound tag.
     */
    public static CompoundTag readFile (String path, boolean compressed) {
        
        return readFile(new File(path), compressed);
    }
    
    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param file: File to read from.
     * @param compressed: Whether the NBT file is compressed.
     * @return CompoundTag: The read compound tag.
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
     * @param tag: Tag to write.
     * @param path: Path to write to.
     */
    public static void writeFile (CompoundTag tag, String path) {
        
        writeFile(tag, new File(path));
    }
    
    /**
     * Writes the given root CompoundTag to the given file.
     *
     * @param tag: Tag to write.
     * @param file: File to write to.
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
     * @param tag: Tag to write.
     * @param file: File to write to.
     * @param compressed: Whether the NBT file should be compressed.
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
     * @param in: Input stream to read from.
     * @return Tag: The read tag, or null if the tag is an end tag.
     * @throws IOException: If an I/O error occurs.
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
     * @param out: Output stream to write to.
     * @param tag: Tag to write.
     * @throws IOException: If an I/O error occurs.
     */
    public static void writeTag (DataOutputStream out, Tag tag) throws IOException {
        
        out.writeByte(TagRegistry.getIdFor(tag.getClass()));
        out.writeUTF(tag.getName());
        tag.write(out);
    }
}