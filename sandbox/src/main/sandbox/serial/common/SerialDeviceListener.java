package sandbox.serial.common;

import sandbox.serial.common.SerialDevice;

public interface SerialDeviceListener {
    public void deviceAttached(SerialDevice device);

    public void deviceDetached(SerialDevice device);
}
