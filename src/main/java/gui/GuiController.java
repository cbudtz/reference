package gui;

import business.MainController;
import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.control.CheckBox;
import javafx.scene.shape.Polyline;

public class GuiController {
    public CheckBox checkBox;
    private int x = 0;
    public Polyline poly;
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void onClick(){
        mainController.startRecording(checkBox.isSelected());

    }

    public void addPoint(int i){
        Platform.runLater(()->
                poly.getPoints().addAll(0.5*x,10.0*i)
        );
        x++;
    }
}
