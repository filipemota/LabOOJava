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
            System.out.println("9 -  Consultar Livro");
            System.out.println("10 - Consultar Caderno");
            System.out.println("11 - Consultar Pedido");
            System.out.println("12 - Deslogar");
            System.out.println("13 - Sair");
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
                    System.out.println(String.format("Volte sempre %s!", clienteLogado.getNome()));
                    clienteLogado = null;
                    break;
                case "13":
                    System.out.println("Aplicação encerrada!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private static void identificarUsuario(String cpf) {

        Optional<Cliente> resultado = clienteNegocio.consultar(cpf);

        if (resultado.isEmpty()) {

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
                    Cliente cliente = LeitoraDados.lerCliente();
                    clienteNegocio.salvar(cliente);
                    clienteLogado = cliente;
                    break;
                case "2":
                    System.exit(0);
                    break;

            }
        } else {
            Cliente cliente = resultado.get();
            System.out.println(String.format("Olá %s! Você está logado.", cliente.getNome()));
            clienteLogado = cliente;
        }
    }
}
