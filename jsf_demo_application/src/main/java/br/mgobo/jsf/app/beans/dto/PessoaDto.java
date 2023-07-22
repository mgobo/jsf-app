package br.mgobo.jsf.app.beans.dto;

public class PessoaDto {

    private long id;
    private String nome;
    private String idade;
    private String sexo;
    private EnderecoDto enderecoDto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public EnderecoDto getEnderecoDto() {
        return enderecoDto;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public void setEnderecoDto(EnderecoDto enderecoDto) {
        this.enderecoDto = enderecoDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
