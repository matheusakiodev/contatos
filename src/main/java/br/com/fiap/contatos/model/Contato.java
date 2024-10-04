package br.com.fiap.contatos.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_contato")
public class Contato {

    @Id                                                                     // setando o id como o primeiro parâmetro da classe
    @GeneratedValue(                                                        // criação da estratégia
            strategy = GenerationType.SEQUENCE,
            generator = "TBL_CONTATOS_SEQ")
    @SequenceGenerator(                                                     // definindo a sequência
            name = "TBL_CONTATOS_SEQ",
            sequenceName = "TBL_CONTATOS_SEQ",
            allocationSize = 1)                                             // aloca 1 identificador (poderia ser 50 por ex, assim toda vez a aplicação usa um identificador que está alocado em memória cache, impedindo que seja necessário fazer requisições novas a cada vez que outra sequencia seja utilizada)
    private Long id;
    private String nome;
    private String email;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }

}
