import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MazeDriver extends Application {
    public static int[][] game;
    public static boolean[][] solution;
    public static GridPane gridPane;
    public static StackPane[][] squares;
    public static int counter = 0;
    static int currenti = 0;
    static int currentj = 0;
    int countSol = 1;
    static Circle circle = new Circle(15);

    @Override
    public void start(Stage primaryStage) throws Exception {
        //    Parent root =  FXMLLoader.load(getClass().getResource("project4.fxml"));
        AnchorPane root = new AnchorPane();
        circle.setFill(Color.CORAL);
        VBox vBox = new VBox();
        Button button = new Button("Restart");
        Button button0 = new Button("New Game");
        Button button1 = new Button("Give UP");
        Button button2 = new Button("Show Paths");
        Button button3 = new Button("Exit");
        button3.setOnAction(event -> {
            primaryStage.close();
        });


        button0.setOnAction(event -> {
            try {
                root.getChildren().remove(gridPane);
                loadNewGame();
                root.getChildren().add(gridPane);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        vBox.setLayoutX(626.0);
        vBox.setLayoutY(39.0);
        vBox.setPrefHeight(520.0);
        vBox.setPrefWidth(219.0);
        vBox.setStyle("-fx-background-color: #00203FFF;");

        button.setMnemonicParsing(false);
        button.setPrefHeight(103.0);
        button.setPrefWidth(220.0);
        button.setStyle("-fx-background-color: #00203FFF; -fx-border-width: 0 0 3 0; -fx-border-color: #adefd1;");
        button.setTextFill(javafx.scene.paint.Color.valueOf("#adefd1"));
        button.setFont(new Font("System Bold", 32.0));

        button0.setMnemonicParsing(false);
        button0.setPrefHeight(103.0);
        button0.setPrefWidth(218.0);
        button0.setStyle("-fx-background-color: #00203FFF; -fx-border-width: 0 0 3 0; -fx-border-color: #adefd1;");
        button0.setTextFill(javafx.scene.paint.Color.valueOf("#adefd1"));
        button0.setFont(new Font("System Bold", 32.0));

        button1.setMnemonicParsing(false);
        button1.setPrefHeight(103.0);
        button1.setPrefWidth(222.0);
        button1.setStyle("-fx-background-color: #00203FFF; -fx-border-width: 0 0 3 0; -fx-border-color: #adefd1;");
        button1.setTextFill(javafx.scene.paint.Color.valueOf("#adefd1"));
        button1.setFont(new Font("System Bold", 32.0));


        button2.setMnemonicParsing(false);
        button2.setPrefHeight(103.0);
        button2.setPrefWidth(222.0);
        button2.setStyle("-fx-background-color: #00203FFF; -fx-border-width: 0 0 3 0; -fx-border-color: #adefd1;");

        button2.setTextFill(javafx.scene.paint.Color.valueOf("#adefd1"));
        button2.setFont(new Font("System Bold", 32.0));

        button3.setMnemonicParsing(false);
        button3.setPrefHeight(106.0);
        button3.setPrefWidth(219.0);
        button3.setStyle("-fx-background-color: #00203FFF; -fx-border-width: 0; -fx-border-color: #adefd1;");

        button3.setTextFill(javafx.scene.paint.Color.valueOf("#adefd1"));
        button3.setFont(new Font("System Bold", 32.0));

        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DOWN) {
                if (currenti  < game.length-1) {
                    if (game[currenti + 1][currentj]!=0) {

                        squares[currenti][currentj].getChildren().remove(circle);
                        squares[currenti + 1][currentj].getChildren().add(circle);
                        currenti++;
                    }
                }
            }

            if (event.getCode() == KeyCode.UP) {

                if (currenti  > 0) {
                    if (game[currenti -1][currentj]!=0) {

                        squares[currenti][currentj].getChildren().remove(circle);
                        squares[currenti - 1][currentj].getChildren().add(circle);
                        currenti--;
                    }
                }
            }

            if (event.getCode() == KeyCode.LEFT) {
                if (currentj  > 0) {
                    if (game[currenti ][currentj-1]!=0) {

                        squares[currenti][currentj].getChildren().remove(circle);
                        squares[currenti][currentj-1].getChildren().add(circle);
                        currentj--;
                    }
                }
            }
            if (event.getCode() == KeyCode.RIGHT) {

                if (currentj  < game.length-1) {
                    if (game[currenti][currentj+1]!=0) {

                        squares[currenti][currentj].getChildren().remove(circle);
                        squares[currenti][currentj+1].getChildren().add(circle);
                        currentj++;
                    }
                }
            }
            if (currenti == getEndi(game) && currentj == getEndj(game))
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("You Won!");
                alert.showAndWait();
                root.getChildren().remove(gridPane);
                try {
                    loadNewGame();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                root.getChildren().add(gridPane);
            }
        });
        button1.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You Lost!");
            alert.showAndWait();

            root.getChildren().remove(gridPane);
            try {
                loadNewGame();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            root.getChildren().add(gridPane);
        });
        button.setOnAction(event -> {
            counter--;
            try {
                root.getChildren().remove(gridPane);
                loadNewGame();
                root.getChildren().add(gridPane);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        loadNewGame();
        vBox.getChildren().addAll(button,button0,button1,button2,button3);
        root.getChildren().addAll(gridPane,vBox);
        gridPane.setLayoutX(0);
        gridPane.setLayoutY(0);
        root.setStyle("-fx-background-color: White;");
        button2.setOnAction(event -> {
            Stage stage = new Stage();
            VBox vBox1 = new VBox();
            vBox1.setPrefWidth(592);
            vBox1.setPrefHeight(716);
            GridPane gridPane = new GridPane();
            gridPane.setPrefWidth(592);
            gridPane.setPrefHeight(597);

            HBox hBox = new HBox();
            Button shownext = new Button("Show next Solution");
            TextField textField = new TextField();
            textField.setEditable(false);
            Button showbest = new Button("Show Best Solution");
            hBox.getChildren().addAll(shownext,textField,showbest);

            hBox.setSpacing(10);
            hBox.setAlignment(Pos.CENTER);
            vBox1.getChildren().addAll(gridPane,hBox);


            Main.solveMaze(game);
            ArrayList<boolean[][]> routes= Main.paths;


            int size = routes.size();
            textField.setText(countSol+"/"+size);
            showbest.setOnAction(event1 -> {
                int index = getBestPath(routes);
                countSol = index+1;
                set(gridPane,countSol,routes);
                textField.setText(countSol+"/"+size);
            });
            shownext.setOnAction(event1 -> {
                if (countSol==size)
                    countSol =0;
             countSol++;
                textField.setText(countSol+"/"+size);
                set(gridPane,countSol,routes);

            });
            set(gridPane,countSol,routes);
            Scene scene = new Scene(vBox1);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
        Scene scene = new Scene(root,867,597);
       primaryStage.setScene(scene);
       primaryStage.show();
    }

    private static int getEndi(int[][] game) {
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game.length; j++) {
                if (game[i][j]==3)
                    return i;
            }
        }
        return game.length-1;
    }
    private static int getEndj(int[][] game) {
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game.length; j++) {
                if (game[i][j]==3)
                    return j;
            }
        }
        return game.length-1;
    }

    private int getBestPath(ArrayList<boolean[][]> routes) {
        int min = Integer.MAX_VALUE;
        int tempc = 0;
        int tempi = -1;
        for(int i= 0;i<routes.size();i++) {
            tempc  = 0;
            for (int j = 0; j < routes.get(i).length; j++) {
                for (int k = 0; k < routes.get(i)[j].length; k++) {
                    if (routes.get(i)[j][k])
                        tempc++;

                }
            }
            if (tempc<min) {
                System.out.println(i+" "+tempc);
                min = tempc ;
                tempi = i;
            }
        }
        return tempi;
    }

    private void set(GridPane gridPane, int countSol, ArrayList<boolean[][]> routes) {
        gridPane.getChildren().clear();
        StackPane[][] squares = new StackPane[routes.get(0).length][routes.get(0).length];
        countSol = countSol-1;
        for (int i = 0; i < routes.get(countSol).length; i++) {
            for (int j = 0; j < routes.get(countSol)[i].length; j++) {
                if (game[i][j]!=0) {
                    squares[i][j] = new StackPane();
                    squares[i][j].setPrefWidth(500);
                    squares[i][j].setPrefHeight(500);
                    squares[i][j].setStyle("-fx-background-color: #adefd1; -fx-border-color: White;-fx-border-width:2;");
                    gridPane.add(squares[i][j],j,i);
                }
                else
                {
                    squares[i][j] = new StackPane();
                    squares[i][j].setPrefWidth(500);
                    squares[i][j].setPrefHeight(500);
                    squares[i][j].setStyle("-fx-background-color:  #00203FFF; -fx-border-color: White;-fx-border-width:2;");
                    gridPane.add(squares[i][j],j,i);
                }
            }
        }

        for (int i = 0; i < routes.get(countSol).length; i++) {
            for (int j = 0; j < routes.get(countSol)[i].length; j++) {

                if (routes.get(countSol)[i][j])
                    squares[i][j].setStyle("-fx-background-color: Coral;-fx-border-color: White;-fx-border-width:2;");
            }
        }
    }

    private static void loadNewGame() throws FileNotFoundException {
        if (squares!=null)
        squares[currenti][currentj].getChildren().remove(circle);

        File file = new File("src/samples/sample"+counter++%5+".txt");
        Scanner scanner = new Scanner(file);
        String Line = scanner.nextLine();
        String tokens[] = Line.split(" ");
        int i = 0,j=0;
        squares = new StackPane[tokens.length][tokens.length];
        game = new int[tokens.length][tokens.length];
        gridPane = new GridPane();
        gridPane.setPrefHeight(597);
        gridPane.setPrefWidth(592);
        for (int k = 0; k < tokens.length; k++) {
            game[i][k] = Integer.parseInt(tokens[k]);
        if (game[i][k]!=0) {
            squares[i][k] = new StackPane();
            squares[i][k].setPrefWidth(500);
            squares[i][k].setPrefHeight(500);
            squares[i][k].setStyle("-fx-background-color: #adefd1; -fx-border-color: White;-fx-border-width:2;");
            gridPane.add(squares[i][k],k,i);
        }
        else
        {
            squares[i][k] = new StackPane();
            squares[i][k].setPrefWidth(500);
            squares[i][k].setPrefHeight(500);
            squares[i][k].setStyle("-fx-background-color:  #00203FFF; -fx-border-color: White;-fx-border-width:2;");
            gridPane.add(squares[i][k],k,i);
        }
        }
        i++;
        while (scanner.hasNextLine()) {
            tokens = scanner.nextLine().split(" ");
            for (int k = 0; k < tokens.length; k++) {
                game[i][k] = Integer.parseInt(tokens[k]);
                if (game[i][k]!=0) {
                    squares[i][k] = new StackPane();
                    squares[i][k].setPrefWidth(500);
                    squares[i][k].setPrefHeight(500);
                    squares[i][k].setStyle("-fx-background-color: #adefd1; -fx-border-color: White;-fx-border-width:2;");
                    gridPane.add(squares[i][k],k,i);
                }
                else
                {
                    squares[i][k] = new StackPane();
                    squares[i][k].setPrefWidth(500);
                    squares[i][k].setPrefHeight(500);
                    squares[i][k].setStyle("-fx-background-color:  #00203FFF; -fx-border-color: White;-fx-border-width:2;");
                    gridPane.add(squares[i][k],k,i);
                }
            }
            i++;
        }
        int starti = findStarti(game);
        int startj = findStartj(game);
        System.out.println(starti);
        System.out.println(startj);
        squares[starti][startj].getChildren().add(circle);
        squares[getEndi(game)][getEndj(game)].setStyle("-fx-background-color:  Coral; -fx-border-color: White;-fx-border-width:2;");
        currenti = starti;
        currentj = startj;
    }

    private static int findStarti(int[][] game) {
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game.length; j++) {
                if (game[i][j]==2)
                    return i;
            }
        }
        return 0;
    }

    private static int findStartj(int[][] game) {
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game.length; j++) {
                if (game[i][j]==2)
                    return j;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
