import java.util.Scanner;

public class CuentaBancaria {
    
    String titular;
    int numeroCuenta;
    double saldo; 

    
    public CuentaBancaria(String titular, int numeroCuenta, double saldo) {
        this.titular = titular;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

   
    public void mostrarDatos() {
        System.out.println("Titular: " + titular);
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Saldo: $" + saldo);
    }

    
    public void depositar(double cantidad) {
        if (cantidad <= 0) {
            System.out.println("Error: No se puede depositar una cantidad negativa o cero.");
        } else {
            saldo += cantidad;
            System.out.println("Depósito exitoso. Nuevo saldo: $" + saldo);
        }
    }

   
    public void retirar(double cantidad) {
        if (cantidad <= 0) {
            System.out.println("Error: No se puede retirar una cantidad negativa o cero.");
        } else if (cantidad > saldo) {
            System.out.println("Error: Saldo insuficiente.");
        } else {
            saldo -= cantidad;
            System.out.println("Retiro exitoso. Nuevo saldo: $" + saldo);
        }
    }

    
    public void transferir(CuentaBancaria cuentaDestino, double cantidad) {
        if (cantidad <= 0) {
            System.out.println("Error: No se puede transferir una cantidad negativa o cero.");
        } else if (cantidad > saldo) {
            System.out.println("Error: Saldo insuficiente para la transferencia.");
        } else {
            saldo -= cantidad;
            cuentaDestino.saldo += cantidad; 
            System.out.println("Transferencia exitosa a la cuenta " + cuentaDestino.numeroCuenta);
            System.out.println("Nuevo saldo: $" + saldo);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Ingrese el nombre del titular: ");
        String titular = scanner.nextLine();
        System.out.print("Ingrese el número de cuenta: ");
        int numeroCuenta = scanner.nextInt();
        System.out.print("Ingrese el saldo inicial: ");
        double saldoInicial = scanner.nextDouble();
        scanner.nextLine(); 

       
        CuentaBancaria cuenta = new CuentaBancaria(titular, numeroCuenta, saldoInicial);

        
        while (true) {
            System.out.println("\nOpciones: Depositar, Retirar, Transferir, Mostrar, Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            if (opcion.equals("depositar")) {
                System.out.print("Ingrese la cantidad a depositar: ");
                double cantidad = scanner.nextDouble();
                cuenta.depositar(cantidad);
            } else if (opcion.equals("retirar")) {
                System.out.print("Ingrese la cantidad a retirar: ");
                double cantidad = scanner.nextDouble();
                cuenta.retirar(cantidad);
            } else if (opcion.equals("transferir")) {
                System.out.print("Ingrese el número de cuenta destino: ");
                int numCuentaDestino = scanner.nextInt();
                System.out.print("Ingrese la cantidad a transferir: ");
                double cantidad = scanner.nextDouble();
                CuentaBancaria cuentaDestino = new CuentaBancaria("Destino", numCuentaDestino, 0);
                cuenta.transferir(cuentaDestino, cantidad);
            } else if (opcion.equals("mostrar")) {
                cuenta.mostrarDatos();
            } else if (opcion.equals("salir")) {
                System.out.println("¡Hasta luego!");
                break;
            } else {
                System.out.println("Opción inválida.");
            }
            scanner.nextLine();
        }

        scanner.close(); 
    }
}
