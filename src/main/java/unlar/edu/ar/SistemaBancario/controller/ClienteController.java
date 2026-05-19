package unlar.edu.ar.SistemaBancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unlar.edu.ar.SistemaBancario.model.Cliente;
import unlar.edu.ar.SistemaBancario.service.ClienteService;
import unlar.edu.ar.SistemaBancario.service.BankStatisticsService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private BankStatisticsService statsService;

    // Listar todos los clientes ordenados por saldo (Punto 4.3)
    @GetMapping("/ordenados")
    public List<Cliente> listarOrdenados() {
        return clienteService.obtenerClientesOrdenadosPorSaldo();
    }

    // Buscar por Nombre (Búsqueda Lineal - Punto 4.3)
    @GetMapping("/buscar/nombre")
    public ResponseEntity<Cliente> buscarNombre(@RequestParam String nombre) {
        Cliente c = clienteService.buscarPorNombre(nombre);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    // Buscar por DNI (Búsqueda Binaria - Punto 4.3)
    @GetMapping("/buscar/dni")
    public ResponseEntity<Cliente> buscarDni(@RequestParam String dni) {
        Cliente c = clienteService.buscarPorDniBinario(dni);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    // Clientes Premium (Streams - Punto 4.4)
    @GetMapping("/premium")
    public List<Cliente> listarPremium(@RequestParam double monto) {
        return statsService.obtenerClientesPremium(monto);
    }
}