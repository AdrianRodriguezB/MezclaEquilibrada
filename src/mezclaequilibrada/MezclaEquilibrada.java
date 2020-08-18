
package mezclaequilibrada;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class MezclaEquilibrada {
    private ArrayList<Secciones> F2;
    private ArrayList<Secciones> F3;
    private  boolean AumentaSeccion;
    private  int IteraccionDeOrdenacion = 1;
    public MezclaEquilibrada()
    {
        this.F2= new ArrayList<Secciones>();
        this.F3= new ArrayList<Secciones>();
        this.AumentaSeccion= false;
    }
    public void IniciaOrdenacion(){
        this.IteraccionDeOrdenacion= 1;
        this.Particion();
    }
    private void Particion(){
        this.F2= new ArrayList<Secciones>();
        this.F3= new ArrayList<Secciones>();
        this.AumentaSeccion= false;
        String Values = Fichero.getDatos("F.txt");
        
        
        System.out.println("F: "+Values);
        System.out.println("PARTICION: "+this.IteraccionDeOrdenacion);
        String V[] = Values.split(" ");
        int ValorActual = Integer.parseInt(V[0]);
        int ValorSiguiente = 0;
        boolean BAND = true;
        int NumSeccion =0;
        this.F2.add(new Secciones());
        this.F2.get(NumSeccion).addValue(ValorActual);
        this.F3.add(new Secciones());
        
        for(int i=1; i<V.length; i++){
            if(this.AumentaSeccion){
                NumSeccion++;
                this.AumentaSeccion= false;
            }
            ValorSiguiente = Integer.parseInt((V[i]));
            if(BAND){
                BAND = this.LLenarF2(ValorSiguiente, ValorActual, NumSeccion);
                ValorActual = ValorSiguiente;
            }
            else{
                BAND = this.LLenarF3(ValorSiguiente, ValorActual, NumSeccion);
                ValorActual = ValorSiguiente;
            }
        }
        String ValoresF2 = this.ConcatenarSecciones(F2);
        String ValoresF3 = this.ConcatenarSecciones(F3);
        System.out.println("F2: "+ValoresF2);
        System.out.println("F3: "+ValoresF3);
        
        Fichero.setDatos("F2.txt", ValoresF2);
        Fichero.setDatos("F3.txt", ValoresF3);
        if(!this.F3.isEmpty() &&!this.F3.get(0).EstaVacio())
            this.FucionParticion();
            
        }
    private String ConcatenarSecciones(ArrayList<Secciones> secciones){
        String Salida = "";
        boolean BANDCambioSeccion = true;
        for(int i=0; i<secciones.size(); i++){
            Salida += ((Salida=="")? "" : " ")+secciones.get(i).ObtenerValoresConcatenados(BANDCambioSeccion);
            //BANDCambioSeccion;
        }
        return Salida;
    }
    
    private void FucionParticion(){
        ArrayList<Integer> F1 = new ArrayList<Integer>();
        for(int i=0; i<this.F2.size(); i++){
            Secciones SeccionF2 = this.F2.get(i);
            Secciones SeccionF3 = this.F3.get(i);
            F1 = this.FucionaParticionIteracion(SeccionF2, SeccionF3, F1);
        }
        String FucionSalida = "";
        for(int i=0; i<F1.size(); i++)
            FucionSalida += F1.get(i)+" ";
        System.out.println("FUSION PARTICION: "+this.IteraccionDeOrdenacion);
        System.out.println(FucionSalida.trim());
        Fichero.setDatos("F.txt", FucionSalida.trim());
    }
    private ArrayList<Integer> FucionaParticionIteracion(Secciones SeccionF2, Secciones SeccionF3, ArrayList<Integer> F1){
        int IndiceF2 = 0;
        int IndiceF3 = 0;
        
        if(!SeccionF2.EstaVacio()){
            int ValorF2 = SeccionF2.ObtenerValor(IndiceF2);
            if(!SeccionF3.EstaVacio()){
                int ValorF3 = SeccionF3.ObtenerValor(IndiceF3);
            if(ValorF2<ValorF3){
                F1.add(ValorF2);
                SeccionF2.EliminarElemento(IndiceF2);
                F1 = this.FucionaParticionIteracion(SeccionF2, SeccionF3, F1);
            }
            else{
                F1.add(ValorF3);
                SeccionF3.EliminarElemento(IndiceF3);
                F1 = this.FucionaParticionIteracion(SeccionF2, SeccionF3, F1);
            }
            }
            else{
                F1 = this.AgregarValoresRestantes(F1, SeccionF2);
                this.IteraccionDeOrdenacion++;
            }
        }
        return F1;
    }
    
    private  ArrayList<Integer> AgregarValoresRestantes(ArrayList<Integer> F1,Secciones seccion){
        while(!seccion.EstaVacio()){
            F1.add(seccion.ObtenerValor(0));
            seccion.EliminarElemento(0);
        }
        return F1;
    }
    
    private Object ObtenerValor(Secciones seccion, int indice){
        if(!seccion.EstaVacio())
            return seccion.ObtenerValor(indice);
        else
            return  null;
    }
    
    private boolean LLenarF2(int ValorSiguiente, int ValorActual, int num_seccion){
        if(ValorSiguiente>=ValorActual){
            this.F2.get(num_seccion).addValue(ValorSiguiente);
            return true;
        }
        else{
            this.F3.get(num_seccion).addValue(ValorSiguiente);
            return false;
        }
    }
    
    private boolean LLenarF3(int ValorSiguiente, int ValorActual, int num_seccion){
        if(ValorSiguiente>=ValorActual){
            this.F3.get(num_seccion).addValue(ValorSiguiente);
            return false;
        }
        else{
            this.F2.add(new Secciones());
            this.F3.add(new Secciones());
            this.AumentaSeccion = true;
            this.F2.get(num_seccion+1).addValue(ValorSiguiente);
            return true;
        }
    }
    public void Imprimir(){
        this.Imprime(this.F2);
        this.Imprime(this.F3);
    }
    private void Imprime(ArrayList<Secciones> F){
        int n = F.size();
        for(int i=0; i<n; i++){
            System.out.println(i+": ");
            F.get(i).Imprime();
            System.out.println();
        }
    }
    

    
    public static void main(String A[]) {
        // TODO code application logic here
        MezclaEquilibrada me= new MezclaEquilibrada();
        me.IniciaOrdenacion();
    }
    
}
