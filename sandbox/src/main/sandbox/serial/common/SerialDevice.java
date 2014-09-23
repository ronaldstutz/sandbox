package sandbox.serial.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerialDevice {
    private String identifier = "n/a";
    private boolean isAttached = false;

    public SerialDevice() {

    }

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

    public String toJsonString() {
        final GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        final Gson gson = builder.create();

        final StringBuilder sb = new StringBuilder(999);
        sb.append(getClass().getSimpleName());
        sb.append("@");
        sb.append(System.identityHashCode(this));
        sb.append("\n");
        sb.append(gson.toJson(this));
        return sb.toString();
    }
}
