package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	private Actions actions = new Actions();
	private int currentPosition = 4;
	private ArrayList<VBox> dates = new ArrayList<>();
	private GridPane root = new GridPane();
	private BorderPane pane = new BorderPane();
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
	public BorderPane mainScene(Stage stage) {
		GridPane root2 = new GridPane();

		root.setVgap(10);
		root.setHgap(10);
		// root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10, 20, 10, 20)); // margins around the whole
														// grid

		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		Menu menuHelp = new Menu("Help");
		MenuItem chooseCSV = new MenuItem("Chose CSV pdf file");
		MenuItem chooseDateFile = new MenuItem("Choose Date file");
		MenuItem openMetaSchema = new MenuItem("Open meta schema");
		openMetaSchema.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

			}
		});
		menuFile.getItems().addAll(chooseCSV, chooseDateFile);
		menuHelp.getItems().add(openMetaSchema);
		menuBar.getMenus().addAll(menuFile, menuHelp);

		Label enterGroupLabel = new Label("Enter your group name");
		TextField enterGroupField = new TextField();
		Label chooseFileLabel = new Label("Please choose your schedule file");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		Button chooseFileButton = new Button("Choose file");
		chooseFileButton.setOnAction(actions.chooseFile());
		Label choosedFileLabel = new Label("Your choosed file is :");
		Text choosedFileText = new Text("File");
		// root.add(menuBar);
		// root.getChildren().addAll(menuBar);
		root.add(enterGroupLabel, 1, 0);
		root.add(enterGroupField, 1, 1);
		root.add(chooseFileLabel, 0, 0);
		root.add(chooseFileButton, 0, 1);
		root.add(choosedFileLabel, 0, 2);
		root.add(choosedFileText, 1, 2);
		root.setMinHeight(550);
		ScrollPane sp = new ScrollPane(root);
		sp.setFitToHeight(true);
		// sp.setFitToWidth(true);
		sp.setMinHeight(550);
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
		pane.setTop(menuBar);
		pane.setCenter(sp);
		// root.getChildren().remove(5);
		return pane;
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
