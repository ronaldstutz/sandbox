package sandbox.serial.usb4java;

import javax.usb.UsbDevice;

public  class Dumper {

    public static String dump(final UsbDevice device, final String indent) {
        final StringBuilder sb = new StringBuilder();
        final String idnt = indent == null ? "\n" : "\n" + indent;



        //@formatter:off
        sb.append(idnt).append("isHub:                                     ");try{sb.append(device.isUsbHub());                                   } catch (final Exception e){e.printStackTrace();sb.append("n/a");}
        sb.append(idnt).append("isConfigured:                              ");try{sb.append(device.isConfigured());                               } catch (final Exception e){e.printStackTrace();sb.append("n/a");}
        sb.append(idnt).append("productString:                             ");try{sb.append(device.getProductString());                           } catch (final Exception e){e.printStackTrace();sb.append("n/a");}
        sb.append(idnt).append("serialNumberString:                        ");try{sb.append(device.getSerialNumberString());                      } catch (final Exception e){e.printStackTrace();sb.append("n/a");}
        sb.append(idnt).append("usbActiveUsbConfigurationNumber:           ");try{sb.append(device.getActiveUsbConfigurationNumber());             } catch (final Exception e){e.printStackTrace();sb.append("n/a");}
//        sb.append(idnt).append("xxxxx:           ");try{sb.append(device.xxxxx);             } catch (final Exception e){e.printStackTrace();sb.append("n/a");}
//        sb.append(idnt).append("xxxxx:           ");try{sb.append(device.xxxxx);             } catch (final Exception e){e.printStackTrace();sb.append("n/a");}
//        sb.append(idnt).append("xxxxx:           ");try{sb.append(device.xxxxx);             } catch (final Exception e){e.printStackTrace();sb.append("n/a");}
//        sb.append(idnt).append("xxxxx:           ");try{sb.append(device.xxxxx);             } catch (final Exception e){e.printStackTrace();sb.append("n/a");}
//        sb.append(idnt).append("xxxxx:           ");try{sb.append(device.xxxxx);             } catch (final Exception e){e.printStackTrace();sb.append("n/a");}

        return sb.toString();
    }

}
/**

final UsbConfiguration usbConfiguration = device.getActiveUsbConfiguration();
final UsbPort parentUsbPort = device.getParentUsbPort();
final Object speed = device.getSpeed();
//        device.getString(index);
//        final UsbConfiguration usbConfigurationXyz = device.getUsbConfiguration(number);
final List<UsbConfiguration> usbConfigurations = device.getUsbConfigurations();
final UsbDeviceDescriptor usbDeviceDescriptor = device.getUsbDeviceDescriptor();
//        final UsbDeviceDescriptor usbDeviceDescriptorXyz =device.getUsbStringDescriptor(index)
final short bcdDevice = usbDeviceDescriptor.bcdDevice();
final short bcdUSB = usbDeviceDescriptor.bcdUSB();
final short idProduct = usbDeviceDescriptor.idProduct();
final short idVendor = usbDeviceDescriptor.idVendor();

final byte bDescriptorType = usbDeviceDescriptor.bDescriptorType();
final byte bDeviceClass = usbDeviceDescriptor.bDeviceClass();
final byte bDeviceProtocol = usbDeviceDescriptor.bDeviceProtocol();
final byte bDeviceSubClass = usbDeviceDescriptor.bDeviceSubClass();
final byte bLength = usbDeviceDescriptor.bLength();
final byte bMaxPacketSize0 = usbDeviceDescriptor.bMaxPacketSize0();
final byte bNumConfigurations = usbDeviceDescriptor.bNumConfigurations();
final byte iManufacturer = usbDeviceDescriptor.iManufacturer();
final byte iProduct = usbDeviceDescriptor.iProduct();
final byte iSerialNumber = usbDeviceDescriptor.iSerialNumber();

try {
    final String descriptorType = device.getString(bDescriptorType);
} catch (final Exception e1) {//ignore
}
//            final String deviceClass = device.getString(bDeviceClass);
//            final String deviceProtocol = device.getString(bDeviceProtocol);
//            final String deviceSubClass = device.getString(bDeviceSubClass);
try {
    final String length = device.getString(bLength);
} catch (final Exception e1) {//ignore
}
//            final String maxPacketSize0 = device.getString(bMaxPacketSize0);
try {
final String numConfigurations = device.getString(bNumConfigurations);
} catch (final Exception e1) {//ignore
}

try {
final String manufacturer = device.getString(iManufacturer);
} catch (final Exception e1) {//ignore
}
try {
final String product = device.getString(iProduct);
} catch (final Exception e1) {//ignore
}
try {
final String serialNumber = device.getString(iSerialNumber);
} catch (final Exception e1) {//ignor
}
*/