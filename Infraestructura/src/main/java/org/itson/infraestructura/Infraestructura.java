/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.infraestructura;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juanl
 */
public class Infraestructura {

    public static void main(String[] args) {
        Map<String, Double> tarjetas = new HashMap<>();
        tarjetas.put("12345", 5000.00);      
        tarjetas.put("41523134", 150.00);    
        tarjetas.put("987654321", 10000.00);

        Map<String, Double> referencias = new HashMap<>();
        referencias.put("REF123", 800.00);
        referencias.put("OXXO999", 3000.00);
        referencias.put("PAY777", 50.00);

        try (ServerSocket serverSocket = new ServerSocket(9001)) {
            System.out.println("=== BANCO CON SALDOS INICIADO ===");
            System.out.println("Esperando cobros en el puerto 9001...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     DataInputStream entrada = new DataInputStream(socket.getInputStream());
                     DataOutputStream salida = new DataOutputStream(socket.getOutputStream())) {

                    String cuenta = entrada.readUTF();
                    double montoCobro = entrada.readDouble(); 
                    
                    System.out.println("-> Intento de cobro. Cuenta: " + cuenta + " | Monto: $" + montoCobro);

                    boolean pagoAprobado = false;
                    String motivo = "Cuenta no existe";

                    if (tarjetas.containsKey(cuenta)) {
                        double saldo = tarjetas.get(cuenta);
                        if (saldo >= montoCobro) {
                            pagoAprobado = true;
                            tarjetas.put(cuenta, saldo - montoCobro); 
                            motivo = "Cobro exitoso a Tarjeta. Nuevo saldo: $" + (saldo - montoCobro);
                        } else {
                            motivo = "Fondos insuficientes (Saldo: $" + saldo + ")";
                        }
                    } 
                    else if (referencias.containsKey(cuenta)) {
                        double saldo = referencias.get(cuenta);
                        if (saldo >= montoCobro) {
                            pagoAprobado = true;
                            referencias.put(cuenta, saldo - montoCobro); 
                            motivo = "Cobro exitoso a Referencia. Nuevo saldo: $" + (saldo - montoCobro);
                        } else {
                            motivo = "Fondos insuficientes (Saldo: $" + saldo + ")";
                        }
                    }

                    salida.writeBoolean(pagoAprobado);
                    
                    

                } catch (IOException e) {
                    System.out.println("Error de red: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error crítico arrancando el servidor.");
        }
    }
}
