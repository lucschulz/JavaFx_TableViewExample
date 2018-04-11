package ca.lucschulz.FXTableView;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final ObservableList<Person> data = FXCollections.observableArrayList(new Person("A", "B", "C"));
    final HBox hb = new HBox();

    @Override
    public void start(Stage stage) throws Exception{

        Scene scene = new Scene(new Group());
        stage.setWidth(450);
        stage.setHeight(550);

        TableView table = new TableView();
        table.setEditable(true);

        // CREATE THREE TABLE COLUMNS USING THE getNewColumn() METHOD
        TableColumn firstNameCol = getNewColumn("First Name", "firstName");
        TableColumn middleNameCol = getNewColumn("Middle Name", "middleName");
        TableColumn lastNameCol = getNewColumn("Last Name", "lastName");

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, middleNameCol, lastNameCol);

        // CREATE A BUTTON TO ADD NEW ROWS
        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            data.add(new Person("First", "Middle", "Last"));
        });

        hb.getChildren().addAll(addButton);
        hb.setSpacing(3);

        final VBox vbox = getNewVBox(10, 0, 0, 10);
        vbox.getChildren().addAll(table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();

        // CONFIGURES THE WINDOW ICON
        stage.getIcons().add(new Image(getClass().getResourceAsStream("circle-icon.png")));
    }

    private TableColumn getNewColumn(String columnHeader, String propertyValue) {
        TableColumn tc = new TableColumn(columnHeader);
        tc.setMinWidth(100);
        tc.setCellValueFactory(new PropertyValueFactory<>(propertyValue));
        return tc;
    }

    private VBox getNewVBox(int top, int right, int bottom, int left) {
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(top, right, bottom, left));
        return vbox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}