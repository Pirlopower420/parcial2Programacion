
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
class EspacioRecreacional {
    String nombre;
    String tipo;
    int capacidad;
    HashMap<String, Integer> horarios;

    EspacioRecreacional(String nombre, String tipo, int capacidad, String[] horariosDisponibles) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.horarios = new HashMap<>();
        for (String horario : horariosDisponibles) {
            this.horarios.put(horario, 0);
        }
    }

    boolean reservar(String horario) {
        if (horarios.containsKey(horario) && horarios.get(horario) < capacidad) {
            horarios.put(horario, horarios.get(horario) + 1);
            return true;
        }
        return false;
    }

    boolean cancelarReserva(String horario) {
        if (horarios.containsKey(horario) && horarios.get(horario) > 0) {
            horarios.put(horario, horarios.get(horario) - 1);
            return true;
        }
        return false;
    }

    void mostrarDisponibilidad() {
        for (String horario : horarios.keySet()) {
            int reservas = horarios.get(horario);
            System.out.println(horario + " - Disponibilidad: " + (capacidad - reservas) + "/" + capacidad);
        }
    }
}
class Estudiante {
    String nombre;
    String id;

    Estudiante(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    boolean reservarEspacio(EspacioRecreacional espacio, String horario) {
        return espacio.reservar(horario);
    }

    boolean cancelarReserva(EspacioRecreacional espacio, String horario) {
        return espacio.cancelarReserva(horario);
    }
}class GestionRecreacional {
    private ArrayList<EspacioRecreacional> espacios;
    private ArrayList<Estudiante> estudiantes;

    GestionRecreacional() {
        espacios = new ArrayList<>();
        estudiantes = new ArrayList<>();
    }

    void registrarEspacio(EspacioRecreacional espacio) {
        espacios.add(espacio);
    }

    void registrarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    EspacioRecreacional buscarEspacio(String nombre) {
        for (EspacioRecreacional espacio : espacios) {
            if (espacio.nombre.equalsIgnoreCase(nombre)) {
                return espacio;
            }
        }
        return null;
    }

	
    Estudiante buscarEstudiante(String id) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.id.equals(id)) {
                return estudiante;
            }
        }
        return null;
    }

    void mostrarEspaciosDisponibles() {
        for (EspacioRecreacional espacio : espacios) {
            System.out.println("Nombre: " + espacio.nombre + ", Tipo: " + espacio.tipo);
            espacio.mostrarDisponibilidad();
        }
    }

    void mostrarReservasActuales() {
        for (EspacioRecreacional espacio : espacios) {
            System.out.println("Espacio: " + espacio.nombre);
            espacio.mostrarDisponibilidad();
        }
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionRecreacional gestion = new GestionRecreacional();

        gestion.registrarEspacio(new EspacioRecreacional("Cancha de Fútbol", "Cancha", 22, new String[]{"8:00-9:00", "10:00-11:00", "14:00-15:00"}));
        gestion.registrarEspacio(new EspacioRecreacional("Sala de Juegos", "Sala", 10, new String[]{"9:00-10:00", "12:00-13:00", "16:00-17:00"}));

        gestion.registrarEstudiante(new Estudiante("Juan Pérez", "U123"));
        gestion.registrarEstudiante(new Estudiante("Ana Gómez", "U456"));

        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenú del Sistema Recreacional:");
            System.out.println("1. Registrar espacio recreacional");
            System.out.println("2. Registrar estudiante");
            System.out.println("3. Mostrar espacios disponibles");
            System.out.println("4. Reservar un espacio");
            System.out.println("5. Cancelar una reserva");
            System.out.println("6. Mostrar reservas actuales");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el nombre del espacio: ");
                    String nombreEspacio = scanner.nextLine();
                    System.out.print("Ingresa el tipo de espacio (Cancha/Sala): ");
                    String tipo = scanner.nextLine();
                    System.out.print("Ingresa la capacidad del espacio: ");
                    int capacidad = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Ingresa los horarios disponibles separados por comas (e.g., 8:00-9:00,10:00-11:00): ");
                    String[] horarios = scanner.nextLine().split(",");
                    gestion.registrarEspacio(new EspacioRecreacional(nombreEspacio, tipo, capacidad, horarios));
                    System.out.println("Espacio recreacional registrado con éxito.");
                    break;

                case 2:
                    System.out.print("Ingresa el nombre del estudiante: ");
                    String nombreEstudiante = scanner.nextLine();
                    System.out.print("Ingresa el ID del estudiante: ");
                    String idEstudiante = scanner.nextLine();
                    gestion.registrarEstudiante(new Estudiante(nombreEstudiante, idEstudiante));
                    System.out.println("Estudiante registrado con éxito.");
                    break;

                case 3:
                    System.out.println("\nEspacios recreativos disponibles:");
                    gestion.mostrarEspaciosDisponibles();
                    break;

                case 4:
                    System.out.print("Ingresa el ID del estudiante que va a reservar un espacio: ");
                    idEstudiante = scanner.nextLine();
                    Estudiante estudiante = gestion.buscarEstudiante(idEstudiante);

                    if (estudiante == null) {
                        System.out.println("Estudiante no encontrado. Regístralo primero.");
                    } else {
                        System.out.print("Ingresa el nombre del espacio a reservar: ");
                        nombreEspacio = scanner.nextLine();
                        EspacioRecreacional espacio = gestion.buscarEspacio(nombreEspacio);

                        if (espacio == null) {
                            System.out.println("Espacio no encontrado.");
                        } else {
                            System.out.println("Horarios disponibles para " + espacio.nombre + ":");
                            espacio.mostrarDisponibilidad();
                            System.out.print("Selecciona un horario: ");
                            String horario = scanner.nextLine();

                            if (estudiante.reservarEspacio(espacio, horario)) {
                                System.out.println("Reserva realizada con éxito.");
                            } else {
                                System.out.println("No se pudo realizar la reserva. Verifica la disponibilidad o la capacidad del espacio.");
                            }
                        }
                    }
                    break;

                case 5:
                    System.out.print("Ingresa el ID del estudiante que va a cancelar una reserva: ");
                    idEstudiante = scanner.nextLine();
                    estudiante = gestion.buscarEstudiante(idEstudiante);

                    if (estudiante == null) {
                        System.out.println("Estudiante no encontrado.");
                    } else {
                        System.out.print("Ingresa el nombre del espacio a cancelar: ");
                        nombreEspacio = scanner.nextLine();
                        EspacioRecreacional espacio = gestion.buscarEspacio(nombreEspacio);

                        if (espacio == null) {
                            System.out.println("Espacio no encontrado.");
                        } else {
                            System.out.print("Ingresa el horario de la reserva a cancelar: ");
                            String horario = scanner.nextLine();

                            if (estudiante.cancelarReserva(espacio, horario)) {
                                System.out.println("Reserva cancelada con éxito.");
                            } else {
                                System.out.println("No se pudo cancelar la reserva. Verifica que exista.");
                            }
                        }
                    }
                    break;

                case 6:
                    System.out.println("\nReservas actuales:");
                    gestion.mostrarReservasActuales();
                    break;

                case 7:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        }
    }
}