module eus.ehu {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires javafx.graphics;
    requires org.hibernate.orm.core;
    opens eus.ehu.controllers to javafx.fxml;
    exports eus.ehu.controllers;

    opens eus.ehu.usermodel to javafx.fxml, org.hibernate.orm.core;
    exports eus.ehu.usermodel;

    opens eus.ehu.ui to javafx.graphics, javafx.fxml;
    exports eus.ehu.ui;
}