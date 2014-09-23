package sandbox.serial.common.gui.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

import org.controlsfx.dialog.Dialogs;

import sandbox.serial.common.gui.DeviceControllerApp;
import sandbox.serial.common.gui.model.DeviceVM;

public class DeviceOverviewController {
    @FXML
    private TableView<DeviceVM>            relevantDeviceTable;

    @FXML
    private TableView<DeviceVM>            otherDeviceTable;

    @FXML
    private TableView<DeviceVM>            allDeviceTable;

    @FXML
    private TableColumn<DeviceVM, String>  relevantDeviceTable_typeColumn;

    @FXML
    private TableColumn<DeviceVM, String>  relevantDeviceTable_driverColumn;

    @FXML
    private TableColumn<DeviceVM, String>  relevantDeviceTable_deviceColumn;

    @FXML
    private TableColumn<DeviceVM, Boolean> relevantDeviceTable_availableColumn;

    @FXML
    private TableColumn<DeviceVM, String>  otherDeviceTable_typeColumn;

    @FXML
    private TableColumn<DeviceVM, String>  otherDeviceTable_driverColumn;

    @FXML
    private TableColumn<DeviceVM, String>  otherDeviceTable_deviceColumn;

    @FXML
    private TableColumn<DeviceVM, Boolean> otherDeviceTable_availableColumn;

    @FXML
    private TableColumn<DeviceVM, String>  allDeviceTable_typeColumn;

    @FXML
    private TableColumn<DeviceVM, String>  allDeviceTable_driverColumn;

    @FXML
    private TableColumn<DeviceVM, String>  allDeviceTable_deviceColumn;

    @FXML
    private TableColumn<DeviceVM, Boolean> allDeviceTable_availableColumn;

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
        relevantDeviceTable_deviceColumn.setCellValueFactory(cellData -> cellData.getValue().identifierProperty());
        relevantDeviceTable_typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        relevantDeviceTable_driverColumn.setCellValueFactory(cellData -> cellData.getValue().driverProperty());
        relevantDeviceTable_availableColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
        //        relevantDeviceTable_availableColumn.setCellFactory(tableCell -> new CheckBoxTableCell<>());
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

        otherDeviceTable_deviceColumn.setCellValueFactory(cellData -> cellData.getValue().identifierProperty());
        otherDeviceTable_typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        otherDeviceTable_driverColumn.setCellValueFactory(cellData -> cellData.getValue().driverProperty());
        otherDeviceTable_availableColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
        //        otherDeviceTable_availableColumn.setCellFactory(tableCell -> new CheckBoxTableCell<>());

        allDeviceTable_deviceColumn.setCellValueFactory(cellData -> cellData.getValue().identifierProperty());
        allDeviceTable_typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        allDeviceTable_driverColumn.setCellValueFactory(cellData -> cellData.getValue().driverProperty());
        allDeviceTable_availableColumn.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
        //        allDeviceTable_availableColumn.setCellFactory(tableCell -> new CheckBoxTableCell<>());
        allDeviceTable_availableColumn.setCellFactory(column -> {
            return new TableCell<DeviceVM, Boolean>() {
                @Override
                protected void updateItem(final Boolean item, final boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // Format date.
                        setText(empty ? "" : item.booleanValue() ? "ok" : "NOK");

                        // Style all dates in March with a different color.
                        if (item.booleanValue()) {
                            setTextFill(Color.GREEN);
                            //                            setStyle("-fx-background-color: green");
                        } else {
                            setTextFill(Color.RED);
                            //                            setStyle("-fx-background-color: red");
                        }
                    }
                }
            };
        });

        // Clear Device details.
        showDeviceDetails(null);

        // Listen for selection changes and show the person details when changed.
        relevantDeviceTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDeviceDetails(newValue));
        otherDeviceTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDeviceDetails(newValue));
        allDeviceTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDeviceDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(final DeviceControllerApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        allDeviceTable.setItems(mainApp.getAllDeviceDataList());
    }

    /**
     * Fills all text fields to show details about the Device.
     * If the specified Device is null, all text fields are cleared.
     *
     * @param device the device or null
     */
    private void showDeviceDetails(final DeviceVM device) {
        if (device != null) {
            // Fill the labels with info from the person object.
            deviceLabel.setText(device.getidentifier());
            typeLabel.setText(Boolean.toString(device.getAvailable()));
        } else {
            // Device is null, remove all the text.
            deviceLabel.setText("");
            typeLabel.setText("");
        }
    }


    @FXML
    private void handleShowDetail() {
        final ObservableList<DeviceVM> selectedDevices = allDeviceTable.getSelectionModel().getSelectedItems();
        if (selectedDevices != null && !selectedDevices.isEmpty()) {
            final DeviceVM selectedDevice = selectedDevices.get(0);
            Dialogs.create().owner(mainApp.getPrimaryStage()).title("Some additional Details").masthead(null)
                    .message(selectedDevice.getAdditionalInfo()).showInformation();
        }
    }

    @FXML
    private void handleRemoveFromView() {
        final int selectedIndex = allDeviceTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            allDeviceTable.getItems().remove(selectedIndex);
            allDeviceTable.getSelectionModel().clearSelection();
        }
    }
}
