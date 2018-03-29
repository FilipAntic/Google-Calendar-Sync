package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.stage.FileChooser;

public class Actions {

	public EventHandler<ActionEvent> chooseFile() {
		EventHandler<ActionEvent> chooseAction = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json"),
						new FileChooser.ExtensionFilter("CSV", "*.csv"));
				Singleton.choosedFile = fileChooser.showOpenDialog(null);
			}
		};

		return chooseAction;

	}

	public ChangeListener<Boolean> checkDate(DatePicker checkOutDatePicker) {
		ChangeListener<Boolean> chooseAction = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					checkOutDatePicker.setDisable(false);
				} else {
					checkOutDatePicker.setDisable(true);
				}

			}
		};

		return chooseAction;

	}
}
