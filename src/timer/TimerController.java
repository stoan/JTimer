/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timer;

import com.stone.timer.domain.TimerUtils;
import com.stone.timer.domain.Utils;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Stoan
 */
public class TimerController implements Initializable {

    @FXML
    private ComboBox<String> comboaction;
    @FXML
    private ComboBox<String> combotime;
    @FXML
    private TextField txtcustomtime;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty();
    private Timeline timeline;
    @FXML
    private Label timerLabel;
    @FXML
    private Label msgLabel;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ImageView logo;
    private static Timer msgTimer = null;

    /**
     * Starts the selected action type with the selected time
     *
     * @param event
     */
    @FXML
    private void handleBtnStartAction(ActionEvent event) {
        long time = 0;
        String action = null;

        try {
            if (txtcustomtime == null || txtcustomtime.getText().isEmpty()) {
                /*Gets the time from the dropdown control by substring the first two
                 * characters and converts the values to miliseconds
                 */

                if (Utils.isNumeric(combotime.getValue().substring(2, 3))) {
                    time = Integer.parseInt(combotime.getValue().substring(0, 3)) * 60 * 1000;
                } else if (Utils.isNumeric(combotime.getValue().substring(1, 2))) {
                    time = Integer.parseInt(combotime.getValue().substring(0, 2)) * 60 * 1000;
                } else {
                    time = Integer.parseInt(combotime.getValue().substring(0, 1)) * 60 * 1000;
                }
            } else {
                time = Integer.parseInt(txtcustomtime.getText()) * 60 * 1000;
            }

            //deduces the respective method name from the dropdown titles
            action = comboaction.getValue();

            if (time > 0 && !action.isEmpty()) {
                Utils.executeTimer(time, action);
            } else {
                throw new NullPointerException();
            }

            //Timer and Progress Bar
            int timeprogress = (int) (time / 1000);

            if (timeline != null) {
                timeline.stop();
            }
            timeSeconds.set((timeprogress + 1) * 100);

            timerLabel.textProperty().bind(timeSeconds.divide(100).asString());
            progressBar.progressProperty().bind(timeSeconds.divide(timeprogress * 100.0).subtract(1).multiply(-1));

            timeline = new Timeline();
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(timeprogress + 1), new KeyValue(timeSeconds, 0)));
            timeline.playFromStart();

        } catch (NumberFormatException e) {
            msgTimer = new Timer();

            msgLabel.setText("Please enter minutes in numbers only.  e.g. 50");

            msgTimer.schedule(new TimerTask() {
                @Override
                public void run() {

                    /*
                     * Can't update the UI from a thread
                     * that is not the application thread. But still, if you really want to
                     * modify your UI from a different thread use the Platform.runlater(new Runnable())
                     * method. And put your modifications inside the Runnable object.
                     * 
                     * http://stackoverflow.com/questions/17850191/why-am-i-getting-java-lang-illegalstateexception-on-javafx
                     */
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            msgLabel.setText("");
                        }
                    });


                }
            }, 3000);

        } catch (NullPointerException npe) {
            msgTimer = new Timer();

            msgLabel.setText("Please enter your desired time and action.");

            msgTimer.schedule(new TimerTask() {
                @Override
                public void run() {

                    /*
                     * Can't update the UI from a thread
                     * that is not the application thread. But still, if you really want to
                     * modify your UI from a different thread use the Platform.runlater(new Runnable())
                     * method. And put your modifications inside the Runnable object.
                     * 
                     * http://stackoverflow.com/questions/17850191/why-am-i-getting-java-lang-illegalstateexception-on-javafx
                     */
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            msgLabel.setText("");
                        }
                    });


                }
            }, 3000);
        }
    }

    /**
     * Cancels the action timer before it's fired.
     *
     * @param event
     */
    @FXML
    private void handleBtnResetAction(ActionEvent event) {
        Utils.resetTimer();
        if (timeline != null) {
            timeline.stop();
        }
    }

    /**
     * Prevents the computer from shutting down once the action has been fired.
     *
     * @param event
     */
    @FXML
    private void handleBtnAbortAction(ActionEvent event) {
        TimerUtils.winCancelShutDown();
        if (timeline != null) {
            timeline.stop();
        }
    }

    /**
     * Ends the application.
     *
     * @param event
     */
    @FXML
    private void handleBtnExitAction(ActionEvent event) {
        Utils.resetTimer();
        TimerUtils.winCancelShutDown();
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set Logo Image
        logo.setImage(new Image("/stopwatch.png"));
    }
}
