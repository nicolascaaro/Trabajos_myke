import java.util.*;

class Libro {
    String titulo;
    String autor;
    String codigo;
    boolean disponible;

    Libro(String titulo, String autor, String codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.disponible = true;
    }

    void mostrarDatos() {
        System.out.println("Código: " + codigo + " | Título: " + titulo + " | Autor: " + autor +
                " | Disponible: " + (disponible ? "Sí" : "No"));
    }

    void marcarPrestado() {
        disponible = false;
    }

    void marcarDisponible() {
        disponible = true;
    }
}

class Usuario {
    String nombre;
    String idUsuario;
    ArrayList<Libro> librosPrestados;

    Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.librosPrestados = new ArrayList<>();
    }

    void mostrarDatos() {
        System.out.println("Usuario: " + nombre + " | ID: " + idUsuario);
        if (librosPrestados.isEmpty()) {
            System.out.println("   Sin libros prestados.");
        } else {
            for (Libro l : librosPrestados) {
                System.out.println("   - " + l.titulo);
            }
        }
    }

    boolean agregarPrestamo(Libro libro) {
        if (librosPrestados.size() < 3) {
            librosPrestados.add(libro);
            return true;
        } else {
            return false;
        }
    }

    boolean devolverLibro(Libro libro) {
        return librosPrestados.remove(libro);
    }
}

class Prestamo {
    Usuario usuario;
    Libro libro;
    int diaInicio;
    int diaLimite;

    Prestamo(Usuario usuario, Libro libro, int diaInicio, int diaLimite) {
        this.usuario = usuario;
        this.libro = libro;
        this.diaInicio = diaInicio;
        this.diaLimite = diaLimite;
    }

    void mostrarPrestamo() {
        System.out.println("Usuario: " + usuario.idUsuario + " | Libro: " + libro.titulo +
                " | Inicio: Día " + diaInicio + " | Límite: Día " + diaLimite);
    }
}

class Biblioteca {
    ArrayList<Libro> libros;
    ArrayList<Usuario> usuarios;
    ArrayList<Prestamo> prestamos;

    Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    void registrarLibro(String titulo, String autor, String codigo) {
        libros.add(new Libro(titulo, autor, codigo));
        System.out.println("Libro registrado.");
    }

    void registrarUsuario(String nombre, String id) {
        usuarios.add(new Usuario(nombre, id));
        System.out.println("Usuario registrado.");
    }

    void prestarLibro(String idUsuario, String codigoLibro, int diaActual) {
        Usuario usuario = buscarUsuario(idUsuario);
        Libro libro = buscarLibro(codigoLibro);

        if (usuario == null || libro == null) {
            System.out.println("Usuario o libro no encontrado.");
            return;
        }

        if (!libro.disponible) {
            System.out.println("El libro no está disponible.");
            return;
        }

        if (!usuario.agregarPrestamo(libro)) {
            System.out.println("El usuario ya tiene 3 libros prestados.");
            return;
        }

        libro.marcarPrestado();
        Prestamo p = new Prestamo(usuario, libro, diaActual, diaActual + 7); // plazo de 7 días
        prestamos.add(p);
        System.out.println("Préstamo realizado. Día límite: " + p.diaLimite);
    }

    void devolverLibro(String idUsuario, String codigoLibro, int diaActual) {
        Usuario usuario = buscarUsuario(idUsuario);
        Libro libro = buscarLibro(codigoLibro);

        if (usuario == null || libro == null) {
            System.out.println("Usuario o libro no encontrado.");
            return;
        }

        Prestamo prestamo = buscarPrestamo(usuario, libro);
        if (prestamo == null) {
            System.out.println("Ese libro no estaba prestado a este usuario.");
            return;
        }

        usuario.devolverLibro(libro);
        libro.marcarDisponible();
        prestamos.remove(prestamo);

        if (diaActual > prestamo.diaLimite) {
            int diasRetraso = diaActual - prestamo.diaLimite;
            int multa = diasRetraso * 500;
            System.out.println("Libro devuelto con retraso. Multa: $" + multa);
        } else {
            System.out.println("Libro devuelto a tiempo. Sin multas.");
        }
    }

    void mostrarLibrosDisponibles() {
        System.out.println("=== Libros disponibles ===");
        for (Libro l : libros) {
            if (l.disponible) l.mostrarDatos();
        }
    }

    void mostrarUsuarios() {
        System.out.println("=== Usuarios registrados ===");
        for (Usuario u : usuarios) {
            u.mostrarDatos();
        }
    }

    void mostrarHistorialPrestamos() {
        System.out.println("=== Préstamos activos ===");
        for (Prestamo p : prestamos) {
            p.mostrarPrestamo();
        }
    }

    Usuario buscarUsuario(String id) {
        for (Usuario u : usuarios) {
            if (u.idUsuario.equals(id)) return u;
        }
        return null;
    }

    Libro buscarLibro(String codigo) {
        for (Libro l : libros) {
            if (l.codigo.equals(codigo)) return l;
        }
        return null;
    }

    Prestamo buscarPrestamo(Usuario u, Libro l) {
        for (Prestamo p : prestamos) {
            if (p.usuario == u && p.libro == l) return p;
        }
        return null;
    }
}

public class GestionBiblioteca {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        int opcion;
        int diaActual = 1; // Simulación de días

        do {
            System.out.println("\n=== MENÚ BIBLIOTECA ===");
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros disponibles");
            System.out.println("6. Mostrar usuarios");
            System.out.println("7. Mostrar préstamos");
            System.out.println("8. Avanzar un día");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Código: ");
                    String codigo = sc.nextLine();
                    biblioteca.registrarLibro(titulo, autor, codigo);
                    break;
                case 2:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    biblioteca.registrarUsuario(nombre, id);
                    break;
                case 3:
                    System.out.print("ID Usuario: ");
                    String idU = sc.nextLine();
                    System.out.print("Código Libro: ");
                    String codL = sc.nextLine();
                    biblioteca.prestarLibro(idU, codL, diaActual);
                    break;
                case 4:
                    System.out.print("ID Usuario: ");
                    String idUD = sc.nextLine();
                    System.out.print("Código Libro: ");
                    String codLD = sc.nextLine();
                    biblioteca.devolverLibro(idUD, codLD, diaActual);
                    break;
                case 5:
                    biblioteca.mostrarLibrosDisponibles();
                    break;
                case 6:
                    biblioteca.mostrarUsuarios();
                    break;
                case 7:
                    biblioteca.mostrarHistorialPrestamos();
                    break;
                case 8:
                    diaActual++;
                    System.out.println("Ha pasado un día. Día actual: " + diaActual);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }
}