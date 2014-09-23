package sandbox.serial.rxtx;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import sandbox.serial.common.AbstractDeviceDetector;
import sandbox.serial.common.SerialDevice;
import sandbox.serial.usb4java.DeviceDetectorUsbImpl;

public class DeviceDetectorRxTxImpl extends AbstractDeviceDetector implements Runnable {

    private volatile static DeviceDetectorRxTxImpl singletonInstance;

    private final Map<String, SerialDevice>        attachedDevicesMap = new HashMap<String, SerialDevice>();

    public static DeviceDetectorRxTxImpl getSingletonInstance() {
        if (null == singletonInstance) {
            synchronized (DeviceDetectorUsbImpl.class) {
                if (null == singletonInstance) {
                    singletonInstance = new DeviceDetectorRxTxImpl();

                }
            }
        }
        return singletonInstance;
    }

    private DeviceDetectorRxTxImpl() {
        super("RxTx");

        try {
            final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            final ScheduledFuture<?> scannerHandle = scheduler.scheduleAtFixedRate(this, 0 /*initialDelay*/, 1/*period*/, TimeUnit.SECONDS);
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        //        System.out.println("SCANNING");
        final List<String> connectedPortNames = getConnectedSerialPortNames();
        final List<String> detachedPortNames = new ArrayList<String>();

        for (final String portName : attachedDevicesMap.keySet()) {
            if (!connectedPortNames.contains(portName)) {
                detachedPortNames.add(portName);
                //                deviceDetached(attachedDevicesMap.get(portName));
            } else {
                //                System.out.println("device " + attachedDevicesMap.get(portName) + " still connected");
            }
        }

        for (final String portName : detachedPortNames) {
            deviceDetached(portName);
        }

        for (final String portName : connectedPortNames) {
            if (!attachedDevicesMap.containsKey(portName)) {
                deviceAttached(portName);
            }
        }
    }

    private void deviceAttached(final String portName) {
        final SerialDevice device = new SerialDevice(portName);
        device.setAttached(true);
        attachedDevicesMap.put(portName, device);
        super.deviceAttached(device);
    }

    private void deviceDetached(final String portName) {
        final SerialDevice device = attachedDevicesMap.remove(portName);
        device.setAttached(false);
        super.deviceDetached(device);
    }

    /**
     *
     * @return
     */
    private static List<String> getConnectedSerialPortNames() {
        final ArrayList<String> deviceNames = new ArrayList<String>();
        final File deviceDir = new File("/dev");
        final File[] devList = deviceDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(final File pathname) {
                final String name = pathname.getName();
                return name.toLowerCase().indexOf("tty") >= 0 && (name.toLowerCase().indexOf("usbserial") >= 0) ? true : false;
            }
        });

        if (devList == null) {
            return deviceNames;
        }
        for (final File dev : devList) {
            deviceNames.add(dev.getAbsolutePath());
        }
        return deviceNames;
    }
}
