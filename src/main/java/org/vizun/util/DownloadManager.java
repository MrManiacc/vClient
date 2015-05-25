package org.vizun.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jamesraynor on 5/25/15.
 */
public class DownloadManager {

    public static void downloadFile(Logger logger, String url, String path, String name)throws MalformedURLException, IOException {
        FileUtils.copyURLToFile(new URL(url), new File(path + File.separator + name));
        logger.debug("Downloaded file {}", name);
    }
}
