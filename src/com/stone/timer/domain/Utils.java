/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stone.timer.domain;

import java.lang.reflect.InvocationTargetException;
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
     * Creates the timer to execute the relevant action
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
                    //Contructs the respective method name from the dropdown titles
                    String newMethodName = "win" + methodName;
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
     * Creates the timer to execute the relevant action
     *
     * @param time in time format
     * @param methodName
     */
    public static void executeTimerDate(Date time, final String methodName) {
        if (timer == null) {
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    String newMethodName = "win" + methodName;
                    newMethodName = newMethodName.replace(" ", "");
                    TimerUtils timerUtils = new TimerUtils();
                    java.lang.reflect.Method method = timerUtils.getClass().getMethod(newMethodName);
                    System.out.println("Method Name: " + newMethodName);
                    method.invoke(timerUtils);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, time);
    }

    /**
     * Cancels the running timer.
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
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
