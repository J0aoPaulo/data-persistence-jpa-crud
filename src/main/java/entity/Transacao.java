package entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "Transacao")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTran;

    @ManyToOne
    private Conta conta;

    @NonNull
    @Column(name = "data_transacao", nullable = false)
    Date dataTransacao;

    private double valorTransacao;
}