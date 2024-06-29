import java.sql;
import java.util.List;

public class BibliotecaService {
    private LivroDAO livroDAO;
    private UsuarioDAO usuarioDAO;
    private EmprestimoDAO emprestimoDAO;
    private ReservaDAO reservaDAO;
    private NotificacaoDAO notificacaoDAO;
    private AvaliacaoDAO avaliacaoDAO;

    public BibliotecaService(Connection connection) {
        this.livroDAO = new LivroDAO(connection);
        this.usuarioDAO = new UsuarioDAO(connection);
        this.emprestimoDAO = new EmprestimoDAO(connection);
        this.reservaDAO = new ReservaDAO(connection);
        this.notificacaoDAO = new NotificacaoDAO(connection);
        this.avaliacaoDAO = new AvaliacaoDAO(connection);
    }

    // Métodos de gerenciamento de livros
    public void adicionarLivro(Livro livro) throws SQLException {
        livroDAO.adicionarLivro(livro);
    }

    public void atualizarLivro(Livro livro) throws SQLException {
        livroDAO.atualizarLivro(livro);
    }

    public void excluirLivro(int id) throws SQLException {
        livroDAO.excluirLivro(id);
    }

    public Livro consultarLivro(int id) throws SQLException {
        return livroDAO.consultarLivro(id);
    }

    public List<Livro> listarLivros() throws SQLException {
        return livroDAO.listarLivros();
    }

    // Métodos de gerenciamento de usuários
    public void adicionarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.adicionarUsuario(usuario);
    }

    public void atualizarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.atualizarUsuario(usuario);
    }

    public void excluirUsuario(int id) throws SQLException {
        usuarioDAO.excluirUsuario(id);
    }

    public Usuario consultarUsuario(int id) throws SQLException {
        return usuarioDAO.consultarUsuario(id);
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        return usuarioDAO.listarUsuarios();
    }

    // Métodos de gerenciamento de empréstimos
    public void adicionarEmprestimo(Emprestimo emprestimo) throws SQLException {
        livroDAO.consultarLivro(emprestimo.getLivroId()).setDisponivel(false);
        emprestimoDAO.adicionarEmprestimo(emprestimo);
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) throws SQLException {
        emprestimoDAO.atualizarEmprestimo(emprestimo);
    }

    public void excluirEmprestimo(int id) throws SQLException {
        Emprestimo emprestimo = emprestimoDAO.consultarEmprestimo(id);
        livroDAO.consultarLivro(emprestimo.getLivroId()).setDisponivel(true);
        emprestimoDAO.excluirEmprestimo(id);
    }

    public Emprestimo consultarEmprestimo(int id) throws SQLException

    public List<Emprestimo> listarEmprestimos() throws SQLException {
        return emprestimoDAO.listarEmprestimos();
    }

    // Métodos de gerenciamento de reservas
    public void adicionarReserva(Reserva reserva) throws SQLException {
        reservaDAO.adicionarReserva(reserva);
    }

    public void atualizarReserva(Reserva reserva) throws SQLException {
        reservaDAO.atualizarReserva(reserva);
    }

    public void excluirReserva(int id) throws SQLException {
        reservaDAO.excluirReserva(id);
    }

    public Reserva consultarReserva(int id) throws SQLException {
        return reservaDAO.consultarReserva(id);
    }

    public List<Reserva> listarReservas() throws SQLException {
        return reservaDAO.listarReservas();
    }

    // Métodos de gerenciamento de notificações
    public void adicionarNotificacao(Notificacao notificacao) throws SQLException {
        notificacaoDAO.adicionarNotificacao(notificacao);
    }

    public void atualizarNotificacao(Notificacao notificacao) throws SQLException {
        notificacaoDAO.atualizarNotificacao(notificacao);
    }

    public void excluirNotificacao(int id) throws SQLException {
        notificacaoDAO.excluirNotificacao(id);
    }

    public Notificacao consultarNotificacao(int id) throws SQLException {
        return notificacaoDAO.consultarNotificacao(id);
    }

    public List<Notificacao> listarNotificacoes() throws SQLException {
        return notificacaoDAO.listarNotificacoes();
    }

    // Métodos de gerenciamento de avaliações
    public void adicionarAvaliacao(Avaliacao avaliacao) throws SQLException {
        avaliacaoDAO.adicionarAvaliacao(avaliacao);
    }

    public void atualizarAvaliacao(Avaliacao avaliacao) throws SQLException {
        avaliacaoDAO.atualizarAvaliacao(avaliacao);
    }

    public void excluirAvaliacao(int id) throws SQLException {
        avaliacaoDAO.excluirAvaliacao(id);
    }

    public Avaliacao consultarAvaliacao(int id) throws SQLException {
        return avaliacaoDAO.consultarAvaliacao(id);
    }

    public List<Avaliacao> listarAvaliacoes() throws SQLException {
        return avaliacaoDAO.listarAvaliacoes();
    }
}
