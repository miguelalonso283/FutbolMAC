module org.example.futbolmacjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires java.desktop;

    opens org.example.futbolmacjavafx to javafx.fxml;
    opens org.example.futbolmacjavafx.control to javafx.fxml;
    opens org.example.futbolmacjavafx.modelo to javafx.base;
    exports org.example.futbolmacjavafx;
}