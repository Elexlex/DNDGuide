package DnDGuide;

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
        switch (text){
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
                insertNumerals(text);
                break;
            case "D4":
            case "D6":
            case "D8":
            case "D10":
            case "D12":
            case "D20":
            case "D100":
                insertNumerals(text);
                break;
            case "+":
            case "-":
                insertOperators(text);
                break;
            case "C":
                if (expression.getText().length() != 0)
                    expression.setText("");
                if (result.getText().length() != 0)
                    result.setText("");
                break;
            case "ROLL":
                result.setText(String.valueOf(EvaluateController.evaluate(expression.getText())));
                break;
        }
    }
    public void insertNumerals(String numerals){
        expression.setText(expression.getText() + numerals);
    }
    public void insertOperators(String operators){
        expression.setText(expression.getText() + " " + operators + " ");
    }
}
