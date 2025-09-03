import java.util.Scanner;

public class empresaTransporte {
    
    interface Vehiculo {
        void arrancar();
        void detener();
    }

    abstract class Transporte implements Vehiculo {
        protected String marca;
        protected String modelo;
        protected double velocidadMaxima;

        public Transporte(String marca, String modelo, double velocidadMaxima) {
            this.marca = marca;
            this.modelo = modelo;
            this.velocidadMaxima = velocidadMaxima;
        }

        public void mostrarInfo() {
            System.out.println("Marca: " + marca);
            System.out.println("Modelo: " + modelo);
            System.out.println("Velocidad maxima: " + velocidadMaxima + " km/h");
        }

        abstract void tipoCombustible();
    }

    class Carro extends Transporte {
        public Carro(String marca, String modelo, double velocidadMaxima) {
            super(marca, modelo, velocidadMaxima);
        }

        @Override
        public void arrancar() {
            System.out.println("El carro esta arrancando");
        }

        @Override
        public void detener() {
            System.out.println("El carro esta detenido");
        }

        @Override
        void tipoCombustible() {
            System.out.println("El carro usa gasolina");
        }
    }

    class Moto extends Transporte {
        public Moto(String marca, String modelo, double velocidadMaxima) {
            super(marca, modelo, velocidadMaxima);
        }

        @Override
        public void arrancar() {
            System.out.println("La moto esta arrancando");
        }

        @Override
        public void detener() {
            System.out.println("La moto esta detenida");
        }

        @Override
        void tipoCombustible() {
            System.out.println("La moto usa gasolina");
        }
    }

    class Bicicleta extends Transporte {
        public Bicicleta(String marca, String modelo, double velocidadMaxima) {
            super(marca, modelo, velocidadMaxima);
        }

        @Override
        public void arrancar() {
            System.out.println("La bicicleta esta arrancando");
        }

        @Override
        public void detener() {
            System.out.println("La bicicleta esta detnida");
        }

        @Override
        void tipoCombustible() {
            System.out.println("La bicicleta no usa combustible");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 

        System.out.println("Ingrese el tipo de vehiculo: carro, moto o bicicleta ");
        String tipoVehiculo = scanner.nextLine();

        System.out.println("Ingrese la marca: ");
        String marca = scanner.nextLine();

        System.out.println("Ingrese el modelo: ");
        String modelo = scanner.nextLine();

        System.out.println("Ingrese la velocidad maxima km/h: ");
        double velocidadMaxima = scanner.nextDouble();
        scanner.nextLine(); 

        Transporte vehiculo = null;
        
        if (tipoVehiculo.equals("carro")) {
            vehiculo = new empresaTransporte().new Carro(marca, modelo, velocidadMaxima);
        } else if (tipoVehiculo.equals("moto")) {
            vehiculo = new empresaTransporte().new Moto(marca, modelo, velocidadMaxima);
        } else if (tipoVehiculo.equals("bicicleta")) {
            vehiculo = new empresaTransporte().new Bicicleta(marca, modelo, velocidadMaxima);
        } else {
            System.out.println("vehiculo no reconocido");
            scanner.close();
            return;
        }

        System.out.println("Desea arrancar el vehiculo?: ");
        String respuesta = scanner.nextLine();
        if (respuesta.equals("si")) {
            vehiculo.arrancar();
        } else if (respuesta.equals("no")) {
            vehiculo.detener();
        } else {
            System.out.println("error");
        }

        System.out.println("Desea ver la informacion del vehiculo?");
        String info = scanner.nextLine();

        if (info.equals("si")) {
            vehiculo.mostrarInfo();
            vehiculo.tipoCombustible();
        } else if (info.equals("no")) {
            System.out.println("chao");
        } else {
            System.out.println("error");
        }

        scanner.close();
    }
}