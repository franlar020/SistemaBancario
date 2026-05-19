1- Swagger: Entra a http://localhost:8080/swagger-ui.html.

2- Búsqueda Binaria: Prueba buscando el DNI 38000000 (o cualquiera del DataInitializer).

3- Depósitos: Elige el CBU 00000001 y deposítale plata. Luego haz un GET para ver si el saldo subió.

4- Polimorfismo: Prueba extraer en una CajaDeAhorro (no te dejará pasar de 0) y en una CuentaCorriente (te dejará hasta -10000).

5- Deposito Concurrencia: para lanzar 100 depósitos al mismo tiempo anda al /api/cuentas/{cbu}/deposito-concurrente y pone el CBU 00000001 y dale a Execute.