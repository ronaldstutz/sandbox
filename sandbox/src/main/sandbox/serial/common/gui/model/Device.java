package sandbox.serial.common.gui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Device.
 *
 * @author str
 */
public class Device {
    private final StringProperty name;
    private final BooleanProperty available;
    private final BooleanProperty isHub;

    /**
     * Default constructor.
     */
    public Device() {
        this(null, false, false);
    }

    /**
     * Constructor with some initial data.
     *
     * @param name
     * @param available
     */
    public Device(final String name, final boolean available, final boolean isHub) {
        this.name = new SimpleStringProperty(name);
        this.available = new SimpleBooleanProperty(available);
        this.isHub = new SimpleBooleanProperty(isHub);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    /**
     * @return the available
     */
    public boolean getAvailable() {
        return available.get();
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(final boolean available) {
        this.available.set(available);
    }

    public BooleanProperty availableProperty() {
        return available;
    }

    /**
    * @return the isHub
    */
    public boolean getIsHub() {
        return isHub.get();
    }

    /**
     * @param isHub the isHub to set
     */
    public void setIsHub(final boolean isHub) {
        this.isHub.set(isHub);
    }

    public BooleanProperty isHubProperty() {
        return isHub;
    }
}
