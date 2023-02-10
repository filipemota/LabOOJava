package lab.laboojava.negocio;

import lab.laboojava.entidade.Cliente;
import lab.laboojava.basedados.Banco;
import lab.laboojava.entidade.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteNegocio {

    private Banco bancoDados;

    public ClienteNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    public Optional<Cliente> consultar(String cpf) {

        Cliente clienteEncontrado = new Cliente("", "");

        if (bancoDados.getCleintes().length == 0) {
            System.out.println("Não existem Clientes cadastrados!");
        } else {
            for (Cliente clientes : bancoDados.getCleintes()) {
                if (clientes.getCpf().equals(cpf)){ clienteEncontrado = clientes;}
            }
        }
        return Optional.of(clienteEncontrado);
    }

    public void listarCliente() {
        if (bancoDados.getCleintes().length == 0) {
            System.out.println("Não existem Clientes cadastrados!");
        } else {
            for (Cliente clientes : bancoDados.getCleintes()) {
                System.out.println(clientes.toString());
            }
        }
    }

    public void salvar(Cliente novoCliente) {
        bancoDados.adicionaCliente(novoCliente);
    }

    public void excluir() {

    }

}
