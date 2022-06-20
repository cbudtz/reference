package gui;

import business.MainController;
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
        poly.getPoints().addAll(1.0*x,1.0*i);
        x++;
    }
}
