package sandbox.serial.usb4java;

import java.util.HashMap;
import java.util.Map;

import javax.usb.UsbDevice;
import javax.usb.UsbHostManager;
import javax.usb.UsbServices;
import javax.usb.event.UsbServicesEvent;
import javax.usb.event.UsbServicesListener;

import sandbox.serial.common.AbstractDeviceScanner;
import sandbox.serial.common.SerialDevice;

public class DeviceScannerUsbImpl extends AbstractDeviceScanner implements UsbServicesListener {

    private volatile static DeviceScannerUsbImpl singletonInstance;

    private final Map<UsbDevice, SerialDevice>   attachedDevicesMap = new HashMap<UsbDevice, SerialDevice>();

    public static DeviceScannerUsbImpl getSingletonInstance() {
        if (null == singletonInstance) {
            synchronized (DeviceScannerUsbImpl.class) {
                if (null == singletonInstance) {
                    singletonInstance = new DeviceScannerUsbImpl();
                }
            }
        }
        return singletonInstance;
    }

    private DeviceScannerUsbImpl() {
        super("Usb4Java");

        UsbServices services;
        try {
            services = UsbHostManager.getUsbServices();
            services.addUsbServicesListener(this);
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void usbDeviceAttached(final UsbServicesEvent event) {
        final UsbDevice usbDevice=event.getUsbDevice();
        if (!usbDevice.isUsbHub()) {
            if (!attachedDevicesMap.containsKey(usbDevice)) {
                final SerialDevice serialDevice = new SerialDevice(getIdentifier(usbDevice));
                attachedDevicesMap.put(usbDevice, serialDevice);
                super.deviceAttached(serialDevice);
            }
        }
    }

    @Override
    public void usbDeviceDetached(final UsbServicesEvent event) {
        final UsbDevice usbDevice=event.getUsbDevice();
        if (!usbDevice.isUsbHub()) {
            final SerialDevice serialDevice = attachedDevicesMap.remove(usbDevice);
            super.deviceDetached(serialDevice);
        }
    }

    private String getIdentifier (final UsbDevice usbDevice){
        try {
            return usbDevice.getManufacturerString() + ":" + usbDevice.getProductString() + ":" + usbDevice.getParentUsbPort().getPortNumber();
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "n/a";
        }
    }
}
