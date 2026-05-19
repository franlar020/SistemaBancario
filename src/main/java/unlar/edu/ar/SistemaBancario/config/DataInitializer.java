package unlar.edu.ar.SistemaBancario.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import unlar.edu.ar.SistemaBancario.model.*;
import unlar.edu.ar.SistemaBancario.repository.BancoRepository;
import java.util.ArrayList;
import java.util.UUID;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(BancoRepository repository) {
        return args -> {
            Banco miBanco = repository.getBanco();

            // 1. Crear Sucursales
            Sucursal sucursalCentro = new Sucursal("S1", "Sucursal Centro", "Av. Perón 123", new ArrayList<>());
            Sucursal sucursalNorte = new Sucursal("S2", "Sucursal Norte", "Calle Las Heras 456", new ArrayList<>());

            // 2. Crear Clientes y Cuentas para Sucursal Centro
            Cliente c1 = new Cliente("C1", "Francisco Gonzalez", "38000000", new ArrayList<>());
            CajaDeAhorro ca1 = new CajaDeAhorro();
            ca1.setCbu("00000001");
            ca1.depositar(50000.0);
            c1.getCuentas().add(ca1);

            Cliente c2 = new Cliente("C2", "Gustavo Fuentes", "39000000", new ArrayList<>());
            CuentaCorriente cc1 = new CuentaCorriente();
            cc1.setCbu("00000002");
            cc1.depositar(10000.0);
            c2.getCuentas().add(cc1);

            sucursalCentro.getClientes().add(c1);
            sucursalCentro.getClientes().add(c2);

            // 3. Crear Clientes para Sucursal Norte
            Cliente c3 = new Cliente("C3", "Virginia Vera", "40000000", new ArrayList<>());
            CajaDeAhorro ca2 = new CajaDeAhorro();
            ca2.setCbu("00000003");
            ca2.depositar(75000.0);
            c3.getCuentas().add(ca2);

            sucursalNorte.getClientes().add(c3);

            // 4. Agregar sucursales al banco
            miBanco.getSucursales().add(sucursalCentro);
            miBanco.getSucursales().add(sucursalNorte);

            System.out.println("✓ Sistema Bancario inicializado: " + miBanco.getNombre());
            System.out.println("✓ Sucursales cargadas: " + miBanco.getSucursales().size());
        };
    }
}