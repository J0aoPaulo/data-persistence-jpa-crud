package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Transacao")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransacao;

    @Column
    private double valorTransacao;
    @Column
    private String categoriaTransacao;
    @Column
    @NonNull private Date dataTransacao;

    @ManyToOne
    @JoinColumn(name = "idConta")
    private Conta conta;
}