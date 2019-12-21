public class Calculator {

    public static String process(String stringToSolve) {
        stringToSolve = stringToSolve.replaceAll("\\s+","");
        String[] operators = stringToSolve.split("[0-9]+");
        String[] operands = stringToSolve.split("[*/+-]");

        int result = Integer.parseInt(operands[0]);

        for (int i = 1; i < operands.length; i++) {
            if (operators[i].equals("+"))
                result += Double.parseDouble(operands[i]);
            else if (operators[i].equals("-"))
                result -= Double.parseDouble(operands[i]);
            else if (operators[i].equals("*"))
                result *= Double.parseDouble(operands[i]);
            else if (operators[i].equals("/"))
                result /= Double.parseDouble(operands[i]);
            else
                break;
        }
        return String.valueOf(result);
    }
}
