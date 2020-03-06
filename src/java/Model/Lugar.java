package Model;

public class Lugar {

    private int ID;
    private String Nombre;

    public Lugar(int ID, String Nombre) {
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
        return "Lugar{" + "ID=" + ID + ", Nombre=" + Nombre + '}';
    }

}
