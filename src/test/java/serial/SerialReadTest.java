package serial;

import org.junit.jupiter.api.Test;

public class SerialReadTest implements SerialObserver {
    SerialReader ser = new SerialReader();

    @Test
    public void testEventSerial() throws InterruptedException {
        ser.registerObserver(this);
        ser.record();
        Thread.sleep(3000);
    }

    @Override
    public void handle(int i) {
        System.out.println("Got Data: "+i);
    }
}
