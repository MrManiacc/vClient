package org.vizun.util;

import org.apache.commons.io.FileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vizun.Vizun;

import java.io.File;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

public class DownloadManager {
    
    private static final Logger logger = LoggerFactory.getLogger("org.vizun");

    public static void downloadFile(String url, String path, String name) throws MalformedURLException, IOException {
        FileUtils.copyURLToFile(new URL(url), new File(path + File.separator + name));
        logger.debug("Downloaded file {}", name);
    }
}
