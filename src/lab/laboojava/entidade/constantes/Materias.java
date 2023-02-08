package lab.laboojava.entidade.constantes;

public enum Materias {
    M2(2),
    M5(5),
    M10(10);
    private double tipo;
    public double getTipo() { return tipo; }
    Materias(double tipo) {
        this.tipo = tipo / 100;
    }
}
