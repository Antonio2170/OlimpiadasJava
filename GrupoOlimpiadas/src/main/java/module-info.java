module ni.edu.uam.grupoolimpiadas {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.grupoolimpiadas to javafx.fxml;
    exports ni.edu.uam.grupoolimpiadas;
}