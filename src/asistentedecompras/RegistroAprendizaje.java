package asistentedecompras;

/**
 * Registro inmutable que almacena un evento de aprendizaje dinámico. Guarda qué
 * producto pensaba el usuario, cuál adivinó el programa, y la nueva pregunta
 * diferenciadora.
 */
public class RegistroAprendizaje {

    private final String productoAdivinado;   // Lo que el árbol sugirió (incorrecto)
    private final String productoReal;         // Lo que el usuario pensaba
    private final String nuevaPregunta;        // Pregunta diferenciadora ingresada

    public RegistroAprendizaje(String productoAdivinado, String productoReal, String nuevaPregunta) {
        this.productoAdivinado = productoAdivinado;
        this.productoReal = productoReal;
        this.nuevaPregunta = nuevaPregunta;
    }

    public String getProductoAdivinado() {
        return productoAdivinado;
    }

    public String getProductoReal() {
        return productoReal;
    }

    public String getNuevaPregunta() {
        return nuevaPregunta;
    }

    @Override
    public String toString() {
        return String.format(
                "  Adiviné: %-25s | Real: %-25s | Pregunta: %s",
                productoAdivinado, productoReal, nuevaPregunta
        );
    }
}
