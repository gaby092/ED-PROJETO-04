import java.sql;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDAO {
    private Connection connection;

    public NotificacaoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarNotificacao(Notificacao notificacao) throws SQLException {
        String sql = "INSERT INTO Notificacoes (usuarioId, mensagem, dataEnvio) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, notificacao.getUsuarioId());
            stmt.setString(2, notificacao.getMensagem());
            stmt.setDate(3, new java.sql.Date(notificacao.getDataEnvio().getTime()));
            stmt.executeUpdate();
        }
    }

    public void atualizarNotificacao(Notificacao notificacao) throws SQLException {
        String sql = "UPDATE Notificacoes SET usuarioId = ?, mensagem = ?, dataEnvio = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, notificacao.getUsuarioId());
            stmt.setString(2, notificacao.getMensagem());
            stmt.setDate(3, new java.sql.Date(notificacao.getDataEnvio().getTime()));
            stmt.setInt(4, notificacao.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirNotificacao(int id) throws SQLException {
        String sql = "DELETE FROM Notificacoes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Notificacao consultarNotificacao(int id) throws SQLException {
        String sql = "SELECT * FROM Notificacoes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Notificacao(
                    rs.getInt("id"),
                    rs.getInt("usuarioId"),
                    rs.getString("mensagem"),
                    rs.getDate("dataEnvio")
                );
            }
        }
        return null;
    }

    public List<Notificacao> listarNotificacoes() throws SQLException {
        String sql = "SELECT * FROM Notificacoes";
        List<Notificacao> notificacoes = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                notificacoes.add(new Notificacao(
                    rs.getInt("id"),
                    rs.getInt("usuarioId"),
                    rs.getString("mensagem"),
                    rs.getDate("dataEnvio")
                ));
            }
        }
        return notificacoes;
    }
}
