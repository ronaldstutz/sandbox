package sandbox.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.controlsfx.control.CheckTreeView;

public class TestCTV extends Application {
    StackPane rootp = new StackPane();

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        final Scene scene = new Scene(rootp);
        primaryStage.setScene(scene);
        primaryStage.show();

        // create the data to show in the CheckTreeView
        final CheckBoxTreeItem<String> root = new CheckBoxTreeItem<String>("Root");
        root.setExpanded(true);
        root.getChildren().addAll(new CheckBoxTreeItem<String>("Jonathan"), new CheckBoxTreeItem<String>("Eugene"),
                new CheckBoxTreeItem<String>("Henri"), new CheckBoxTreeItem<String>("Samir"));

        // Create the CheckTreeView with the data
        final CheckTreeView<String> checkTreeView = new CheckTreeView<>(root);
        checkTreeView.setShowRoot(false);
        rootp.getChildren().add(checkTreeView);
    }
}