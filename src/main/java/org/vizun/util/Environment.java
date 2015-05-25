package org.vizun.util;

/**
 * Computer environment and system information util class* 
 */
public class Environment {
  
  enum OPERATING_SYSTEM {WINDOWS, LINUX, OSX, UNSUPPORTED}

  public static OPERATING_SYSTEM getOperatingSystem() {
    String OS = System.getProperty("os.name").toLowerCase();
    if (OS.contains("win")) {
      return OPERATING_SYSTEM.WINDOWS;
    } else if (OS.contains("mac")) {
      return OPERATING_SYSTEM.OSX;
    } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix") || OS
	.contains("linux")) {
      return OPERATING_SYSTEM.LINUX;
    } else {
      return OPERATING_SYSTEM.UNSUPPORTED;
    }
  }
}
