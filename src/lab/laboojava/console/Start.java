package lab.laboojava.console;

import lab.laboojava.basedados.Banco;
import lab.laboojava.entidade.*;
import lab.laboojava.negocio.ClienteNegocio;
import lab.laboojava.negocio.PedidoNegocio;
import lab.laboojava.negocio.ProdutoNegocio;
import lab.laboojava.utilidade.LeitoraDados;

import java.util.Optional;

public class Start {

    private static Cliente clienteLogado = null;
    private static Banco banco = new Banco();
    private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
    private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
    private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

    public static void main(String[] args) {
        System.out.println("###########################");
        System.out.println("Bem vindo ao e-Compras");
        System.out.println("###########################");

        String opcao = "";

        while (true) {

            if (clienteLogado == null) {
                System.out.println(" ");
                System.out.println("Digite o cpf:");
                System.out.println("#------------------#");
                String cpf = "";
                cpf = LeitoraDados.lerDado();
                identificarUsuario(cpf);
            }

            System.out.println("##########################");
            System.out.println("Selecione uma opção:");
            System.out.println("1 -  Cadastrar Livro");
            System.out.println("2 -  Excluir Livro");
            System.out.println("3 -  Cadastrar Caderno");
            System.out.println("4 -  Excluir Caderno");
            System.out.println("5 -  Fazer pedido");
            System.out.println("6 -  Excluir pedido");
            System.out.println("7 -  Listar produtos");
            System.out.println("8 -  Listar pedidos");
            System.out.println("----------------------");
            System.out.println("9 -  Consultar Livro");
            System.out.println("10 - Consultar Caderno");
            System.out.println("11 - Consultar Pedido");
            System.out.println("----------------------");
            System.out.println("12 - Consultar Clientes");
            System.out.println("13 - Listar Clientes");
            System.out.println("14 - Cadasrar Clientes");
            System.out.println("15 - Excluir Clientes");
            System.out.println("----------------------");
            System.out.println("16 - Deslogar");
            System.out.println("17 - Sair");
            System.out.println("##########################");

            opcao = LeitoraDados.lerDado();

            switch (opcao) {
                case "1":
                    Livro livro = LeitoraDados.lerLivro();
                    produtoNegocio.salvar(livro);
                    break;
                case "2":
                    System.out.println("Digite o código do livro:");
                    String codigoLivro = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoLivro);
                    break;
                case "3":
                    Caderno caderno = LeitoraDados.lerCaderno();
                    produtoNegocio.salvar(caderno);
                    break;
                case "4":
                    System.out.println("Digite o código do Caderno:");
                    String codigoCaderno = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoCaderno);
                    break;
                case "5":
                    Pedido pedido = LeitoraDados.lerPedido(banco);
                    Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

                    if (cupom.isPresent()) {
                        pedidoNegocio.salvar(pedido, cupom.get());
                    } else {
                        pedidoNegocio.salvar(pedido);
                    }
                    break;
                case "6":
                    System.out.println("Digite o código do pedido:");
                    String codigoPedido = LeitoraDados.lerDado();
                    pedidoNegocio.excluir(codigoPedido);
                    break;
                case "7":
                    produtoNegocio.listarTodos();
                    break;
                case "8":
                    pedidoNegocio.listarTodos();
                    break;
                case "9":
                    System.out.println("Digite o código do livro:");
                    String buscaLivro = LeitoraDados.lerDado();
                    System.out.println(produtoNegocio.consultar(buscaLivro).toString());
                    break;
                case "10":
                    System.out.println("Digite o código do Caderno:");
                    String buscaCaderno = LeitoraDados.lerDado();
                    System.out.println(produtoNegocio.consultar(buscaCaderno).toString());
                    break;
                case "11":
                    System.out.println("Digite o código numero do Pedido:");
                    String buscaPedido = LeitoraDados.lerDado();
                    pedidoNegocio.consultar(buscaPedido);
                    break;
                case "12":
                    System.out.println("Consultar Clientes");
                    System.out.println(" ");
                    System.out.println("Digite o cpf:");
                    System.out.println("#------------------#");
                    String cpf = "";
                    cpf = LeitoraDados.lerDado();
                    consultarUsuario(cpf);
                    break;
                case "13":
                    System.out.println("Listar Clientes");
                    clienteNegocio.listarCliente();
                    break;
                case "14":
                    System.out.println("Cadastrar Clientes");
                    Cliente novoCliente = LeitoraDados.lerCliente();
                    clienteNegocio.salvar(novoCliente);
                    clienteLogado = novoCliente;
                    break;
                case "15":
                    System.out.println("Excluir Clientes");
                    System.out.println(" ");
                    System.out.println("Digite o cpf:");
                    System.out.println("#------------------#");
                    String cpfCliente = "";
                    cpfCliente = LeitoraDados.lerDado();
                    excluirUsuario(cpfCliente);
                    break;
                case "16":
                    System.out.println(String.format("Volte sempre %s!", clienteLogado.getNome()));
                    clienteLogado = null;
                    break;
                case "17":
                    System.out.println("Aplicação encerrada!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private static Cliente retornaUsuario(String cpf){
        Optional<Cliente> resultado = clienteNegocio.consultar(cpf);
        Cliente cliente = resultado.get();
        return cliente;
    }

    private static void consultarUsuario(String cpf) {

        Cliente cliente = retornaUsuario(cpf);

        if (cliente.getCpf().equals("")) System.out.println(String.format("Cliente não encontrado!"));
        else
            System.out.println(String.format("Cliente encontrado: Nome - " + cliente.getNome() + "    Cpf: " + cliente.getCpf()));
    }

    private static void excluirUsuario(String cpf) {

        Cliente cliente = retornaUsuario(cpf);

        if (cliente.getCpf().equals("")) System.out.println(String.format("Cliente não encontrado!"));
        else
            clienteNegocio.excluir(cpf);
    }


    private static void identificarUsuario(String cpf) {

        Cliente cliente = retornaUsuario(cpf);

        if (cliente.getCpf().equals("")) {

            System.out.println("Usuário não cadastrado.");
            System.out.println("Deseja Cadastrar um novo usuário? ");
            System.out.println("##########################");
            System.out.println("Selecione uma opção:");
            System.out.println("1 -  Sim");
            System.out.println("2 -  Não");

            String opt = LeitoraDados.lerDado();
            System.out.println(opt);

            switch (opt) {
                case "1":
                    Cliente novoCliente = LeitoraDados.lerCliente();
                    clienteNegocio.salvar(novoCliente);
                    clienteLogado = novoCliente;
                    break;
                case "2":
                    System.exit(0);
                    break;
            }
        } else {
            System.out.println(String.format("Olá %s! Você está logado.", cliente.getNome()));
            clienteLogado = cliente;
            clienteNegocio.registrarUsuário(cliente);

        }
    }
}
