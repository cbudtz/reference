package serial;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.util.Arrays;

public class SerialReader implements ISerialReader {
    public static final int BAUD_RATE = 38400;
    public static final int PORT_NO = 0;
    String readData = "";

    private SerialObserver observer;

    @Override
    public void registerObserver(SerialObserver observer){
        this.observer=observer;
    }

    @Override
    public void record(){
        SerialPort[] commPorts = SerialPort.getCommPorts();
        System.out.println("Found " + commPorts.length + " SerialPorts");
        System.out.println("Opening port: " + commPorts[0].getSystemPortName());
        SerialPort port = commPorts[PORT_NO];
        port.setBaudRate(BAUD_RATE);
        port.openPort();
        port.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                if (serialPortEvent.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return;
                }
                if(observer!=null){
                    byte[] receivedData = serialPortEvent.getReceivedData();
                    readData += new String(receivedData);
                    if (readData.length() >10){
                        String[] split = readData.split("\\s+");
                        readData = split[split.length-1];
                        for (int i =0; i <split.length-1;i++){
                            int data = Integer.parseInt(split[i]);
                            observer.handle(data);
                        }
                    }


                }
            }
        });

    }
}
