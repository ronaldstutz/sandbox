package sandbox.serial.common.gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import sandbox.serial.common.gui.DeviceControllerApp;
import sandbox.serial.common.gui.model.Device;

public class DeviceOverviewController {
    @FXML
    private TableView<Device>            relevantDeviceTable;

    @FXML
    private TableView<Device>            otherDeviceTable;

    @FXML
    private TableView<Device>            allDeviceTable;

    @FXML
    private TableColumn<Device, Boolean> relevantDeviceTable_isHubColumn;

    @FXML
    private TableColumn<Device, String>  relevantDeviceTable_deviceColumn;

    @FXML
    private TableColumn<Device, Boolean> relevantDeviceTable_availableColumn;

    @FXML
    private TableColumn<Device, Boolean> otherDeviceTable_isHubColumn;

    @FXML
    private TableColumn<Device, String>  otherDeviceTable_deviceColumn;

    @FXML
    private TableColumn<Device, Boolean> otherDeviceTable_availableColumn;

    @FXML
    private TableColumn<Device, Boolean> allDeviceTable_isHubColumn;

    @FXML
    private TableColumn<Device, String>  allDeviceTable_deviceColumn;

    @FXML
    private TableColumn<Device, Boolean> allDeviceTable_availableColumn;

    @FXML
    private Label                        deviceLabel;
    @FXML
    private Label                        typeLabel;

    // Reference to the main application.
    private DeviceControllerApp          mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public DeviceOverviewController() {
    }



    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        relevantDeviceTable_isHubColumn.setCellValueFactory(cellData -> cellData.getValue().isHubProperty());
        relevantDeviceTable_deviceColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        relevantDeviceTable_availableColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
        relevantDeviceTable_availableColumn.setCellFactory(tableCell -> new CheckBoxTableCell<>());
        //        relevantDeviceTable_availableColumn.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
        //
        //            @Override
        //            public TableCell<Device, Boolean> call(final TableColumn<Device, Boolean> p) {
        //
        //                return new CheckBoxTableCell<Device, Boolean>();
        //
        //            }
        //
        //        });

        otherDeviceTable_isHubColumn.setCellValueFactory(cellData -> cellData.getValue().isHubProperty());
        otherDeviceTable_deviceColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        otherDeviceTable_availableColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
        otherDeviceTable_availableColumn.setCellFactory(tableCell -> new CheckBoxTableCell<>());

        allDeviceTable_isHubColumn.setCellValueFactory(cellData -> cellData.getValue().isHubProperty());
        allDeviceTable_deviceColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        allDeviceTable_availableColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
        allDeviceTable_availableColumn.setCellFactory(tableCell -> new CheckBoxTableCell<>());

        // Clear Device details.
        showDeviceDetails(null);

        // Listen for selection changes and show the person details when changed.
        relevantDeviceTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDeviceDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(final DeviceControllerApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        relevantDeviceTable.setItems(mainApp.getAllDeviceDataList());
    }

    /**
     * Fills all text fields to show details about the Device.
     * If the specified Device is null, all text fields are cleared.
     *
     * @param device the device or null
     */
    private void showDeviceDetails(final Device device) {
        if (device != null) {
            // Fill the labels with info from the person object.
            deviceLabel.setText(device.getName());
            typeLabel.setText(Boolean.toString(device.getAvailable()));
        } else {
            // Device is null, remove all the text.
            deviceLabel.setText("");
            typeLabel.setText("");
        }
    }
}
