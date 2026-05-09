package unlar.edu.ar.SistemaBancario.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
public abstract class Cuenta {
    private String cbu;
    private double saldo;
    private List<Movimiento> movimientos = new ArrayList<>(); // Composición

    public abstract void extraer(double monto);
    
    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            this.movimientos.add(new Movimiento(
                UUID.randomUUID().toString(), 
                "DEPOSITO", 
                monto, 
                LocalDateTime.now()
            ));
        }
    }
}