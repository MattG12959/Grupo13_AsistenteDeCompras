package asistentedecompras;

/**
 * Clase que representa un nodo del árbol binario de decisión. - Si es nodo
 * interno: contiene una pregunta, hijo izquierdo (NO) y derecho (SÍ) - Si es
 * hoja: contiene el nombre de un producto sugerido
 */
public class Nodo {

    private String contenido;   // Pregunta o nombre de producto
    private boolean esHoja;     // true = producto, false = pregunta
    private Nodo hijoDerecho;   // Respuesta SÍ
    private Nodo hijoIzquierdo; // Respuesta NO

    // Constructor para nodo pregunta (nodo interno)
    public Nodo(String pregunta) {
        this.contenido = pregunta;
        this.esHoja = false;
        this.hijoDerecho = null;
        this.hijoIzquierdo = null;
    }

    // Constructor para hoja (producto)
    public Nodo(String producto, boolean esHoja) {
        this.contenido = producto;
        this.esHoja = esHoja;
        this.hijoDerecho = null;
        this.hijoIzquierdo = null;
    }

    // Getters y Setters
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean esHoja() {
        return esHoja;
    }

    public void setEsHoja(boolean esHoja) {
        this.esHoja = esHoja;
    }

    public Nodo getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(Nodo hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public Nodo getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(Nodo hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    @Override
    public String toString() {
        return (esHoja ? "[PRODUCTO] " : "[PREGUNTA] ") + contenido;
    }
}
