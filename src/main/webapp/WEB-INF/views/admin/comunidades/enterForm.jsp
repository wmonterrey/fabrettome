<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<jsp:include page="../../fragments/headTag.jsp" />
<!-- Styles required by this views -->
<spring:url value="/resources/vendors/css/select2.min.css" var="select2css" />
<link href="${select2css}" rel="stylesheet" type="text/css"/>
</head>
<!-- BODY options, add following classes to body to change options

// Header options
1. '.header-fixed'					- Fixed Header

// Brand options
1. '.brand-minimized'       - Minimized brand (Only symbol)

// Sidebar options
1. '.sidebar-fixed'					- Fixed Sidebar
2. '.sidebar-hidden'				- Hidden Sidebar
3. '.sidebar-off-canvas'		- Off Canvas Sidebar
4. '.sidebar-minimized'			- Minimized Sidebar (Only icons)
5. '.sidebar-compact'			  - Compact Sidebar

// Aside options
1. '.aside-menu-fixed'			- Fixed Aside Menu
2. '.aside-menu-hidden'			- Hidden Aside Menu
3. '.aside-menu-off-canvas'	- Off Canvas Aside Menu

// Breadcrumb options
1. '.breadcrumb-fixed'			- Fixed Breadcrumb

// Footer options
1. '.footer-fixed'					- Fixed footer

-->
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
  <!-- Header -->
  <jsp:include page="../../fragments/bodyHeader.jsp" />
  <div class="app-body">
  	<!-- Navigation -->
  	<jsp:include page="../../fragments/sideBar.jsp" />
    <!-- Main content -->
    <main class="main">
	  <spring:url value="/super/comunidades/guardarComunidad/" var="saveComunidadUrl"></spring:url>
  	  <spring:url value="/super/comunidades/" var="comunidadUrl"/>	
      <!-- Breadcrumb -->
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a></li>
        <li class="breadcrumb-item"><a href="<spring:url value="/super/comunidades/" htmlEscape="true "/>"><spring:message code="comunidades" /></a></li>
        <li class="breadcrumb-item active"><c:out value="${comunidad.idUnico}" /></li>
        <!-- Breadcrumb Menu-->
        <li class="breadcrumb-menu d-md-down-none">
          <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
            <a class="btn" href="#"><i class="icon-speech"></i></a>
            <a class="btn" href="<spring:url value="/" htmlEscape="true "/>"><i class="icon-graph"></i> &nbsp;<spring:message code="dashboard" /></a>
            <a class="btn" href="<spring:url value="/logout" htmlEscape="true" />"><i class="icon-logout"></i> &nbsp;<spring:message code="logout" /></a>
          </div>
        </li>
      </ol>
	  <!-- Container -->
      <div class="container-fluid">

        <div class="animated fadeIn">
          <div class="row">
            <div class="col-md-12">
              <spring:url value="/resources/img/fabrettoapple.png" var="logofab" />
              <div class="card">
                <div class="card-header">
                  <img src="${logofab}" alt="<spring:message code="'title'" />" />&nbsp;<i class="icon-note"></i> <spring:message code="edit" /> <spring:message code="catalogs.com" />
                  <div class="card-actions">
                    
                  </div>
                </div>
                <div class="card-body">

                  <div class="row">

                    <div class="col-md-8">
                      <form action="#" autocomplete="off" id="add-comunidad-form">                      
						<div class="form-group row">
	                      <label class="col-md-3 col-form-label" for="idUnico"><strong><spring:message code="idUnico" /></strong></label>
	                      <div class="col-md-9">
	                      	<div class="input-group">
	                      		<span class="input-group-addon"><i class="fa fa-key"></i></span>
	                        	<input type="text" id="idUnico" readonly name="idUnico" value="${comunidad.idUnico}" class="form-control" placeholder="<spring:message code="idUnico" />">
	                        	<span class="input-group-addon"><i class="fa fa-sort-alpha-asc"></i></span>
	                        </div>
	                        <span class="help-block"></span>
	                      </div>
	                    </div>
	                    <div class="form-group row">
	                      <label class="col-md-3 col-form-label" for="nombreComunidad"><strong><spring:message code="nombreComunidad" /></strong></label>
	                      <div class="col-md-9">
	                      	<div class="input-group">
	                      		<span class="input-group-addon"><i class="fa fa-map-pin"></i></span>
	                        	<input type="text" id="nombreComunidad" name="nombreComunidad" value="${comunidad.nombreComunidad}" class="form-control" placeholder="<spring:message code="nombreComunidad" />">
	                        	<span class="input-group-addon"><i class="fa fa-sort-alpha-asc"></i></span>
	                        </div>
	                        <span class="help-block"></span>
	                      </div>
	                    </div>
	                    <div class="form-group row">
	                      <label class="col-md-3 col-form-label" for="descComunidad"><strong><spring:message code="descComunidad" /></strong></label>
	                      <div class="col-md-9">
	                      	<div class="input-group">
	                      		<span class="input-group-addon"><i class="fa fa-comments-o"></i></span>
	                        	<input type="text" id="descComunidad" name="descComunidad" value="${comunidad.descComunidad}" class="form-control" placeholder="<spring:message code="descComunidad" />">
	                        	<span class="input-group-addon"><i class="fa fa-sort-alpha-asc"></i></span>
	                        </div>
	                        <span class="help-block"></span>
	                      </div>
	                    </div>
	                    <div class="form-group row">
	                      <label class="col-md-3 col-form-label" for="municipio"><strong><spring:message code="class ni.org.fabretto.me.domain.catalogs.Municipio" />&nbsp;</strong><i class="fa fa-hand-o-up"></i></label>
	                      <div class="col-md-9">
	                      	<fieldset class="form-group">
	                        	<select name="municipio" id="municipio" class="form-control select2-single">
			                    	<option value=""><spring:message code="blank"/></option>
			                    	<c:forEach items="${municipios}" var="municipio">
										<c:choose> 
											<c:when test="${municipio.idUnico eq comunidad.municipio.idUnico}">
												<option selected value="${municipio.idUnico}">${municipio.nombreMunicipio}</option>
											</c:when>
											<c:otherwise>
												<option value="${municipio.idUnico}">${municipio.nombreMunicipio}</option>
											</c:otherwise>
										</c:choose> 
									</c:forEach>
			                    </select>
	                        </fieldset>
	                        <span class="help-block"></span>
	                      </div>
	                    </div>
	                    <div class="form-group row">
	                      <label class="col-md-3 col-form-label" for="centro"><strong><spring:message code="centroEscuela" />&nbsp;</strong><i class="fa fa-hand-o-up"></i></label>
	                      <div class="col-md-9">
	                      	<fieldset class="form-group">
	                        	<select name="centro" id="centro" class="form-control select2-single">
			                    	<option value=""><spring:message code="blank"/></option>
			                    	<c:forEach items="${centros}" var="centro">
										<c:choose> 
											<c:when test="${centro.idUnico eq escuela.centro.idUnico}">
												<option selected value="${centro.idUnico}">${centro.nombreCentro}</option>
											</c:when>
											<c:otherwise>
												<option value="${centro.idUnico}">${centro.nombreCentro}</option>
											</c:otherwise>
										</c:choose> 
									</c:forEach>
			                    </select>
	                        </fieldset>
	                        <span class="help-block"></span>
	                      </div>
	                    </div>
                        <div class="form-group">
                          <button type="submit" class="btn btn-primary" id="guardar"><i class="fa fa-save"></i>&nbsp;<spring:message code="save" /></button>
						  <a href="${fn:escapeXml(comunidadUrl)}" class="btn btn-danger"><i class="fa fa-undo"></i>&nbsp;<spring:message code="cancel" /></a>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- /.col -->
          </div>
          <!-- /.row -->
        </div>

      </div>
      <!-- /.conainer-fluid -->
    </main>
    
  </div>
  <!-- Pie de p�gina -->
  <jsp:include page="../../fragments/bodyFooter.jsp" />

  <!-- Bootstrap and necessary plugins -->
  <jsp:include page="../../fragments/corePlugins.jsp" />

  <!-- GenesisUI main scripts -->
  <spring:url value="/resources/js/app.js" var="App" />
  <script src="${App}" type="text/javascript"></script>
  
  <!-- Lenguaje -->
  <c:choose>
	<c:when test="${cookie.eFabrettoLang.value == null}">
		<c:set var="lenguaje" value="es"/>
	</c:when>
	<c:otherwise>
		<c:set var="lenguaje" value="${cookie.eFabrettoLang.value}"/>
	</c:otherwise>
  </c:choose>
  
  <!-- Plugins and scripts required by this views -->
  <spring:url value="/resources/vendors/js/jquery.validate.min.js" var="JQueryValidate" />
  <script src="${JQueryValidate}" type="text/javascript"></script>
  <spring:url value="/resources/vendors/js/i18n/validation/messages_{language}.js" var="jQValidationLoc">
      <spring:param name="language" value="${lenguaje}" />
  </spring:url>
  <script src="${jQValidationLoc}"></script>
  <spring:url value="/resources/vendors/js/select2.min.js" var="Select2" />
  <script src="${Select2}" type="text/javascript"></script>

  <!-- Custom scripts required by this view -->
  <spring:url value="/resources/js/views/Comunidad.js" var="processComunidad" />
  <script src="${processComunidad}"></script>
  
<c:set var="successmessage"><spring:message code="process.success" /></c:set>
<c:set var="errormessage"><spring:message code="process.errors" /></c:set>
<c:set var="waitmessage"><spring:message code="process.wait" /></c:set>

<script>
	jQuery(document).ready(function() {
		var parametros = {saveComunidadUrl: "${saveComunidadUrl}", successmessage: "${successmessage}",
				errormessage: "${errormessage}",waitmessage: "${waitmessage}",
				comunidadUrl: "${comunidadUrl}" 
		};
		ProcessComunidad.init(parametros);
	});
</script>
  
</body>
</html>