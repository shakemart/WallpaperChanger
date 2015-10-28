package Wallpaper;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class WallpaperChanger {
    public static interface User32 extends Library {
        User32 INSTANCE = (User32) Native.loadLibrary("user32",User32.class,W32APIOptions.DEFAULT_OPTIONS);
        boolean SystemParametersInfo (int one, int two, String s ,int three);
    }
    public static void ChangeDesktopBackgroundOnWindows(String ruta){
        User32.INSTANCE.SystemParametersInfo(0x0014, 0, ruta , 1);
    }

    public static void ChangeDesktopBackgroundOnOSX(final String filename) {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                File file = null;
                try
                {
                    file = new File(filename);

                    String as[] = {
                            "osascript",
                            "-e", "tell application \"Finder\"",
                            "-e", "set desktop picture to POSIX file \"" + file.getAbsolutePath() + "\"",
                            "-e", "end tell"
                    };
                    Runtime runtime = Runtime.getRuntime();
                    Process process;
                    process = runtime.exec(as);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }
    public static void main(String[] args) {

        String ruta="";

        ruta=InputStreamAFile(WallpaperChanger.class.getResourceAsStream("image.jpg"),"imagen.jpg");
        System.out.printf("path:"+ruta);

        System.out.printf("\nSO:"+System.getProperty("os.name"));

        if(System.getProperty("os.name").toLowerCase().contains("windows")){
            ChangeDesktopBackgroundOnWindows(ruta);
        }else if(System.getProperty("os.name").toLowerCase().contains("macos")){
            ChangeDesktopBackgroundOnOSX(ruta);
        }

		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}

        File borrar = new File(ruta );
        borrar.delete();



    }
    public static String InputStreamAFile(InputStream entrada,String archivoDestino){
        String retorno= "";
        try{
            File f=new File(archivoDestino);//Aqui le dan el nombre y/o con la ruta del archivo salida
            retorno = f.getAbsolutePath() ;
            OutputStream salida=new FileOutputStream(f);
            byte[] buf =new byte[1024];//Actualizado me olvide del 1024
            int len;
            while((len=entrada.read(buf))>0){
                salida.write(buf,0,len);
            }
            salida.close();
            entrada.close();

            //System.out.println("Se realizo la conversion con exito");
        }catch(IOException e){
            System.out.println("Se produjo el error : "+e.toString());
        }
        return retorno;
    }
}
