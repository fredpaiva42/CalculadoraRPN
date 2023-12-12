import java.util.Stack;

public class CalculadoraRPN {

    private Stack<NumeroComplexo> pilhaOperandos;

    public CalculadoraRPN() {
        pilhaOperandos = new Stack<>();
    }

    public void push(double real, double imag) {
        NumeroComplexo num = new NumeroComplexo(real, imag);
        pilhaOperandos.push(num);
    }

    // Método que executa uma operação binária (como adição ou multiplicação)
    // com os dois últimos operandos da pilha
    public void execute(char operador) {
        if (pilhaOperandos.size() < 2) {
            throw new IllegalStateException("Operador " + operador + " (posição: " +
                    pilhaOperandos.size() + "): parâmetros insuficientes");
        }

        NumeroComplexo operando2 = pilhaOperandos.pop();
        NumeroComplexo operando1 = pilhaOperandos.pop();

        switch (operador) {
            case '+':
                pilhaOperandos.push(operando1.soma(operando2));
                break;
            case '-':
                pilhaOperandos.push(operando1.subtrai(operando2));
                break;
            case '*':
                pilhaOperandos.push(operando1.multiplica(operando2));
                break;
            case '/':
                try {
                    pilhaOperandos.push(operando1.divide(operando2));
                } catch (IllegalArgumentException ex) {
                    System.err.println("Error: divisão por 0 não é permitida");
                    return;
                }
                break;
            case '^':
                pilhaOperandos.push(operando1.pow(operando2));
                break;
            case '%':
                pilhaOperandos.push(operando1.porcentagem(operando2));
                break;
            default:
                throw new IllegalArgumentException("Operador desconhecido " + operador);
        }
    }

    public NumeroComplexo getResultado() {
        if (pilhaOperandos.isEmpty()) {
            throw new IllegalStateException("Sem operandos na pilha");
        }
        return pilhaOperandos.peek();
    }
}