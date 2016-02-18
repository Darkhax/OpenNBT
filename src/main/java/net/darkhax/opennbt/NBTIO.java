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
public class NBTIO {
    
    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param path: Path of the file.
     * @return CompoundTag: The read compound tag.
     * @throws IOException: If an I/O error occurs.
     */
    public static CompoundTag readFile (String path) throws IOException {
        
        return readFile(new File(path));
    }
    
    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param file File: to read from.
     * @return CompoundTag: The read compound tag.
     * @throws IOException: If an I/O error occurs.
     */
    public static CompoundTag readFile (File file) throws IOException {
        
        return readFile(file, true);
    }
    
    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param path: Path of the file.
     * @param compressed: Whether the NBT file is compressed.
     * @return CompoundTag: The read compound tag.
     * @throws IOException: If an I/O error occurs.
     */
    public static CompoundTag readFile (String path, boolean compressed) throws IOException {
        
        return readFile(new File(path), compressed);
    }
    
    /**
     * Reads the root CompoundTag from the given file.
     *
     * @param file: File to read from.
     * @param compressed: Whether the NBT file is compressed.
     * @return CompoundTag: The read compound tag.
     * @throws IOException: If an I/O error occurs.
     */
    public static CompoundTag readFile (File file, boolean compressed) throws IOException {
        
        InputStream in = new FileInputStream(file);
        
        if (compressed)
            in = new GZIPInputStream(in);
            
        Tag tag = readTag(new DataInputStream(in));
        
        if (!(tag instanceof CompoundTag))
            throw new IOException("Root tag is not a CompoundTag!");
            
        return (CompoundTag) tag;
    }
    
    /**
     * Writes the given root CompoundTag to the given file.
     *
     * @param tag: Tag to write.
     * @param path: Path to write to.
     * @throws IOException: If an I/O error occurs.
     */
    public static void writeFile (CompoundTag tag, String path) throws IOException {
        
        writeFile(tag, new File(path));
    }
    
    /**
     * Writes the given root CompoundTag to the given file.
     *
     * @param tag: Tag to write.
     * @param file: File to write to.
     * @throws IOException: If an I/O error occurs.
     */
    public static void writeFile (CompoundTag tag, File file) throws IOException {
        
        writeFile(tag, file, true);
    }
    
    /**
     * Writes the given root CompoundTag to the given file.
     *
     * @param tag Tag to write.
     * @param path Path to write to.
     * @param compressed Whether the NBT file should be compressed.
     * @throws IOException If an I/O error occurs.
     */
    public static void writeFile (CompoundTag tag, String path, boolean compressed) throws IOException {
        
        writeFile(tag, new File(path), compressed);
    }
    
    /**
     * Writes the given root CompoundTag to the given file.
     *
     * @param tag: Tag to write.
     * @param file: File to write to.
     * @param compressed: Whether the NBT file should be compressed.
     * @throws IOException: If an I/O error occurs.
     */
    public static void writeFile (CompoundTag tag, File file, boolean compressed) throws IOException {
        
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