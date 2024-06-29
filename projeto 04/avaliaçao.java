import java.util.Date;

public class Avaliacao {
    private int id;
    private int usuarioId;
    private int livroId;
    private int avaliacao;
    private String comentario;
    private Date dataAvaliacao;

    public Avaliacao(int id, int usuarioId, int livroId, int avaliacao, String comentario, Date dataAvaliacao) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.livroId = livroId;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getLivroId() { return livroId; }
    public void setLivroId(int livroId) { this.livroId = livroId; }

    public int getAvaliacao() { return avaliacao; }
    public void setAvaliacao(int avaliacao) { this.avaliacao = avaliacao; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public Date getDataAvaliacao() { return dataAvaliacao; }
    public void setDataAvaliacao(Date dataAvaliacao) { this.dataAvaliacao = dataAvaliacao; }
}
