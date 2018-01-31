/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author LeBorg
 */
public class CheckerboardFXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    private Stage stage;
    private VBox vbox;
    private int numRows = 8;
    private int numCols = 8;
    
    private double gridWidth;
    private double gridHeight;
    
    private final Color[] colors = {Color.CORAL, Color.DEEPPINK, Color.BISQUE, Color.AZURE, Color.SEAGREEN};
    private final Color[] redAndBlack = {Color.RED, Color.BLACK};
    private final Color[] shadesOfBlue = {Color.SKYBLUE, Color.DARKBLUE};
    private Color[] currentColor;
    
    @FXML
    private AnchorPane anchorPane; 
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentColor = redAndBlack;
    }  
    
    public void start(Stage stage) {
        this.stage = stage;
        this.vbox = (VBox)stage.getScene().getRoot();
        System.out.println(anchorPane);
        System.out.println(anchorPane.getWidth());
        
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
            refresh();
        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener); 
        
        refresh();
    }
    
    @FXML
    private void handleRefresh(ActionEvent event) {
        System.out.println("refresh");
        refresh();
    }
    
    @FXML
    private void handleClear(ActionEvent event) {
        System.out.println("clear");
        clearAnchorPane();
    }
    
    @FXML
    private void handleDefault(ActionEvent event) {
        currentColor = redAndBlack;
        refresh();
    }
    
    @FXML
    private void handleBlue(ActionEvent event) {
        currentColor = shadesOfBlue;
        refresh();
    }
    
    @FXML
    private void handle16(ActionEvent event) {
        numCols = numRows = 16;
        refresh();
    }
    
    @FXML
    private void handle10(ActionEvent event) {
        numCols = numRows = 10;
        refresh();
    }
    
    @FXML
    private void handle8(ActionEvent event) {
        numCols = numRows = 8;
        refresh();
    }
    
    @FXML
    private void handle3(ActionEvent event) {
        numCols = numRows = 3;
        refresh();
    }
    
    @FXML
    private void handleAbout(ActionEvent event) {
        System.out.println("about");
    }
    
    private void refresh() {
        clearAnchorPane();
        
        gridWidth = vbox.getWidth();
        gridHeight = vbox.getHeight()-29;
        
        Random rn = new Random();
        
        double rectWidth = Math.ceil(gridWidth / numCols);
        double rectHeight = Math.ceil(gridHeight / numRows);
        System.out.println(rectHeight);
        
        int numColors = colors.length;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Color color = currentColor[(row+col)%2];
                Rectangle rect = new Rectangle(rectWidth, rectHeight, color);
                
                AnchorPane.setTopAnchor(rect, (double)col * rectHeight);
                AnchorPane.setLeftAnchor(rect, (double)row * rectWidth);
                anchorPane.getChildren().addAll(rect);
            }
        }        
        
    }
    private void clearAnchorPane() {
        anchorPane.getChildren().clear();
    }
    
}
