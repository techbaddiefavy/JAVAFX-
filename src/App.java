import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Scene; 
 public class App extends Application {

    public void start(Stage primaryStage){

        Circle circle = new Circle();
        circle.setCenterX(300); // Set the x-coordinate of the center
        circle.setCenterY(250); // set the y-coordinate of the center
        circle.setRadius(200);  // Set the radius of the circle 
        circle.setFill(Color.PINK); //SET the border color of the circle 
        circle.setStroke(Color.BLACK); //set the border color of the circle 
        circle.setStrokeWidth(2); //set the border width of the circle 

        //create a pane to hold the circle
        Pane pane = new Pane();
        pane.getChildren().add(circle);

        // create a scene with  the pane
        Scene scene = new Scene(pane, 400 ,300);

        // set the stage 
        primaryStage.setTitle("Circle 1");
        primaryStage.setScene(scene);
        primaryStage.show();






    }




public static void main(String[] args){
    launch(args);
}





}