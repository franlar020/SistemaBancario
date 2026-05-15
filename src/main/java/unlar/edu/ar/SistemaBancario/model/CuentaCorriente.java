package unlar.edu.ar.SistemaBancario.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class CuentaCorriente extends Cuenta {
    private double limiteDescubierto = 10000.0; // Límite de saldo negativo

    @Override
    public void extraer(double monto) {
        if (monto > 0 && (getSaldo() + limiteDescubierto) >= monto) {
            setSaldo(getSaldo() - monto);
            getMovimientos().add(new Movimiento(
                UUID.randomUUID().toString(), 
                "EXTRACCION (DESCUBIERTO)", 
                monto, 
                LocalDateTime.now()
            ));
        } else {
            System.out.println("Límite de descubierto excedido.");
        }
    }
}