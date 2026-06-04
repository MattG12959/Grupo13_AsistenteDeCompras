package asistentedecompras;

import java.util.Stack;

public class ArbolDecision {

    private Nodo raiz;

    public ArbolDecision() {
        this.raiz = null;
    }

    // ── CONSTRUCCION DEL ARBOL ────────────────────────────────────
    public void construirArbolInicial() {

        // Hojas: dispositivos moviles / llamadas
        Nodo iphone = new Nodo("iPhone 15 Pro", true);
        Nodo samsungA54 = new Nodo("Samsung Galaxy A54", true);
        Nodo motorola = new Nodo("Motorola Moto G", true);

        Nodo prefAndroid = new Nodo("¿Prefieres Android sobre iOS?");
        prefAndroid.setHijoDerecho(samsungA54);
        prefAndroid.setHijoIzquierdo(motorola);

        Nodo presupuestoMovil = new Nodo("¿Tienes presupuesto mayor a $500.000?");
        presupuestoMovil.setHijoDerecho(iphone);
        presupuestoMovil.setHijoIzquierdo(prefAndroid);

        // Hojas: tablets
        Nodo ipadPro = new Nodo("iPad Pro", true);
        Nodo ipadMini = new Nodo("iPad Mini", true);

        Nodo pantallaGrande = new Nodo("¿Necesitas pantalla grande (mas de 10 pulgadas)?");
        pantallaGrande.setHijoDerecho(ipadPro);
        pantallaGrande.setHijoIzquierdo(ipadMini);

        Nodo necesitaLlamar = new Nodo("¿Necesitas hacer llamadas telefónicas?");
        necesitaLlamar.setHijoDerecho(presupuestoMovil);
        necesitaLlamar.setHijoIzquierdo(pantallaGrande);

        // Hojas: laptops
        Nodo macbookPro = new Nodo("MacBook Pro M3", true);
        Nodo dellXPS = new Nodo("Dell XPS 15", true);
        Nodo thinkPad = new Nodo("Lenovo ThinkPad E15", true);
        Nodo acerAspire = new Nodo("Acer Aspire 5", true);

        Nodo diseno = new Nodo("¿La usaras para diseño o edicion de video?");
        diseno.setHijoDerecho(macbookPro);
        diseno.setHijoIzquierdo(dellXPS);

        Nodo buenaBateria = new Nodo("¿Necesitas bateria mayor a 10 horas?");
        buenaBateria.setHijoDerecho(thinkPad);
        buenaBateria.setHijoIzquierdo(acerAspire);

        Nodo presupuestoLaptop = new Nodo("¿Tienes presupuesto mayor a $1.000.000?");
        presupuestoLaptop.setHijoDerecho(diseno);
        presupuestoLaptop.setHijoIzquierdo(buenaBateria);

        // Hojas: escritorio
        Nodo pcGamer4070 = new Nodo("PC Gamer RTX 4070", true);
        Nodo pcGtx1660 = new Nodo("PC con GTX 1660 Super", true);
        Nodo chromebox = new Nodo("Chromebox", true);
        Nodo smartTv = new Nodo("Smart TV con Android", true);

        Nodo presupuestoGaming = new Nodo("¿Tienes presupuesto mayor a $800.000?");
        presupuestoGaming.setHijoDerecho(pcGamer4070);
        presupuestoGaming.setHijoIzquierdo(pcGtx1660);

        Nodo usoBasico = new Nodo("¿Es para uso basico (Internet, peliculas)?");
        usoBasico.setHijoDerecho(chromebox);
        usoBasico.setHijoIzquierdo(smartTv);

        Nodo potencia = new Nodo("¿Necesitas mucha potencia (gaming o edicion)?");
        potencia.setHijoDerecho(presupuestoGaming);
        potencia.setHijoIzquierdo(usoBasico);

        Nodo laptopOEscritorio = new Nodo("¿Necesitas una laptop?");
        laptopOEscritorio.setHijoDerecho(presupuestoLaptop);
        laptopOEscritorio.setHijoIzquierdo(potencia);

        raiz = new Nodo("¿Buscas un dispositivo movil (telefono o tablet)?");
        raiz.setHijoDerecho(necesitaLlamar);
        raiz.setHijoIzquierdo(laptopOEscritorio);
    }

    // ── CONSULTA INTERACTIVA ──────────────────────────────────────
    public Nodo consultar(java.util.Scanner scanner) {
        if (raiz == null) {
            System.out.println("El arbol esta vacio.");
            return null;
        }

        Nodo actual = raiz;
        while (!actual.esHoja()) {
            System.out.print(actual.getContenido() + " [s/n]: ");
            String resp = scanner.nextLine().trim().toLowerCase();

            if (resp.equals("s") || resp.equals("si") || resp.equals("sí")) {
                actual = actual.getHijoDerecho();
            } else if (resp.equals("n") || resp.equals("no")) {
                actual = actual.getHijoIzquierdo();
            } else {
                System.out.println("Ingrese 's' o 'n'.");
                continue;
            }

            if (actual == null) {
                System.out.println("Error: rama incompleta.");
                return null;
            }
        }
        return actual;
    }

    // ── APRENDIZAJE DINAMICO ──────────────────────────────────────
    public void aprenderNuevoProducto(Nodo hojaIncorrecta, String productoReal, String nuevaPregunta) {
        String productoAnterior = hojaIncorrecta.getContenido();

        hojaIncorrecta.setContenido(nuevaPregunta);
        hojaIncorrecta.setEsHoja(false);
        hojaIncorrecta.setHijoDerecho(new Nodo(productoReal, true));
        hojaIncorrecta.setHijoIzquierdo(new Nodo(productoAnterior, true));

        System.out.println("  SI -> " + productoReal);
        System.out.println("  NO -> " + productoAnterior);
    }

    // ── IMPRIMIR ARBOL (iterativo con Stack) ──────────────────────
    public void imprimirArbol() {
        if (raiz == null) {
            System.out.println("El arbol esta vacio.");
            return;
        }

        System.out.println();
        Stack<Object[]> pila = new Stack<>();
        pila.push(new Object[]{raiz, 0, "RAIZ"});

        while (!pila.isEmpty()) {
            Object[] frame = pila.pop();
            Nodo nodo = (Nodo) frame[0];
            int nivel = (int) frame[1];
            String etiqueta = (String) frame[2];

            String sangria = "    ".repeat(nivel);
            String tipo = nodo.esHoja() ? "[P]" : "[?]";
            System.out.println(sangria + tipo + " (" + etiqueta + ") " + nodo.getContenido());

            if (nodo.getHijoDerecho() != null) {
                pila.push(new Object[]{nodo.getHijoDerecho(), nivel + 1, "SI"});
            }
            if (nodo.getHijoIzquierdo() != null) {
                pila.push(new Object[]{nodo.getHijoIzquierdo(), nivel + 1, "NO"});
            }
        }
        System.out.println();
    }

    // ── ESTADISTICAS ──────────────────────────────────────────────
    public int contarNodos() {
        if (raiz == null) {
            return 0;
        }
        int count = 0;
        Stack<Nodo> pila = new Stack<>();
        pila.push(raiz);
        while (!pila.isEmpty()) {
            Nodo n = pila.pop();
            count++;
            if (n.getHijoDerecho() != null) {
                pila.push(n.getHijoDerecho());
            }
            if (n.getHijoIzquierdo() != null) {
                pila.push(n.getHijoIzquierdo());
            }
        }
        return count;
    }

    public int calcularAltura() {
        if (raiz == null) {
            return 0;
        }
        int max = 0;
        Stack<Object[]> pila = new Stack<>();
        pila.push(new Object[]{raiz, 1});
        while (!pila.isEmpty()) {
            Object[] f = pila.pop();
            Nodo n = (Nodo) f[0];
            int nv = (int) f[1];
            if (nv > max) {
                max = nv;
            }
            if (n.getHijoDerecho() != null) {
                pila.push(new Object[]{n.getHijoDerecho(), nv + 1});
            }
            if (n.getHijoIzquierdo() != null) {
                pila.push(new Object[]{n.getHijoIzquierdo(), nv + 1});
            }
        }
        return max;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }
}
