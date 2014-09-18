package sandbox.serial.usb4java;

import java.util.HashMap;
import java.util.Map;

import javax.usb.UsbDevice;
import javax.usb.UsbHostManager;
import javax.usb.UsbServices;
import javax.usb.event.UsbServicesEvent;
import javax.usb.event.UsbServicesListener;

import sandbox.serial.common.AbstractDeviceDetector;
import sandbox.serial.common.SerialDevice;

public class DeviceDetectorUsbImpl extends AbstractDeviceDetector implements UsbServicesListener {

    private volatile static DeviceDetectorUsbImpl singletonInstance;

    private final Map<UsbDevice, SerialDevice>   attachedDevicesMap = new HashMap<UsbDevice, SerialDevice>();

    public static DeviceDetectorUsbImpl getSingletonInstance() {
        if (null == singletonInstance) {
            synchronized (DeviceDetectorUsbImpl.class) {
                if (null == singletonInstance) {
                    singletonInstance = new DeviceDetectorUsbImpl();
                }
            }
        }
        return singletonInstance;
    }

    private DeviceDetectorUsbImpl() {
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
        try {
            //            if (!usbDevice.isUsbHub() && usbDevice.getManufacturerString().equals("FTDI")) {
            //            if (usbDevice.getManufacturerString().equals("FTDI")) {
                if (!attachedDevicesMap.containsKey(usbDevice)) {
                    final SerialDevice serialDevice = new SerialDevice(getIdentifier(usbDevice));
                    serialDevice.setAttached(true);
                    attachedDevicesMap.put(usbDevice, serialDevice);
                    super.deviceAttached(serialDevice);
                //                    System.out.println(Dumper.dump(usbDevice, "  " /*indent*/));
                }
            //            }
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void usbDeviceDetached(final UsbServicesEvent event) {
        final UsbDevice usbDevice=event.getUsbDevice();
            final SerialDevice serialDevice = attachedDevicesMap.remove(usbDevice);
        if (serialDevice != null) {
            serialDevice.setAttached(false);
            super.deviceDetached(serialDevice);
        }
    }

    private String getIdentifier (final UsbDevice usbDevice){
        final StringBuilder sb = new StringBuilder();
        //@formatter:off
        try{sb.append(usbDevice.getManufacturerString());                         } catch (final Exception e){sb.append("n/a");} finally {sb.append(":");}
        try{sb.append(usbDevice.getProductString());                              } catch (final Exception e){sb.append("n/a");} finally {sb.append(":");}
        try{sb.append(usbDevice.getParentUsbPort().getPortNumber());              } catch (final Exception e){sb.append("n/a");} finally {sb.append(":");}
        //@formatter:on
        return sb.toString();
    }
}
