package sandbox.serial.common;

import java.util.ArrayList;
import java.util.List;

public class AbstractDeviceDetector implements DeviceDetectorIF {
    private final String type;

    List<DeviceDetectorListenerIF> listeners = new ArrayList<DeviceDetectorListenerIF>();

    public AbstractDeviceDetector(final String type) {
        this.type = type;
    }

    @Override
    public final String getType() {
        return type;
    }

    public void addListener(final DeviceDetectorListenerIF listener) {
        if (listeners.contains(listener)) {
            return;
        }
        listeners.add(listener);
    }

    public void removeListener(final DeviceDetectorListenerIF listener) {
        listeners.remove(listener);
    }

    protected void deviceAttached(final SerialDevice device) {
        for (final DeviceDetectorListenerIF listener : listeners) {
            listener.deviceAttached(device);
        }
    }

    protected void deviceDetached(final SerialDevice device) {
        for (final DeviceDetectorListenerIF listener : listeners) {
            listener.deviceDetached(device);
        }
    }
}
