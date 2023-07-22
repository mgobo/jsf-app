package br.mgobo.jsf.app.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pessoa")
@SequenceGenerator(name = "pessoa_seq", sequenceName = "pessoa_seq", allocationSize = 1, initialValue = 1)
public class Pessoa implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "pessoa_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "O nome deve ser preenchido")
    private String nome;
    private Date idade;
    private String sexo;

    @OneToOne(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Endereco endereco;

    public Pessoa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getIdade() {
        return idade;
    }

    public void setIdade(Date idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
