package lab.laboojava.entidade;

import lab.laboojava.entidade.constantes.Materias;

public class Caderno extends Produto{
    private String nome;
    private Materias tipo;

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public Materias getTipo() { return tipo;  }
    public void setTipo(Materias tipo) { this.tipo = tipo; }

    @Override
    public double calcularFrete() {
        return (getPreco() * getQuantidade()) * (1 + tipo.getTipo());
    }
    @Override
    public String toString() {
        return "Livro{" +
                "nome='" + nome + '\'' +
                ", materias=" + tipo + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", preço='" + getPreco() + '\'' +
                '}';
    }

}
