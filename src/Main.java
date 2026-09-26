import model.Prioridade;
import model.Usuario;
import service.ChamadoService;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String opcao = "";

        while(!opcao.equals("5")){
            System.out.print("=== SISTEMA DE CHAMADOS ===\n" +
                    "1 - Abrir chamado\n" +
                    "2 - Listar chamados\n" +
                    "3 - Buscar chamado\n" +
                    "4 - Alterar status\n" +
                    "5 - Sair\n" +
                    "Escolha: ");

            opcao = scanner.next();

            switch (opcao) {
                case "1":
                    System.out.print("Titulo do chamado: ");
                    String titulo = scanner.next();
                    System.out.println("");
                    System.out.print("Descrição do chamado: ");
                    String descricao = scanner.next();
                    System.out.println("");

                    Prioridade prioridade = null;
                    String escolhaPrioridade;
                    while(prioridade == null) {
                        System.out.print("Prioridade do chamado:\n" +
                                "1 - BAIXA\n" +
                                "2 - MEDIA\n" +
                                "3 - ALTA\n" +
                                "4 - URGENTE\n" +
                                "Escolha: ");
                        escolhaPrioridade = scanner.next();

                        prioridade = switch (escolhaPrioridade) {
                            case "1" -> Prioridade.BAIXA;
                            case "2" -> Prioridade.MEDIA;
                            case "3" -> Prioridade.ALTA;
                            case "4" -> Prioridade.URGENTE;
                            default -> null;
                        };
                        if (prioridade == null){
                            System.out.println("Opção inválida.\n");
                        }
                    }
                    System.out.println("");
                    System.out.println("Usuário solicitante do chamado: ");
                    break;
                case "2":
                    System.out.println("(listar chamados)");
                    break;
                case "3":
                    System.out.println("(buscar chamado)");
                    break;
                case "4":
                    System.out.println("(alterar status)");
                    break;
                case "5":
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println();
        }
    }
}