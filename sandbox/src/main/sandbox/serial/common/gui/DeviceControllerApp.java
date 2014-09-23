package sandbox.serial.common.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sandbox.serial.common.DeviceManager;
import sandbox.serial.common.SerialDevice;
import sandbox.serial.common.SerialDeviceListenerIF;
import sandbox.serial.common.gui.model.DeviceVM;
import sandbox.serial.common.gui.view.DeviceOverviewController;

public class DeviceControllerApp extends Application implements SerialDeviceListenerIF {

    private Stage      primaryStage;
    private BorderPane rootLayout;


    /**
     * The data as an observable list of Devices.
     */
    private final ObservableList<DeviceVM> allDeviceDataList = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public DeviceControllerApp() {
        // Add some sample data
        //        deviceDataList.add(new Device("Hardcoded Device 1", false, true));
        //        deviceDataList.add(new Device("Hardcoded Device 2", true, false));
        DeviceManager.getSingletonInstance().addListener(this);
    }


	/**
     * @return Returns the data as an observable list of Devices
     */
    public ObservableList<DeviceVM> getAllDeviceDataList() {
        return allDeviceDataList;
    }

    @Override
	public void start(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Device Controller");

        initRootLayout();

        showDeviceOverview();

        //        DeviceController.getSingletonInstance().addObserver(this);

        //        deviceController.
	}

    /* (non-Javadoc)
     * @see javafx.application.Application#stop()
     */
    @Override
    public void stop() throws Exception {
        //        deviceController.stop();
        DeviceManager.getSingletonInstance().removeListener(this);
        super.stop();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DeviceControllerApp.class.getResource("view/DeviceController.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            final Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the device overview inside the root layout.
     */
    public void showDeviceOverview() {
        try {
            // Load device overview.
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DeviceControllerApp.class.getResource("view/DeviceOverview.fxml"));
            final AnchorPane deviceOverview = (AnchorPane) loader.load();

            // Set device overview into the center of root layout.
            rootLayout.setCenter(deviceOverview);

            // Give the controller access to the main app.
            final DeviceOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void deviceAttached(final SerialDevice serialDevice) {
        for (final DeviceVM device : allDeviceDataList) {
            if (device.getidentifier().equalsIgnoreCase(serialDevice.getIdentifier())) {
                device.setAvailable(true);
                return;
            }
        }

        allDeviceDataList.add(new DeviceVM(serialDevice.getIdentifier(), true, "n/a", "n/a", serialDevice.toJsonString()));
    }

    @Override
    public void deviceDetached(final SerialDevice serialDevice) {
        for (final DeviceVM device : allDeviceDataList) {
            if (device.getidentifier().equalsIgnoreCase(serialDevice.getIdentifier())) {
                device.setAvailable(false);
                return;
            }
        }
    }

	public static void main(final String[] args) {
		launch(args);
	}
}
