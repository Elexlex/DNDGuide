package DnD;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DNDCalculatorController{
    @FXML private TextField expression;
    @FXML private TextField result;
    @FXML private AnchorPane anchorPane;


    public void onMouseClicked(MouseEvent mouseEvent){
        Button button = (Button) mouseEvent.getSource();
        String text = button.getText();
        switch (text) {
            case "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" -> insertNumerals(text);
            case "D4", "D6", "D8", "D10", "D12", "D20", "D100" -> insertNumerals(text);
            case "+", "-" -> {
                if (expression.getText().matches(".*\\d+.*"))
                    insertOperators(text);
            }
            case "C" -> {
                if (expression.getText().length() != 0)
                    expression.setText("");
                if (result.getText().length() != 0)
                    result.setText("");
            }
            case "ROLL" -> {
                if (expression.getText().matches(".*\\d+.*"))
                    result.setText(String.valueOf(EvaluateController.evaluate(expression.getText())));
            }

            case "<-" -> {
                String currentExpression = expression.getText();
                if (!currentExpression.isEmpty()) {
                    String newExpression = currentExpression.substring(0, currentExpression.length() - 1);
                    expression.setText(newExpression);
                }
            }
        }
    }
    private void insertNumerals(String numerals){
        expression.setText(expression.getText() + numerals);
    }
    private void insertOperators(String operators){
        expression.setText(expression.getText() + " " + operators + " ");
    }
}
