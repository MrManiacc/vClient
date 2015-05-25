package org.vizun.util;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.vizun.Vizun;
import org.vizun.engine.Game;
import org.vizun.lib.DataFolder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jamesraynor on 5/25/15.
 */
public class ResourceLoader {
    private Game instance;
    private Logger logger;

    public ResourceLoader(Game main){
        this.instance = main;
        logger = instance.getLogger();
      }

    /**
     * Checks if the natives are already downloaded, if they aren't it downloades them and loades them, if they are it just loades the min
     */
    public void loadNatives(){
        if(checkNatives(logger))
            downloadNatives(logger);
        System.setProperty("org.lwjgl.librarypath", Vizun.getDataFolder().getNativesFolder().getAbsolutePath());
        logger.debug("Natives loaded from {}", Vizun.getDataFolder().getNativesFolder().getAbsolutePath());
    }

    /**
     * Checks to see if the natives need to be downloaded or not
     * @return Native files
     */
    private static boolean checkNatives(Logger logger){
        if(Vizun.getDataFolder().getNativesFolder().list().length>0){
            return false;
        }
        return true;
    }

    /**
     * Downloads the required system natives for openGL instance. Will only use mediafire temporally
     * @param logger
     */
    private void downloadNatives(Logger logger){
        logger.debug("Downloading natives");
        //TODO download natives
        try{
            if(Vizun.getDataFolder().getOs() == DataFolder.OPERATING_SYSTEM.Mac) {
                DownloadManager.downloadFile(logger, "http://download615.mediafire.com/4doe75vcp6fg/61pxn61sy146tyy/openal.dylib", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "openal.dylib");
                DownloadManager.downloadFile(logger, "http://download1382.mediafire.com/dmgdhk82oh4g/4bujeooimpcrkh3/libjinput-osx.dylib", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "libjinput-osx.dylib");
                DownloadManager.downloadFile(logger, "http://download1073.mediafire.com/vx4ikzi5wvqg/cx0s087fmvgm1pg/liblwjgl.dylib", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "liblwjgl.dylib");
            }else if(Vizun.getDataFolder().getOs() == DataFolder.OPERATING_SYSTEM.Windows){
                DownloadManager.downloadFile(logger, "http://download1318.mediafire.com/kdn5an9fyjeg/njw3bga841w86ea/OpenAL32.dll", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "OpenAL32.dll");
                DownloadManager.downloadFile(logger, "http://download1921.mediafire.com/2tdzuactoqdg/h62f0n12iwog43v/OpenAL64.dll", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "OpenAL64.dll");
                DownloadManager.downloadFile(logger, "http://download1164.mediafire.com/6t35x5uqu9ng/9c3ubk2ccj502ho/lwjgl64.dll", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "lwjgl64.dll");
                DownloadManager.downloadFile(logger, "http://download1952.mediafire.com/az232bu22rig/n647i19agxn38bh/lwjgl.dll", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "lwjgl.dll");
                DownloadManager.downloadFile(logger, "http://download1521.mediafire.com/9p317s6s5amg/kudba1remdn9aa2/jinput-dx8_64.dll", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "jinput-dx8_64.dll");
                DownloadManager.downloadFile(logger, "http://download1014.mediafire.com/4kgvib9e9udg/4k6kssfwfhrhx1n/jinput-raw_64.dll", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "jinput-raw_64.dll");
                DownloadManager.downloadFile(logger, "http://download1055.mediafire.com/9l558t9kw1tg/649kkaxb3k5ohp7/jinput-dx8.dll", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "jinput-dx8.dll");
                DownloadManager.downloadFile(logger, "http://download1422.mediafire.com/yg6tb3p2gwvg/dpfii0ygj2dhbv1/jinput-raw.dll", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "jinput-raw.dll");
            }
            else if(Vizun.getDataFolder().getOs() == DataFolder.OPERATING_SYSTEM.Linux){
                //TODO download natives for linux
            }
        }
        catch(MalformedURLException e){
            logger.error("An error occured while downloading natives");
        }catch(IOException e){
            logger.error("An error occuerd while write file to destination");
        }
        logger.debug("Natives downloaded");
    }
}
