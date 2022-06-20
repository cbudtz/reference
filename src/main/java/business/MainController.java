package business;

import data.Dao;
import data.IDao;
import gui.GuiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import serial.DummySerialReader;
import serial.ISerialReader;
import serial.SerialObserver;

public class MainController extends Application implements SerialObserver {
     ISerialReader reader = new DummySerialReader();
     IDao dao = new Dao();
    private GuiController guiController;

    public static void run(){
         launch();
     }

    public void startRecording() {
        dao.start();
        reader.registerObserver(this);
        reader.record();
    }

    @Override
    public void handle(int i) {
        dao.enqueue(i);
        guiController.addPoint(i);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui.fxml"));
        Scene scene = loader.load();
        guiController = loader.getController();
        guiController.setMainController(this);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((WindowEvent e)->System.exit(1));
    }
}
