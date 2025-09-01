import java.util.Scanner;

public class sonidoAnimal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Elige un animal para escuchar su sonido:");
        System.out.println("Perro");
        System.out.println("Gato");
        System.out.print("Ingresa 1 o 2: ");
        
        int opcion = scanner.nextInt();
        
        Animal animal;
        
        if (opcion == 1) {
            animal = new Perro();
        } else if (opcion == 2) {
            animal = new Gato();
        } else {
            System.out.println("error");
            animal = new Perro();
        }
        
        animal.hacerSonido();
        
        scanner.close();
    }
}

abstract class Animal {
    public abstract void hacerSonido();
}

class Perro extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("Guau");
    }
}

class Gato extends Animal {
    @Override
    public void hacerSonido() {
        System.out.println("Miau");
    }
}

