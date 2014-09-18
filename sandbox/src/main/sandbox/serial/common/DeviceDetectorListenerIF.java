package sandbox.serial.common;

import sandbox.serial.common.SerialDevice;

public interface DeviceDetectorListenerIF {
    public void deviceAttached(SerialDevice device);

    public void deviceDetached(SerialDevice device);
}
