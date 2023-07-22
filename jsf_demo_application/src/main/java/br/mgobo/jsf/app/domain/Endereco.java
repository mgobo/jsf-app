package br.mgobo.jsf.app.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "endereco")
@SequenceGenerator(name = "endereco_seq", sequenceName = "endereco_seq", allocationSize = 1, initialValue = 1)
public class Endereco implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "endereco_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 2)
    @NotBlank(message = "O estado deve ser preenchido")
    private String estado;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "A cidade deve ser preenchido")
    private String cidade;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "O endereço deve ser preenchido")
    private String logradouro;

    @Column(nullable = false)
    @NotNull(message = "O número deve ser preenchido")
    private Integer nro;
    private String cep;

    @JoinColumn(name = "ID_PESSOA")
    @OneToOne
    private Pessoa pessoa;

    public Endereco() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNro() {
        return nro;
    }

    public void setNro(Integer nro) {
        this.nro = nro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
