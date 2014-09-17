package sandbox.serial.common;

import java.util.ArrayList;
import java.util.List;

import sandbox.serial.usb4java.DeviceScannerUsbImpl;

public class SerialDeviceManager implements DeviceScannerListenerIF {
    List<SerialDevice> devices = new ArrayList<SerialDevice>();
    List<SerialDeviceListenerIF> listeners = new ArrayList<SerialDeviceListenerIF>();

    private volatile static SerialDeviceManager singletonInstance;

    public static SerialDeviceManager getSingletonInstance() {
        if (null == singletonInstance) {
            synchronized (SerialDeviceManager.class) {
                if (null == singletonInstance) {
                    singletonInstance = new SerialDeviceManager();
                }
            }
        }
        return singletonInstance;
    }

    private SerialDeviceManager() {
        DeviceScannerUsbImpl.getSingletonInstance().addListener(this);
    }

    public void addListener(final SerialDeviceListenerIF listener) {
        if (listeners.contains(listener)) {
            return;
        }
        listeners.add(listener);
    }

    public void removeListener(final SerialDeviceListenerIF listener) {
        listeners.remove(listener);
    }

    @Override
    public void deviceAttached(final SerialDevice device) {
        for (final SerialDeviceListenerIF listener : listeners) {
            if (device.getIdentifier().startsWith("GANTNER") || device.getIdentifier().startsWith("FTDI")) {
                devices.add(device);
                listener.deviceAttached(device);
            }
        }
    }

    @Override
    public void deviceDetached(final SerialDevice device) {
        for (final SerialDeviceListenerIF listener : listeners) {
            devices.remove(device);
            listener.deviceDetached(device);
        }
    }

    public void dumpDevices() {
        System.out.println("##########################################################");
        for (final SerialDevice serialDevice : devices) {
            System.out.println(serialDevice.getIdentifier());
        }
        System.out.println("##########################################################");
    }
}
