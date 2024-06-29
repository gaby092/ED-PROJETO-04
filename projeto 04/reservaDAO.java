import java.sql;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarReserva(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO Reservas (usuarioId, livroId, dataReserva) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getUsuarioId());
            stmt.setInt(2, reserva.getLivroId());
            stmt.setDate(3, new java.sql.Date(reserva.getDataReserva().getTime()));
            stmt.executeUpdate();
        }
    }

    public void atualizarReserva(Reserva reserva) throws SQLException {
        String sql = "UPDATE Reservas SET usuarioId = ?, livroId = ?, dataReserva = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getUsuarioId());
            stmt.setInt(2, reserva.getLivroId());
            stmt.setDate(3, new java.sql.Date(reserva.getDataReserva().getTime()));
            stmt.setInt(4, reserva.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirReserva(int id) throws SQLException {
        String sql = "DELETE FROM Reservas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Reserva consultarReserva(int id) throws SQLException {
        String sql = "SELECT * FROM Reservas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reserva(
                    rs.getInt("id"),
                    rs.getInt("usuarioId"),
                    rs.getInt("livroId"),
                    rs.getDate("dataReserva")
                );
            }
        }
        return null;
    }

    public List<Reserva> listarReservas() throws SQLException {
        String sql = "SELECT * FROM Reservas";
        List<Reserva> reservas = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reservas.add(new Reserva(
                    rs.getInt("id"),
                    rs.getInt("usuarioId"),
                    rs.getInt("livroId"),
                    rs.getDate("dataReserva")
                ));
            }
        }
        return reservas;
    }
}
