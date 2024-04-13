package grafo;

 

public class Arista {
    
    private double peso;
    private Nodo destino;
    //! tal vez toque agregar el origen tambien
    public Arista( double peso, Nodo destino){
    
        this.peso=peso;
        this.destino=destino;
    }

    public Arista(){

    }
    public Nodo getNodo() {
        return destino;
    }

    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

   
    

    public Nodo agregarNodo(String nombre, double peso){
        
        Nodo miNodo=new Nodo(nombre, peso);
        return miNodo;

    }

    @Override
    public String toString(){
        return "Arista (peso="+peso+",destino="+destino.getNombre();
    }

    
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

}
