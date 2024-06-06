module sn.ucao.repertoire {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens sn.ucao.repertoire to javafx.fxml;
    exports sn.ucao.repertoire;
}