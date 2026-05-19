package unlar.edu.ar.SistemaBancario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unlar.edu.ar.SistemaBancario.model.Cliente;
import unlar.edu.ar.SistemaBancario.model.Cuenta;
import unlar.edu.ar.SistemaBancario.model.Movimiento;
import unlar.edu.ar.SistemaBancario.repository.BancoRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BankStatisticsService {

    @Autowired
    private BancoRepository repository;

    // 1. Filtrar Clientes con saldo total mayor a un monto (filter + mapToDouble)
    public List<Cliente> obtenerClientesPremium(double montoMinimo) {
        return repository.getAllClientes().stream()
                .filter(c -> c.getCuentas().stream()
                        .mapToDouble(Cuenta::getSaldo).sum() >= montoMinimo)
                .collect(Collectors.toList());
    }

    // 2. Obtener todos los CBUs del banco (map)
    public List<String> listarTodosLosCbus() {
        return repository.getAllClientes().stream()
                .flatMap(c -> c.getCuentas().stream())
                .map(Cuenta::getCbu)
                .collect(Collectors.toList());
    }

    // 3. Agrupar clientes por sucursal (groupingBy)
    public Map<String, List<Cliente>> clientesPorSucursal() {
        return repository.getBanco().getSucursales().stream()
                .collect(Collectors.toMap(
                    s -> s.getNombre(),
                    s -> s.getClientes()
                ));
    }

    // 4. Calcular el total de dinero depositado en todo el banco (reduce)
    public double calcularPatrimonioTotal() {
        return repository.getAllClientes().stream()
                .flatMap(c -> c.getCuentas().stream())
                .mapToDouble(Cuenta::getSaldo)
                .sum();
    }

    // 5. Obtener los últimos 5 movimientos de una cuenta específica
    public List<Movimiento> obtenerUltimosMovimientos(String cbu) {
        return repository.getAllClientes().stream()
                .flatMap(c -> c.getCuentas().stream())
                .filter(cta -> cta.getCbu().equals(cbu))
                .flatMap(cta -> cta.getMovimientos().stream())
                .sorted((m1, m2) -> m2.getFecha().compareTo(m1.getFecha())) // Más recientes primero
                .limit(5)
                .collect(Collectors.toList());
    }
}