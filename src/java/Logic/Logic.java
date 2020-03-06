package Logic;

import Model.Empresa;
import Model.Graph;
import Model.Lugar;
import Model.Resultado;
import Model.Ruta;
import Model.Vertex;
import java.util.ArrayList;
import java.sql.*;
import java.util.Collections;

public class Logic {

    private ArrayList<Ruta> Rutas = new ArrayList<>();
    private ArrayList<Empresa> Empresas = new ArrayList<>();
    private ArrayList<Lugar> Lugares = new ArrayList<>();
    private ArrayList<Graph> Grafos = new ArrayList<>();

    public Logic() {
        Rutas = rutasConn();
        Empresas = empresasConn();
        Lugares = lugaresConn();
        Grafos = makeGraphs();
    }

    public ArrayList<Graph> getGrafos() {
        return Grafos;
    }

    public ArrayList<Ruta> getRutas() {
        return Rutas;
    }

    public ArrayList<Empresa> getEmpresas() {
        return Empresas;
    }

    public ArrayList<Lugar> getLugares() {
        return Lugares;
    }

    public boolean insertRoute(int id_empresa, int id_salida, int id_llegada, int duracion, int distancia, int costo) {
        for (Ruta ruta : Rutas) {
            if ((ruta.getID_empresa() == id_empresa) & (ruta.getID_salida() == id_salida) & (ruta.getID_llegada() == id_llegada)) {
                return false;
            }
        }
        try {
            // create a mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/programacion3";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            Statement st = conn.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate("INSERT INTO rutas "
                    + "VALUES (" + id_empresa + ", " + id_salida + ", " + id_llegada + ", " + duracion + "," + distancia + "," + costo + ")");
            st.executeUpdate("INSERT INTO rutas "
                    + "VALUES (" + id_empresa + ", " + id_llegada + ", " + id_salida + ", " + duracion + "," + distancia + "," + costo + ")");
            Rutas = rutasConn();
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean updateRute(int id_empresa, int id_salida, int id_llegada, int duracion, int distancia, int costo) {
        try {
            // create a mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/programacion3";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            Statement st = conn.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate("UPDATE rutas "
                    + "SET duracion = " + duracion + ", distancia = "
                    + distancia + ", costo = " + costo + " WHERE id_empresa = "
                    + id_empresa + " and id_salida = " + id_salida + " and id_llegada=" + id_llegada);
            st.executeUpdate("UPDATE rutas "
                    + "SET duracion = " + duracion + ", distancia = "
                    + distancia + ", costo = " + costo + " WHERE id_empresa = "
                    + id_empresa + " and id_salida = " + id_llegada + " and id_llegada=" + id_salida);
            Rutas = rutasConn();
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteRute(int id_empresa, int id_salida, int id_llegada) {
        try {
            // create a mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/programacion3";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            Statement st = conn.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate("DELETE FROM rutas WHERE id_empresa = " + id_empresa + " and id_salida = " + id_salida + " and id_llegada = " + id_llegada);
            st.executeUpdate("DELETE FROM rutas WHERE id_empresa = " + id_empresa + " and id_salida = " + id_llegada + " and id_llegada = " + id_salida);
            Rutas = rutasConn();
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean insertEmpresa(String empresa_name) {
        for (Empresa empresa : Empresas) {
            if (empresa.getNombre().equals(empresa_name)) {
                return false;
            }
        }
        try {
            // create a mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/programacion3";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            Statement st = conn.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate("INSERT INTO empresas (nombre) values ('" + empresa_name + "')");
            Empresas = empresasConn();
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean updateEmpresa(int id_empresa, String nombre) {
        try {
            // create a mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/programacion3";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            Statement st = conn.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate("UPDATE empresas "
                    + "SET nombre = '" + nombre + "' WHERE id = "
                    + id_empresa);
            Empresas = empresasConn();
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteEmpresa(int id_empresa) {
        try {
            // create a mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/programacion3";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            Statement st = conn.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate("DELETE FROM empresas WHERE id = " + id_empresa);
            Empresas = empresasConn();
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean insertLugar(String lugar_name) {
        for (Lugar lugar : Lugares) {
            if (lugar.getNombre().equals(lugar_name)) {
                return false;
            }
        }
        try {
            // create a mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/programacion3";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            Statement st = conn.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate("INSERT INTO lugares (nombre) values ('" + lugar_name + "')");
            Lugares = lugaresConn();
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean updateLugar(int id_lugar, String nombre) {
        try {
            // create a mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/programacion3";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            Statement st = conn.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate("UPDATE lugares "
                    + "SET nombre = '" + nombre + "' WHERE id = "
                    + id_lugar);
            Lugares = lugaresConn();
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteLugar(int id_lugar) {
        try {
            // create a mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/programacion3";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            Statement st = conn.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate("DELETE FROM lugares WHERE id = " + id_lugar);
            Lugares = lugaresConn();
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<Resultado> getShortestRute(int id_salida, int id_llegada) {
        ArrayList<Resultado> Resultados = new ArrayList<>();
        Grafos.stream().forEach((Graph grafo) -> {
            int column = 0;
            if (grafo.getNombre().equals("D")) {
                column = 4;
            } else if (grafo.getNombre().equals("K")) {
                column = 5;
            } else if (grafo.getNombre().equals("C")) {
                column = 6;
            }
            ArrayList<Integer> list = new ArrayList<>();
            grafo.getShortestPath((char) id_salida, (char) id_llegada).stream().forEach((element) -> {
                list.add((int) element);
            });
            list.add(id_salida);
            Collections.reverse(list);
            if ((list.get(0) == id_salida) & (list.get(list.size() - 1) == id_llegada)) {
                ArrayList<Integer> valors = new ArrayList<>();
                ArrayList<String> names = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (i != list.size() - 1) {
                        valors.add(getValor(grafo.getID(), list.get(i), list.get(i + 1), column));
                    }
                }
                list.stream().forEach((id) -> {
                    Lugares.stream().filter((lugar) -> (lugar.getID() == id)).forEach((lugar) -> {
                        names.add(lugar.getNombre());
                    });
                });
                Resultados.add(new Resultado(grafo.getID(), names, valors, grafo.getNombre()));
            }
        });
        return Resultados;
    }

    private ArrayList<Graph> makeGraphs() {
        Empresas.stream().forEach((empresa) -> {
            Grafos.add(new Graph(empresa.getID(), "D"));
            Grafos.add(new Graph(empresa.getID(), "K"));
            Grafos.add(new Graph(empresa.getID(), "C"));
        });
        Grafos.stream().forEach((grafo) -> {
            if (grafo.getNombre().equals("D")) {
                Lugares.stream().forEach((lugar) -> {
                    ArrayList<Vertex> vertices = getVertex(grafo.getID(), lugar.getID(), 4);
                    if (vertices.isEmpty() == false) {
                        grafo.addVertex((char) lugar.getID(), vertices);
                    }
                });
            } else if (grafo.getNombre().equals("K")) {
                Lugares.stream().forEach((lugar) -> {
                    ArrayList<Vertex> vertices = getVertex(grafo.getID(), lugar.getID(), 5);
                    if (vertices.isEmpty() == false) {
                        grafo.addVertex((char) lugar.getID(), vertices);
                    }
                });
            } else if (grafo.getNombre().equals("C")) {
                Lugares.stream().forEach((lugar) -> {
                    ArrayList<Vertex> vertices = getVertex(grafo.getID(), lugar.getID(), 6);
                    if (vertices.isEmpty() == false) {
                        grafo.addVertex((char) lugar.getID(), vertices);
                    }
                });
            }
        });
        return Grafos;
    }

    private ArrayList<Vertex> getVertex(int id_empresa, int id_salida, int column) {
        ArrayList<Vertex> vertices = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/programacion3", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from rutas where id_empresa = " + id_empresa + " and id_salida = " + id_salida);
            while (rs.next()) {
                vertices.add(new Vertex((char) rs.getInt(3), rs.getInt(column)));
            }
            con.close();
            return vertices;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private int getValor(int id_empresa, int id_salida, int id_llegada, int column) {
        int valor = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/programacion3", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from rutas where id_empresa = " + id_empresa + " and id_salida = " + id_salida + " and id_llegada = " + id_llegada);
            while (rs.next()) {
                valor = rs.getInt(column);
            }
            con.close();
            return valor;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    private ArrayList<Empresa> empresasConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/programacion3", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from empresas");
            while (rs.next()) {
                Empresas.add(new Empresa(rs.getInt(1), rs.getString(2)));
            }
            con.close();
            return Empresas;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private ArrayList<Lugar> lugaresConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/programacion3", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from lugares");
            while (rs.next()) {
                Lugares.add(new Lugar(rs.getInt(1), rs.getString(2)));
            }
            con.close();
            return Lugares;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private ArrayList<Ruta> rutasConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/programacion3", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from rutas");
            while (rs.next()) {
                Rutas.add(new Ruta(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
            }
            con.close();
            return Rutas;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
