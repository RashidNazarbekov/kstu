package com.kstu.fitnes.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.kstu.fitnes.animations.Shake;
import com.kstu.fitnes.model.User;
import com.kstu.fitnes.service.UserDBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AuthentificationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    private Label label1;

    @FXML
    private ImageView warning_image;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void loginClick(ActionEvent event) {
        UserDBHandler userDBHandler = new UserDBHandler();
        String txtUsernameVar = txtUsername.getText();
        String txtPasswordVar = txtPassword.getText();
        if(!txtUsernameVar.equals("")) {
            if(!txtPasswordVar.equals("")) {
                try {
                    User user = userDBHandler.findUserByUsername(txtUsernameVar);
                    String userPasswordStr = user.getPassword();
                    if(userPasswordStr.equals(txtPasswordVar)) {
                        label1.setText("You logged in successfully!");
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/kstu/fitnes/main_page.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root1));
                            stage.setTitle("Главная страница");
                            stage.getIcons().add(new Image(("D:\\Neobis\\Dima Neman\\fitnes\\fitnes\\images\\logo.jpg")));
                            stage.setResizable(false);
                            stage.show();
                            Stage stage1 = new Stage();
                            stage1 = (Stage) btnLogin.getScene().getWindow();
                            stage1.hide();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        showLabel("Password is incorrect!");
                    }
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    showLabel("Username is incorrect!");
                }
            } else {
                showLabel("Please, input password field!");
            }
        } else {
            showLabel("Please, input username field!");
        }
    }

    public void showLabel(String text) {
        warning_image.setVisible(true);
        label1.setText(text);
        Shake labelShake = new Shake(label1);
        labelShake.playAnim();
    }

    @FXML
    void initialize() {
    }

}

