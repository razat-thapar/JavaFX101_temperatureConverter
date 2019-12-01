package com.javaLearningPath.javaFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("inside init");
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox rootnode = FXMLLoader.load(getClass().getResource("temperatureLayout.fxml"));
        MenuBar menuBar = this.createMenu();
        rootnode.getChildren().add(0,menuBar);
        primaryStage.setTitle("Temp Converter tool");
        primaryStage.setScene(new Scene(rootnode));
        primaryStage.show();
    }
    private MenuBar createMenu() {
        MenuBar menuBar= new MenuBar();
        Menu fileMenu = new Menu("file");
        MenuItem newMenuItem= new MenuItem("new");
        newMenuItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                System.out.println("new item clicked!");
            }
        });
        MenuItem quitMenuItem= new MenuItem("quit");
        quitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        SeparatorMenuItem sepMenuItem= new SeparatorMenuItem();
        fileMenu.getItems().addAll(newMenuItem,sepMenuItem,quitMenuItem);

        Menu helpMenu  = new Menu("help");
        MenuItem aboutMenuItem = new MenuItem("about");
        aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                aboutMethod();
            }
        });
        helpMenu.getItems().add(aboutMenuItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        return menuBar;
    }
    private void aboutMethod(){
        //Alert Dialog
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        alertDialog.setTitle("Temparature Converter Application");
        alertDialog.setHeaderText("Farenhit to celcius and visa-versa");
        alertDialog.setContentText("My first application but will become pro soon!");

        //creating yes and no button instead of default set of button and replacing them.
	    ButtonType yesBttn = new ButtonType("yes");
	    ButtonType noBttn = new ButtonType("no");
	    alertDialog.getButtonTypes().setAll(yesBttn,noBttn);
	    //now we need to check which button is selected and take the required action
	    Optional<ButtonType> clickedBttn= alertDialog.showAndWait();
	    if(clickedBttn.isPresent()){
	    	if(clickedBttn.get()==yesBttn){
	    		System.out.println("yes clicked!");
		    }else if(clickedBttn.get()==noBttn){
		    	System.out.println("no clicked!");
		    }else{
		    	System.out.println("nothing!");
		    }
	    }
	    //alertDialog.show();
    }
    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("inside stop!");
    }
}
