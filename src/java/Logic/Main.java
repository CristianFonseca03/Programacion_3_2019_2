package Logic;

import Logic.Logic;
import Model.Resultado;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Resultado> Resultados;

    public static void main(String[] args) {
        Logic lg = new Logic();
        System.out.println(lg.getShortestRute(18, 19));
    }
}
