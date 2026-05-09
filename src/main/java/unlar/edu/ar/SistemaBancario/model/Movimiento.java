package unlar.edu.ar.SistemaBancario.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {
    private String id;
    private String tipo; // "DEPOSITO" o "EXTRACCION"
    private double monto;
    private LocalDateTime fecha;
}