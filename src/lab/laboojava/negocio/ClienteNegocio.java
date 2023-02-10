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
        return Optional.of(localizaCliente(cpf));
    }

    public void registrarUsuário(Cliente cliente){
        bancoDados.setCliente(cliente);
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
        System.out.println("Cliente Excluido com sucesso");
    }

    public void excluir(String cpf) {
        bancoDados.removerCliente(localizaCliente(cpf));
        System.out.println("Cliente Excluido com sucesso");

    }

    private Cliente localizaCliente(String cpf) {

        Cliente clienteEncontrado = new Cliente("", "");

        if (bancoDados.getCleintes().length == 0) {
            System.out.println("Não existem Clientes cadastrados!");
        } else {
            for (Cliente clientes : bancoDados.getCleintes()) {
                if (clientes.getCpf().equals(cpf)) {
                    clienteEncontrado = clientes;
                }
            }
        }
        return clienteEncontrado;
    }

}
