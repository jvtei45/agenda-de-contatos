package agendadecontato2;

import java.util.UUID;

public class Contato {
    private String codigo;
    private String nome;
    private String telefone;
    private String fax;
    private String celular;
    private String observacao;
    private String tipoContato;
    private boolean isFavorite;
    private UUID uuid;

    public Contato(String nome, String telefone, String fax, String celular, String observacao, String tipoContato, boolean isFavorite) {
        this.codigo = UUID.randomUUID().toString(); // Gere um UUID aleatório para o código
        this.nome = nome;
        this.telefone = telefone;
        this.fax = fax;
        this.celular = celular;
        this.observacao = observacao;
        this.tipoContato = tipoContato;
        this.isFavorite = isFavorite;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
