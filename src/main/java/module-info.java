module com.example.dndguide {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens DnD to javafx.fxml;
    exports DnD;
}