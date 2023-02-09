package lab.laboojava.negocio;

import lab.laboojava.entidade.Cliente;
import lab.laboojava.basedados.Banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteNegocio {

    private Banco bancoDados;

   List<Cliente> clientes = new ArrayList<Cliente>();

    public ClienteNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    public Optional<Cliente> consultar(String cpf) {

        if (bancoDados.getClientes().getCpf() != "") {
            return Optional.of(bancoDados.getClientes());
        } else {
            return Optional.empty();
        }
    }



    public void salvar(Cliente novoCliente) {
      bancoDados.adicionaCliente(novoCliente);
    }

    public void excluir() {

    }

}
