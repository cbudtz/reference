package serial;

import javafx.scene.chart.XYChart;
import org.junit.jupiter.api.Test;

public class SerialReadTest implements SerialObserver {
    //ISerialReader SerialReader = new SerialReader();
    ISerialReader SerialReader = new DummySerialReader();

    @Test
    public void testEventSerial() throws InterruptedException {
        SerialReader.registerObserver(this);
        SerialReader.record();
        Thread.sleep(3000);
    }

    @Override
    public void handle(int i) {
        System.out.println("Got Data: "+i);
    }
}
