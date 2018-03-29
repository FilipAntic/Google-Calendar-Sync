package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	private Actions actions = new Actions();
	private int currentPosition = 4;
	private ArrayList<VBox> dates = new ArrayList<>();
	private GridPane root = new GridPane();
	private Scene scene;

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			scene = new Scene(mainScene(primaryStage), 1000, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * 
	 * @param stage
	 * @return
	 */
	public GridPane mainScene(Stage stage) {
		GridPane root2 = new GridPane();

		root.setVgap(10);
		root.setHgap(10);
		root.setAlignment(Pos.CENTER);

		Label chooseFileLabel = new Label("Please choose your schedule file");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		Button chooseFileButton = new Button("Choose file");
		chooseFileButton.setOnAction(actions.chooseFile());
		Label choosedFileLabel = new Label("Your choosed file is :");
		Text choosedFileText = new Text("File");

		root.add(chooseFileLabel, 0, 0);
		root.add(chooseFileButton, 0, 1);
		root.add(choosedFileLabel, 0, 2);
		root.add(choosedFileText, 1, 2);
		ScrollPane sp = new ScrollPane(root);
		root2.add(sp, 0, 1);
		root2.setHgrow(sp, Priority.ALWAYS);
		// root.add(b, 1, 3);
		// root.add(vbox, 0, 4);
		Button addButton = new Button("Add new date");

		root.add(addButton, 0, 3);
		Button chooseDateFileButton = new Button("Choose date file");

		root.add(chooseDateFileButton, 1, 3);
		root.add(pickUpDate(), 0, currentPosition++);
		addButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				dates.add(pickUpDate());
				root.add(dates.get(dates.size() - 1), 0, currentPosition++);

			}
		});

		// root.getChildren().remove(5);
		return root2;
	}

	public VBox pickUpDate() {
		VBox vbox = new VBox(10);
		HBox hbox = new HBox(10);
		Label chooseDate = new Label("Please choose date: ");
		DatePicker checkInDatePicker = new DatePicker();
		DatePicker checkOutDatePicker = new DatePicker();
		CheckBox disabledCheckOut = new CheckBox("Check this if you have ending date");
		Image imageDecline = new Image(getClass().getResourceAsStream("/x-button.png"));
		Button deleteButton = new Button();
		deleteButton.setGraphic(new ImageView(imageDecline));

		checkOutDatePicker.setDisable(true);
		disabledCheckOut.selectedProperty().addListener(actions.checkDate(checkOutDatePicker));
		hbox.getChildren().add(checkInDatePicker);
		hbox.getChildren().add(checkOutDatePicker);
		hbox.getChildren().add(disabledCheckOut);
		hbox.getChildren().add(deleteButton);
		vbox.getChildren().add(chooseDate);
		vbox.getChildren().add(hbox);
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				dates.remove(vbox);
				root.getChildren().remove(vbox);
			}
		});
		return vbox;
	}
}
