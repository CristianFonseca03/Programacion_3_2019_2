package Model;

public class Empresa {

    private int ID;
    private String Nombre;

    public Empresa(int ID, String Nombre) {
        this.ID = ID;
        this.Nombre = Nombre;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "Empresa{" + "ID=" + ID + ", Nombre=" + Nombre + '}';
    }

}
