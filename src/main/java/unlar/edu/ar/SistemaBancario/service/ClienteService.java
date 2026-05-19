package unlar.edu.ar.SistemaBancario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unlar.edu.ar.SistemaBancario.model.Cliente;
import unlar.edu.ar.SistemaBancario.model.Cuenta;
import unlar.edu.ar.SistemaBancario.repository.BancoRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private BancoRepository repository;

    // 1. Búsqueda Lineal por Nombre
    public Cliente buscarPorNombre(String nombre) {
        List<Cliente> clientes = repository.getAllClientes();
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombre)) {
                return cliente;
            }
        }
        return null; // O lanzar una excepción
    }

    // 2. Búsqueda Binaria por DNI (Requiere lista ordenada)
    public Cliente buscarPorDniBinario(String dni) {
        List<Cliente> clientes = repository.getAllClientes();
        
        // Primero ordenamos por DNI para que la búsqueda binaria funcione
        clientes.sort(Comparator.comparing(Cliente::getDni));
        
        int inicio = 0;
        int fin = clientes.size() - 1;
        
        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            int comparacion = clientes.get(medio).getDni().compareTo(dni);
            
            if (comparacion == 0) return clientes.get(medio);
            if (comparacion < 0) inicio = medio + 1;
            else fin = medio - 1;
        }
        return null;
    }

    // 3. Ordenamiento por Saldo Total (de mayor a menor)
    public List<Cliente> obtenerClientesOrdenadosPorSaldo() {
        List<Cliente> clientes = repository.getAllClientes();
        
        clientes.sort((c1, c2) -> {
            double saldoC1 = c1.getCuentas().stream().mapToDouble(Cuenta::getSaldo).sum();
            double saldoC2 = c2.getCuentas().stream().mapToDouble(Cuenta::getSaldo).sum();
            return Double.compare(saldoC2, saldoC1); // Orden descendente
        });
        
        return clientes;
    }
}