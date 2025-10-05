import   javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField celsiusField = new TextField();
    private Label resultLabel = new Label();
    private ComboBox<String> fromOption = new ComboBox<>();
    private ComboBox<String> toOption = new ComboBox<>();
    private double fahrenheit;
    private double celsius;
    private double kelvin;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        celsiusField.setPromptText("Enter Celsius");
        fromOption.getItems().addAll("Celsius", "Kelvin", "Fahrenheit");
        toOption.getItems().addAll("Celsius", "Kelvin", "Fahrenheit");


        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> convertTemperature());



        Button saveButton = new Button("Save to DB");
        saveButton.setOnAction(e -> Database.saveTemperature(
                celsius, fahrenheit, kelvin, resultLabel));




        VBox root = new VBox(10, celsiusField, fromOption, toOption, convertButton, resultLabel, saveButton);
        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("Celsius to Fahrenheit");
        stage.setScene(scene);
        stage.show();
    }

    private void convertTemperature() {
        try {
            double valueFrom = Double.parseDouble(celsiusField.getText());
            String from = fromOption.getValue();
            String to = toOption.getValue();
            if(from.equals("Celsius") && to.equals("Fahrenheit")){
                celsius = valueFrom;
                fahrenheit = (celsius * 9 / 5) + 32;
                resultLabel.setText(String.format("Fahrenheit: %.2f", fahrenheit));
            }else if(from.equals("Fahrenheit") && to.equals("Celsius")){
                fahrenheit = valueFrom;
                celsius = (fahrenheit - 32) * 5 / 9;
                resultLabel.setText(String.format("Celsius: %.2f", fahrenheit));
            }else if(from.equals("Celsius") && to.equals("Kelvin")){
                celsius = valueFrom;
                kelvin = celsius + 273.15;
                resultLabel.setText(String.format("Kelvin: %.2f", fahrenheit));
            }else if(from.equals("Kelvin") && to.equals("Celsius")){
                kelvin = valueFrom;
                celsius = kelvin - 273.15;
                resultLabel.setText(String.format("Celsius: %.2f", fahrenheit));
            }else if(from.equals("Kelvin") && to.equals("Fahrenheit")){
                kelvin = valueFrom;
                fahrenheit = (kelvin - 273.15) * 9 / 5 + 32;
                resultLabel.setText(String.format("Fahrenheit: %.2f", fahrenheit));
            }else if(from.equals("Fahrenheit") && to.equals("Kelvin")){
                fahrenheit = valueFrom;
                kelvin = (fahrenheit - 32) * 5 / 9 + 273.15;
                resultLabel.setText(String.format("Kelvin: %.2f", fahrenheit));
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }
}