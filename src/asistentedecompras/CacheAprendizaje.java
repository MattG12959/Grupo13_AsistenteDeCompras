package asistentedecompras;

import java.util.LinkedList;

public class CacheAprendizaje {

    private final LinkedList<RegistroAprendizaje> historial;
    private int totalCorrecciones;

    public CacheAprendizaje() {
        this.historial = new LinkedList<>();
        this.totalCorrecciones = 0;
    }

    public void registrar(String productoAdivinado, String productoReal, String nuevaPregunta) {
        historial.addFirst(new RegistroAprendizaje(productoAdivinado, productoReal, nuevaPregunta));
        totalCorrecciones++;
    }

    public void mostrarHistorial() {
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("  HISTORIAL DE APRENDIZAJE");
        System.out.println("----------------------------------------");
        if (historial.isEmpty()) {
            System.out.println("  Sin correcciones registradas.");
            return;
        }
        int i = 1;
        for (RegistroAprendizaje r : historial) {
            System.out.println("  " + i++ + ". " + r);
        }
    }

    public RegistroAprendizaje obtenerUltimo() {
        return historial.isEmpty() ? null : historial.peekFirst();
    }

    public int getTotalCorrecciones() {
        return totalCorrecciones;
    }

    public boolean estaVacia() {
        return historial.isEmpty();
    }
}
