package com.kstu.fitnes.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.kstu.fitnes.model.*;
import com.kstu.fitnes.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainPageController {
    private boolean checkClient;
    private boolean checkAbonement;
    private boolean checkInstructor;
    private boolean checkAccounting;

    @FXML
    private TextField abonementDescriptionTxt;

    @FXML
    private AnchorPane abonementForm;

    @FXML
    private TextField abonementHallIdTxt;

    @FXML
    private TextField abonementIdTxt;

    @FXML
    private TextField abonementPriceTxt;

    @FXML
    private TextField accountingAbonementIdTxt;

    @FXML
    private AnchorPane accountingForm;

    @FXML
    private TextField accountingIdTxt;

    @FXML
    private TextField accountingMonthTxt;

    @FXML
    private TextField accountingStatusOplatyTxt;

    @FXML
    private TextField accountingClientIdTxt;

    @FXML
    private Button buttonCancelAbonement;

    @FXML
    private Button buttonCancelAccounting;

    @FXML
    private Button buttonCancelInstructor;

    @FXML
    private Button buttonOkAbonement;

    @FXML
    private Button buttonOkAccounting;

    @FXML
    private Button buttonOkInstructor;

    @FXML
    private Button buttonChortClients;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonInstructorClient;

    @FXML
    private Button buttonInstructorPremium;

    @FXML
    private TextField instructorFirstNameTxt;

    @FXML
    private AnchorPane instructorForm;

    @FXML
    private TextField instructorIdTxt;

    @FXML
    private TextField instructorLastNameTxt;

    @FXML
    private TextField instructorOklad;

    @FXML
    private AnchorPane mainpage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem btnAboutUs;

    @FXML
    private Button btnAddClients;

    @FXML
    private Button btnDeleteClients;

    @FXML
    private Menu btnExit;

    @FXML
    private Button btnUpdateClients;

    @FXML
    private TextField userIdTxt;

    @FXML
    private TableColumn<Client, Long> client_id_column;

    @FXML
    private TableView<Client> clientsTableView;

    @FXML
    private TableColumn<Client, String> first_name_column;

    @FXML
    private TableColumn<Client, Long> instructor_id_fk_column;

    @FXML
    private TableColumn<Client, String> last_name_column;

    @FXML
    private TableColumn<Client, String> phone_number_column;

    @FXML
    private Button updateClientTebleView;

    @FXML
    private Button buttonCancelClient;

    @FXML
    private TextField clientFirstNameTxt;

    @FXML
    private AnchorPane clientForm;

    @FXML
    private TextField clientIdTxt;

    @FXML
    private TextField clientInstructorIdTxt;

    @FXML
    private TextField clientLastNameTxt;

    @FXML
    private TextField clientPhoneNumberTxt;

    @FXML
    private TableColumn<Accounting, Long> accountingClientIdColumn;

    @FXML
    void buttonCancelClient(ActionEvent event) {
        clientForm.setVisible(false);
    }

    @FXML
    void buttonOkClientClick(ActionEvent event) {
        if(!clientIdTxt.getText().equals("")
                && !clientLastNameTxt.getText().equals("")
                && !clientFirstNameTxt.getText().equals("")
                && !clientPhoneNumberTxt.getText().equals("")
                && !clientInstructorIdTxt.getText().equals("")
        ) {
            try{
                Long client_id = Long.parseLong(clientIdTxt.getText());
                Long instructor_id = Long.parseLong(clientInstructorIdTxt.getText());
                Client client = new Client(client_id, clientLastNameTxt.getText(), clientFirstNameTxt.getText(), clientPhoneNumberTxt.getText(), instructor_id);
                ClientDBHandler dbHandler = new ClientDBHandler();
                if(checkClient) {
                    dbHandler.update(client);
                } else {
                    dbHandler.add(client);
                }
                client = null;
                clientForm.setVisible(false);
                showTableViewClients();
            } catch (Exception ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Поля код клиента и код тренера должны содержать число!");
                alert.setContentText("Пожалуйста, заполните поля код клиента и код тренера числом.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не все поля заполнены!");
            alert.setContentText("Пожалуйста, заполните все поля.");
            alert.showAndWait();
        }
    }

    @FXML
    void buttonCancelAbonementClick(ActionEvent event) {
        abonementForm.setVisible(false);
    }

    @FXML
    void buttonCancelAccountingClick(ActionEvent event) {
        accountingForm.setVisible(false);
    }

    @FXML
    void buttonCancelInstructorClick(ActionEvent event) {
        instructorForm.setVisible(false);
    }

    @FXML
    void buttonOkAbonementClick(ActionEvent event) {
        if(!abonementIdTxt.getText().equals("")
                && !abonementDescriptionTxt.getText().equals("")
                && !abonementPriceTxt.getText().equals("")
                && !abonementHallIdTxt.getText().equals("")
        ) {
            try{
                Long abonement_id = Long.parseLong(abonementIdTxt.getText());
                Long abonement_hall_id = Long.parseLong(abonementHallIdTxt.getText());
                Double abonementPrice = Double.parseDouble(abonementPriceTxt.getText());
                Abonement abonement = new Abonement(abonement_id, abonementDescriptionTxt.getText(), abonementPrice, abonement_hall_id);
                AbonementDBHandler dbHandler = new AbonementDBHandler();
                if(checkAbonement) {
                    dbHandler.update(abonement);
                } else {
                    dbHandler.add(abonement);
                }
                abonement = null;
                abonementForm.setVisible(false);
                showTableViewAbonements();
            } catch (Exception ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Поля код абонемента и код зала должны содержать число!");
                alert.setContentText("Пожалуйста, заполните код абонемента и код зала числом.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не все поля заполнены!");
            alert.setContentText("Пожалуйста, заполните все поля.");
            alert.showAndWait();
        }
    }

    @FXML
    void buttonOkAccountingClick(ActionEvent event) {
        if(!accountingIdTxt.getText().equals("")
                && !accountingClientIdTxt.getText().equals("")
                && !accountingAbonementIdTxt.getText().equals("")
                && !accountingMonthTxt.getText().equals("")
                && !accountingStatusOplatyTxt.getText().equals("")
        ) {
            try{
                Long accounting_id = Long.parseLong(accountingIdTxt.getText());
                Long client_id = Long.parseLong(accountingClientIdTxt.getText());
                Long abonement_id = Long.parseLong(accountingAbonementIdTxt.getText());
                Boolean statusOplaty = Boolean.parseBoolean(accountingStatusOplatyTxt.getText());
                Accounting accounting = new Accounting(accounting_id, client_id, abonement_id, accountingMonthTxt.getText(), statusOplaty);
                AccountingDBHandler dbHandler = new AccountingDBHandler();
                if(checkAccounting) {
                    dbHandler.update(accounting);
                } else {
                    dbHandler.add(accounting);
                }
                accounting = null;
                accountingForm.setVisible(false);
                showTableViewAccountings();
            } catch (Exception ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Поля код абонемента, код клиента и код зала должны содержать число!");
                alert.setContentText("Пожалуйста, заполните код абонемента, код клиента и код зала числом.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не все поля заполнены!");
            alert.setContentText("Пожалуйста, заполните все поля.");
            alert.showAndWait();
        }
    }

    @FXML
    void buttonOkInstructorClick(ActionEvent event) {
        if(!instructorIdTxt.getText().equals("")
                && !instructorLastNameTxt.getText().equals("")
                && !instructorFirstNameTxt.getText().equals("")
                && !instructorOklad.getText().equals("")
        ) {
            try{
                Long instructor_id = Long.parseLong(instructorIdTxt.getText());
                Double instructor_oklad = Double.parseDouble(instructorOklad.getText());
                Instructor instructor = new Instructor(instructor_id, instructorLastNameTxt.getText(), instructorFirstNameTxt.getText(), instructor_oklad);
                InstructorDBHandler dbHandler = new InstructorDBHandler();
                if(checkInstructor) {
                    dbHandler.update(instructor);
                } else {
                    dbHandler.add(instructor);
                }
                instructor = null;
                instructorForm.setVisible(false);
                showTableViewInstructors();
            } catch (Exception ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Поля код абонемента и код зала должны содержать число!");
                alert.setContentText("Пожалуйста, заполните код абонемента и код зала числом.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не все поля заполнены!");
            alert.setContentText("Пожалуйста, заполните все поля.");
            alert.showAndWait();
        }
    }

    @FXML
    private AnchorPane selectAnchor;

    @FXML
    private Label selectLabel;

    @FXML
    private Button buttonClose;

    @FXML
    void buttonCloseClick(ActionEvent event) {
        selectAnchor.setVisible(false);
    }

    @FXML
    void buttonChortClientsClick(ActionEvent event) {
        selectAnchor.setVisible(true);
        selectLabel.setText("Запрос клиенты-должники");

        SelectDBHandler selectDBHandler = new SelectDBHandler();
        List<AccountingClient> accountingClients = selectDBHandler.getBadClients();
        ObservableList<AccountingClient> accountingClientObservableList = FXCollections.observableArrayList(accountingClients);

        TableView<AccountingClient> selectTableView = new TableView<AccountingClient>(accountingClientObservableList);
        selectTableView.setPrefWidth(798);
        selectTableView.setPrefHeight(450);
        selectTableView.setLayoutX(14);
        selectTableView.setLayoutY(66);

        TableColumn<AccountingClient, Long> clientId = new TableColumn<AccountingClient, Long>("Код клиента");
        clientId.setPrefWidth(200);
        clientId.setCellValueFactory(new PropertyValueFactory<AccountingClient, Long>("clientId"));
        selectTableView.getColumns().add(clientId);

        TableColumn<AccountingClient, String> clientLastNameColumn = new TableColumn<AccountingClient, String>("Фамилия");
        clientLastNameColumn.setPrefWidth(200);
        clientLastNameColumn.setCellValueFactory(new PropertyValueFactory<AccountingClient, String>("lastName"));
        selectTableView.getColumns().add(clientLastNameColumn);

        TableColumn<AccountingClient, String> clientFirstNameColumn = new TableColumn<AccountingClient, String>("Имя");
        clientFirstNameColumn.setPrefWidth(200);
        clientFirstNameColumn.setCellValueFactory(new PropertyValueFactory<AccountingClient, String>("firstName"));
        selectTableView.getColumns().add(clientFirstNameColumn);

        TableColumn<AccountingClient, Boolean> status = new TableColumn<AccountingClient, Boolean>("Статус оплаты");
        status.setPrefWidth(200);
        status.setCellValueFactory(new PropertyValueFactory<AccountingClient, Boolean>("status_oplaty"));
        selectTableView.getColumns().add(status);

        selectAnchor.getChildren().add(selectTableView);
    }

    @FXML
    void buttonExit(ActionEvent event) {
        ButtonType foo = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType bar = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Вы точно хотите выйти?", foo, bar);
        alert.setTitle("Выход");
        alert.setHeaderText("Внимание!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == foo) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/kstu/fitnes/authentification.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Аутентификация");
                stage.getIcons().add(new Image(("D:\\Neobis\\Dima Neman\\fitnes\\fitnes\\images\\logo.jpg")));
                stage.show();
                Stage stage1 = new Stage();
                stage1 = (Stage) buttonExit.getScene().getWindow();
                stage1.hide();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }

    @FXML
    void buttonInstructorClientClick(ActionEvent event) {
        selectAnchor.setVisible(true);
        selectLabel.setText("Запрос тренеры + кол-во клиентов");

        SelectDBHandler selectDBHandler = new SelectDBHandler();
        List<InstructorClients> instructorClients = selectDBHandler.getInstructorClients();
        ObservableList<InstructorClients> instructorClientsObservableList = FXCollections.observableArrayList(instructorClients);

        TableView<InstructorClients> selectTableView = new TableView<InstructorClients>(instructorClientsObservableList);
        selectTableView.setPrefWidth(798);
        selectTableView.setPrefHeight(450);
        selectTableView.setLayoutX(14);
        selectTableView.setLayoutY(66);

        TableColumn<InstructorClients, Long> instructorIdColumn = new TableColumn<InstructorClients, Long>("Код тренера");
        instructorIdColumn.setPrefWidth(200);
        instructorIdColumn.setCellValueFactory(new PropertyValueFactory<InstructorClients, Long>("instructorId"));
        selectTableView.getColumns().add(instructorIdColumn);

        TableColumn<InstructorClients, String> instructorLastNameColumn = new TableColumn<InstructorClients, String>("Фамилия");
        instructorLastNameColumn.setPrefWidth(200);
        instructorLastNameColumn.setCellValueFactory(new PropertyValueFactory<InstructorClients, String>("lastName"));
        selectTableView.getColumns().add(instructorLastNameColumn);

        TableColumn<InstructorClients, String> instructorFirstNameColumn = new TableColumn<InstructorClients, String>("Имя");
        instructorFirstNameColumn.setPrefWidth(200);
        instructorFirstNameColumn.setCellValueFactory(new PropertyValueFactory<InstructorClients, String>("firstName"));
        selectTableView.getColumns().add(instructorFirstNameColumn);

        TableColumn<InstructorClients, Integer> count = new TableColumn<InstructorClients, Integer>("Кол-во клиентов");
        count.setPrefWidth(200);
        count.setCellValueFactory(new PropertyValueFactory<InstructorClients, Integer>("count"));
        selectTableView.getColumns().add(count);

        selectAnchor.getChildren().add(selectTableView);
    }

    @FXML
    void buttonInstructorPremiumClick(ActionEvent event) {
        selectAnchor.setVisible(true);
        selectLabel.setText("Запрос премии тренерам");

        SelectDBHandler selectDBHandler = new SelectDBHandler();
        List<InstructorClients> instructorClients = selectDBHandler.getInstructorPremium();
        ObservableList<InstructorClients> instructorClientsObservableList = FXCollections.observableArrayList(instructorClients);

        TableView<InstructorClients> selectTableView = new TableView<InstructorClients>(instructorClientsObservableList);
        selectTableView.setPrefWidth(798);
        selectTableView.setPrefHeight(450);
        selectTableView.setLayoutX(14);
        selectTableView.setLayoutY(66);

        TableColumn<InstructorClients, Long> instructorIdColumn = new TableColumn<InstructorClients, Long>("Код тренера");
        instructorIdColumn.setPrefWidth(160);
        instructorIdColumn.setCellValueFactory(new PropertyValueFactory<InstructorClients, Long>("instructorId"));
        selectTableView.getColumns().add(instructorIdColumn);

        TableColumn<InstructorClients, String> instructorLastNameColumn = new TableColumn<InstructorClients, String>("Фамилия");
        instructorLastNameColumn.setPrefWidth(160);
        instructorLastNameColumn.setCellValueFactory(new PropertyValueFactory<InstructorClients, String>("lastName"));
        selectTableView.getColumns().add(instructorLastNameColumn);

        TableColumn<InstructorClients, String> instructorFirstNameColumn = new TableColumn<InstructorClients, String>("Имя");
        instructorFirstNameColumn.setPrefWidth(160);
        instructorFirstNameColumn.setCellValueFactory(new PropertyValueFactory<InstructorClients, String>("firstName"));
        selectTableView.getColumns().add(instructorFirstNameColumn);

        TableColumn<InstructorClients, Integer> count = new TableColumn<InstructorClients, Integer>("Кол-во клиентов");
        count.setPrefWidth(160);
        count.setCellValueFactory(new PropertyValueFactory<InstructorClients, Integer>("count"));
        selectTableView.getColumns().add(count);

        TableColumn<InstructorClients, Double> premium = new TableColumn<InstructorClients, Double>("Премия");
        premium.setPrefWidth(160);
        premium.setCellValueFactory(new PropertyValueFactory<InstructorClients, Double>("premium"));
        selectTableView.getColumns().add(premium);

        selectAnchor.getChildren().add(selectTableView);
    }

    @FXML
    private Button buttonOkClient;

    @FXML
    private Button btnClearHallForm;

    @FXML
    private Button btnClearUserForm;

    @FXML
    void btnClearHallFormClick(ActionEvent event) {
        hallIdtxt.setText("");
        nameHallTxt.setText("");
    }

    @FXML
    void btnClearUserFormClick(ActionEvent event) {
        userIdTxt.setText("");
        usernameTxt.setText("");
        passwordTxt.setText("");
    }


    @FXML
    void btnAddClientsClick(ActionEvent event) {
        clientForm.setVisible(true);
        checkClient = false;
        clientIdTxt.setText("");
        clientLastNameTxt.setText("");
        clientFirstNameTxt.setText("");
        clientPhoneNumberTxt.setText("");
        clientInstructorIdTxt.setText("");
    }

    @FXML
    void btnDeleteClientsClick(ActionEvent event) {
        Client client = clientsTableView.getSelectionModel().getSelectedItem();
        System.out.println(client);
        if(client != null) {
            ClientDBHandler clientDBHandler = new ClientDBHandler();
            clientDBHandler.delete(client.getClientId());
            showTableViewClients();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не выбрана срока для удаления!");
            alert.setContentText("Пожалуйста, выберите строку, которую хотите удалить.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnUpdateClientsClick(ActionEvent event) {
        Client client = clientsTableView.getSelectionModel().getSelectedItem();
        if(client != null) {
            checkClient = true;
            clientForm.setVisible(true);
            clientIdTxt.setText(client.getClientId() + "");
            clientLastNameTxt.setText(client.getLastName());
            clientFirstNameTxt.setText(client.getFirstName());
            clientPhoneNumberTxt.setText(client.getPhoneNumber());
            clientInstructorIdTxt.setText(client.getInstructor() + "");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не выбрана строка для редактирования!");
            alert.setContentText("Пожалуйста, выберите строку, которую хотите редактировать.");
            alert.showAndWait();
        }
    }

    @FXML
    void clientsColumnClick(MouseEvent event) {

    }

    @FXML
    void btnAddAbonementClick(ActionEvent event) {
        abonementForm.setVisible(true);
        checkAbonement = false;
        abonementIdTxt.setText("");
        abonementDescriptionTxt.setText("");
        abonementPriceTxt.setText("");
        abonementHallIdTxt.setText("");
    }

    @FXML
    void btnAddAccountingClick(ActionEvent event) {
        accountingForm.setVisible(true);
        checkAccounting = false;
        accountingIdTxt.setText("");
        accountingClientIdTxt.setText("");
        accountingAbonementIdTxt.setText("");
        accountingMonthTxt.setText("");
        accountingStatusOplatyTxt.setText("");
    }

    @FXML
    void btnAddHallClick(ActionEvent event) {
        if(
                !hallIdtxt.getText().equals("")
                && !nameHallTxt.getText().equals("")
        ) {
            HallDBHandler hallDBHandler = new HallDBHandler();
            try {
                Long hall_id = Long.parseLong(hallIdtxt.getText());
                Hall hall = new Hall(hall_id, nameHallTxt.getText());
                hallDBHandler.add(hall);
                showTableViewHalls();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Поле Код зала должен содержать число!");
                alert.setContentText("Пожалуйста, введите число в поле Код Зала.");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Пользователь с таким именем уже существует!");
                alert.setContentText("Пожалуйста, введите уникальное имя.");
                alert.showAndWait();
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не все поля заполнены!");
            alert.setContentText("Пожалуйста, заполните все поля.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnAddInstructorsClick(ActionEvent event) {
        instructorForm.setVisible(true);
        checkInstructor = false;
        instructorIdTxt.setText("");
        instructorLastNameTxt.setText("");
        instructorFirstNameTxt.setText("");
        instructorOklad.setText("");
    }

    @FXML
    void btnAddUserClick(ActionEvent event) {
        if(!userIdTxt.getText().equals("") && !usernameTxt.getText().equals("") && !passwordTxt.getText().equals("")) {
            UserDBHandler userDBHandler = new UserDBHandler();
            try{
                Long user_id = Long.parseLong(userIdTxt.getText());
                User user = new User(user_id, usernameTxt.getText(), passwordTxt.getText());
                userDBHandler.add(user);
                showTableViewUsers();
            } catch(Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Поле код админа должен содержать число!");
                alert.setContentText("Пожалуйста, введите число в поле код админа.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не все поля заполнены!");
            alert.setContentText("Пожалуйста, заполните все поля.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnDeleteAbonementClick(ActionEvent event) {
        Abonement abonement = abonementTableView.getSelectionModel().getSelectedItem();
        if(abonement != null) {
            AbonementDBHandler abonementDBHandler = new AbonementDBHandler();
            abonementDBHandler.delete(abonement.getAbonementId());
            showTableViewAbonements();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не выбрана срока для удаления!");
            alert.setContentText("Пожалуйста, выберите строку, которую хотите удалить.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnDeleteAccountingClick(ActionEvent event) {
        Accounting accounting = accountingTableView.getSelectionModel().getSelectedItem();
        if(accounting != null) {
            AccountingDBHandler accountingDBHandler = new AccountingDBHandler();
            accountingDBHandler.delete(accounting.getAccountingId());
            showTableViewAccountings();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не выбрана срока для удаления!");
            alert.setContentText("Пожалуйста, выберите строку, которую хотите удалить.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnDeleteHallClick(ActionEvent event) {
        Hall hall = hallTableView.getSelectionModel().getSelectedItem();
        if(hall != null) {
            HallDBHandler hallDBHandler = new HallDBHandler();
            hallDBHandler.delete(hall.getHallId());
            showTableViewHalls();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не выбрана срока для удаления!");
            alert.setContentText("Пожалуйста, выберите строку, которую хотите удалить.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnDeleteInstructorsClick(ActionEvent event) {
        Instructor instructor = instructorTableView.getSelectionModel().getSelectedItem();
        if(instructor != null) {
            InstructorDBHandler instructorDBHandler = new InstructorDBHandler();
            instructorDBHandler.delete(instructor.getInstructorId());
            showTableViewInstructors();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не выбрана срока для удаления!");
            alert.setContentText("Пожалуйста, выберите строку, которую хотите удалить.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnDeleteUserClick(ActionEvent event) {
        User user = usersTableView.getSelectionModel().getSelectedItem();
        if(user != null) {
            UserDBHandler userDBHandler = new UserDBHandler();
            userDBHandler.delete(user.getUserId());
            showTableViewUsers();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не выбрана срока для удаления!");
            alert.setContentText("Пожалуйста, выберите строку, которую хотите удалить.");
            alert.showAndWait();
        }
    }

    @FXML
    void hallColumnClick(MouseEvent event) {
        Hall hall = hallTableView.getSelectionModel().getSelectedItem();
        System.out.println(hall);
        if(hall != null) {
            hallIdtxt.setText(hall.getHallId() + "");
            nameHallTxt.setText(hall.getName());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не выбрана срока для редактирование!");
            alert.setContentText("Пожалуйста, выберите строку, которую хотите удалить.");
            alert.showAndWait();
        }
    }

    @FXML
    void adminColumnClick(MouseEvent event) {
        User user = usersTableView.getSelectionModel().getSelectedItem();
        System.out.println(user);
        if(user != null) {
            userIdTxt.setText(user.getUserId() + "");
            usernameTxt.setText(user.getUsername());
            passwordTxt.setText(user.getPassword());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не выбрана срока для редактирование!");
            alert.setContentText("Пожалуйста, выберите строку, которую хотите удалить.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnUpdateAbonementClick(ActionEvent event) {
        Abonement abonement = abonementTableView.getSelectionModel().getSelectedItem();
        if(abonement != null) {
            checkAbonement = true;
            accountingForm.setVisible(true);
            abonementIdTxt.setText(abonement.getAbonementId() + "");
            abonementDescriptionTxt.setText(abonement.getDescription());
            abonementPriceTxt.setText(abonement.getPrice() + "");
            abonementHallIdTxt.setText(abonement.getHall() + "");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не выбрана строка для редактирования!");
            alert.setContentText("Пожалуйста, выберите строку, которую хотите редактировать.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnUpdateAccountingClick(ActionEvent event) {
        Accounting accounting = accountingTableView.getSelectionModel().getSelectedItem();
        if(accounting != null) {
            checkAccounting = true;
            accountingForm.setVisible(true);
            accountingIdTxt.setText(accounting.getAccountingId() + "");
            accountingClientIdTxt.setText(accounting.getClientId() + "");
            accountingAbonementIdTxt.setText(accounting.getAbonementId() + "");
            accountingMonthTxt.setText(accounting.getMonth());
            accountingStatusOplatyTxt.setText(accounting.getStatus_oplaty() + "");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не выбрана строка для редактирования!");
            alert.setContentText("Пожалуйста, выберите строку, которую хотите редактировать.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnUpdateHallClick(ActionEvent event) {
        if(!hallIdtxt.getText().equals("") && !hall_nameColumn.getText().equals("")) {
            try {
                HallDBHandler hallDBHandler = new HallDBHandler();
                Long hall_id = Long.parseLong(hallIdtxt.getText());
                Hall hall = new Hall(hall_id, nameHallTxt.getText());
                hallDBHandler.update(hall);
                showTableViewHalls();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Поле код зала должен содержать число!");
                alert.setContentText("Пожалуйста, введите число в поле код зада.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не все поля заполнены!");
            alert.setContentText("Пожалуйста, заполните все поля.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnUpdateInstructorsClick(ActionEvent event) {
        Instructor instructor = instructorTableView.getSelectionModel().getSelectedItem();
        if(instructor != null) {
            checkInstructor = true;
            instructorForm.setVisible(true);
            instructorIdTxt.setText(instructor.getInstructorId() + "");
            instructorLastNameTxt.setText(instructor.getLastName());
            instructorFirstNameTxt.setText(instructor.getFirstName());
            instructorOklad.setText(instructor.getOklad() + "");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не выбрана строка для редактирования!");
            alert.setContentText("Пожалуйста, выберите строку, которую хотите редактировать.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnUpdateUserClick(ActionEvent event) {
        if(!userIdTxt.getText().equals("") && !usernameTxt.getText().equals("") && !passwordTxt.getText().equals("")) {
            UserDBHandler userDBHandler = new UserDBHandler();
            try{
                Long user_id = Long.parseLong(userIdTxt.getText());
                User user = new User(user_id, usernameTxt.getText(), passwordTxt.getText());
                userDBHandler.update(user);
                showTableViewUsers();
            } catch(Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Поле код админа должен содержать число!");
                alert.setContentText("Пожалуйста, введите число в поле код админа.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не все поля заполнены!");
            alert.setContentText("Пожалуйста, заполните все поля.");
            alert.showAndWait();
        }
    }

    @FXML
    void updateAbonementTableViewClick(ActionEvent event) {
        showTableViewAbonements();
    }

    @FXML
    void updateAccountingTableViewClick(ActionEvent event) {
        showTableViewAccountings();
    }

    @FXML
    void updateHallTableViewClick(ActionEvent event) {
        showTableViewHalls();
    }

    @FXML
    void updateInstructorTableViewClick(ActionEvent event) {
        showTableViewInstructors();
    }

    @FXML
    void updateUserTableViewClick(ActionEvent event) {
        showTableViewUsers();
    }

    @FXML
    private TableView<Abonement> abonementTableView;

    @FXML
    private TableColumn<Abonement, Long> abonement_id_column;

    @FXML
    private TableColumn<Accounting, Long> abonement_id_fkColumn;

    @FXML
    private TableView<Accounting> accountingTableView;

    @FXML
    private TableColumn<Accounting, Long> accounting_idColumn;

    @FXML
    private Button btnAddAbonement;

    @FXML
    private Button btnAddAccounting;

    @FXML
    private Button btnAddHall;

    @FXML
    private Button btnAddInstructors;

    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnDeleteAbonement;

    @FXML
    private Button btnDeleteAccounting;

    @FXML
    private Button btnDeleteHall;

    @FXML
    private Button btnDeleteInstructors;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnUpdateAbonement;

    @FXML
    private Button btnUpdateAccounting;

    @FXML
    private Button btnUpdateHall;

    @FXML
    private Button btnUpdateInstructors;

    @FXML
    private Button btnUpdateUser;

    @FXML
    private TableColumn<Abonement, String> descriptionColumn;

    @FXML
    private TableColumn<Instructor, String> first_name_column1;

    @FXML
    private TableView<Hall> hallTableView;

    @FXML
    private TableColumn<Hall, Long> hall_idColumn;

    @FXML
    private TableColumn<Abonement, Long> hall_id_fkColumn;

    @FXML
    private TableColumn<Hall, String> hall_nameColumn;

    @FXML
    private TableView<Instructor> instructorTableView;

    @FXML
    private TableColumn<Instructor, Long> instructor_id_column;

    @FXML
    private TableColumn<Instructor, String> last_name_column1;

    @FXML
    private TableColumn<Accounting, String> month;

    @FXML
    private TableColumn<Instructor, Double> oklad_column;

    @FXML
    private TableColumn<User, String> password_column;

    @FXML
    private TableColumn<Abonement, Double> priceColumn;

    @FXML
    private TableColumn<Accounting, Boolean> status_oplaty_column;

    @FXML
    private Button updateAbonementTableView;

    @FXML
    private Button updateAccountingTableView;

    @FXML
    private Button updateHallTableView;

    @FXML
    private Button updateInstructorTableView;

    @FXML
    private Button updateUserTableView;

    @FXML
    private TextField hallIdtxt;

    @FXML
    private TextField nameHallTxt;

    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    private TableColumn<User, Long> user_id_column;

    @FXML
    private TableColumn<User, String> username_column;

    @FXML
    private TableView<User> usersTableView;

    @FXML
    void updateClientTebleViewClick(ActionEvent event) {
        showTableViewClients();
    }

    @FXML
    void initialize() {
        checkClient = false;
        checkAbonement = false;
        checkInstructor = false;
        checkAccounting = false;
        showTableViewClients();
        showTableViewInstructors();
        showTableViewAbonements();
        showTableViewHalls();
        showTableViewAccountings();
        showTableViewUsers();
    }

    public void showTableViewClients() {
        ClientDBHandler clientDBHandler = new ClientDBHandler();
        List<Client> clientList = clientDBHandler.getAll();
        ObservableList<Client> clientsData = FXCollections.observableArrayList(clientList);

        client_id_column.setCellValueFactory(new PropertyValueFactory<Client, Long>("clientId"));
        last_name_column.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        first_name_column.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        phone_number_column.setCellValueFactory(new PropertyValueFactory<Client, String>("phoneNumber"));
        instructor_id_fk_column.setCellValueFactory(new PropertyValueFactory<Client, Long>("instructor"));

        clientsTableView.setItems(clientsData);
    }

    public void showTableViewInstructors() {
        InstructorDBHandler instructorDBHandler = new InstructorDBHandler();
        List<Instructor> instructorList = instructorDBHandler.getAll();
        ObservableList<Instructor> instructorObservableList = FXCollections.observableArrayList(instructorList);

        instructor_id_column.setCellValueFactory(new PropertyValueFactory<Instructor, Long>("instructorId"));
        last_name_column1.setCellValueFactory(new PropertyValueFactory<Instructor, String>("lastName"));
        first_name_column1.setCellValueFactory(new PropertyValueFactory<Instructor, String>("firstName"));
        oklad_column.setCellValueFactory(new PropertyValueFactory<Instructor, Double>("oklad"));

        instructorTableView.setItems(instructorObservableList);
    }

    public void showTableViewAbonements() {
        AbonementDBHandler abonementDBHandler = new AbonementDBHandler();
        List<Abonement> abonementList = abonementDBHandler.getAll();
        ObservableList<Abonement> abonementObservableList = FXCollections.observableArrayList(abonementList);

        abonement_id_column.setCellValueFactory(new PropertyValueFactory<Abonement, Long>("abonementId"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Abonement, String>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Abonement, Double>("price"));
        hall_id_fkColumn.setCellValueFactory(new PropertyValueFactory<Abonement, Long>("hall"));

        abonementTableView.setItems(abonementObservableList);
    }

    public void showTableViewHalls() {
        HallDBHandler hallDBHandler = new HallDBHandler();
        List<Hall> hallList = hallDBHandler.getAll();
        ObservableList<Hall> hallObservableList = FXCollections.observableArrayList(hallList);

        hall_idColumn.setCellValueFactory(new PropertyValueFactory<Hall, Long>("hallId"));
        hall_nameColumn.setCellValueFactory(new PropertyValueFactory<Hall, String>("name"));

        hallTableView.setItems(hallObservableList);
    }

    public void showTableViewAccountings() {
        AccountingDBHandler accountingDBHandler = new AccountingDBHandler();
        List<Accounting> accountingList = accountingDBHandler.getAll();
        ObservableList<Accounting> accountingObservableList = FXCollections.observableArrayList(accountingList);

        accounting_idColumn.setCellValueFactory(new PropertyValueFactory<Accounting, Long>("accountingId"));
        accountingClientIdColumn.setCellValueFactory(new PropertyValueFactory<Accounting, Long>("clientId"));
        abonement_id_fkColumn.setCellValueFactory(new PropertyValueFactory<Accounting, Long>("abonementId"));
        month.setCellValueFactory(new PropertyValueFactory<Accounting, String>("month"));
        status_oplaty_column.setCellValueFactory(new PropertyValueFactory<Accounting, Boolean>("status_oplaty"));

        accountingTableView.setItems(accountingObservableList);
    }

    public void showTableViewUsers() {
        UserDBHandler userDBHandler = new UserDBHandler();
        List<User> userList = userDBHandler.getAll();
        ObservableList<User> userObservableList = FXCollections.observableArrayList(userList);

        user_id_column.setCellValueFactory(new PropertyValueFactory<User, Long>("userId"));
        username_column.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        password_column.setCellValueFactory(new PropertyValueFactory<User, String>("password"));

        usersTableView.setItems(userObservableList);
    }
}
