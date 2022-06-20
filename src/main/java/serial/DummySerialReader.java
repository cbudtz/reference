package serial;

public class DummySerialReader implements ISerialReader{
    private SerialObserver observer;

    @Override
    public void registerObserver(SerialObserver observer) {
        this.observer=observer;
    }

    @Override
    public void record() {
        new Thread(()->{
            while(true) {
                if (observer != null) {
                    int i = (int) (Math.random() * 20);
                    this.observer.handle(i);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
