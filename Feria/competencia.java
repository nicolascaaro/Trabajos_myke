class inventor {
    protected String nombre;
    protected int edad;
    private int creatividad;

    public inventor(String nombre, int edad, int creatividad) {
        this.nombre = nombre;
        this.edad = edad;
        this.creatividad = creatividad;
    }

    public int Creatividad() {
        return creatividad;
    }

    public void agregarPuntos(int puntos) {
        if (puntos > 0) {
            creatividad += puntos;
            System.out.println(nombre + " ha ganado " + puntos);
        } else {
            System.out.println("error");
        }
    }

    public void presentar() {
        System.out.println(nombre + ", edad " + edad + ", creatividad " + Creatividad() + ". presenta su invento");
    }
}

class mecanico extends inventor {
    public mecanico(String nombre, int edad, int creatividad) {
        super(nombre, edad, creatividad);
    }

    @Override
    public void presentar() {
        System.out.println(nombre + ", edad " + edad + ", creatividad " + Creatividad() + ". prende la maquina");
    }
}

class software extends inventor {
    public software(String nombre, int edad, int creatividadInicial) {
        super(nombre, edad, creatividadInicial);
    }

    @Override
    public void presentar() {
        System.out.println(nombre + ", edad " + edad + ", creatividad " + Creatividad() + ". ejecuta programa de software");
    }
}

class quimico extends inventor {
    public quimico(String nombre, int edad, int creatividad) {
        super(nombre, edad, creatividad);
    }

    @Override
    public void presentar() {
        System.out.println(nombre + ", edad " + edad + ", creatividad " + Creatividad() + ". mezcla sustancias");
    }
}

class Main {
    public static void main(String[] args) {

        inventor[] inventores = new inventor[3];

        inventores[0] = new mecanico("Nicolas Caro", 17, 11);
        inventores[1] = new software("Laura Medina", 17, 15);
        inventores[2] = new quimico("Daniel Galindo", 18, 12);

        inventores[0].agregarPuntos(5);
        inventores[1].agregarPuntos(8);
        inventores[2].agregarPuntos(3);

        System.out.println("presentacion al jurado:");
        for (inventor inventor : inventores) {
            inventor.presentar();
        }
    }
}