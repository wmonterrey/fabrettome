<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="sidebar">
    <nav class="sidebar-nav">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/" htmlEscape="true "/>"><i class="icon-graph"></i><spring:message code="dashboard" /></a>
            </li>
            <sec:authorize url="/admin/">
            <li class="nav-item nav-dropdown administracion">
	            <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-settings"></i><spring:message code="admin" /></a>
	            <ul class="nav-dropdown-items">
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/admin/usuarios/" htmlEscape="true "/>"><i class="fa fa-users"></i><spring:message code="users" /></a>
	                </li>
	            </ul>
	        </li>
	        </sec:authorize>
	        <sec:authorize url="/admin/">
            <li class="nav-item nav-dropdown catalogos">
	            <a class="nav-link nav-dropdown-toggle" href="#"><i class="fa fa-cogs"></i><spring:message code="catalogs" /></a>
	            <ul class="nav-dropdown-items">
	            	<li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/admin/departamentos/" htmlEscape="true "/>"><i class="fa fa-map-o"></i><spring:message code="catalogs.dep" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/admin/municipios/" htmlEscape="true "/>"><i class="fa fa-map-marker"></i><spring:message code="catalogs.mun" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/admin/comunidades/" htmlEscape="true "/>"><i class="fa fa-map-pin"></i><spring:message code="catalogs.com" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/admin/centros/" htmlEscape="true "/>"><i class="fa fa-building"></i><spring:message code="catalogs.cen" /></a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="<spring:url value="/admin/escuelas/" htmlEscape="true "/>"><i class="fa fa-university"></i><spring:message code="catalogs.esc" /></a>
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