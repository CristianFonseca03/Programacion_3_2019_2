package Model;

import java.util.ArrayList;

public class Resultado {

    private int id_empresa;
    private ArrayList<String> rutas;
    private ArrayList<Integer> valores;
    private String tipo;

    public Resultado(int id_empresa, ArrayList<String> rutas, ArrayList<Integer> valores, String tipo) {
        this.id_empresa = id_empresa;
        this.rutas = rutas;
        this.valores = valores;
        this.tipo = tipo;
    }

    public ArrayList<Integer> getValores() {
        return valores;
    }

    public void setValores(ArrayList<Integer> valores) {
        this.valores = valores;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public ArrayList<String> getRutas() {
        return rutas;
    }

    public void setRutas(ArrayList<String> rutas) {
        this.rutas = rutas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Resultado{" + "id_empresa=" + id_empresa + ", rutas=" + rutas + ", valores=" + valores + ", tipo=" + tipo + '}';
    }

}
