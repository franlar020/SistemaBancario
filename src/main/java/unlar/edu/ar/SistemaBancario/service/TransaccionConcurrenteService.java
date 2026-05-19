package unlar.edu.ar.SistemaBancario.service;

import org.springframework.stereotype.Service;
import unlar.edu.ar.SistemaBancario.model.Cuenta;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class TransaccionConcurrenteService {

    // Simula 100 depósitos de $100 cada uno realizados en milisegundos
    public void procesarDepositosMasivos(Cuenta cuenta) {
        // Creamos un pool de 10 hilos para procesar las 100 tareas
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                // Sincronizamos el bloque de código para evitar "Condición de Carrera"
                synchronized (cuenta) {
                    cuenta.depositar(100.0);
                }
            });
        }

        executor.shutdown();
        try {
            // Esperamos a que todos los hilos terminen
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}