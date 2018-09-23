# OpenNBT
A Java library for reading and writing NBT files as specified [here](http://minecraft.gamepedia.com/NBT_format). In addition to the base data types supported by the NBT format, OpenNBT allows for new data types to be supported, and comes pre packaged with support for other data types. If you are planning to use OpenNBT in a project that is not related to Minecraft, I would recommend [Extremely Simple Storage](https://github.com/darkhax/Extremely-Simple-Storage). Extremely Simple Storage offers similar functionality to OpenNBT but is not associated Minecraft and is not tag based.

## NOTICE
I no longer have a need for this project, so it has been discontinued. All files can be found on the [new maven](http://maven.epoxide.xyz/net/darkhax/opennbt/OpenNBT/). If you are looking for an alternative storage option that is just as simple, check out [Extremely Simple Storage](https://github.com/darkhax/Extremely-Simple-Storage) which offers the same functionality as OpenNBT. Extremely Simple Storage has faster read/write times, greater flexability and lighter weight than OpenNBT. It is also completely unassociated with Minecraft.

## Source Code
The latest source code can be found here on [GitHub](https://github.com/darkhax/OpenNBT). If you are using Git, you can use the following command to clone the project: `git clone git:github.com/darkhax/OpenNBT.git`

## Building from Source
This project can be built using the Gradle Wrapper included in the project. When the `gradlew build` command is executed, a compiled JAR will be created in `~/build/libs`. Sources and Javadocs will also be generated in the same directory. Alternatively the latest builds of OpenNBT along with Sources and Javadocs can be found [here](http://maven.rubbix.net/net/darkhax/opennbt/OpenNBT)

## Contributing
This project is open to contributions from other members of the community. If you would like to contribute towards this project you can open an issue, or create a new pull request. If you create a new pull request, please be sure to read the project [guidelines and coding standards](https://github.com/darkhax/OpenNBT/blob/master/CONTRIBUTING.md).

## Quick Start
OpenNBT is very easy to start using. The below is an example of how to read and write an NBT file using OpenNBT.
```
  // Creates a new CompoundTag with various data stored.
  CompoundTag tag = new CompoundTag("TestTag");
  tag.setInt("TestInteger", 1337);
  tag.setString("TestString", "Hello World!");
  tag.setIntArray("TestIntegerArray", new int[] { 200, 200, 208, 208, 203, 205, 203, 205, 48, 30 });
  
  // Writes the tag to a new file called NBTExample.nbt. Any extension can be used. 
  NBTHelper.writeFile(tag, "NBTExample.nbt");
  
  // Reads the NBT data from the NBTExample.nbt file.
  tag = NBTHelper.readFile("NBTExample.nbt");
```

## Legal Information
OpenNBT is licensed under the [MIT license](https://opensource.org/licenses/mit-license.html). Please see the `LICENSE.txt` for more details. 

## Credits
* [Steveice10](https://github.com/Steveice10) - Original author of OpenNBT.
* [Darkhax](https://github.com/darkhax) - Maintainer of this specific fork of OpenNBT.
* [Notch](http://notch.net) - Designer of the original NBT specification.
* [Lclc98](https://github.com/lclc98) - Maintainer of build system and gradle setup. 
