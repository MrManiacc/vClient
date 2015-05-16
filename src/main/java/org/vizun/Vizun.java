package org.vizun;

import org.vizun.engine.display.Displaymanager;

/**
 * Created by jamesraynor on 5/14/15.
 */
public class Vizun {

    public static void main(String[] args){
        Displaymanager.createdisplay(600, 400, "Vizun");
        System.out.println("init");
    }

}
