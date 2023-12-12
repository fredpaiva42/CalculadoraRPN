public class NumeroComplexo {
    private double real;
    private double imaginario;

    public NumeroComplexo(double real, double imaginario) {
        this.real = real;
        this.imaginario = imaginario;
    }

    public double getReal() {
        return this.real;
    }

    public double getImaginario() {
        return this.imaginario;
    }

    public NumeroComplexo soma(NumeroComplexo other) {
        double real = this.real + other.real;
        double imaginario = this.imaginario + other.imaginario;
        return new NumeroComplexo(real, imaginario);
    }

    public NumeroComplexo subtrai(NumeroComplexo other) {
        double real = this.real - other.real;
        double imaginario = this.imaginario - other.imaginario;
        return new NumeroComplexo(real, imaginario);
    }

    public NumeroComplexo multiplica(NumeroComplexo other) {
        double real = this.real * other.real - this.imaginario * other.imaginario;
        double imaginario = this.real * other.imaginario + this.imaginario * other.real;
        return new NumeroComplexo(real, imaginario);
    }

    public NumeroComplexo divide(NumeroComplexo other) {
        if (other.real == 0 && other.imaginario == 0) {
            throw new IllegalArgumentException("Não é possível dividir por 0");
        }

        double denominador = other.real * other.real + other.imaginario * other.imaginario;
        double real = (this.real * other.real + this.imaginario * other.imaginario) / denominador;
        double imaginario = (this.imaginario * other.real - this.real * other.imaginario) / denominador;
        return new NumeroComplexo(real, imaginario);
    }

    public NumeroComplexo pow(NumeroComplexo other){
        // Calcula a magnitude do número complexo
        double magnitude = Math.sqrt(real * real + imaginario * imaginario);

        // Calcula o argumento do número complexo
        double argumento = Math.atan2(imaginario, real);

        // Calcula a magnitude do resultado
        double magnitudeResultado = Math.pow(magnitude, other.real) * Math.exp(-argumento * other.imaginario);

        // Calcula o argumento do resultado
        double argumentoResultado = argumento * other.real + 0.5 * Math.log(magnitude) * other.imaginario;

        // Calcula as partes reais e imaginárias do resultado
        double realResult = magnitudeResultado * Math.cos(argumentoResultado);
        double imaginarioResultado = magnitudeResultado * Math.sin(argumentoResultado);

        return new NumeroComplexo(realResult, imaginarioResultado);
    }

    public NumeroComplexo porcentagem(NumeroComplexo other) {
        double real = (this.real * other.real) / 100;
        double imaginario = (this.imaginario * other.imaginario) / 100;
        return new NumeroComplexo(real, imaginario);
    }

}
