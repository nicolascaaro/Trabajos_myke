import java.util.Scanner;

public class dragonball {
    public static void main(String[] args) {
        
        Personaje goku = new Guerrero("Goku", 70, "Ya dominó el ultra instinto");
        Personaje vegeta = new Guerrero("Vegeta", 60, "Nueva transformación: ultra ego");
        Personaje trunks = new Guerrero("Trunks", 50, "Tiene una espada con poder oculto");
        Personaje gohan = new Guerrero("Gohan", 55, "Se vuelve más fuerte cuando se enoja");

        System.out.println(goku.getNombre() + ": " + goku.actuar());
        System.out.println(vegeta.getNombre() + ": " + vegeta.actuar());
        System.out.println(trunks.getNombre() + ": " + trunks.actuar());
        System.out.println(gohan.getNombre() + ": " + gohan.actuar());

        Scanner sc = new Scanner(System.in);

        System.out.println("Escoge un personaje: Goku, Vegeta, Trunks, Gohan");
        String nombre = sc.nextLine();

        if(nombre.equalsIgnoreCase("Goku")){
            System.out.println("Escogiste a Goku. Su ataque es: " + goku.actuar() + " con " + goku.getAtaque() + " de daño.");
        }else if(nombre.equalsIgnoreCase("Vegeta")){
            System.out.println("Escogiste a Vegeta. Su ataque es: " + vegeta.actuar() + " con " + vegeta.getAtaque() + " de daño.");
        }else if(nombre.equalsIgnoreCase("Trunks")){
            System.out.println("Escogiste a Trunks. Su ataque es: " + trunks.actuar() + " con " + trunks.getAtaque() + " de daño.");
        }else if(nombre.equalsIgnoreCase("Gohan")){
            System.out.println("Escogiste a Gohan. Su ataque es: " + gohan.actuar() + " con " + gohan.getAtaque() + " de daño.");
        }else{
            System.out.println("Ese personaje no existe");
        }

        System.out.print("Quieres ver los secretos? Ingresa la clave: ");
        String clave = sc.nextLine();

        if(clave.equals("Nico123")){
            System.out.println("Secreto de " + goku.getNombre() + ": " + goku.consultarSecreto());
            System.out.println("Secreto de " + vegeta.getNombre() + ": " + vegeta.consultarSecreto());
            System.out.println("Secreto de " + trunks.getNombre() + ": " + trunks.consultarSecreto());
            System.out.println("Secreto de " + gohan.getNombre() + ": " + gohan.consultarSecreto());
        }else{
            System.out.println("Clave incorrecta");
        }

        sc.close();
    }
}

abstract class Personaje {
    private String nombre;
    private int ataque;
    private String secreto;

    public Personaje(String nombre, int ataque, String secreto){
        this.nombre = nombre;
        this.ataque = ataque;
        this.secreto = secreto;
    }

    public String getNombre(){
        return nombre;
    }

    public int getAtaque(){
        return ataque;
    }

    public String consultarSecreto(){
        return secreto;
    }

    public abstract String actuar();
}

class Guerrero extends Personaje {
    public Guerrero(String nombre, int ataque, String secreto){
        super(nombre, ataque, secreto);
    }

    @Override
    public String actuar(){
        if(getNombre().equalsIgnoreCase("Goku")){
            return "Kamehameha";
        }else if(getNombre().equalsIgnoreCase("Vegeta")){
            return "Resplandor Final";
        }else if(getNombre().equalsIgnoreCase("Trunks")){
            return "Espadazo";
        }else if(getNombre().equalsIgnoreCase("Gohan")){
            return "Masenko";
        }else{
            return "No hizo nada";
        }
    }
}
