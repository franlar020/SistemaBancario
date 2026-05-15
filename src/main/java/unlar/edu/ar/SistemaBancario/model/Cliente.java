package unlar.edu.ar.SistemaBancario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private String id;
    private String nombre;
    private String dni;
    private List<Cuenta> cuentas = new ArrayList<>(); // Asociación
}