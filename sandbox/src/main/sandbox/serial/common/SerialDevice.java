package sandbox.serial.common;

public class SerialDevice {
    private String identifier = "n/a";

    public SerialDevice(final String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
