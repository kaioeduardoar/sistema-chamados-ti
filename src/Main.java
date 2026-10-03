import model.Chamado;
import model.Prioridade;
import model.Usuario;
import service.ChamadoService;
import service.UsuarioService;

import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioService usuarioService = new UsuarioService();
        ChamadoService chamadoService = new ChamadoService();
        Usuario usuarioLogado = null;

        String opcao = "";
        String email = "";
        boolean acesso = false;
        while (!acesso) {
            System.out.print(
                    "0 - Encerrar\n" +
                            "1 - Login\n" +
                            "2 - Signup\n" +
                            "Escolha: ");
            opcao = scanner.nextLine();
            if(opcao.equals("0")){
                break;
            }

            switch (opcao) {
                case "1": {
                    System.out.print("Insira seu nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Insira seu e-mail: ");
                    email = scanner.nextLine();
                    if (usuarioService.validarUsuarioPorEmail(email)) {
                        usuarioLogado = usuarioService.buscarPorEmail(email);
                        System.out.println("Sucesso.");
                        acesso = true;
                    } else {
                        System.out.println("Usuário não encontrado.\n");
                    }
                    System.out.println("Pressione ENTER para prosseguir.");
                    scanner.nextLine();
                    break;
                }
                case "2":
                    System.out.print("Insira seu nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Insira o seu e-mail: ");
                    email = scanner.nextLine();
                    if (usuarioService.buscarPorEmail(email) == null) {
                        usuarioLogado = usuarioService.cadastrarUsuario(nome, email);
                        acesso = true;
                        break;
                    } else {
                        System.out.println("O e-mail já está vinculado a um usuário");

                        System.out.println("Pressione ENTER para prosseguir.");
                        scanner.nextLine();
                    }

                default:
                    System.out.println("Opção inválida.");
                    System.out.println("Pressione ENTER para prosseguir.");
                    scanner.nextLine();
                    break;
            }
        }

        opcao = "";
        while (!opcao.equals("5") && acesso) {
            System.out.print("=== SISTEMA DE CHAMADOS ===\n" +
                    "1 - Abrir chamado\n" +
                    "2 - Listar chamados\n" +
                    "3 - Buscar chamado\n" +
                    "4 - Alterar status\n" +
                    "5 - Sair\n" +
                    "Escolha: ");

            opcao = scanner.nextLine();

            switch (opcao) {
                case "1": {
                    System.out.print("Titulo do chamado: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Descrição do chamado: ");
                    String descricao = scanner.nextLine();

                    Prioridade prioridade = null;
                    while (prioridade == null) {
                        System.out.print("Prioridade do chamado:\n" +
                                "1 - BAIXA\n" +
                                "2 - MEDIA\n" +
                                "3 - ALTA\n" +
                                "4 - URGENTE\n" +
                                "Escolha: ");
                        opcao = scanner.nextLine();

                        prioridade = switch (opcao) {
                            case "1" -> Prioridade.BAIXA;
                            case "2" -> Prioridade.MEDIA;
                            case "3" -> Prioridade.ALTA;
                            case "4" -> Prioridade.URGENTE;
                            default -> null;
                        };
                        if (prioridade == null) {
                            System.out.println("Opção inválida.\n");

                            System.out.println("Pressione ENTER para prosseguir.");
                            scanner.nextLine();
                        }
                    }
                    Chamado chamado = chamadoService.abrirChamado(titulo, descricao, prioridade, usuarioLogado);
                    System.out.println("Seu chamado foi aberto. ID: " + chamado.getId());

                    System.out.println("Pressione ENTER para prosseguir.");
                    scanner.nextLine();
                    break;
                }
                case "2": {
                    System.out.println("(listar chamados)");
                    break;
                }
                case "3": {
                    opcao = "";
                    while (!opcao.equals("1") && !opcao.equals("2")) {
                        System.out.print("1 - Buscar chamado por ID.\n" +
                                "2 - Buscar chamado por Nome do usuário.\n" +
                                "Escolha: ");
                        opcao = scanner.nextLine();

                        switch (opcao) {
                            case "1": {
                                System.out.print("ID do Chamdo: ");
                                int id = scanner.nextInt();
                                Chamado chamadoEncontrado = chamadoService.buscarPorId(id);
                                if (chamadoEncontrado != null){
                                    System.out.println(chamadoEncontrado);
                                }else{
                                    System.out.println("Chamdo não encontrado.");
                                }
                                System.out.println("Pressione ENTER para prosseguir.");
                                scanner.nextLine();
                                break;
                            }
                            case "2": {
                                System.out.print("Nome do solicitante do Chamdo: ");
                                String nome = scanner.nextLine();
                                Chamado chamadoEncontrado = chamadoService.buscarPorUsuario(nome);
                                if (chamadoEncontrado != null){
                                    System.out.println(chamadoEncontrado);
                                }else{
                                    System.out.println("Chamado não encontrado.");
                                }

                                System.out.println("Pressione ENTER para prosseguir.");
                                scanner.nextLine();
                                break;
                            }
                            default: {
                                System.out.println("Opção inválida.");

                                System.out.println("Pressione ENTER para prosseguir.");
                                scanner.nextLine();
                                break;
                            }
                        }
                    }
                    break;
                }
                case "4": {
                    System.out.println("(alterar status)");
                    break;
                }
                case "5": {
                    System.out.println("Saindo...");
                    break;
                }
                default: {
                    System.out.println("Opção inválida!");
                }
            }
            System.out.println();
        }
    }
}