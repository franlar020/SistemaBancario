package unlar.edu.ar.SistemaBancario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banco {
    private String nombre; // Ej: "Banco de La Rioja"
    private List<Sucursal> sucursales = new ArrayList<>(); // Composición/Agregación
}