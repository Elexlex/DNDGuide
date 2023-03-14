module com.example.dndguide {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens DnDGuide to javafx.fxml;
    exports DnDGuide;
}