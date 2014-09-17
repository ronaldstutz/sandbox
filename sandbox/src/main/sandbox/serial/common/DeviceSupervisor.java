package sandbox.serial.common;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DeviceSupervisor implements Runnable, SerialDeviceListenerIF {

    @Override
    public void deviceAttached(final SerialDevice device) {
        System.out.println("+++ Device Attached: " + device.getIdentifier() + device);
        SerialDeviceManager.getSingletonInstance().dumpDevices();
    }

    @Override
    public void deviceDetached(final SerialDevice device) {
        System.out.println("+++ Device Detached: " + device.getIdentifier() + device);
        SerialDeviceManager.getSingletonInstance().dumpDevices();
    }

    public DeviceSupervisor() {
        final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        SerialDeviceManager.getSingletonInstance().addListener(this);

        final ScheduledFuture<?> scannerHandle = scheduler.scheduleAtFixedRate(this, 0 /*initialDelay*/, 20/*period*/, TimeUnit.SECONDS);
    }

    public static void main(final String[] args) {

        new DeviceSupervisor();
    }

    @Override
    public void run() {
        System.out.println("alive ...");
        SerialDeviceManager.getSingletonInstance().dumpDevices();
    }
}
