import java.sql;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BibliotecaUI {
    private BibliotecaService bibliotecaService;
    private Scanner scanner;

    public BibliotecaUI() {
        // Inicializa o serviço da biblioteca com uma conexão JDBC válida
        Connection connection = ConnectionFactory.getConnection();
        this.bibliotecaService = new BibliotecaService(connection);
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcao = 0;
        do {
            System.out.println("\n===== Sistema de Gerenciamento de Biblioteca =====");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Gerenciar Livros");
            System.out.println("2. Gerenciar Usuários");
            System.out.println("3. Gerenciar Empréstimos");
            System.out.println("4. Gerenciar Reservas");
            System.out.println("5. Gerenciar Notificações");
            System.out.println("6. Gerenciar Avaliações");
            System.out.println("0. Sair");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    menuLivros();
                    break;
                case 2:
                    menuUsuarios();
                    break;
                case 3:
                    menuEmprestimos();
                    break;
                case 4:
                    menuReservas();
                    break;
                case 5:
                    menuNotificacoes();
                    break;
                case 6:
                    menuAvaliacoes();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void menuLivros() {
        int opcao = 0;
        do {
            System.out.println("\n===== Menu de Livros =====");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Atualizar Livro");
            System.out.println("3. Excluir Livro");
            System.out.println("4. Consultar Livro");
            System.out.println("5. Listar Todos os Livros");
            System.out.println("0. Voltar");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    adicionarLivro();
                    break;
                case 2:
                    atualizarLivro();
                    break;
                case 3:
                    excluirLivro();
                    break;
                case 4:
                    consultarLivro();
                    break;
                case 5:
                    listarLivros();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarLivro() {
        System.out.println("\n===== Adicionar Livro =====");
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Gênero do livro: ");
        String genero = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, genero, true); // Novos livros são inicialmente disponíveis

        try {
            bibliotecaService.adicionarLivro(livro);
            System.out.println("Livro adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }

    private void atualizarLivro() {
        System.out.println("\n===== Atualizar Livro =====");
        System.out.print("ID do livro a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner

        try {
            Livro livro = bibliotecaService.consultarLivro(id);
            if (livro != null) {
                System.out.print("Novo título (atual: " + livro.getTitulo() + "): ");
                String titulo = scanner.nextLine();
                System.out.print("Novo autor (atual: " + livro.getAutor() + "): ");
                String autor = scanner.nextLine();
                System.out.print("Novo gênero (atual: " + livro.getGenero() + "): ");
                String genero = scanner.nextLine();

                livro.setTitulo(titulo);
                livro.setAutor(autor);
                livro.setGenero(genero);

                bibliotecaService.atualizarLivro(livro);
                System.out.println("Livro atualizado com sucesso!");
            } else {
                System.out.println("Livro não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    private void excluirLivro() {
        System.out.println("\n===== Excluir Livro =====");
        System.out.print("ID do livro a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner

        try {
            bibliotecaService.excluirLivro(id);
            System.out.println("Livro excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir livro: " + e.getMessage());
        }
    }

    private void consultarLivro() {
        System.out.println("\n===== Consultar Livro =====");
        System.out.print("ID do livro a ser consultado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner

        try {
            Livro livro = bibliotecaService.consultarLivro(id);
            if (livro != null) {
                System.out.println("ID: " + livro.getId());
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Gênero: " + livro.getGenero());
                System.out.println("Disponível: " + (livro.isDisponivel() ? "Sim" : "Não"));
            } else {
                System.out.println("Livro não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar livro: " + e.getMessage());
        }
    }

    private void listarLivros() {
        System.out.println("\n===== Listar Todos os Livros =====");
        try {
            List<Livro> livros = bibliotecaService.listarLivros();
            for (Livro livro : livros) {
                System.out.println("ID: " + livro.getId() +
                                   ", Título: " + livro.getTitulo() +
                                   ", Autor: " + livro.getAutor() +
                                   ", Gênero: " + livro.getGenero() +
                                   ", Disponível: " + (livro.isDisponivel() ? "Sim" : "Não"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
    }

    private void menuUsuarios() {
        // Implemente métodos para gerenciar usuários (adicionar, atualizar, excluir, consultar, listar)
    }

    private void menuEmprestimos() {
        // Implemente métodos para gerenciar empréstimos (adicionar, atualizar, excluir, consultar, listar)
    }

    private void menuReservas() {
        // Implemente métodos para gerenciar reservas (adicionar, atualizar, excluir, consultar, listar)
    }

    private void menuNotificacoes() {
        // Implemente métodos para gerenciar notificações (adicionar, atualizar, excluir, consultar, listar)
    }

    private void menuAvaliacoes() {
        // Implemente métodos para gerenciar avaliações (adicionar, atualizar, excluir, consultar, listar)
    }

    public static void main(String[] args) {
        BibliotecaUI bibliotecaUI = new BibliotecaUI();
        bibliotecaUI.mostrarMenu();
    }
}
