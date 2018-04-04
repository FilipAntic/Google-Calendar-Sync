package application;

import java.io.BufferedReader;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.BorderPane;

public class JsonViewerDialog extends Dialog {

	private TextArea jsonTextArea = new TextArea(jsonFromFile());
	private Button saveButton = new Button("Save");
	private ScrollPane scrollPane;
	private boolean changed = false;
	private BorderPane root;

	public JsonViewerDialog() {
		setHeight(500);
		setWidth(500);
		// jsonTextArea.getDocument().addDocumentListener(validator);
		// saveButton.addActionListener(new SaveAction());
		jsonTextArea.setOnInputMethodTextChanged(new EventHandler<InputEvent>() {

			@Override
			public void handle(InputEvent event) {

			}
		});
		scrollPane = new ScrollPane(jsonTextArea);
		setTitle("JSON editor");
		root.setCenter(scrollPane);

	}

	public static String jsonFromFile() {
		BufferedReader br = null;
		// try {
		//// br = new BufferedReader(new FileReader(MetaSchema.path));
		// } catch (FileNotFoundException e1) {
		// e1.printStackTrace();
		// }
		String everything = "";
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			everything = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return everything;
	}

	public TextArea getJsonTextArea() {
		return jsonTextArea;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}
}
