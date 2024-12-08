import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Interfaz para operaciones calculables
interface Calculable {
    double calcular();
}
// Clase abstracta para operaciones
abstract class Operacion implements Calculable {
    protected Scanner scanner = new Scanner(System.in);

    // Método común para leer un valor double con validación
    protected double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido. Intente nuevamente.");
                scanner.nextLine();
            }
        }
    }
}

// Subclase para áreas
class Area extends Operacion {
    private final int figura;

    public Area(int figura) {
        this.figura = figura;
    }
    public double calcular() {
        switch (figura) {
            case 1: // Círculo
                double radio = leerDouble("Ingrese el radio del círculo: ");
                return Math.PI * radio * radio;
            case 2: // Cuadrado
                double lado = leerDouble("Ingrese el lado del cuadrado: ");
                return lado * lado;
            case 3: // Triángulo
                double base = leerDouble("Ingrese la base del triángulo: ");
                double altura = leerDouble("Ingrese la altura del triángulo: ");
                return (base * altura) / 2;
            case 4: // Rectángulo
                double largo = leerDouble("Ingrese el largo del rectángulo: ");
                double ancho = leerDouble("Ingrese el ancho del rectángulo: ");
                return largo * ancho;
            case 5: // Pentágono
                double ladoPentagono = leerDouble("Ingrese el lado del pentágono: ");
                double apotema = leerDouble("Ingrese el apotema del pentágono: ");
                return (5 * ladoPentagono * apotema) / 2;
            default:
                System.out.println("Figura no válida.");
                return 0;
        }
    }
}

// Subclase para perímetros
class Perimetro extends Operacion {
    private final int figura;

    public Perimetro(int figura) {
        this.figura = figura;
    }
    public double calcular() {
        switch (figura) {
            case 1: // Círculo
                double radio = leerDouble("Ingrese el radio del círculo: ");
                return 2 * Math.PI * radio;
            case 2: // Cuadrado
                double lado = leerDouble("Ingrese el lado del cuadrado: ");
                return 4 * lado;
            case 3: // Triángulo
                double base = leerDouble("Ingrese la base del triángulo: ");
                double lado1 = leerDouble("Ingrese el lado 1 del triángulo: ");
                double lado2 = leerDouble("Ingrese el lado 2 del triángulo: ");
                return base + lado1 + lado2;
            case 4: // Rectángulo
                double largo = leerDouble("Ingrese el largo del rectángulo: ");
                double ancho = leerDouble("Ingrese el ancho del rectángulo: ");
                return 2 * (largo + ancho);
            case 5: // Pentágono
                double ladoPentagono = leerDouble("Ingrese el lado del pentágono: ");
                return 5 * ladoPentagono;
            default:
                System.out.println("Figura no válida.");
                return 0;
        }
    }
}

// Subclase para potencia
class Potencia extends Operacion {
    public double calcular() {
        double base = leerDouble("Ingrese la base: ");
        int exponente = (int) leerDouble("Ingrese el exponente (entero): ");
        return calcularPotencia(base, exponente);
    }

    // Método recursivo para potencia
    private double calcularPotencia(double base, int exponente) {
        if (exponente == 0) {
            return 1;
        } else if (exponente < 0) {
            return 1 / calcularPotencia(base, -exponente);
        } else {
            return base * calcularPotencia(base, exponente - 1);
        }
    }
}
// Clase principal de la calculadora
public class Asignacion_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> resultados = new ArrayList<>();
        boolean continuar = true;

        System.out.println("Bienvenido a la calculadora de operaciones matemáticas con POO.");

        while (continuar) {
            try {
                System.out.println("\nFiguras disponibles:");
                System.out.println("1. Círculo");
                System.out.println("2. Cuadrado");
                System.out.println("3. Triángulo");
                System.out.println("4. Rectángulo");
                System.out.println("5. Pentágono");
                System.out.println("6. Salir");

                System.out.print("\nSeleccione una figura (1-6): ");
                int figura = scanner.nextInt();

                if (figura == 6) {
                    System.out.println("Saliendo del programa...");
                    break;
                }

                System.out.println("\nOperaciones disponibles:");
                System.out.println("1. Área");
                System.out.println("2. Perímetro");
                System.out.println("3. Potencia");

                System.out.print("\nSeleccione una operación (1-3): ");
                int operacion = scanner.nextInt();

                Calculable calculable;
                switch (operacion) {
                    case 1: // Área
                        calculable = new Area(figura);
                        break;
                    case 2: // Perímetro
                        calculable = new Perimetro(figura);
                        break;
                    case 3: // Potencia
                        calculable = new Potencia();
                        break;
                    default:
                        System.out.println("Operación no válida.");
                        continue;
                }

                double resultado = calculable.calcular();
                resultados.add(resultado);
                System.out.printf("\nResultado de la operación: %.2f\n", resultado);

            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Intente nuevamente.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }

        System.out.println("\nResultados acumulados:");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.printf("Operación %d: %.2f\n", i + 1, resultados.get(i));
        }

        scanner.close();
    }
}