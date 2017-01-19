/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.housescent.timer.domain.TimerUtils;
import com.housescent.timer.domain.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Stoan
 */
public class Timer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("JTimer v0.1");

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/timer.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        //Set Logo icon
        stage.getIcons().add(new Image(Utils.getIconURL()));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Utils.resetTimer();
        TimerUtils.winCancelShutDown();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}