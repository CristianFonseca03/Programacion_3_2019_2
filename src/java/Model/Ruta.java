package Model;

public class Ruta {

    private int ID_empresa, ID_salida, ID_llegada, Duracion, Kilometraje, Costo;

    public Ruta(int ID_empresa, int ID_salida, int ID_llegada, int Duracion, int Kilometraje, int Costo) {
        this.ID_empresa = ID_empresa;
        this.ID_salida = ID_salida;
        this.ID_llegada = ID_llegada;
        this.Duracion = Duracion;
        this.Kilometraje = Kilometraje;
        this.Costo = Costo;
    }

    public int getID_empresa() {
        return ID_empresa;
    }

    public void setID_empresa(int ID_empresa) {
        this.ID_empresa = ID_empresa;
    }

    public int getID_salida() {
        return ID_salida;
    }

    public void setID_salida(int ID_salida) {
        this.ID_salida = ID_salida;
    }

    public int getID_llegada() {
        return ID_llegada;
    }

    public void setID_llegada(int ID_llegada) {
        this.ID_llegada = ID_llegada;
    }

    public int getDuracion() {
        return Duracion;
    }

    public void setDuracion(int Duracion) {
        this.Duracion = Duracion;
    }

    public int getKilometraje() {
        return Kilometraje;
    }

    public void setKilometraje(int Kilometraje) {
        this.Kilometraje = Kilometraje;
    }

    public int getCosto() {
        return Costo;
    }

    public void setCosto(int Costo) {
        this.Costo = Costo;
    }

    @Override
    public String toString() {
        return "Ruta{" + "ID_empresa=" + ID_empresa + ", ID_salida=" + ID_salida + ", ID_llegada=" + ID_llegada + ", Duracion=" + Duracion + ", Kilometraje=" + Kilometraje + ", Costo=" + Costo + '}';
    }

}
