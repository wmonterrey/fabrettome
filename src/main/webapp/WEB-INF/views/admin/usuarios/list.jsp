<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<jsp:include page="../../fragments/headTag.jsp" />
<!-- Styles required by this views -->
<spring:url value="/resources/vendors/css/dataTables.bootstrap4.min.css" var="dataTablesCSS" />
<link href="${dataTablesCSS}" rel="stylesheet" type="text/css"/>

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
      <!-- Breadcrumb -->
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a></li>
        <li class="breadcrumb-item active"><spring:message code="users" /></li>
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
      	  <spring:url value="/resources/img/fabrettoapple.png" var="logofab" />
          <div class="card">
            <div class="card-header">
              <img src="${logofab}" alt="<spring:message code="'title'" />" />&nbsp;<i class="fa fa-users"></i>&nbsp;<strong><spring:message code="users.list" /></strong>
              <div class="card-actions">
              </div>
            </div>
            <div class="card-body">
              <spring:url value="/admin/usuarios/nuevoUsuario/"	var="newUser"/>	
              <button id="lista_usuarios_new" onclick="location.href='${fn:escapeXml(newUser)}'" type="button" class="btn btn-outline-primary"><i class="fa fa-user-plus"></i>&nbsp; <spring:message code="add" /></button><br><br>	
              <table id="lista_usuarios" class="table table-striped table-bordered datatable" width="100%">
                <thead>
                	<tr>
	                    <th><spring:message code="username" /></th>
	                    <th class="hidden-xs"><spring:message code="userdesc" /></th>
	                    <th class="hidden-xs"><spring:message code="useremail" /></th>
	                    <th><spring:message code="enabled" /></th>
	                    <th><spring:message code="userlock" /></th>
	                    <th><spring:message code="usercred" /></th>
	                    <th><spring:message code="actions" /></th>
                	</tr>
                </thead>
                <tbody>
                	<c:forEach items="${usuarios}" var="usuario">
                		<tr>
                			<spring:url value="/admin/usuarios/{username}/"
                                        var="usuarioUrl">
                                <spring:param name="username" value="${usuario.nombreUsuario}" />
                            </spring:url>
                            <spring:url value="/admin/usuarios/editUsuario/{username}/"
                                        var="editUrl">
                                <spring:param name="username" value="${usuario.nombreUsuario}" />
                            </spring:url>
                            <spring:url value="/admin/usuarios/habdes/disable1/{username}/"
                                        var="disableUrl">
                                <spring:param name="username" value="${usuario.nombreUsuario}" />
                            </spring:url>
                            <spring:url value="/admin/usuarios/habdes/enable1/{username}/"
                                        var="enableUrl">
                                <spring:param name="username" value="${usuario.nombreUsuario}" />
                            </spring:url>
                            <spring:url value="/admin/usuarios/chgpass/{username}/"
                                        var="chgpassUrl">
                                <spring:param name="username" value="${usuario.nombreUsuario}" />
                            </spring:url>
                            <td><a href="${fn:escapeXml(usuarioUrl)}"><c:out value="${usuario.nombreUsuario}" /></a></td>
                            <td class="hidden-xs"><c:out value="${usuario.nombreCompleto}" /></td>
                            <td class="hidden-xs"><c:out value="${usuario.correoElectronico}" /></td>
                            <c:choose>
                                <c:when test="${usuario.habilitado}">
                                    <td><span class="badge badge-success"><spring:message code="CAT_SINO_SI" /></span></td>
                                </c:when>
                                <c:otherwise>
                                    <td><span class="badge badge-danger"><spring:message code="CAT_SINO_NO" /></span></td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${usuario.cuentaSinBloquear}">
                                    <td><span class="badge badge-success"><spring:message code="CAT_SINO_NO" /></span></td>
                                </c:when>
                                <c:otherwise>
                                    <td><span class="badge badge-danger"><spring:message code="CAT_SINO_SI" /></span></td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${usuario.credencialSinExpirar}">
                                    <td><span class="badge badge-success"><spring:message code="CAT_SINO_NO" /></span></td>
                                </c:when>
                                <c:otherwise>
                                    <td><span class="badge badge-danger"><spring:message code="CAT_SINO_SI" /></span></td>
                                </c:otherwise>
                            </c:choose>
                            <td>
                                <a href="${fn:escapeXml(usuarioUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-search"></i></a>
                                <a href="${fn:escapeXml(editUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a>
                                <a href="${fn:escapeXml(chgpassUrl)}" class="btn btn-outline-primary btn-sm"><i class="fa fa-key"></i></a>
                                <c:choose>
									<c:when test="${usuario.habilitado}">
										<a class="btn btn-outline-primary btn-sm desact" data-toggle="modal" data-whatever="${fn:escapeXml(disableUrl)}"><i class="fa fa-trash-o"></i></a>
									</c:when>
									<c:otherwise>
										<a class="btn btn-outline-primary btn-sm act" data-toggle="modal" data-whatever="${fn:escapeXml(enableUrl)}"><i class="fa fa-check"></i></a>
									</c:otherwise>
								</c:choose>
                            </td>
                		</tr>
                	</c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        </div>
      <!-- /.container-fluid -->
      <!-- Modal -->
  	  <div class="modal fade" id="basic" tabindex="-1" data-role="basic" data-backdrop="static" data-aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<div id="titulo"></div>
				</div>
				<div class="modal-body">
					<input type="hidden" id="accionUrl"/>
					<div id="cuerpo">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
					<button type="button" class="btn btn-info" onclick="ejecutarAccion()"><spring:message code="ok" /></button>
				</div>
			</div>
			<!-- /.modal-content -->
	    </div>
	  <!-- /.modal-dialog -->
  	  </div>
    </main>
    
  </div>
  <!-- Pie de página -->
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
  <spring:url value="/resources/vendors/js/i18n/datatables/label_{language}.json" var="dataTablesLang">
  	<spring:param name="language" value="${lenguaje}" />
  </spring:url>
  
  <!-- Plugins and scripts required by this views -->
  <spring:url value="/resources/vendors/js/jquery.dataTables.min.js" var="dataTablesSc" />
  <script src="${dataTablesSc}" type="text/javascript"></script>
  <spring:url value="/resources/vendors/js/dataTables.bootstrap4.min.js" var="dataTablesBsSc" />
  <script src="${dataTablesBsSc}" type="text/javascript"></script>
  <c:set var="userEnabledLabel"><spring:message code="login.userEnabled" /></c:set>
  <c:set var="userDisabledLabel"><spring:message code="login.userDisabled" /></c:set>
  <c:set var="habilitar"><spring:message code="enable" /></c:set>
  <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
  <c:set var="confirmar"><spring:message code="confirm" /></c:set>
  <!-- Custom scripts required by this view -->
  <script>
  	$(function(){
	  $('.datatable').DataTable({
          "oLanguage": {
              "sUrl": "${dataTablesLang}"
          },
          "scrollX": true
      });
	  $('.datatable').attr('style', 'border-collapse: collapse !important');
	});
	
	$(".act").click(function(){ 
		var titHab = $(this).data('whatever').substr(0,$(this).data('whatever').length-1);
		$('#accionUrl').val($(this).data('whatever'));
    	$('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
    	$('#cuerpo').html('<h3>'+"${habilitar}"+' '+ titHab.substr(titHab.lastIndexOf("/")+1) +'?</h3>');
    	$('#basic').modal('show');
    });
    
    $(".desact").click(function(){ 
        var titDes = $(this).data('whatever').substr(0,$(this).data('whatever').length-1);
    	$('#accionUrl').val($(this).data('whatever'));
    	$('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
    	$('#cuerpo').html('<h3>'+"${deshabilitar}"+ ' ' + titDes.substr(titDes.lastIndexOf("/")+1) +'?</h3>');
    	$('#basic').modal('show');
    });

    function ejecutarAccion() {
		window.location.href = $('#accionUrl').val();		
	}
    
  	if ("${usuarioHabilitado}"){
		toastr.info("${userEnabledLabel}", "${nombreUsuario}", {
		    closeButton: true,
		    progressBar: true,
		  } );
	}
	if ("${usuarioDeshabilitado}"){
		toastr.error("${userDisabledLabel}", "${nombreUsuario}" , {
		    closeButton: true,
		    progressBar: true,
		  });
	}
  </script>
</body>
</html>