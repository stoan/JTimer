/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stone.timer.domain;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stoan
 */
public class TimerUtils {

    /**
     * Windows Commands
     */
    /**
     * Shutdown Command
     */
    public static void winShutdown() {
        try {
            Runtime.getRuntime().exec("shutdown -s");
        } catch (IOException ex) {
            Logger.getLogger(TimerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Force Shutdown Command
     */
    public static void winForceShutdown() {
        try {
            Runtime.getRuntime().exec("shutdown -s -p");
        } catch (IOException ex) {
            Logger.getLogger(TimerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Restart Command
     */
    public static void winRestart() {
        try {
            Runtime.getRuntime().exec("shutdown -r");
        } catch (IOException ex) {
            Logger.getLogger(TimerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Logoff Command
     */
    public static void winLogoff() {
        try {
            Runtime.getRuntime().exec("shutdown -l");
        } catch (IOException ex) {
            Logger.getLogger(TimerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sleep Command
     */
    public static void winStandby() {
        try {
            Runtime.getRuntime().exec("Rundll32.exe powrprof.dll,SetSuspendState Sleep");
        } catch (IOException ex) {
            Logger.getLogger(TimerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      /**
     * Lock Command
     */
    public static void winLock() {
        try {
            Runtime.getRuntime().exec("Rundll32.exe user32.dll,LockWorkStation");
        } catch (IOException ex) {
            Logger.getLogger(TimerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Hibernate Command
     */
    public static void winHibernate() {
        try {
            Runtime.getRuntime().exec("shutdown -h");
        } catch (IOException ex) {
            Logger.getLogger(TimerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cancel computer from being shutdown Command
     */
    public static void winCancelShutDown() {
        try {
            Runtime.getRuntime().exec("shutdown -a");
        } catch (IOException ex) {
            Logger.getLogger(TimerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
