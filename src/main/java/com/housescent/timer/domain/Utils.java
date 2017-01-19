/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.housescent.timer.domain;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stoan
 */
public class Utils {

    static Timer timer = null;

    /**
     * Creates the main to execute the relevant action
     *
     * @param time in minutes
     * @param methodName
     */
    public static void executeTimer(long time, final String methodName) {

        timer = new Timer();


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    //Constructs the respective method name from the dropdown titles
                    String newMethodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
                    ;
                    newMethodName = newMethodName.replace(" ", "");
                    TimerUtils timerUtils = new TimerUtils();
                    java.lang.reflect.Method method = timerUtils.getClass().getMethod(newMethodName);
                    //invokes the relevant method
                    method.invoke(timerUtils);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, time);
    }

    /**
     * Cancels the running main.
     */
    public static void resetTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    /**
     * Check String if it's numeric.
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String getIconURL() {
        File file = new File("src/main/resources/images/stopwatch.png");
        String localUrl = null;
        try {
            localUrl = file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return localUrl;
    }
}
