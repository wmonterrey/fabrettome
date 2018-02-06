<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="sidebar">
    <nav class="sidebar-nav">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/" htmlEscape="true "/>"><i class="icon-graph"></i><spring:message code="dashboard" /></a>
            </li>
            <sec:authorize url="/perfil/">
            <li class="nav-item nav-dropdown perfiles">
	            <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-people"></i><spring:message code="profiles" /></a>
	            <ul class="nav-dropdown-items">
	            	<li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/perfil/estudiantes/" htmlEscape="true "/>"><i class="icon-notebook"></i><spring:message code="profiles.est" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/perfil/docentes/" htmlEscape="true "/>"><i class="icon-eyeglass"></i><spring:message code="profiles.doc" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/perfil/colaboradores/" htmlEscape="true "/>"><i class="icon-screen-desktop"></i><spring:message code="profiles.col" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/perfil/personas/" htmlEscape="true "/>"><i class="icon-user-follow"></i><spring:message code="profiles.per" /></a>
	                </li>
	            </ul>
	        </li>
	        </sec:authorize>
	        <sec:authorize url="/academico/">
            <li class="nav-item nav-dropdown academico">
	            <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-graduation"></i><spring:message code="academics" /></a>
	            <ul class="nav-dropdown-items">
	            	<li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/academico/matricula/" htmlEscape="true "/>"><i class="icon-list"></i><spring:message code="academics.mat" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/academico/diagnosticos/" htmlEscape="true "/>"><i class="icon-question"></i><spring:message code="academics.diag" /></a>
	                </li>
	            </ul>
	        </li>
	        </sec:authorize>
	        <sec:authorize url="/super/">
            <li class="nav-item nav-dropdown catalogos">
	            <a class="nav-link nav-dropdown-toggle" href="#"><i class="fa fa-cogs"></i><spring:message code="catalogs" /></a>
	            <ul class="nav-dropdown-items">
	            	<li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/super/departamentos/" htmlEscape="true "/>"><i class="fa fa-map-o"></i><spring:message code="catalogs.dep" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/super/municipios/" htmlEscape="true "/>"><i class="fa fa-map-marker"></i><spring:message code="catalogs.mun" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/super/comunidades/" htmlEscape="true "/>"><i class="fa fa-map-pin"></i><spring:message code="catalogs.com" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/super/centros/" htmlEscape="true "/>"><i class="fa fa-building"></i><spring:message code="catalogs.cen" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/super/escuelas/" htmlEscape="true "/>"><i class="fa fa-university"></i><spring:message code="catalogs.esc" /></a>
	                </li>
	            </ul>
	        </li>
	        </sec:authorize>
	        <sec:authorize url="/admin/">
            <li class="nav-item nav-dropdown administracion">
	            <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-settings"></i><spring:message code="admin" /></a>
	            <ul class="nav-dropdown-items">
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/admin/usuarios/" htmlEscape="true "/>"><i class="fa fa-users"></i><spring:message code="users" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/admin/traduccion/" htmlEscape="true "/>"><i class="fa fa-flag"></i><spring:message code="translation" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/admin/catalogos/" htmlEscape="true "/>"><i class="fa fa-archive"></i><spring:message code="seccatalogs" /></a>
	                </li>
	            </ul>
	        </li>
	        </sec:authorize>
	        <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/logout" htmlEscape="true" />"><i class="icon-logout"></i><spring:message code="logout" /></a>
            </li>
        </ul>
    </nav>
    <button class="sidebar-minimizer brand-minimizer" type="button"></button>
</div>