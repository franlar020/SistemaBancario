package unlar.edu.ar.SistemaBancario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {
    private String id;
    private String nombre; // Ej: "Sucursal Centro", "Sucursal La Rioja"
    private String direccion;
    private List<Cliente> clientes = new ArrayList<>(); // Agregación
}