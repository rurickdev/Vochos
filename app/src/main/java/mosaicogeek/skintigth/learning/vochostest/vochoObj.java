package mosaicogeek.skintigth.learning.vochostest;

public class vochoObj {

    private int colorImge;
    private int contador;
    private String ColString;

    public vochoObj(int colorImge, int contador, String ColString){

        this.colorImge = colorImge;
        this.contador = contador;
        this.ColString = ColString;
    }

    public int getColorImge(){
        return colorImge;
    }

    public String getColString(){
        return ColString;
    }

    public int getContador() {
        return contador;
    }
}