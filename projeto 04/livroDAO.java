import java.sql;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private Connection connection;

    public LivroDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO Livros (titulo, autor, genero, disponivel) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getGenero());
            stmt.setBoolean(4, livro.isDisponivel());
            stmt.executeUpdate();
        }
    }

    public void atualizarLivro(Livro livro) throws SQLException {
        String sql = "UPDATE Livros SET titulo = ?, autor = ?, genero = ?, disponivel = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getGenero());
            stmt.setBoolean(4, livro.isDisponivel());
            stmt.setInt(5, livro.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirLivro(int id) throws SQLException {
        String sql = "DELETE FROM Livros WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Livro consultarLivro(int id) throws SQLException {
        String sql = "SELECT * FROM Livros WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Livro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("genero"),
                    rs.getBoolean("disponivel")
                );
            }
        }
        return null;
    }

    public List<Livro> listarLivros() throws SQLException {
        String sql = "SELECT * FROM Livros";
        List<Livro> livros = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                livros.add(new Livro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("genero"),
                    rs.getBoolean("disponivel")
                ));
            }
        }
        return livros;
    }
}
