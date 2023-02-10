package lab.laboojava.basedados;

import lab.laboojava.entidade.*;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Produto> produtos;

    private List<Pedido> pedidos;

    private List<Cupom> cupons;

    private List<Cliente> clientes;
    private Cliente cliente;

    public Banco() {

        this.produtos = new ArrayList<>();
        this.pedidos = new ArrayList<>();

        this.clientes = new ArrayList<>();
        clientes.add(new Cliente("Filipe","123456789011"));
        clientes.add(new Cliente("Pedro","123456789"));
        clientes.add(new Cliente("Maria","12345678"));
        clientes.add(new Cliente("Ana","1234567"));
        clientes.add(new Cliente("Amaro","123456"));

        this.cupons = new ArrayList<>();
        cupons.add(new Cupom("CUPOM2", 2));
        cupons.add(new Cupom("CUPOM5", 5));
        cupons.add(new Cupom("CUPOM7", 7));

        this.cliente = new Cliente("","");

    }

    public Cliente getCliente() { return cliente; }

    public Cliente[] getCleintes(){ return clientes.toArray( new Cliente[clientes.size()]); }

    public Cupom[] getCupons() {
        return cupons.toArray(new Cupom[cupons.size()]);
    }

    public Pedido[] getPedidos() {
        return pedidos.toArray(new Pedido[pedidos.size()]);
    }

    public Produto[] getProdutos() { return produtos.toArray(new Produto[produtos.size()]); }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(int posicao) {
        produtos.remove(posicao);
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void adicionaCliente(Cliente cliente){
        this.clientes.add(cliente);
    }

    public void removerPedido(int posicao) {
        pedidos.remove(posicao);
    }
}
