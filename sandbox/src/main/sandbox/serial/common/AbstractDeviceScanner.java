package sandbox.serial.common;

import java.util.ArrayList;
import java.util.List;

public class AbstractDeviceScanner implements DeviceScannerIF {
    private final String type;

    List<DeviceScannerListenerIF> listeners = new ArrayList<DeviceScannerListenerIF>();

    public AbstractDeviceScanner(final String type) {
        this.type = type;
    }

    @Override
    public final String getType() {
        return type;
    }

    public void addListener(final DeviceScannerListenerIF listener) {
        if (listeners.contains(listener)) {
            return;
        }
        listeners.add(listener);
    }

    public void removeListener(final DeviceScannerListenerIF listener) {
        listeners.remove(listener);
    }

    protected void deviceAttached(final SerialDevice device) {
        for (final DeviceScannerListenerIF listener : listeners) {
            listener.deviceAttached(device);
        }
    }

    protected void deviceDetached(final SerialDevice device) {
        for (final DeviceScannerListenerIF listener : listeners) {
            listener.deviceDetached(device);
        }
    }
}
