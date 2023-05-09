package DnD;

import java.util.Random;

public class EvaluateController {
   public static int evaluate(String expression){
       Random rand = new Random();
       String[] expressionParts = expression.split("\\+|\\-");
       int[] values = new int[expressionParts.length];
       for (int i = 0; i < expressionParts.length; i++) {
           String parts = expressionParts[i].trim();
           if (parts.contains("D")) {
               String[] diceParts = parts.split("D");
               if (diceParts[0].equals(""))
                   diceParts[0] = "1";
               int count = Integer.parseInt(diceParts[0]);
               int max = Integer.parseInt(diceParts[1]);
               int summary = 0;
               for (int j = 0; j < count; j++) {
                   summary += rand.nextInt(max) + 1;
               }
               values[i] = summary;
           } else {
               values[i] = Integer.parseInt(parts);
           }
       }
       char[] operators = expression.replaceAll("[0-9D ]", "").toCharArray();
       int expressionResult = values[0];
       for (int i = 0; i < operators.length; i++) {
           switch (operators[i]) {
               case '+' -> expressionResult += values[i + 1];
               case '-' -> expressionResult -= values[i + 1];
           }
       }
        return expressionResult;
   }
}
