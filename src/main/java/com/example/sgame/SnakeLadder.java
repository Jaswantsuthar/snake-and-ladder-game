package com.example.sgame;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class SnakeLadder extends Application {
    public static final int tileSize =40,width=10,height=10,buttonLine=height*tileSize+50,infoLine=height*tileSize+20;
    Player playerFirst,playerSecond;

    boolean firstPlayerturn =true,gameStart=false;
    int diceValue;
    private Pane createContent(){
        Pane root =new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+100);

        for (int i = 0; i <height; i++) {
            for (int j = 0; j <width; j++) {
                Tile tile =new Tile(tileSize);
                tile.setTranslateX(i*tileSize);
                tile.setTranslateY(j*tileSize);
                root.getChildren().addAll(tile);
            }

        }
        Tile tile =new Tile(tileSize);
        root.getChildren().addAll(tile);

        //putting image on root
        Image img = new Image("C:\\Users\\JASWANT SUTHAR\\IdeaProjects\\sgame\\src\\img.png");
        ImageView boarImage =new ImageView();
        boarImage.setFitWidth(width*tileSize);
        boarImage.setFitHeight(height*tileSize);
        boarImage.setImage(img);

        root.getChildren().addAll(boarImage);

        //buttons and info
        Button startButton =new Button("Start");
        startButton.setTranslateX(180);
        startButton.setTranslateY(buttonLine);

        Button playerOneButton =new Button("Player one");
        playerOneButton.setTranslateX(10);
        playerOneButton.setTranslateY(buttonLine);

        Button playerTwoButton =new Button("Player two");
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(buttonLine);

        Label diceLable =new Label("Start the game");
        diceLable.setTranslateX(160);
        diceLable.setTranslateY(infoLine);

        //Players
        playerFirst =new Player("jaswant", Color.BLACK,tileSize/2);
        playerSecond =new Player("savai",Color.WHITE,tileSize/2-5);

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStart){
                    if (firstPlayerturn){
                        diceValue =rollDice();
                        diceLable.setText("Dice : "+ diceValue );
                        playerFirst.movePlayer(diceValue);
                        firstPlayerturn =!firstPlayerturn;
                        if (playerFirst.checkWinner()){
                            diceLable.setText("winner is "+ playerFirst.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstPlayerturn=true;
                            gameStart =false;
                        }
                    }
                }

            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStart){
                    if (!firstPlayerturn){
                        diceValue =rollDice();
                        diceLable.setText("Dice : "+ diceValue );
                        playerSecond.movePlayer(diceValue);
                        firstPlayerturn =!firstPlayerturn;
                        if (playerSecond.checkWinner()){
                            diceLable.setText("winner is "+ playerSecond.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstPlayerturn=true;
                            gameStart =false;
                        }
                    }
                }

            }
        });
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStart =true;
                startButton.setDisable(true);
                playerFirst.setStart();
                playerSecond.setStart();

            }
        });

        root.getChildren().addAll(startButton,playerOneButton,playerTwoButton,diceLable,playerFirst.getCoin(),playerSecond.getCoin());

        return root;
    }

    private int rollDice(){
        return (int)(Math.random()*6+1);
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}