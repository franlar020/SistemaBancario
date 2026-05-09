package unlar.edu.ar.SistemaBancario.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true) // Para incluir los campos de la clase padre en equals y hashCode
public class CajaDeAhorro extends Cuenta { // Herencia

    @Override
    public void extraer(double monto) {
        if (monto > 0 && getSaldo() >= monto) {
            setSaldo(getSaldo() - monto);
            getMovimientos().add(new Movimiento(
                UUID.randomUUID().toString(), 
                "EXTRACCION", 
                monto, 
                LocalDateTime.now()
            ));
        } else {
        
            System.out.println("Saldo insuficiente en Caja de Ahorro.");
        }
    }
}