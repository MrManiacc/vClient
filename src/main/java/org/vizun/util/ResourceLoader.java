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

    public void loadConfigurations() {
        if (checkConfig(logger)) {
            downloadConfigs(logger);
            logger.debug("Configurations downloaded");
        }
    }
    public void loadTextures(){
        if (checkTextures(logger))
            downloadTextures(logger);

        }
    public void loadShaders(){
        if(checkShaders(logger))
            downloadShaders(logger);
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
    private static boolean checkTextures(Logger logger) {
        if (Vizun.getDataFolder().getTextureFolder().list().length > 0)
            return false;
        return true;
    }
    private static boolean checkShaders(Logger logger) {
        if (Vizun.getDataFolder().getShaderFolder().list().length > 0)
            return false;
        return true;
    }
    private static boolean checkConfig(Logger logger) {
        if (Vizun.getDataFolder().getConfigFolder().list().length > 3)
            return false;
        return true;
    }

    /**
     * Downloads the required system natives for openGL instance. Will only use mediafire temporally
     * @param logger
     */
    private void downloadNatives(Logger logger){
        logger.debug("Downloading natives");
        try {
                if (Vizun.getOs() == DataFolder.OPERATING_SYSTEM.Mac) {
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/0piww9yqly09enh/openal.dylib?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "openal.dylib");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/f8v0agpiq31dfvm/libjinput-osx.dylib?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "libjinput-osx.dylib");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/vapsvfz01hgyycc/liblwjgl.dylib?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "liblwjgl.dylib");
                } else if (Vizun.getOs() == DataFolder.OPERATING_SYSTEM.Windows) {
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/d0numqhxa1grcft/OpenAL32.dll?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "OpenAL32.dll");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/sdbe2edd9ee4nry/OpenAL64.dll?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "OpenAL64.dll");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/3kteh30eup5bg3v/lwjgl64.dll?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "lwjgl64.dll");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/wfe390oeptprenw/lwjgl.dll?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "lwjgl.dll");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/rv2j8skbvztj8r7/jinput-dx8_64.dll?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "jinput-dx8_64.dll");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/yxyivugbl7169bg/jinput-raw_64.dll?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "jinput-raw_64.dll");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/uimdseub821lpfi/jinput-dx8.dll?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "jinput-dx8.dll");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/svcked2y0lqc6s1/jinput-raw.dll?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "jinput-raw.dll");
                } else if (Vizun.getOs() == DataFolder.OPERATING_SYSTEM.Linux) {
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/2ns4vdsq6u37yfw/libjinput-linux64.so?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "libjinput-linux64.so");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/1d2v0b6ykc9r5sd/libjinput-linux.so?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "libjinput-linux.so");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/09h0rkock5zqsrz/liblwjgl64.so?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "liblwjgl64.so");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/w67p0nwi5wbsrxd/liblwjgl.so?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "liblwjgl.so");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/737hm65qn4pq8ym/libopenal64.so?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "libopenal64.so");
                    DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/zjh6kj0ses1hemm/libopenal.so?dl=1", Vizun.getDataFolder().getNativesFolder().getAbsolutePath(), "libopenal.so");
                }
        } catch (MalformedURLException e) {
            logger.error("An error occured while downloading natives");
        } catch (IOException e) {
            logger.error("An error occuerd while write native file to destination");
        }
    }

    private void downloadConfigs(Logger logger) {
        try {
            DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/e827v187mzuc62q/english.json?dl=1", Vizun.getDataFolder().getConfigFolder().getAbsolutePath(), "english.json");
            DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/96caj020gclhovr/german.json?dl=1", Vizun.getDataFolder().getConfigFolder().getAbsolutePath(), "german.json");
            DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/9nnfow3uh2mkz3c/spanish.json?dl=1", Vizun.getDataFolder().getConfigFolder().getAbsolutePath(), "spanish.json");
            DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/1r2h4kfi3xthe81/display.json?dl=1", Vizun.getDataFolder().getConfigFolder().getAbsolutePath(), "display.json");
        } catch (MalformedURLException e) {
            logger.error("An error occured while downloading configs");
        } catch (IOException e) {
            logger.error("An error occuerd while write config file to destination");
        }
    }
    private void downloadShaders(Logger logger){
        try {
            DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/uyvsuafhmh4qedd/vertexShader.txt?dl=1", Vizun.getDataFolder().getShaderFolder().getAbsolutePath(), "vertexShader.txt");
            DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/cakrf5znwpyojm1/fragmentShader.txt?dl=1", Vizun.getDataFolder().getShaderFolder().getAbsolutePath(), "fragmentShader.txt");
        } catch (MalformedURLException e) {
            logger.error("An error occured while downloading shaders");
        } catch (IOException e) {
            logger.error("An error occuerd while write shaders file to destination");
        }
    }
    private void downloadTextures(Logger logger){
        try {
            DownloadManager.downloadFile(logger, "https://www.dropbox.com/s/wqat57etnvvvucn/BasicBlock.png?dl=1", Vizun.getDataFolder().getTextureFolder().getAbsolutePath(), "BasicBlock.png");
         } catch (MalformedURLException e) {
            logger.error("An error occured while downloading shaders");
        } catch (IOException e) {
            logger.error("An error occuerd while write shaders file to destination");
        }
    }
}