![Wall](http://i.imgur.com/6L5RfpN.png)

##Review

Java application capable of change the user's desktop wallpaper to one .jpg image included in the executable jar file. It includes a html file, where users click a "Change Wallpaper" button to download the WallpaperChanger.jar file, then execute the jar file and it will do the job.

##How to use

* Create a directory in a Java project sources root directory like "Wallpaper".
* Put the .java file inside that directory.
* Create a resources root directory on the Java project.
* Create a directory in the resources with the same name as the directory where the .java file is located (in this case Wallpaper).
* Put an image in the resources root directory with the name "image.jpg".
* Compile project.

##Dependencies

* JNA 4.1.0 or higher.
* JNA-Platform 4.1.0 or higher.

##Support

* Windows. Tested on XP, Vista, 7, 8 and 10.
* Mac OSX. Tested on "Yosemite" and "El Capitan"

##Aditional Information

The WallpaperChanger.html file contains a simple html code to show a button to download the executable file, this html file also contains the Arrow.js code to assist users to click on their downloaded file and open it. This way, users only need to download and click the file to change the desktop background.

The href code on this html must be changed depending on the directory where the .jar file is located:

    <a href="SERVER_DIRECTORY\WallpaperChanger.jar" download="WallpaperChanger.jar" onclick="Arrow.show()">Set Wallpaper</a>
