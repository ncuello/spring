<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<spring:url value="/" var="urlRoot"></spring:url>
<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
  
  
  	<c:choose>
  		<c:when test="${usuario.hasRole('editor')}">
  			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${urlRoot}">My CineSite | Administración</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="${urlRoot}peliculas/indexPaginate?page=0">Películas</a></li>
			        <li><a href="${urlRoot}horarios/indexPaginate?page=0">Horarios</a></li> 
			        <li><a href="${urlRoot}noticias/index">Noticias</a></li> 
			        <li><a href="${urlRoot}admin/logout">Salir</a></li>  
				</ul>
			</div>
  		</c:when>
  		<c:when test="${usuario.hasRole('gerente')}">
  			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${urlRoot}">My CineSite | Administración</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="${urlRoot}peliculas/indexPaginate?page=0">Películas</a></li>
			        <li><a href="${urlRoot}horarios/indexPaginate?page=0">Horarios</a></li> 
			        <li><a href="${urlRoot}noticias/index">Noticias</a></li> 
			        <li><a href="${urlRoot}banners/index">Banners</a></li>   
			        <li><a href="${urlRoot}admin/logout">Salir</a></li>  
				</ul>
			</div>
  		</c:when>
  	
	  	<c:otherwise>
	  		<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${urlRoot}">My CineSite</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="${urlRoot}about">Acerca</a></li>
					<li><a href="${urlRoot}admin/index">Login</a></li>
				</ul>
			</div>
	  	</c:otherwise>
  	</c:choose>
  </div>
</nav>