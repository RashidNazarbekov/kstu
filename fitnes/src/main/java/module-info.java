module com.kstu.fitnes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires commons.dbcp;

    opens com.kstu.fitnes.model to javafx.fxml;
    opens com.kstu.fitnes.controller to javafx.fxml;

    exports com.kstu.fitnes.model;
    exports com.kstu.fitnes.controller;
    exports com.kstu.fitnes;
}