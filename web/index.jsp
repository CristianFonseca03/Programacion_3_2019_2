<%@page import="Model.Lugar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Logic.Logic"%>
<%
    Logic lg = new Logic();
    ArrayList<Lugar> Lugares = lg.getLugares();
%>

<!doctype html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/60a79f829f.js" crossorigin="anonymous"></script>
        <title>Terminal de sogamoso - Buscar ruta</title>
    </head>
    <script>
        function validateForm() {
            var salida = document.forms["form"]["salida"].value;
            var llegada = document.forms["form"]["llegada"].value;
            if (salida === llegada) {
                alert("El destino de salida no puede ser igual al de llegada");
                return false;
            } else {
                return true;
            }
        }
    </script>

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
                            <a class="btn btn-primary" href="empresas.jsp">Empresas <i class="fas fa-bus-alt"></i></a>
                        </li>
                        <li class="nav-item ml-2">
                            <a class="btn btn-primary" href="lugares.jsp">Lugares <i class="fas fa-map"></i></a>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="form-group mt-5">
                <form name="form" action="results.jsp" class="border p-3 form" method="GET" onsubmit="return validateForm()">
                    <div class="form-group">
                        <label for="salida">Salida</label>
                        <select class="custom-select" name="salida">
                            <%
                                for (int i = 0; i < Lugares.size(); i++) {
                                    String lugar = Lugares.get(i).getNombre();
                                    int id = Lugares.get(i).getID();
                            %>
                            <option value="<%= id%>"><%= lugar%></option>
                            <% }%>
                        </select>
                        <label for="llegada">llegada</label>
                        <select class="custom-select" name="llegada">
                            <%
                                for (int i = 0; i < Lugares.size(); i++) {
                                    String lugar = Lugares.get(i).getNombre();
                                    int id = Lugares.get(i).getID();
                            %>
                            <option value="<%= id%>"><%= lugar%></option>
                            <% }%>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary" id="load_button">Enviar</button>
                </form>
            </div>

        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
                integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous">
        </script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
        </script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
                integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
        </script>
    </body>

</html>