package com.example.gametictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TicTacToe extends Application {
int playerXscore=0,playerOscore=0;
Button buttons[][]=new Button[3][3];
boolean playerXTurn=true;
private Label PlayerXscoreLabel,PlayerOscoreLabel;
    private BorderPane createContent(){
        BorderPane root = new BorderPane();
        // Tittle
        Label titleLabel=new Label("Tic Tac Toe");
        titleLabel.setStyle("-fx-font-size: 24px;-fx-font-weight: bold;");
          root.setTop(titleLabel);

        //Game Board
        HBox ScoreBord=new HBox(20);
        PlayerXscoreLabel=new Label("Player X :0");
        PlayerXscoreLabel.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");
        PlayerOscoreLabel=new Label("Player O :0");
        PlayerOscoreLabel.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");
        ScoreBord.getChildren().addAll(PlayerXscoreLabel,PlayerOscoreLabel);
       root.setBottom(ScoreBord);

        //Score
        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                Button button =new Button();
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size: 35px;-fx-font-weight: bold;");
                button.setOnAction(actionEvent -> buttonClicked(button));
                buttons[i][j]=button;

                gridPane.add(button,i,j);
            }

        }
              root.setCenter(gridPane);
        return root;
    }

    private void buttonClicked(Button button){
        if(button.getText().equals("")){
            if(playerXTurn){
             button.setText("X");
            }else{
                button.setText("O");
            }
        }playerXTurn=!playerXTurn;
        checkWinner();
    }
    private void checkWinner(){
        //row
        for (int row = 0; row < 3; row++) {
            if(buttons[row][0].getText().equals(buttons[row][1].getText())
            && buttons[row][1].getText().equals(buttons[row][2].getText())
            && !buttons[row][0].getText().isEmpty()
            ){
                //we will have a winner
                String winner=buttons[row][0].getText();
                showWinnerDialogue(winner);
                updateScore(winner);
                resetboard();
                return;
            }
        }
        //col
        for (int col = 0; col< 3; col++) {
            if(buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()
            ){
                //we will have a winner
                String winner=buttons[0][col].getText();
                showWinnerDialogue(winner);
                updateScore(winner);
                resetboard();
                return;
            }
        }
        //diagonal
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(buttons[0][0].getText().equals(buttons[1][1].getText())
                        &&buttons[1][1].getText().equals(buttons[2][2].getText())
                        && !buttons[0][0].getText().isEmpty()
                ){
                    // we will have a winner
                    String winner=buttons[0][0].getText();
                    showWinnerDialogue(winner);
                    updateScore(winner);
                    resetboard();
                    return;

                }if(buttons[2][0].getText().equals(buttons[1][1].getText())
                        && buttons[1][1].getText().equals(buttons[0][2].getText())
                        && !buttons[2][0].getText().isEmpty()
        ){
                    // we will have a winner
                    String winner=buttons[2][0].getText();
                    showWinnerDialogue(winner);
                    updateScore(winner);
                    resetboard();
                      return;
                }
            }
        }
        //tie
        boolean tie=true;
        for(Button row[]:buttons){
            for(Button button: row){
                if(button.getText().isEmpty())
                    tie=false;
            }
        }
        if(tie){
            resetboard();
            showTieDialogue();
        }
    }
    private void showWinnerDialogue(String winner){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulations  "+winner+"! You won the game");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void showTieDialogue(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("game over!  its a Tie!");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void updateScore(String winner){
        if(winner.equals("X")){
            playerXscore++;
            PlayerXscoreLabel.setText("Player X: "+playerXscore);
        }else{
            playerOscore++;
            PlayerOscoreLabel.setText("Player O: "+playerOscore);
        }
    }
    private void resetboard(){
       for(Button row[]:buttons){
           for(Button button: row){
               button.setText("");
           }
       }
    }
    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(createContent());
        stage.setTitle("Welcome to Tic Tac Toe Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}