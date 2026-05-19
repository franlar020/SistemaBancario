package unlar.edu.ar.SistemaBancario.repository;

import org.springframework.stereotype.Repository;
import unlar.edu.ar.SistemaBancario.model.Banco;
import unlar.edu.ar.SistemaBancario.model.Cliente;
import unlar.edu.ar.SistemaBancario.model.Sucursal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BancoRepository {
    // El contenedor principal del sistema
    private Banco banco = new Banco("Banco UNLaR", new ArrayList<>());

    public Banco getBanco() {
        return banco;
    }

    // Método útil para obtener todos los clientes de todas las sucursales
    public List<Cliente> getAllClientes() {
        List<Cliente> todos = new ArrayList<>();
        for (Sucursal s : banco.getSucursales()) {
            todos.addAll(s.getClientes());
        }
        return todos;
    }
}