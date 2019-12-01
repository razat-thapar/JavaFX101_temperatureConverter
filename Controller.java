package com.javaLearningPath.javaFX;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
	public Label label1;
	@FXML
	public TextField textField1;
	@FXML
	public Button button1;
	@FXML
	public ChoiceBox<String> choiceBox1;
	private static final String C_To_F_text = "Celcius to Farenheit";
	private static final String F_To_C_text = "Farenheit to Celcius";
	private boolean isC_to_F_selected;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//choiceBox
		choiceBox1.getItems().add(C_To_F_text);
		choiceBox1.getItems().add(F_To_C_text);
		//default value
		choiceBox1.setValue(F_To_C_text);

		//listener
		choiceBox1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.equals(C_To_F_text)){
					isC_to_F_selected =true;
				}else if(newValue.equals(F_To_C_text)){
					isC_to_F_selected=false;
				}
			}
		});
		//button
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				convert();
			}
		});

	}
	private void convert(){
		double enteredTemp;
		try {
			enteredTemp = Double.parseDouble(textField1.getText());
		}catch(Exception e){
			System.out.println("Invalid temperature value entered!");
			Alert alertDialog = new Alert(Alert.AlertType.ERROR);
			alertDialog.setTitle("Invalid Temperature");
			alertDialog.setContentText("Please enter a valid temperature value!");
			alertDialog.show();
			return;
		}
		//converting the temp according to the choiceBox selection
		double newTemp;
		if(isC_to_F_selected){
			newTemp= 9.0/5.0*enteredTemp+32;
		}else{
			newTemp= 5.0/9.0*(enteredTemp-32);
		}
		display(newTemp);
	}
	private void display(double newTemp){
		char unit= (isC_to_F_selected)?'F':'C';
		System.out.println("the new temp is :"+newTemp+ unit);
		Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
		alertDialog.setTitle("Temperature Reading!");
		alertDialog.setContentText("the new temp is :"+newTemp+ unit);
		alertDialog.show();
	}
}
