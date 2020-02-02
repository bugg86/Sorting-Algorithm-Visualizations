package BubbleSort;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Main extends Application {

    public BubbleSort.SortArray array = new BubbleSort.SortArray();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        VBox layout1 = new VBox(20);
        primaryStage.setTitle("Hello World");

        //Create the buttons that will call the sort methods
        Button insertionSort = new Button("Insertion Sort");
        Button bubbleSort = new Button("Bubble Sort");

        //Create the initial set of rectangles.
        array.makeRectangles();
        Group rectangles = new Group(array.getRectangles());
        layout1.getChildren().addAll(bubbleSort, insertionSort, rectangles);
        primaryStage.setScene(new Scene(layout1, 1800, 1000));
        primaryStage.show();

        //Add functionality to the buttons
        bubbleSort.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                array.bubbleSort();
            }
        });

        insertionSort.setOnAction(new EventHandler<ActionEvent>(){
           @Override public void handle(ActionEvent e) {
                InsertionSort.SortArray newArray = new InsertionSort.SortArray(array.getArray(), array.getRectangles());
                newArray.insertionSort();
           }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
