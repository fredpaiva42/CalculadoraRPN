import java.util.Scanner;

public class CalculadoraCLI {
    private CalculadoraRPN calc;

    public CalculadoraCLI() {
        calc = new CalculadoraRPN();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Entre com uma expressão no formato RPN (e.g. 1 1 +):");
            String expressao = scanner.nextLine();

            if(expressao.equalsIgnoreCase("sair"))
                break;

            prcoessarEntrada(expressao);
        } while (true);
    }



    private void prcoessarEntrada(String expressao) {
        // Divide a expressão em tokens (números e operadores)
        String[] tokens = expressao.split(" ");

        try {
            // Avalia a expressão
            for (String token : tokens) {
                // Se o tokem for um número, ele é adicionado a pilha.
                if (isNumero(token)) {
                    double numero = Double.parseDouble(token);
                    calc.push(numero, 0);
                } else
                    // token é um operador
                    calc.execute(token.charAt(0));
            }


        NumeroComplexo resultado = calc.getResultado();
        // Checa se o resultado é um número complexo
        if (resultado.getImaginario() != 0)
            // Então imprime a parte imaginária
            System.out.println("Resultado: " + resultado.getReal() + " + " + resultado.getImaginario() + "i");
        else
            System.out.println("Resultado: " + resultado.getReal());

        } catch(Exception e){
            // Imprime a mensagem de erro para o usuário
            System.out.println("Erro: " + e.getMessage());
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
