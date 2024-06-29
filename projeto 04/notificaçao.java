import java.util.Date;

public class Notificacao {
    private int id;
    private int usuarioId;
    private String mensagem;
    private Date dataEnvio;

    public Notificacao(int id, int usuarioId, String mensagem, Date dataEnvio) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public Date getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(Date dataEnvio) { this.dataEnvio = dataEnvio; }
}
