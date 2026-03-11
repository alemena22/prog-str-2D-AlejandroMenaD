package com.example.demolistview.controllers;

import com.example.demolistview.services.PersonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class AppController {
    @FXML
    private ListView<String> listView;
    @FXML
    private Label lblMsg;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAge;
    @FXML
    private final ObservableList<String> data = FXCollections.observableArrayList();
    private PersonService service = new PersonService();
    @FXML
    public void initialize(){
        listView.setItems(data);
        loadFromFile();
    }
    public void onAddPerson(){
        try{
            String name = txtName.getText();
            String email = txtEmail.getText();
            int age = Integer.parseInt(txtAge.getText());

            service.addPerson(name, email, age);
            lblMsg.setText("Persona agregada con exito");
            lblMsg.setStyle("-fx-text-fill: green");
            txtName.clear();
            txtEmail.clear();
            txtAge.clear();
            loadFromFile();

        }catch (IOException e){
            lblMsg.setText("Hubo un error en el archivo.");
            lblMsg.setStyle("-fx-text-fill: red");
        }catch (IllegalArgumentException ex){
            lblMsg.setText("Hubo un error con los datos");
            lblMsg.setStyle("-fx-text-fill: red");
        }

    }

    private void loadFromFile(){
        try {
            List<String> items = service.loadDataforList();
            data.setAll(items);
            lblMsg.setText("Datos cargados exitosamente!");
            lblMsg.setStyle("-fx-text-fill: green");
        } catch (IOException e) {
            lblMsg.setText(e.getMessage());
            lblMsg.setStyle("-fx-text-fill: red");
        }

    }
}
