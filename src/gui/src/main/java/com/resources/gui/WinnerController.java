package com.resources.gui;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import com.resources.logic.Game;

public class WinnerController {
    private Stage winnerStage;
    
    @FXML
    private Label resultLabel;

    public void setWinner(Stage winnerStage){
        this.winnerStage = winnerStage;
        setWinnerLabel();
    }

    @FXML
    public void setWinnerLabel(){
        int scorePlayer1 = Game.getInstance().getPlayer1().getGold();
        int scorePlayer2 = Game.getInstance().getPlayer2().getGold();

        if(scorePlayer1 > scorePlayer2){
            resultLabel.setText("The winner is Player 1!");
        }
        else if(scorePlayer1 < scorePlayer2){
            resultLabel.setText("The winner is Player 2!");
        }
        else{
            resultLabel.setText("The game is draw");
        }
    }
}
