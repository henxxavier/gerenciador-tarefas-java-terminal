public class Tarefa {
    private String titulo;
    private String descricao;
    private EstadoTarefa estado;

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public EstadoTarefa getEstado() {
        return estado;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setEstado(EstadoTarefa estado) {
        this.estado = estado;
    }

    public Tarefa(String titulo, String descricao, EstadoTarefa estado) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.estado = estado;
    }

}
