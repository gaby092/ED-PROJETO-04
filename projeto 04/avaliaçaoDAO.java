import java.sql;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO {
    private Connection connection;

    public AvaliacaoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) throws SQLException {
        String sql = "INSERT INTO Avaliacoes (usuarioId, livroId, avaliacao, comentario, dataAvaliacao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, avaliacao.getUsuarioId());
            stmt.setInt(2, avaliacao.getLivroId());
            stmt.setInt(3, avaliacao.getAvaliacao());
            stmt.setString(4, avaliacao.getComentario());
            stmt.setDate(5, new java.sql.Date(avaliacao.getDataAvaliacao().getTime()));
            stmt.executeUpdate();
        }
    }

    public void atualizarAvaliacao(Avaliacao avaliacao) throws SQLException {
        String sql = "UPDATE Avaliacoes SET usuarioId = ?, livroId = ?, avaliacao = ?, comentario = ?, dataAvaliacao = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, avaliacao.getUsuarioId());
            stmt.setInt(2, avaliacao.getLivroId());
            stmt.setInt(3, avaliacao.getAvaliacao());
            stmt.setString(4, avaliacao.getComentario());
            stmt.setDate(5, new java.sql.Date(avaliacao.getDataAvaliacao().getTime()));
            stmt.setInt(6, avaliacao.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirAvaliacao(int id) throws SQLException {
        String sql = "DELETE FROM Avaliacoes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Avaliacao consultarAvaliacao(int id) throws SQLException {
        String sql = "SELECT * FROM Avaliacoes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Avaliacao(
                    rs.getInt("id"),
                    rs.getInt("usuarioId"),
                    rs.getInt("livroId"),
                    rs.getInt("avaliacao"),
                    rs.getString("comentario"),
                    rs.getDate("dataAvaliacao")
                );
            }
        }
        return null;
    }

    public List<Avaliacao> listarAvaliacoes() throws SQLException {
        String sql = "SELECT * FROM Avaliacoes";
        List<Avaliacao> avaliacoes = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                avaliacoes.add(new Avaliacao(
                    rs.getInt("id"),
                    rs.getInt("usuarioId"),
                    rs.getInt("livroId"),
                    rs.getInt("avaliacao"),
                    rs.getString("comentario"),
                    rs.getDate("dataAvaliacao")
                ));
            }
        }
        return avaliacoes;
    }
}
