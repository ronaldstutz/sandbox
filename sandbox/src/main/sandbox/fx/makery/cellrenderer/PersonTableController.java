package sandbox.fx.makery.cellrenderer;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

/**
 * View-Controller for the person table.
 *
 * @author Marco Jakob
 */
public class PersonTableController {

	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;
	@FXML
	private TableColumn<Person, LocalDate> birthdayColumn;

	private final ObservableList<Person> personData = FXCollections.observableArrayList();

	/**
	 * The constructor (is called before the initialize()-method).
	 */
	public PersonTableController() {
		// Add some sample data.
		personData.add(new Person("Hans", "Muster", LocalDate.of(2012, 3, 22)));
		personData.add(new Person("Ruth", "Mueller", LocalDate.of(2012, 4, 2)));
		personData.add(new Person("Heinz", "Kurz", LocalDate.of(2011, 3, 22)));
		personData.add(new Person("Cornelia", "Meier", LocalDate.of(2012, 6, 13)));
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the columns.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		birthdayColumn.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());

		final DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		// Custom rendering of the table cell.
		birthdayColumn.setCellFactory(column -> {
			return new TableCell<Person, LocalDate>() {
				@Override
				protected void updateItem(final LocalDate item, final boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						// Format date.
						setText(myDateFormatter.format(item));

						// Style all dates in March with a different color.
						if (item.getMonth() == Month.MARCH) {
							setTextFill(Color.CHOCOLATE);
							setStyle("-fx-background-color: yellow");
						} else {
							setTextFill(Color.BLACK);
							setStyle("");
						}
					}
				}
			};
		});

		// Add data to the table
		personTable.setItems(personData);
	}
}