<%@page import="Model.Ruta"%>
<%@page import="Model.Lugar"%>
<%@page import="Model.Empresa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Resultado"%>
<%@page import="Logic.Logic"%>

<%
    Logic lg = new Logic();
    ArrayList<Empresa> empresas = lg.getEmpresas();
    ArrayList<Lugar> lugares = lg.getLugares();
    ArrayList<Ruta> rutas = lg.getRutas();
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
                            <a class="btn btn-primary" href="index.jsp">Buscar ruta <i class="fas fa-route"></i></a>
                        </li>
                        <li class="nav-item ml-2">
                            <a class="btn btn-success" href="rutas.jsp">Rutas <i class="fas fa-map-marked"></i></a>
                        </li>
                        <li class="nav-item ml-2">
                            <a class="btn btn-primary" href="empresas.jsp">Empresas <i class="fas fa-bus-alt"></i></a>
                        </li>
                        <li class="nav-item ml-2">
                            <a class="btn btn-primary" href="lugares.jsp">Lugares <i class="fas fa-map"></i></a>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="mt-5">
                <div class="d-flex justify-content-center align-items-center">
                    <h1>Rutas</h1> 
                    <a class="btn btn-outline-success ml-4" href="createRuta.jsp" role="button"><i class="fas fa-plus"></i></a>
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Empresa</th>
                            <th scope="col">Salida</th>
                            <th scope="col">Llegada</th>
                            <th scope="col">Duracion</th>
                            <th scope="col">Kilometraje</th>
                            <th scope="col">Costo</th>
                            <th scope="col">Opciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 0; i < rutas.size(); i++) {
                                out.print("<tr>");
                                out.print("<th scope='row'>" + (i + 1) + "</th>");
                                for (int j = 0; j < empresas.size(); j++) {
                                    if (rutas.get(i).getID_empresa() == empresas.get(j).getID()) {
                                        out.print("<td>" + empresas.get(j).getNombre() + "</td>");
                                        break;
                                    }
                                }
                                for (int j = 0; j < lugares.size(); j++) {
                                    if (rutas.get(i).getID_salida() == lugares.get(j).getID()) {
                                        out.print("<td>" + lugares.get(j).getNombre() + "</td>");
                                        break;
                                    }
                                }
                                for (int j = 0; j < lugares.size(); j++) {
                                    if (rutas.get(i).getID_llegada() == lugares.get(j).getID()) {
                                        out.print("<td>" + lugares.get(j).getNombre() + "</td>");
                                        break;
                                    }
                                }
                                out.print("<td>" + rutas.get(i).getDuracion() + "</td>");
                                out.print("<td>" + rutas.get(i).getKilometraje() + "</td>");
                                out.print("<td>$" + rutas.get(i).getCosto() + "</td>");
                                String url_paramaters_update = "?empresa=" + rutas.get(i).getID_empresa() + "&salida=" + rutas.get(i).getID_salida() + "&llegada=" + rutas.get(i).getID_llegada() + "&d=" + rutas.get(i).getDuracion() + "&k=" + rutas.get(i).getKilometraje() + "&c=" + rutas.get(i).getCosto();
                                String url_paramaters_delete = "?empresa=" + rutas.get(i).getID_empresa() + "&salida=" + rutas.get(i).getID_salida() + "&llegada=" + rutas.get(i).getID_llegada() + "&d=" + rutas.get(i).getDuracion();
                                out.print("<td><a class='btn btn-outline-warning' href='editRute.jsp" + url_paramaters_update + "' role='button'><i class='fas fa-pen'></i></a><a class='btn btn-outline-danger' href='deleteRuteResult.jsp"+url_paramaters_delete+"' role='button'><i class='fas fa-minus-circle'></i></a></td>");
                                out.print("</tr>");
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>