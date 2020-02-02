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

    public SortArray array = new SortArray();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        VBox layout1 = new VBox(20);
        primaryStage.setTitle("Hello World");
        //Create the button that will call the sort method.
        Button sort = new Button("Sort");
        //Create the initial set of rectangles.
        array.makeRectangles();
        Group rectangles = new Group(array.getRectangles());
        layout1.getChildren().addAll(sort, rectangles);
        primaryStage.setScene(new Scene(layout1, 1800, 1000));
        primaryStage.show();
        //Makes the button call the sort method.
        sort.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                array.sort();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
