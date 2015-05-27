package org.vizun.util;

import org.apache.commons.io.FileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

public class DownloadManager {
    
    private static final Logger logger = LoggerFactory.getLogger("org.vizun");

    public static void downloadFile(Logger logger, String url, String path, String name) throws MalformedURLException, IOException {
        File file = new File(path + File.separator + name);
        if(!file.exists()){
            FileUtils.copyURLToFile(new URL(url), file);
            logger.debug("Downloaded file {}", name);
        }
    }
}
