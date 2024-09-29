module com.edu.uniquindio.co.marketplace {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    exports com.edu.uniquindio.co.marketplace.clases;
    opens com.edu.uniquindio.co.marketplace.clases to javafx.fxml;
    exports com.edu.uniquindio.co.marketplace.clases.enums;
    exports com.edu.uniquindio.co.marketplace.clases.market;
    exports com.edu.uniquindio.co.marketplace.clases.personas;
    exports com.edu.uniquindio.co.marketplace.clases.util;
    opens com.edu.uniquindio.co.marketplace.clases.enums to javafx.fxml;
    opens com.edu.uniquindio.co.marketplace.clases.market to javafx.fxml;
    opens com.edu.uniquindio.co.marketplace.clases.personas to javafx.fxml;
    opens com.edu.uniquindio.co.marketplace.clases.util to javafx.fxml;
}