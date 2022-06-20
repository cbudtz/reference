package gui;

import business.MainController;
import javafx.application.Platform;
import javafx.scene.shape.Polyline;

public class GuiController {
    private int x = 0;
    public Polyline poly;
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void onClick(){
        mainController.startRecording();
    }
    public void addPoint(int i){
        Platform.runLater(()->
                poly.getPoints().addAll(0.2*x,10.0*i)
                );
        x++;
    }
}
