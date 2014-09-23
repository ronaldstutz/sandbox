package sandbox.fx.makery.cellrenderer;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class to start the application.
 *
 * @author Marco Jakob
 */
public class Main extends Application {

	@Override
	public void start(final Stage primaryStage) {
		primaryStage.setTitle("Table Cell Rendering");

		try {
			final FXMLLoader loader = new FXMLLoader(Main.class.getResource("PersonTable.fxml"));
			final AnchorPane page = (AnchorPane) loader.load();
			final Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) {
		launch(args);
	}
}
