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
public class DeviceVM {
    private final StringProperty  identifier;
    private final BooleanProperty available;
    private final StringProperty  type;
    private final StringProperty  driver;
    private final StringProperty  additionalInfo;

    /**
     *
     * @param identifier
     * @param available
     * @param type
     * @param additionalInfo
     */
    public DeviceVM(final String identifier, final boolean available, final String type, final String driver, final String additionalInfo) {
        this.identifier = new SimpleStringProperty(identifier);
        this.available = new SimpleBooleanProperty(available);
        this.type = new SimpleStringProperty(type);
        this.driver = new SimpleStringProperty(driver);
        this.additionalInfo = new SimpleStringProperty(additionalInfo);
    }

    /**
     * Constructor with some initial data.
     *
     * @param identifier
     * @param available
     * @param type
     * @param driver
     */
    public DeviceVM(final String identifier, final boolean available, final String type, final String driver) {
        this(null, false, type, driver, "");

    }

    /**
     * @return the identifier
     */
    public String getidentifier() {
        return identifier.get();
    }

    /**
     * @param identifier the identifier to set
     */
    public void setidentifier(final String identifier) {
        this.identifier.set(identifier);
    }

    public StringProperty identifierProperty() {
        return identifier;
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
     * @return the type
     */
    public StringProperty typeProperty() {
        return type;
    }


    /**
     * @return the type
     */
    public String getType() {
        return type.get();
    }

    /**
     * @param type the type to set
     */
    public void setType(final String type) {
        this.type.set(type);
    }

    /**
     * @return the additionalInfo
     */
    public String getAdditionalInfo() {
        return additionalInfo.get();
    }

    /**
     * @return the driver
     */
    public StringProperty driverProperty() {
        return driver;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver.get();
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(final String driver) {
        this.driver.set(driver);
    }

    /**
     * @param additionalInfo the additionalInfo to set
     */
    public void setAdditionalInfo(final String additionalInfo) {
        this.additionalInfo.set(additionalInfo);
    }

    public StringProperty additionalInfoProperty() {
        return additionalInfo;
    }

}
