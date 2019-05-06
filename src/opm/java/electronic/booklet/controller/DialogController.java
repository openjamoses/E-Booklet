/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opm.java.electronic.booklet.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 *
 * @author john
 */
public class DialogController implements Initializable{

    private int page;
    @FXML
    private Label pageText;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
     @FXML
    private void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void addAction(ActionEvent event) {
        
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        
    }
    

    public void delete(int page) {
        this.page = page;
        pageText.setText("Selected Page: "+page);
    }
    
}
