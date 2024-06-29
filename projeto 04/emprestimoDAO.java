import java.sql;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {
    private Connection connection;

    public EmprestimoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO Emprestimos (usuarioId, livroId, dataEmprestimo, dataDevolucao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getUsuarioId());
            stmt.setInt(2, emprestimo.getLivroId());
            stmt.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            stmt.setDate(4, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
            stmt.executeUpdate();
        }
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE Emprestimos SET usuarioId = ?, livroId = ?, dataEmprestimo = ?, dataDevolucao = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getUsuarioId());
            stmt.setInt(2, emprestimo.getLivroId());
            stmt.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            stmt.setDate(4, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
            stmt.setInt(5, emprestimo.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirEmprestimo(int id) throws SQLException {
        String sql = "DELETE FROM Emprestimos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Emprestimo consultarEmprestimo(int id) throws SQLException {
        String sql = "SELECT * FROM Emprestimos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Emprestimo(
                    rs.getInt("id"),
                    rs.getInt("usuarioId"),
                    rs.getInt("livroId"),
                    rs.getDate("dataEmprestimo"),
                    rs.getDate("dataDevolucao")
                );
            }
        }
        return null;
    }

    public List<Emprestimo> listarEmprestimos() throws SQLException {
        String sql = "SELECT * FROM Emprestimos";
        List<Emprestimo> emprestimos = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                emprestimos.add(new Emprestimo(
                    rs.getInt("id"),
                    rs.getInt("usuarioId"),
                    rs.getInt("livroId"),
                    rs.getDate("dataEmprestimo"),
                    rs.getDate("dataDevolucao")
                ));
            }
        }
        return emprestimos;
    }
}
