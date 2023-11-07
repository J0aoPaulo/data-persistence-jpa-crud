package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

    @NonNull Date dataTransacao;
    private double valorTransacao;
}