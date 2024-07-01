
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class calculator extends Application{
    private TextField display;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Calculator");

        display = new TextField();
        display.setEditable(false);
        display.setStyle("-fx-font-size: 24px;");

        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);

        String[][] buttonLabels = {
          {"7","8","9","/"},
          {"4","5","6","*"},
          {"1","2","3","-"},
          {"0",".","=","+"}
};
    for (int i = 0; i < buttonLabels.length; i++){
       for(int j = 0 ; j < buttonLabels[i].length; j++ ){
        String label = buttonLabels[i][j];
        Button button = new Button(label);
        button.setPrefSize(50, 50);
        button.setStyle("-fx-font-size: 18px;");
        button.setOnAction(e -> handleButtonAction(label));
        grid.add(button, j,i + 1);
       }
    }
    grid.add(display, 0, 0 , 4 , 1);

    Scene scene = new Scene(grid, 220, 270);
    primaryStage.setScene(scene);
    primaryStage.show();
}
private void handleButtonAction(String label){
    switch (label) {
        case "=":
        calculateResult();
        break;
            
        case "C":
        display.clear();
            break;
    
        default:
        display.appendText(label);
            break;
    }

}
private void calculateResult(){
    try{
        String input = display.getText();
        double result = eval(input);
        display.setText(String.valueOf(result));
    }
    catch(Exception e ){
        display.setText("Error");
    }
}
private double eval(String expression) {
    //simplistic evaluation logic for the sake if examples though not a safe way to evaluate expressions in production
    return new Object() {
        int pos = -1, ch;

        void nextChar() {
            ch = (++pos < expression.length())  ? expression.charAt(pos) : -1;
        }
        boolean eat(int charToEat) {
            while (ch == ' ') nextChar();
            if (ch == charToEat) {
                nextChar();
                return true;
                
            }
            return false; 
        }
            double parse (){
                nextChar();
                double x = parseExpression();
                if(pos < expression.length()) throw new RuntimeException("Unexpected:" + (char)ch);
                return x;
            }

            double parseExpression() {
             double x = parseTerm();
             for (;;){
               if (eat('*')) x += parseTerm(); //addition
               else if (eat ('-')) x -= parseTerm(); // subtraction
               else return x ;
             }
          
            
        }
        double parseTerm(){
            double x = parseFactor();
            for(;;){
                if     (eat('*')) x  *= parseFactor(); //multiplication
                else if (eat('/')) x /= parseFactor(); //division
                else return x;
                    
                }
            }
            double parseFactor(){
                if (eat('+')) return parseFactor(); // unary plus 
                if (eat('-')) return -parseFactor(); // unary minus
                 
            double x;
            int startPos = this.pos;
            if (eat('(')) { // parentheses
                x = parseExpression();
                eat(')');
            }
              else if ((ch >= '0' && ch <= '9') || ch == ',') { //numbers
                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                x = Double.parseDouble(expression.substring(startPos, this.pos)); 
                    
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }
                return x;
                
              }      
                }.parse();
            }
        }
    
