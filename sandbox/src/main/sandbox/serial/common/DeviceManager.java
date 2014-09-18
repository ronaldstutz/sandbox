package sandbox.serial.common;

import java.util.ArrayList;
import java.util.List;

import sandbox.serial.rxtx.DeviceDetectorRxTxImpl;
import sandbox.serial.usb4java.DeviceDetectorUsbImpl;

public class DeviceManager implements DeviceDetectorListenerIF {
    List<SerialDevice> devices = new ArrayList<SerialDevice>();
    List<SerialDeviceListenerIF> listeners = new ArrayList<SerialDeviceListenerIF>();

    private volatile static DeviceManager singletonInstance;

    public static DeviceManager getSingletonInstance() {
        if (null == singletonInstance) {
            synchronized (DeviceManager.class) {
                if (null == singletonInstance) {
                    singletonInstance = new DeviceManager();
                }
            }
        }
        return singletonInstance;
    }

    private DeviceManager() {
        DeviceDetectorUsbImpl.getSingletonInstance().addListener(this);
        DeviceDetectorRxTxImpl.getSingletonInstance().addListener(this);
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
                devices.add(device);
                listener.deviceAttached(device);
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
