<%@page import="Model.Lugar"%>
<%@page import="Model.Empresa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Resultado"%>
<%@page import="Logic.Logic"%>

<%
    int salida = Integer.parseInt(request.getParameter("salida"));
    int llegada = Integer.parseInt(request.getParameter("llegada"));
    Logic lg = new Logic();
    ArrayList<Resultado> resultados = lg.getShortestRute(salida, llegada);
    ArrayList<Empresa> empresas = lg.getEmpresas();
    ArrayList<Lugar> lugares = lg.getLugares();
%>

<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Terminal Sogamoso</title>
        <script src="https://kit.fontawesome.com/60a79f829f.js" crossorigin="anonymous"></script>
        <style>
            .border-div{
                border-radius: 25px;
                border-style: solid;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">

                <a class="navbar-brand" href="index.jsp">
                    <img src="https://www.horariodebuses.com.co/images/terminales/logo-terminal-sogamoso.jpg" width="200"
                         class="d-inline-block align-top" alt="">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item active ml-2">
                            <a class="btn btn-success" href="index.jsp">Buscar ruta <i class="fas fa-route"></i></a>
                        </li>
                        <li class="nav-item ml-2">
                            <a class="btn btn-primary" href="rutas.jsp">Rutas <i class="fas fa-map-marked"></i></a>
                        </li>
                        <li class="nav-item ml-2">
                            <a class="btn btn-primary" href="lugares.jsp">Empresas <i class="fas fa-bus-alt"></i></a>
                        </li>
                        <li class="nav-item ml-2">
                            <a class="btn btn-primary" href="empresas.jsp">Lugares <i class="fas fa-map"></i></a>
                        </li>
                    </ul>
                </div>
            </nav>
            <%
                if (resultados.isEmpty()) {
                    out.print("<div class='alert alert-danger mt-4' role='alert'>");
                    out.print("No se encontraron resultados para esta consulta");
                    out.print("</div>");
                } else {
                    out.print("<div class='d-flex justify-content-center mt-4'>");
                    String llegada_name = "";
                    for (int j = 0; j < lugares.size(); j++) {
                        if (llegada == lugares.get(j).getID()) {
                            llegada_name = lugares.get(j).getNombre();
                        }
                    }
                    out.print("<h1><span class='badge badge-primary mr-4'>Sogamoso</span><i class='fas fa-arrow-right'></i><span class='badge badge-success ml-4'>" + llegada_name + "</span></h1>");
                    out.print("</div>");
                    for (int i = 0; i < resultados.size(); i++) {
                        out.print("<div class='mt-4 border-div p-5'>");
                        if (resultados.get(i).getTipo().equals("D")) {
                            out.print("<h2>Mejor ruta segun <span class='badge badge-warning mr-4'>Duracion</span></h2>");
                        } else if (resultados.get(i).getTipo().equals("K")) {
                            out.print("<h2>Mejor ruta segun <span class='badge badge-secondary mr-4'>Distancia</span></h2>");
                        } else if (resultados.get(i).getTipo().equals("C")) {
                            out.print("<h2>Mejor ruta segun <span class='badge badge-primary mr-4'>Costo</span></h2>");
                        }
                        String empresa = "null";
                        for (int j = 0; j < empresas.size(); j++) {
                            if (empresas.get(j).getID() == resultados.get(i).getId_empresa()) {
                                empresa = empresas.get(j).getNombre();
                            }
                        }
                        out.print("<h3>Empresa: <span class='badge badge-info mr-4'>" + empresa + "</span></h3>");
                        out.print("<h3>Ruta:</h3>");
                        out.print("<div class='d-flex justify-content-center'>");
                        out.print("<h5>");
                        if (resultados.get(i).getRutas().size() == 2) {
                            out.print("<span class='badge badge-info mr-2'>"+resultados.get(i).getRutas().get(0)+"</span><i class='fas fa-arrow-right'></i><span class='badge badge-info ml-2'>"+resultados.get(i).getRutas().get(1)+"</span>");
                        } else {
                            for (int j = 0; j < resultados.get(i).getRutas().size(); j++) {
                                if ((j == 0) || (j == (resultados.size() - 1))) {
                                    out.print("<span class='badge badge-info'>" + resultados.get(i).getRutas().get(j) + "</span>");
                                } else {
                                    out.print("<i class='fas fa-arrow-right ml-2 mr-2'></i><span class='badge badge-info'>" + resultados.get(i).getRutas().get(j) + "</span><i class='fas fa-arrow-right ml-2 mr-2'></i>");
                                }
                            }
                        }
                        out.print("</h5>");
                        out.print("</div>");
                        int final_cost = 0;
                        for (int j = 0; j < resultados.get(i).getValores().size(); j++) {
                            final_cost += resultados.get(i).getValores().get(j);
                        }
                        if (resultados.get(i).getTipo().equals("D")) {
                            out.print("<h3>Duracion del viaje: " + final_cost + "min</h3>");
                        } else if (resultados.get(i).getTipo().equals("K")) {
                            out.print("<h3>Total de kilometros recorridos: " + final_cost + "Km</h3>");
                        } else if (resultados.get(i).getTipo().equals("C")) {
                            out.print("<h3>Total gastado en pasajes: $" + final_cost + "</h3>");
                        }
                        out.print("</div>");
                    }
                }
            %>
        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>