package com.example.myvlc;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;

import static java.lang.System.exit;
import static javafx.beans.binding.Bindings.*;

public class HelloController {
    //declarations
    private Media m;
    @FXML
    private MediaView mview;
    private MediaPlayer mplayer;
    private HBox hbox;
    private String Mypath;

    @FXML
    private Button MyFile;

    @FXML
    private Slider Timer;

    @FXML
    private Button Backward;

    @FXML
    private Button Play;

    @FXML
    private Button Pause;

    @FXML
    private Button Stop;

    @FXML
    private Button Forward;

    @FXML
    private Slider Volume;

    //playing the media backwards with on click button
    @FXML
    void ActionSlowBackward(ActionEvent event) {
        mplayer.setRate(0.5);
    }
    //playing the media forward with on clickforward button
    @FXML
    void ActionFastForward(ActionEvent event) {
        mplayer.setRate(2);
    }
    //pausing the media with the oause button
    @FXML
    void ActionPause(ActionEvent event) {
        mplayer.pause();
    }
    //resuming or playing the media at a normal speed
    @FXML
    void ActionPlay(ActionEvent event) {
        Button button;
        mplayer.play();
        mplayer.setRate(1);//setting the normal rate
    }

    @FXML
    void ActionStop(ActionEvent event) {
        mplayer.stop();//stoping and restarting the media
    }
    //selecting a media from the file explore
    @FXML
    void TopFile(ActionEvent event) {
        FileChooser files = new FileChooser();
        File file = files.showOpenDialog(null);// leading to the file explorer
        Mypath = file.toURI().toString();

        //set where the media should play after the selection is made
        if (Mypath != null) {
            Media view = new Media(Mypath);
            mplayer = new MediaPlayer(view);
            mview.setMediaPlayer(mplayer);// setting up the selected media to be played / viewed on the media player

            mplayer.play();//selected audio / video auto play
        }

        //setting the responsiveness of the video/ media view acccording to the screen size
        DoubleProperty height = mview.fitHeightProperty();
        DoubleProperty width = mview.fitWidthProperty();

        //setting up the width and height size on the screen
        height.bind(Bindings.selectDouble(mview.sceneProperty(), "height"));
        width.bind(Bindings.selectDouble(mview.sceneProperty(), "width"));

        mplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                Timer.setValue(newValue.toSeconds());
            }
        });
        Timer.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mplayer.seek(javafx.util.Duration.seconds(Timer.getValue()));
            }
        });
        //defines a function to be called whe the mouse is dragged from one position to the other
        Timer.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //duration set up
                mplayer.seek(javafx.util.Duration.seconds(Timer.getValue()));
            }
        });
    }
    //volume increase when volume slide bar is clicked or dragged
    @FXML
    void ActionVolume(MouseEvent event) {
        Volume.setValue(mplayer.getVolume() * 200);//setting up the media playing volume
        Volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mplayer.setVolume(Volume.getValue() / 200);// volume value
            }
           }
        );
    }
    //button used to close the program
    public void closeAction(ActionEvent event) {
        exit(0);
    }
}