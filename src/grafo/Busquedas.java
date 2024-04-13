package grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Busquedas {
    //! hacer un mapa o enviar los nodos
    public static boolean porAmplitud(String origen, String destino, ArrayList<Nodo> grafo) {
        Queue<Nodo> cola = new LinkedList<Nodo>();
        ArrayList<Nodo> extraccion = new ArrayList<>();
        ArrayList<Nodo> nodosVisitados = new ArrayList<>();
        ArrayList<Queue<Nodo>> colasAImprimir = new ArrayList<>();
        //!cambiar a mapa en vez de arraylis <String, Nodo>
        int pos = getPosicion(origen, grafo);
        boolean flag = false;
        if (pos != -1) {
            Nodo nodo = grafo.get(pos);
            cola.add(nodo);
            nodosVisitados.add(nodo);
            while (!cola.isEmpty() && !flag) {
                colasAImprimir.add(agregarSubcola(cola));
                Nodo extraido = cola.poll();
                extraccion.add(extraido);
                if (extraido.getNombre().equalsIgnoreCase(destino)) {
                    colasAImprimir.add(agregarSubcola(cola));
                    flag = true;
                    break;
                } else {
                    //!mandar esto a otra funcion
                    for (Arista hijo : extraido.gethijos()) {
                        if (!nodosVisitados.contains(hijo.getNodo())) {
                            cola.add(hijo.getNodo());
                            nodosVisitados.add(hijo.getNodo());
                        }

                    }

                }
            }
            if (flag) {
                
                mostrarTabla(colasAImprimir, extraccion);
                return true;
            }
            mostrarTabla(colasAImprimir, extraccion);
        }

        return false;
    }

    public static int getPosicion(String nombre, ArrayList<Nodo> nodos) {
        for (int i = 0; i < nodos.size(); i++) {
            if (nodos.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean porProfundidad(String origen, String destino, ArrayList<Nodo> grafo) {
        Queue<Nodo> cola = new LinkedList<Nodo>();
        ArrayList<Nodo> extraccion = new ArrayList<>();
        ArrayList<Nodo> nodosVisitados = new ArrayList<>();
        ArrayList<Queue<Nodo>> colasAImprimir = new ArrayList<>();
        int pos = getPosicion(origen, grafo);
        boolean flag = false;
        if (pos != -1) {
            Nodo nodo = grafo.get(pos);
            cola.add(nodo);

            nodosVisitados.add(nodo);

            while (!cola.isEmpty() && !flag) {
                colasAImprimir.add(agregarSubcola(cola));
                Nodo extraido = cola.poll();
                extraccion.add(extraido);
                if (extraido.getNombre().equalsIgnoreCase(destino)) {
                    colasAImprimir.add(agregarSubcola(cola));
                    flag = true;
                    break;
                } else {
                    for (int i = extraido.gethijos().size() - 1; i >= 0; i--) {
                        Arista hijo = extraido.gethijos().get(i);
                        if (!nodosVisitados.contains(hijo.getNodo())) {
                            ((LinkedList<Nodo>) cola).addFirst(hijo.getNodo());
                            nodosVisitados.add(hijo.getNodo());
                        }

                    }
                }
            }
            
            if (flag) {
                
                mostrarTabla(colasAImprimir, extraccion);
                return true;
            }
            mostrarTabla(colasAImprimir, extraccion);
        }

        return false;
    }

    public static boolean porProfundidadIterativa(String origen, String destino, ArrayList<Nodo> grafo) {

        ArrayList<Nodo> extraccion = new ArrayList<>(); // nodos visitados ->reiniciar
        //ArrayList<Queue<Nodo>> colasPorNiveles = new ArrayList<>();
        int pos = getPosicion(origen, grafo);
        int level = 1;
        HashMap<Nodo, Integer> mapa=new HashMap<>();
        //mapa de nodo nivel o nivel lista de nodos pertenecientes
        //boolean flag = false;
        if (pos != -1) {
            Nodo nodo = grafo.get(pos);
            Queue<Nodo> cola = new LinkedList<Nodo>();
            ArrayList<Nodo> miLista=new ArrayList<>();
            miLista.add(nodo);
            mapa.put(nodo,1);
            cola.add(nodo);
            Nodo extraido = cola.poll();
            extraccion.add(extraido);
            for (int i = 0; i < level; i++) {
                extraccion = new ArrayList<>(); 
                
                
                if (mapa.get(extraido)==level) {
                    
                } else {
            
                    for (int j = extraido.gethijos().size() - 1; j >= 0; j--) {
                        Arista hijo = extraido.gethijos().get(j);
                        if (!extraccion.contains(hijo.getNodo())) {
                            ((LinkedList<Nodo>) cola).addFirst(hijo.getNodo());
                            extraccion.add(hijo.getNodo());
                            if(!mapa.containsKey(hijo.getNodo())){
                                mapa.put(hijo.getNodo(), level);
                            }
                           
                            
                        }

                    }
                    extraido=cola.poll();
                    if(mapa.get(extraido)<level){
                        level++;
                        continue;
                    }
                    //! USAR LO DE LOS NO VISITADOS COMO EN PROFUNDIAD NORMAL 
                    while (!cola.isEmpty()) { //! hacer otra condicion considerando nivel de avance y nivel del nodo                    
                        extraido = cola.poll();
                        extraccion.add(extraido);
                        if (extraido.getNombre().equalsIgnoreCase(destino)) {
                            
                            break;
                        } 
                    
                    
                    
                    
                    //extraido.getNombre().equals(destino)
                    // vaciar todo;
                }
                extraido=nodo;
            }
                level++;
            }
            // while(!flag) {
            // level=0;
            // cola.add(nodo);
            // Queue<Nodo> subcola=new LinkedList<>();
            // subcola.add(nodo);

            // Nodo extraido = cola.poll();
            // extraccion.add(extraido);
            // if((level==colasPorNiveles.size())&&cola.isEmpty()){

            // colasPorNiveles.add(subcola);
            // level+=1;
            // }else if((level<colasPorNiveles.size())&&cola.isEmpty()){

            // subcola.poll();
            // for (Arista hijo : extraido.gethijos()) {

            // cola.add(hijo.getDestino());
            // subcola.add(hijo.getDestino());
            // }
            // colasPorNiveles.add(subcola);
            // }

            // if (extraido.getNombre().equals(destino)) {
            // flag = true;
            // break;
            // }

            // }
            // for(Nodo elemento: extraccion){
            // System.out.println(elemento);
            // }
            // if (flag) {
            // // mostrarTabla(cola, extraccion);
            // return true;
            // }
        }

        return false;
    }

    public static void mostrarTabla(ArrayList<Queue<Nodo>> colas, ArrayList<Nodo> extraccion) {
        System.out.println("==========================================================");
        System.out.println("                  Cola                  |   Extraccion   |");
        System.out.println("==========================================================");

        int maxLength = Math.max(colas.size(), extraccion.size());
        for (int i = 0; i < maxLength; i++) {
            if (i < colas.size()) {
                Queue<Nodo> cola = colas.get(i);
                StringBuilder colaString = new StringBuilder();
                for (Nodo elemento : cola) {
                    colaString.append(elemento.getNombre()).append(", ");
                }
                System.out.printf("%-40s|", colaString.toString());

            }

            if (i < (extraccion.size() + 1) && i > 0) {
                Nodo nodo = extraccion.get(i - 1);
                System.out.println("\t" + nodo.getNombre());
            } else {
                System.out.println();
            }

            System.out.println("---------------------------------------------------------");
        }
    }

    public static Queue<Nodo> agregarSubcola(Queue<Nodo> colaGeneral) {
        Queue<Nodo> nuevaCola = new LinkedList<>();
        for (Nodo elemento : colaGeneral) {
            nuevaCola.add(elemento);
        }
        return nuevaCola;
    }
}
