package serial;

public interface ISerialReader {
    void registerObserver(SerialObserver observer);

    void record();
}
