module com.example.gametictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gametictactoe to javafx.fxml;
    exports com.example.gametictactoe;
}