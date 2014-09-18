package sandbox.serial.common;

public class SerialDevice {
    private String identifier = "n/a";

    private boolean isAttached = false;

    public SerialDevice(final String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    /**
     * @return the isAttached
     */
    public boolean isAttached() {
        return isAttached;
    }

    /**
     * @param isAttached the isAttached to set
     */
    public void setAttached(final boolean isAttached) {
        this.isAttached = isAttached;
    }
}
