package asistentedecompras;

import java.util.Scanner;

public class main {

    private static final String SEP = "----------------------------------------";

    private static ArbolDecision arbol = new ArbolDecision();
    private static CacheAprendizaje cache = new CacheAprendizaje();
    private static Scanner scanner = new Scanner(System.in);
    private static int totalConsultas = 0;
    private static int aciertos = 0;

    public static void main(String[] args) {
        arbol.construirArbolInicial();
        System.out.println(SEP);
        System.out.println("  ASISTENTE DE COMPRAS");
        System.out.println(SEP);

        boolean ejecutando = true;
        while (ejecutando) {
            mostrarMenu();
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1" ->
                    iniciarConsulta();
                case "2" ->
                    arbol.imprimirArbol();
                case "3" ->
                    mostrarEstadisticas();
                case "4" ->
                    cache.mostrarHistorial();
                case "5" ->
                    reconstruirArbol();
                case "0" -> {
                    ejecutando = false;
                    despedida();
                }
                default ->
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println(SEP);
        System.out.println("  1. Iniciar consulta");
        System.out.println("  2. Ver arbol");
        System.out.println("  3. Estadisticas");
        System.out.println("  4. Historial de aprendizaje");
        System.out.println("  5. Reconstruir arbol");
        System.out.println("  0. Salir");
        System.out.println(SEP);
        System.out.print("  Opcion: ");
    }

    private static void iniciarConsulta() {
        System.out.println();
        System.out.println("Piense en un producto electronico y responda s/n.");

        Nodo hoja = arbol.consultar(scanner);
        if (hoja == null) {
            return;
        }

        totalConsultas++;
        System.out.println();
        System.out.println("Mi sugerencia: " + hoja.getContenido());
        System.out.print("¿Acerte? [s/n]: ");
        String resp = scanner.nextLine().trim().toLowerCase();

        if (resp.equals("s") || resp.equals("si") || resp.equals("sí")) {
            aciertos++;
            System.out.println("Correcto!");
        } else {
            manejarCorreccion(hoja, hoja.getContenido());
        }

        System.out.println();
        System.out.print("¿Otra consulta? [s/n]: ");
        String otra = scanner.nextLine().trim().toLowerCase();
        if (otra.equals("s") || otra.equals("si") || otra.equals("sí")) {
            iniciarConsulta();
        }
    }

    private static void manejarCorreccion(Nodo hojaIncorrecta, String productoAdivinado) {
        System.out.println("No acerte. Voy a aprender.");

        String productoReal = "";
        while (productoReal.isEmpty()) {
            System.out.print("¿En que producto pensabas?: ");
            productoReal = scanner.nextLine().trim();
        }

        System.out.println("Ingresa una pregunta que diferencie \"" + productoReal + "\" de \"" + productoAdivinado + "\".");
        System.out.println("(Respuesta SI debe llevar a tu producto)");

        String nuevaPregunta = "";
        while (nuevaPregunta.isEmpty()) {
            System.out.print("Nueva pregunta: ");
            nuevaPregunta = scanner.nextLine().trim();
            if (!nuevaPregunta.isEmpty() && !nuevaPregunta.endsWith("?")) {
                nuevaPregunta += "?";
            }
        }

        arbol.aprenderNuevoProducto(hojaIncorrecta, productoReal, nuevaPregunta);
        cache.registrar(productoAdivinado, productoReal, nuevaPregunta);
        System.out.println("Listo! Ahora conozco \"" + productoReal + "\".");
    }

    private static void mostrarEstadisticas() {
        System.out.println();
        System.out.println(SEP);
        System.out.println("  ESTADISTICAS");
        System.out.println(SEP);
        System.out.println("  Consultas  : " + totalConsultas);
        System.out.println("  Aciertos   : " + aciertos);
        System.out.println("  Aprendizajes: " + cache.getTotalCorrecciones());
        if (totalConsultas > 0) {
            System.out.printf("  Precision  : %.1f%%%n", (aciertos * 100.0) / totalConsultas);
        }
        System.out.println("  Nodos      : " + arbol.contarNodos());
        System.out.println("  Altura     : " + arbol.calcularAltura());
    }

    private static void reconstruirArbol() {
        System.out.print("¿Confirmar reset? [s/n]: ");
        String conf = scanner.nextLine().trim().toLowerCase();
        if (conf.equals("s") || conf.equals("si") || conf.equals("sí")) {
            arbol = new ArbolDecision();
            cache = new CacheAprendizaje();
            totalConsultas = 0;
            aciertos = 0;
            arbol.construirArbolInicial();
            System.out.println("Arbol reconstruido.");
        } else {
            System.out.println("Cancelado.");
        }
    }

    private static void despedida() {
        System.out.println();
        System.out.println("Hasta luego!");
        if (totalConsultas > 0) {
            System.out.println("Consultas: " + totalConsultas + " | Aciertos: " + aciertos + " | Aprendizajes: " + cache.getTotalCorrecciones());
        }
        scanner.close();
    }
}
