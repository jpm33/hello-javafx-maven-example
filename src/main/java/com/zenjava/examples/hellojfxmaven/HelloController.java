package com.zenjava.examples.hellojfxmaven;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//
// https://code.makery.ch/blog/javafx-2-event-handlers-and-change-listeners/
//
public class HelloController implements Initializable
{
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private Label messageLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                changeValue();
            }
        });

        lastNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                changeValue();
            }
        });
    }

    public void sayHello() {

        final String nombre = getNombre();
        if (nombre.length() > 0) {
            log.debug("Saying hello to {}", nombre);
            messageLabel.setText(String.format("Hello: %s", nombre));
        } else {
            log.debug("Neither first name nor last name was set, saying hello to anonymous person");
            messageLabel.setText("Hello mysterious person");
        }
    }

    public void now() {


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hora Actual");
        alert.setHeaderText("Hora Actual");
        alert.setContentText(String.format("La hora actual es %s", fechaActual()));
        alert.showAndWait();

        System.out.printf("La hora actual es %s\n", fechaActual());
    }

    public void changeValue() {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Tu nombre es");
//        alert.setHeaderText("Tu nombre es");
//        alert.setContentText(String.format("Tu nombre es %s", getNombre()));
//        alert.showAndWait();
        System.out.printf("%s - %s - Tu nombre es %s\n", fechaActual(), HelloController.class.getCanonicalName(), getNombre());
    }

    private String getNombre() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        StringBuilder builder = new StringBuilder();

        if (!StringUtils.isEmpty(firstName)) {
            builder.append(firstName);
        }

        if (!StringUtils.isEmpty(lastName)) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(lastName);
        }

        return builder.toString();
    }

    private String fechaActual() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
