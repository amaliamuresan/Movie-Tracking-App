<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Search movies</title>



    <!-- Bootstrap core CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>


  </head>
  <body>

<header>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">MovieApp</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="#">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/movies">Search</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/movies/discover">Discover</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Profile</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/user/listToWatch">To Watch List</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/user/listWatched">Watched List</a>
       </li>
    </ul>
  </div>
</nav>
</header>

<main>


<script>
function removeToWatch(name,el) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      el.innerHTML = "Removed";
    }
  };
  xhttp.open("GET", "/user/removeToWatch/"+name, true);
  xhttp.send();
}
</script>

  <div class="album py-5 bg-light">
    <div class="container">
       <c:if test="${fn:length(discoverMovies)>1}">
<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
       <c:forEach items="${discoverMovies}" var="movie">


                           <div class="col">
                             <div class="card shadow-sm">
                                   <img class="bd-placeholder-img card-img-top" width="100%" height="100%" src="${movie.poster_path}" alt="alternatetext">
                               <div class="card-body">

                                 <p class="card-text">${movie.title}</p>
                                 <div class="d-flex justify-content-between align-items-center">
                                   <div class="btn-group">
                                         <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                         <button type="button" class="btn btn-sm btn-outline-secondary" onclick="removeToWatch('${movie.id}',this)"><c:choose> <c:when test="${user.watched_movies.contains(movie.id)}">Watched</c:when><c:otherwise>Remove</c:otherwise></c:choose></button>
                                   </div>
                                   <small class="text-muted"> </small>
                                 </div>
                               </div>
                             </div>
                           </div>




       </c:forEach>
       </div>



        </c:if>
    </div>
  </div>

</main>




  </body>
</html>
