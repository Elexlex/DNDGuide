package DnD;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DNDCalculatorController {
    @FXML private Label expression;
    @FXML private Label result;
    public void onMouseClicked(MouseEvent mouseEvent){
        Button button = (Button) mouseEvent.getSource();
        String text = button.getText();
        switch (text) {
            case "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" -> insertNumerals(text);
            case "D4", "D6", "D8", "D10", "D12", "D20", "D100" -> insertNumerals(text);
            case "+", "-" -> insertOperators(text);
            case "C" -> {
                if (expression.getText().length() != 0)
                    expression.setText("");
                if (result.getText().length() != 0)
                    result.setText("");
            }
            case "ROLL" -> result.setText(String.valueOf(EvaluateController.evaluate(expression.getText())));
        }
    }
    public void insertNumerals(String numerals){
        expression.setText(expression.getText() + numerals);
    }
    public void insertOperators(String operators){
        expression.setText(expression.getText() + " " + operators + " ");
    }
}
