package unlar.edu.ar.SistemaBancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unlar.edu.ar.SistemaBancario.model.Cuenta;
import unlar.edu.ar.SistemaBancario.repository.BancoRepository;
import unlar.edu.ar.SistemaBancario.service.TransaccionConcurrenteService;

import java.util.Optional;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private BancoRepository repository;

    // Endpoint para depositar (Modifica el estado del objeto)
    @PostMapping("/{cbu}/depositar")
    public ResponseEntity<String> depositar(@PathVariable String cbu, @RequestParam double monto) {
        Optional<Cuenta> cuentaOpt = repository.getAllClientes().stream()
                .flatMap(c -> c.getCuentas().stream())
                .filter(cta -> cta.getCbu().equals(cbu))
                .findFirst();

        if (cuentaOpt.isPresent()) {
            cuentaOpt.get().depositar(monto);
            return ResponseEntity.ok("Depósito exitoso. Nuevo saldo: " + cuentaOpt.get().getSaldo());
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint para extraer (Punto 4.1 - Polimorfismo)
    @PostMapping("/{cbu}/extraer")
    public ResponseEntity<String> extraer(@PathVariable String cbu, @RequestParam double monto) {
        Optional<Cuenta> cuentaOpt = repository.getAllClientes().stream()
                .flatMap(c -> c.getCuentas().stream())
                .filter(cta -> cta.getCbu().equals(cbu))
                .findFirst();

        if (cuentaOpt.isPresent()) {
            double saldoAntes = cuentaOpt.get().getSaldo();
            cuentaOpt.get().extraer(monto);
            
            if (saldoAntes == cuentaOpt.get().getSaldo()) {
                return ResponseEntity.badRequest().body("Operación fallida (Saldo insuficiente o límite excedido)");
            }
            return ResponseEntity.ok("Extracción exitosa. Nuevo saldo: " + cuentaOpt.get().getSaldo());
        }
        return ResponseEntity.notFound().build();
    }

    @Autowired
private TransaccionConcurrenteService concurrenteService;

// PUNTO 4.6: Simulación de depósitos masivos
@PostMapping("/{cbu}/deposito-concurrente")
public ResponseEntity<String> depositoMasivo(@PathVariable String cbu) {
    Optional<Cuenta> cuentaOpt = repository.getAllClientes().stream()
            .flatMap(c -> c.getCuentas().stream())
            .filter(cta -> cta.getCbu().equals(cbu))
            .findFirst();

    if (cuentaOpt.isPresent()) {
        double saldoInicial = cuentaOpt.get().getSaldo();
        concurrenteService.procesarDepositosMasivos(cuentaOpt.get());
        double saldoFinal = cuentaOpt.get().getSaldo();
        
        return ResponseEntity.ok("Simulación terminada.\n" +
                "Saldo Inicial: " + saldoInicial + "\n" +
                "Depósitos realizados: 100 de $100 c/u ($10.000 total)\n" +
                "Saldo Final: " + saldoFinal);
    }
    return ResponseEntity.notFound().build();
}
}