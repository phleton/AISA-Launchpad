package com.phleton.apps.tutorial;

import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The open sourced AISA-Launchpad 1.0
 * @author Kais el Kara
 * @version 1.0.0 (99% completed)
 * @date Tuesday,26Mayo2026Cristos
 * @revision Tu,26.05.2026>05h-06h>creating the AISA Launchpad with javafx
 */
public class AISALaunchpad extends Application {

	/**
	 * launch the javafx application and its window
	 * @param args arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
    @Override
    public void start(Stage primaryStage) {
    	int incrementar = 0;
    	// buffer
    	StringBuffer buffer = new StringBuffer();
		buffer.append("AISA LAUNCHPAD");
		// creating the labels
		Label lblTitle = new Label(buffer.toString());
		Label lblSubtitle = new Label("World four letters space agency identification:");
    	Label lblTitleMessage = new Label("Application is to 99% complete");
		Label lblSpaceAgencyName = new Label
				("AISA : The Arabo-Islamic Space Agency shortly the AISA Space Agency\n"
						+ "NASA : The National Aeronautics Space Administration of the United States of America\n"
						+ "ESSA : The European Space Agency with its 23 member states\n"
						+ "CCCP : The Soviet and Russian Space Agency\n"
						+ "INSA : The National Space and Research Organization of India\n"
						+ "ZHGO : The National Space Administration of China\n"
						+ "NZSA : The New Zealand Space Agency\n");
    	
    	// setting the title to AISA Launchpad
		lblTitle.setPadding(new Insets(40, 0, 0, 40));
		lblTitle.setTextFill(Color.WHITE);
		lblTitle.setFont(new Font("Arial", 40));
    	// setting the subtitle
		lblSubtitle.setPadding(new Insets(20, 0, 0, 40));
		lblSubtitle.setTextFill(Color.WHITE);
		lblSubtitle.setFont(new Font("Arial", 18));
		// setting the information about the space agencies
		lblSpaceAgencyName.setPadding(new Insets(20, 0, 0, 40));
		lblSpaceAgencyName.setTextFill(Color.WHITE);
		lblSpaceAgencyName.setFont(new Font("Arial", 18));
		
		// creating objects and constants
		Button[] btnSpaceAgencyApp = new Button[7];
		GridPane paneForSpaceAgencies = new GridPane();
		paneForSpaceAgencies.setPadding(new Insets(50, 0, 50, 0));
		final int numApps = 7;
		final String[] picsLinks = { "aisa-avatar.png","nasa-avatar.png","essa-avatar.png","cccp-avatar.png",
				"zhgo-avatar.png","insa-avatar.png","nzsa-avatar.png"};
		final String[] urls = {
				"www.phleton.com","www.nasa.gov","www.esa.int",
				"https://en.wikipedia.org/wiki/Roscosmos",
				"www.cnsa.gov.cn/english/",
				"www.isro.gov.in",
				"www.mbie.govt.nz/science-and-technology/space"
				};
		final String styleForButton = ""+
				"  -fx-background-color: \r\n" + 
				"      #c3c4c4,\r\n" + 
				"      linear-gradient(#d6d6d6 50%, white 100%),\r\n" + 
				"      radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\r\n" + 
				"  -fx-background-radius: 200;\r\n" + 
				"  -fx-background-insets: 0,1,1;\r\n" + 
				"  -fx-text-fill: black;\r\n" + 
				"  -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );";
		for (incrementar = 0; incrementar < numApps; incrementar++) {
			btnSpaceAgencyApp[incrementar] = new Button(urls[incrementar], getImage("pics/avatar/"+picsLinks[incrementar]));
			//btnSpaceAgencyApp[incrementar].setId("saButton");
			btnSpaceAgencyApp[incrementar].setStyle(styleForButton);
			btnSpaceAgencyApp[incrementar].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						// not quite an elegant solution
						// we put the url link of the space agency under the title of the button
						// the url is shown along the image X(
						// I have no other solution to propose because passing a variable had to be final
						Button btn = (Button) event.getSource();
						Desktop.getDesktop().browse(new URI(btn.getText()));//urls[1]
					} catch (IOException | URISyntaxException e) {
						System.err.println("Cannot open the desired website!");;
					}
				}
			});
			paneForSpaceAgencies.add(btnSpaceAgencyApp[incrementar], incrementar, 0, 1, 1);
		}
		VBox vbox = new VBox(lblTitle,lblSubtitle,lblSpaceAgencyName,paneForSpaceAgencies);
		vbox.setStyle("-fx-background-color: #000;");
		Scene scene = new Scene(vbox);
		//scene.setFill(Color.BLACK); // not working to fill the scene to black
		//scene.getStylesheets().add("aisalaunchpad.css");
		
		Stage stage = new Stage();
		stage.setTitle("AISA Launchpad 1.0.0"+" ("+lblTitleMessage.getText()+")");
		stage.setX(200);
		stage.setY(50);
		stage.setScene(scene);
		stage.setWidth(1200);
		stage.setHeight(800);
		
		stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        //primaryStage.show();
		stage.showAndWait();
    }
    
    /**
     * getting the image view
     * @param parImagePath the path of the image
     * @return return the image view
     */
	public static ImageView getImage(String parImagePath) {
		Image img = null;
		ImageView imgViewer = null;
		//String imageAbsolutPath;
		try {
			//imageAbsolutPath = AppHelper.getFileLocation(parImagePath);
			FileInputStream input = new FileInputStream(parImagePath);
			img = new Image(input);
			imgViewer = new ImageView(img);
		} catch (Exception parEx) {
			//parEx.printStackTrace();
			System.err.println(parEx.getMessage());
		}
		return imgViewer;
	}

}
