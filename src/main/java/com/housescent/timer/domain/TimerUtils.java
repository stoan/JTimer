/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.housescent.timer.domain;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.housescent.timer.domain.OSUtil.*;

/**
 *
 * @author Stoan
 */
public class TimerUtils {

    public static void shutdown() {
        if (isWindows()) {
            winShutdown();
        } else {
            unixShutdown();
        }
    }

    public static void forceShutdown() {
        if (isWindows()) {
            winForceShutdown();
        } else {
            unixForceShutdown();
        }
    }

    public static void restart() {
        if (isWindows()) {
            winRestart();
        } else {
            unixRestart();
        }
    }

    public static void logoff() {
        if (isWindows()) {
            winLogoff();
        } else {
            unixLogoff();
        }
    }

    public static void standby() {
        if (isWindows()) {
            winStandby();
        } else {
            unixStandby();
        }
    }

    public static void lock() {
        if (isWindows()) {
            winLock();
        } else {
            unixLock();
        }
    }

    public static void hibernate() {
        if (isWindows()) {
            winHibernate();
        } else {
            unixHibernate();
        }
    }

    public static void cancelShutDown() {
        if (isWindows()) {
            winCancelShutDown();
        } else {
            unixCancelShutDown();
        }
    }


    /**
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

    public static void unixShutdown() {
        try {
            Runtime.getRuntime().exec("shutdown -h now");
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
     * Force Shutdown Command
     */
    public static void unixForceShutdown() {
        unixShutdown();
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
     * Restart Command
     */
    public static void unixRestart() {
        try {
            Runtime.getRuntime().exec("shutdown -r now");
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
     * Logoff Command
     */
    public static void unixLogoff() {
        try {
            Runtime.getRuntime().exec("logout");
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
     * Sleep Command
     */
    public static void unixStandby() {
        unixShutdown();
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
     * Lock Command
     */
    public static void unixLock() {
        try {
            Runtime.getRuntime().exec("gnome-screensaver-command -l");
            //MAC OS
            Runtime.getRuntime().exec("/System/Library/CoreServices/Menu\\ Extras/User.menu/Contents/Resources/CGSession -suspend");
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
     * Hibernate Command
     */
    public static void unixHibernate() {
        unixShutdown();
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

    /**
     * Cancel computer from being shutdown Command
     */
    public static void unixCancelShutDown() {
        try {
            Runtime.getRuntime().exec("shutdown -c");
        } catch (IOException ex) {
            Logger.getLogger(TimerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
