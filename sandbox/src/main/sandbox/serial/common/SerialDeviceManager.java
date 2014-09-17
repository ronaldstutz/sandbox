package sandbox.serial.common;

import java.util.ArrayList;
import java.util.List;

import sandbox.serial.common.SerialDevice;
import sandbox.serial.common.SerialDeviceListener;

public class SerialDeviceManager {
    List<SerialDevice> devices = new ArrayList<SerialDevice>();
    List<SerialDeviceListener> listeners = new ArrayList<SerialDeviceListener>();

    public void addListener(final SerialDeviceListener listener) {
        if (listeners.contains(listener)) {
            return;
        }
        listeners.add(listener);
    }

    public void removeListener(final SerialDeviceListener listener) {
        listeners.remove(listener);
    }

}
