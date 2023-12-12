import java.util.Scanner;

public class CalculadoraCLI {
    // Instância da calculadora RPN
    private CalculadoraRPN calc;

    // Construtor que inicializa a calculadora
    public CalculadoraCLI() {
        calc = new CalculadoraRPN();
    }

    // Método principal para iniciar o programa
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Entre com uma expressão no formato RPN (e.g. 1 2 +):");
            String expressao = scanner.nextLine();

            // Checa se usuário digitou sair
            if (expressao.equalsIgnoreCase("sair")) {
                break;
            }

            // Divide a expressão em tokens (números e operadores)
            String[] tokens = expressao.split(" ");

            try {
                // Avalia a expressão usando a calculadora
                for (String token : tokens) {
                    // Se o token for um número, coloque-o na pilha
                    if (isNumero(token)) {
                        // Converte o número e coloca na pilha
                        double number = Double.parseDouble(token);
                        calc.push(number, 0); // Assume que o número seja um número real (sem parte imaginária)
                    } else {
                        // O token é um operador, então execute-o usando a calculadora
                        calc.execute(token.charAt(0));
                    }
                }

                // Obtém o resultado do cálculo e imprime para o usuário
                NumeroComplexo resultado = calc.getResultado();
                // Checa se o resultado é um número complexo
                if (resultado.getImaginario() != 0) {
                    // O resultado é um número complexo, então imprime a parte imaginária
                    System.out.println("Result: " + resultado.getReal() + " + " + resultado.getImaginario() + "i");
                } else {
                    // Imprime só a parte real
                    System.out.println("Result: " + resultado.getReal());
                }
            } catch (Exception e) {
                //Ocorreu um erro, então imprime a mensagem de erro para o usuário
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // // Método auxiliar para verificar se uma string é um número
    private static boolean isNumero(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
