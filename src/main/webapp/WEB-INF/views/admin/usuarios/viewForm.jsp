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
	  	<c:set var="userEnabledLabel"><spring:message code="login.userEnabled" /></c:set>
		<c:set var="userDisabledLabel"><spring:message code="login.userDisabled" /></c:set>
		<c:set var="rolEnabledLabel"><spring:message code="rolEnabled" /></c:set>
		<c:set var="rolDisabledLabel"><spring:message code="rolDisabled" /></c:set>
		<c:set var="rolAddedLabel"><spring:message code="rolAdded" /></c:set>
		<c:set var="allRolesLabel"><spring:message code="rolAll" /></c:set>
		<c:set var="centroEnabledLabel"><spring:message code="centroEnabled" /></c:set>
		<c:set var="centroDisabledLabel"><spring:message code="centroDisabled" /></c:set>
		<c:set var="centroAddedLabel"><spring:message code="centroAdded" /></c:set>
		<c:set var="allCentrosLabel"><spring:message code="centroAll" /></c:set>
		<c:set var="userLockedLabel"><spring:message code="login.accountLocked" /></c:set>
		<c:set var="userUnlockedLabel"><spring:message code="login.accountNotLocked" /></c:set>
		<c:set var="habilitar"><spring:message code="enable" /></c:set>
		<c:set var="deshabilitar"><spring:message code="disable" /></c:set>
		<c:set var="bloquear"><spring:message code="lock" /></c:set>
		<c:set var="desbloquear"><spring:message code="unlock" /></c:set>
		<c:set var="confirmar"><spring:message code="confirm" /></c:set>
		<spring:url value="/admin/usuarios/editUsuario/{username}/" var="editUrl">
			<spring:param name="username" value="${user.nombreUsuario}" />
		</spring:url>
		<spring:url value="/admin/usuarios/chgpass/{username}/" var="chgpassUrl">
			<spring:param name="username" value="${user.nombreUsuario}" />
		</spring:url>
		<spring:url value="/admin/usuarios/habdes/disable2/{username}/" var="disableUrl">
			<spring:param name="username" value="${user.nombreUsuario}" />
		</spring:url>
		<spring:url value="/admin/usuarios/habdes/enable2/{username}/" var="enableUrl">
			<spring:param name="username" value="${user.nombreUsuario}" />
		</spring:url>
		<spring:url value="/admin/usuarios/lockunl/lock2/{username}/" var="lockUrl">
			<spring:param name="username" value="${user.nombreUsuario}" />
		</spring:url>
		<spring:url value="/admin/usuarios/lockunl/unlock2/{username}/" var="unlockUrl">
			<spring:param name="username" value="${user.nombreUsuario}" />
		</spring:url>	
      <!-- Breadcrumb -->
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a></li>
        <li class="breadcrumb-item"><a href="<spring:url value="/admin/usuarios/" htmlEscape="true "/>"><spring:message code="users" /></a></li>
        <li class="breadcrumb-item active"><c:out value="${user.nombreUsuario}" /></li>
        <!-- Breadcrumb Menu-->
        <li class="breadcrumb-menu d-md-down-none">
          <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
            <a class="btn" href="#"><i class="icon-speech"></i></a>
            <a class="btn" href="<spring:url value="/" htmlEscape="true "/>"><i class="icon-graph"></i>&nbsp;<spring:message code="dashboard" /></a>
            <a class="btn" href="<spring:url value="/logout" htmlEscape="true" />"><i class="icon-logout"></i>&nbsp;<spring:message code="logout" /></a>
          </div>
        </li>
      </ol>
	  <!-- Container -->
      <div class="container-fluid">
      	<div class="animated fadeIn">

          	<div class="row">
	            <div class="col-md-12">
	              <div class="card">
	                <div class="card-header">
	                  <i class="icon-user"></i>&nbsp;<strong><c:out value="${user.nombreUsuario}" /></strong>
	                  <ul class="nav nav-tabs float-right">
						  <li class="nav-item dropdown">
						    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"><i class="icon-settings">&nbsp;<spring:message code="actions" /></i></a>
						    <div class="dropdown-menu">
						    	<a class="dropdown-item" href="${fn:escapeXml(editUrl)}"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
						    	<a class="dropdown-item" href="${fn:escapeXml(chgpassUrl)}"><i class="fa fa-key"></i> <spring:message code="changepass" /></a>
						    	<c:choose>
									<c:when test="${user.habilitado}">
										<a class="dropdown-item desact" data-toggle="modal" data-whatever="${fn:escapeXml(disableUrl)}"><i class="fa fa-trash-o"></i> <spring:message code="disable" /></a>
									</c:when>
									<c:otherwise>
										<a class="dropdown-item act" data-toggle="modal" data-whatever="${fn:escapeXml(enableUrl)}"><i class="fa fa-check"></i> <spring:message code="enable" /></a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${user.cuentaSinBloquear}">
										<a class="dropdown-item lock" data-toggle="modal" data-whatever="${fn:escapeXml(lockUrl)}"><i class="fa fa-lock"></i> <spring:message code="lock" /></a>
									</c:when>
									<c:otherwise>
										<a class="dropdown-item unlock" data-toggle="modal" data-whatever="${fn:escapeXml(unlockUrl)}"><i class="fa fa-unlock"></i> <spring:message code="unlock" /></a>
									</c:otherwise>
								</c:choose>
						    </div>
						  </li>
						</ul>
	                </div>
	                <div class="card-body">
	                	<div class="row">
							<div class="col-md-3">
								<div>
									<label class="control-label"><spring:message code="userdesc" />:</label>
									<div>
										<p class="form-control-static">
											 <strong><c:out value="${user.nombreCompleto}" /></strong>
										</p>
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-3">
								<div>
									<label class="control-label"><spring:message code="useremail" />:</label>
									<div>
										<p class="form-control-static">
											 <strong><c:out value="${user.correoElectronico}" /></strong>
										</p>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label"><spring:message code="enabled" />:</label>
									<div>
										<p class="form-control-static">
											<c:choose>
												<c:when test="${user.habilitado}">
													<strong><spring:message code="CAT_SINO_SI" /></strong>
												</c:when>
												<c:otherwise>
													<strong><spring:message code="CAT_SINO_NO" /></strong>
												</c:otherwise>
											</c:choose>
										</p>
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label"><spring:message code="locked" />:</label>
									<div>
										<p class="form-control-static">
											<c:choose>
												<c:when test="${user.cuentaSinBloquear}">
													<strong><spring:message code="CAT_SINO_NO" /></strong>
												</c:when>
												<c:otherwise>
													<strong><spring:message code="CAT_SINO_SI" /></strong>
												</c:otherwise>
											</c:choose>
										</p>
									</div>
								</div>
							</div>
							<!--/span-->
						</div>
						<!--/row-->
						<div class="row">
							<!--/span-->
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label"><spring:message code="usercred" />:</label>
									<div>
										<p class="form-control-static">
											<c:choose>
												<c:when test="${user.credencialSinExpirar}">
													<strong><spring:message code="CAT_SINO_NO" /></strong>
												</c:when>
												<c:otherwise>
													<strong><spring:message code="CAT_SINO_SI" /></strong>
												</c:otherwise>
											</c:choose>
										</p>
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label"><spring:message code="userexp" />:</label>
									<div>
										<p class="form-control-static">
											<c:choose>
												<c:when test="${user.cuentaSinExpirar}">
													<strong><spring:message code="CAT_SINO_NO" /></strong>
												</c:when>
												<c:otherwise>
													<strong><spring:message code="CAT_SINO_SI" /></strong>
												</c:otherwise>
											</c:choose>
										</p>
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label"><spring:message code="createdBy" />:</label>
									<div>
										<p class="form-control-static">
											 <strong><c:out value="${user.usuarioRegistro}" /></strong>
										</p>
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label"><spring:message code="dateCreated" />:</label>
									<div>
										<p class="form-control-static">
											<strong><c:out value="${user.fechaCreacion}" /></strong>
										</p>
									</div>
								</div>
							</div>
							<!--/span-->
						</div>
						<!--/row-->
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label"><spring:message code="modifiedBy" />:</label>
									<div>
										<p class="form-control-static">
											 <strong><c:out value="${user.usuarioModifica}" /></strong>
										</p>
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label"><spring:message code="dateModified" />:</label>
									<div>
										<p class="form-control-static">
											<strong><c:out value="${user.fechaUltimaModificacion}" /></strong>
										</p>
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label"><spring:message code="lastAccess" />:</label>
									<div>
										<p class="form-control-static">
											 <strong><c:out value="${user.fechaUltimoAcceso}" /></strong>
										</p>
									</div>
								</div>
							</div>
							<!--/span-->
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label"><spring:message code="dateCredentials" />:</label>
									<div>
										<p class="form-control-static">
											<strong><c:out value="${user.ultimoCambioCredencial}" /></strong>
										</p>
									</div>
								</div>
							</div>
							<!--/span-->
						</div>
	                </div>
	                <div class="card-header">
	                	<div class="row float-right mr-4" >
			            	<a class="btn btn-primary" href="${fn:escapeXml(editUrl)}"><i class="fa fa-edit"></i> <spring:message code="edit" /></a>
			            </div>
	                </div>
	              </div>
	            </div> 
	            <!--/.col-->
         	</div>
         	<div class="row">
	            <div class="col-md-12">
	              <div class="card">
	                <div class="card-header">
	                  <i class="icon-check"></i>&nbsp;<strong><spring:message code="userroles" /></strong>
	                </div>
	                <div class="card-body">
	                	<table id="lista_roles" class="table table-striped table-bordered datatable" width="100%">
			                <thead>
			                	<tr>
				                    <th><spring:message code="userroles" /></th>
									<th><spring:message code="enabled" /></th>
									<th><spring:message code="addedBy" /></th>
									<th><spring:message code="dateAdded" /></th>
									<th><spring:message code="actions" /></th>
			                	</tr>
			                </thead>
			                <tbody>
			                	<c:forEach items="${rolesusuario}" var="rol">
								<tr>
									<spring:url value="/admin/usuarios/disableRol/{username}/{rol}/" var="disableRolUrl">
		                               <spring:param name="username" value="${rol.rolUsuarioId.nombreUsuario}" />
		                               <spring:param name="rol" value="${rol.rolUsuarioId.nombreRol}" />
		                            </spring:url>
		                            <spring:url value="/admin/usuarios/enableRol/{username}/{rol}/" var="enableRolUrl">
		                               <spring:param name="username" value="${rol.rolUsuarioId.nombreUsuario}" />
		                               <spring:param name="rol" value="${rol.rolUsuarioId.nombreRol}" />
		                            </spring:url>
									<td><spring:message code="${rol.rol.nombreRol}" /></td>
									<c:choose>
										<c:when test="${rol.pasivo=='0'.charAt(0)}">
											<td><span class="badge badge-success"><spring:message code="CAT_SINO_SI" /></span></td>
										</c:when>
										<c:otherwise>
											<td><span class="badge badge-danger"><spring:message code="CAT_SINO_NO" /></span></td>
										</c:otherwise>
									</c:choose>
									<td><c:out value="${rol.usuarioRegistro}" /></td>
									<td><c:out value="${rol.fechaRegistro}" /></td>
									<c:choose>
										<c:when test="${rol.pasivo=='0'.charAt(0)}">
											<td><a data-toggle="modal" data-whatever="${fn:escapeXml(disableRolUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-trash-o"></i></a></td>
										</c:when>
										<c:otherwise>
											<td><a data-toggle="modal" data-whatever="${fn:escapeXml(enableRolUrl)}" class="btn btn-outline-primary btn-sm act"><i class="fa fa-check"></i></a></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
			                </tbody>
			            </table>
	                </div>
	                <div class="card-header">
	                    <div class="row float-right mr-4" >
			            	<spring:url value="/admin/usuarios/addRol/{username}/" var="addRolUrl"><spring:param name="username" value="${user.nombreUsuario}" /></spring:url>
			            	<button type="button" class="btn btn-primary" id="addRol" data-toggle="modal" data-whatever="${fn:escapeXml(addRolUrl)}"><i class="fa fa-plus"></i>&nbsp;<spring:message code="add" /></button>
			            </div>
	                </div>
	              </div>
	            </div>
            </div>
            <div class="row">
	            <div class="col-md-12">
	              <div class="card">
	                <div class="card-header">
	                  <i class="fa fa-building"></i>&nbsp;<strong><spring:message code="catalogs.cen" /></strong>
	                </div>
	                <div class="card-body">
	                	<table id="lista_centros" class="table table-striped table-bordered datatable" width="100%">
			                <thead>
			                	<tr>
				                    <th><spring:message code="catalogs.cen" /></th>
									<th><spring:message code="enabled" /></th>
									<th><spring:message code="addedBy" /></th>
									<th><spring:message code="dateAdded" /></th>
									<th><spring:message code="actions" /></th>
			                	</tr>
			                </thead>
			                <tbody>
			                	<c:forEach items="${centrosusuario}" var="centrousuario">
								<tr>
									<spring:url value="/admin/usuarios/disableCentro/{username}/{centro}/" var="disableCentroUrl">
		                               <spring:param name="username" value="${centrousuario.usuarioCentroId.usuario}" />
		                               <spring:param name="centro" value="${centrousuario.usuarioCentroId.centro}" />
		                            </spring:url>
		                            <spring:url value="/admin/usuarios/enableCentro/{username}/{centro}/" var="enableCentroUrl">
		                               <spring:param name="username" value="${centrousuario.usuarioCentroId.usuario}" />
		                               <spring:param name="centro" value="${centrousuario.usuarioCentroId.centro}" />
		                            </spring:url>
									<td><c:out value="${centrousuario.centro.nombreCentro}" /> - <c:out value="${centrousuario.centro.nombreCentro}" /></td>
									<c:choose>
										<c:when test="${centrousuario.pasivo=='0'.charAt(0)}">
											<td><span class="badge badge-success"><spring:message code="CAT_SINO_SI" /></span></td>
										</c:when>
										<c:otherwise>
											<td><span class="badge badge-danger"><spring:message code="CAT_SINO_NO" /></span></td>
										</c:otherwise>
									</c:choose>
									<td><c:out value="${centrousuario.usuarioRegistro}" /></td>
									<td><c:out value="${centrousuario.fechaRegistro}" /></td>
									<c:choose>
										<c:when test="${centrousuario.pasivo=='0'.charAt(0)}">
											<td><a data-toggle="modal" data-whatever="${fn:escapeXml(disableCentroUrl)}" class="btn btn-outline-primary btn-sm desact"><i class="fa fa-trash-o"></i></a></td>
										</c:when>
										<c:otherwise>
											<td><a data-toggle="modal" data-whatever="${fn:escapeXml(enableCentroUrl)}" class="btn btn-outline-primary btn-sm act"><i class="fa fa-check"></i></a></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
			                </tbody>
			            </table>
	                </div>
	                <div class="card-header">
	                	<div class="row float-right mr-4" >
			            	<spring:url value="/admin/usuarios/addCentro/{username}/" var="addCentroUrl"><spring:param name="username" value="${user.nombreUsuario}" /></spring:url>
			            	<button type="button" class="btn btn-primary" id="addCentro" data-toggle="modal" data-whatever="${fn:escapeXml(addCentroUrl)}"><i class="fa fa-plus"></i>&nbsp;<spring:message code="add" /></button>
			            </div>
	                </div>
	              </div>
	            </div>
            </div>
            <div class="row">
	            <div class="col-md-12">
	              <div class="card">
	                <div class="card-header">
	                  <i class="icon-key"></i>&nbsp;<strong><spring:message code="access" /></strong>
	                </div>
	                <div class="card-body">
	                	<table id="lista_accesos" class="table table-striped table-bordered datatable" width="100%">
			                <thead>
			                	<tr>
				                    <th class="hidden-xs"><spring:message code="session" /></th>
									<th class="hidden-xs"><spring:message code="ipaddress" /></th>
									<th><spring:message code="logindate" /></th>
									<th><spring:message code="logoutdate" /></th>
									<th class="hidden-xs"><spring:message code="logouturl" /></th>
			                	</tr>
			                </thead>
			                <tbody>
			                <c:forEach items="${accesses}" var="acceso">
								<tr>
									<td class="hidden-xs"><c:out value="${acceso.idSesion}" /></td>
									<td class="hidden-xs"><c:out value="${acceso.direccionRemota}" /></td>
									<td><c:out value="${acceso.fechaIngreso}" /></td>
									<td><c:out value="${acceso.fechaSalida}" /></td>
									<td class="hidden-xs"><c:out value="${acceso.urlSalida}" /></td>
								</tr>
							</c:forEach>
			                </tbody>
			            </table>
	                </div>
	              </div>
	            </div>
            </div>
            <div class="row">
	            <div class="col-md-12">
	              <div class="card">
	                <div class="card-header">
	                  <i class="icon-pencil"></i>&nbsp;<strong><spring:message code="audittrail" /></strong>
	                </div>
	                <div class="card-body">
	                	<table id="lista_cambios" class="table table-striped table-bordered datatable" width="100%">
			                <thead>
			                	<tr>
									<th><spring:message code="entityClass" /></th>
									<th><spring:message code="entityName" /></th>
									<th><spring:message code="entityProperty" /></th>
									<th><spring:message code="entityPropertyOldValue" /></th>
									<th><spring:message code="entityPropertyNewValue" /></th>
									<th><spring:message code="modifiedBy" /></th>
									<th><spring:message code="dateModified" /></th>
			                	</tr>
			                </thead>
			                <tbody>
							<c:forEach items="${bitacora}" var="cambio">
								<tr>
									<td><spring:message code="${cambio.entityClass}" /></td>
									<td><c:out value="${cambio.entityName}" /></td>
									<td><c:out value="${cambio.entityProperty}" /></td>
									<td><c:out value="${cambio.entityPropertyOldValue}" /></td>
									<td><c:out value="${cambio.entityPropertyNewValue}" /></td>
									<td><c:out value="${cambio.username}" /></td>
									<td><c:out value="${cambio.operationDate}" /></td>
								</tr>
							</c:forEach>
			                </tbody>
			            </table>
	                </div>
	              </div>
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
  	  <!-- Modal -->
  	  <div class="modal fade" id="rolesForm" data-role="rolesForm" data-backdrop="static" data-aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title"><spring:message code="add" /> <spring:message code="userroles" /></h2>
				</div>
				<div class="modal-body">
					<input type="hidden" id="inputAddRolUrl"/>
					<div id="cuerpo">
						<fieldset class="form-group">
		                 	<i class="fa fa-check"></i>
		                    <label><spring:message code="userroles" /></label>
		                    <select id="roles" name="roles" class="form-control select2-single">
		                      <c:forEach items="${roles}" var="rol">
		                      	<option value="${rol.nombreRol}"><spring:message code="${rol.nombreRol}" /></option>
		                      </c:forEach>
		                    </select>
		                 </fieldset>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
					<button type="button" id="buttonAgregarRol" class="btn btn-info" onclick="ejecutarAgregarRol()"><spring:message code="ok" /></button>
				</div>
			</div>
			<!-- /.modal-content -->
	    </div>
	  <!-- /.modal-dialog -->
  	  </div>
  	  <!-- Modal -->
  	  <div class="modal fade" id="centrosForm" tabindex="-1" data-role="centrosForm" data-backdrop="static" data-aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title"><spring:message code="add" /> <spring:message code="catalogs.cen" /></h2>
				</div>
				<div class="modal-body">
					<input type="hidden" id="inputAddCentroUrl"/>
					<div id="cuerpo">
		                 	<i class="fa fa-map-o"></i>
		                    <label><spring:message code="catalogs.cen" /></label>
		                    <select id="centros" name="centros" class="form-control select2-single">
		                      <c:forEach items="${centros}" var="centro">
		                      	<option value="${centro.idUnico}">${centro.codigo}-${centro.nombreCentro}</option>
		                      </c:forEach>
		                    </select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
					<button type="button" id="buttonAgregarCentro" class="btn btn-info" onclick="ejecutarAgregarCentro()"><spring:message code="ok" /></button>
				</div>
			</div>
			<!-- /.modal-content -->
	    </div>
	  <!-- /.modal-dialog -->
  	  </div>
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
  <spring:url value="/resources/vendors/js/i18n/datatables/label_{language}.json" var="dataTablesLang">
  	<spring:param name="language" value="${lenguaje}" />
  </spring:url>
  <spring:url value="/resources/vendors/js/jquery.dataTables.min.js" var="dataTablesSc" />
  <script src="${dataTablesSc}" type="text/javascript"></script>
  <spring:url value="/resources/vendors/js/dataTables.bootstrap4.min.js" var="dataTablesBsSc" />
  <script src="${dataTablesBsSc}" type="text/javascript"></script>
  <spring:url value="/resources/vendors/js/select2.min.js" var="Select2" />
  <script src="${Select2}" type="text/javascript"></script>
  <c:set var="successmessage"><spring:message code="process.success" /></c:set>
  <c:set var="errormessage"><spring:message code="process.errors" /></c:set>
  <c:set var="waitmessage"><spring:message code="process.wait" /></c:set>
  <script>
  
    $(function(){
	  $('.datatable').DataTable({
          "oLanguage": {
              "sUrl": "${dataTablesLang}"
          },
          "scrollX": true,
          "lengthMenu": [[5,10, 25, 50], [5,10, 25, 50]]
      });
	  $('.datatable').attr('style', 'border-collapse: collapse !important');
	});

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
	if ("${usuarioBloqueado}"){
		toastr.error("${userLockedLabel}", "${nombreUsuario}", {
		    closeButton: true,
		    progressBar: true,
		  } );
	}
	if ("${usuarioNoBloqueado}"){
		toastr.info("${userUnlockedLabel}", "${nombreUsuario}" , {
		    closeButton: true,
		    progressBar: true,
		  });
	}

	if ("${rolHabilitado}"){
		toastr.info("${rolEnabledLabel}", "${nombreRol}", {
		    closeButton: true,
		    progressBar: true,
		  } );
	}
	if ("${rolDeshabilitado}"){
		toastr.error("${rolDisabledLabel}", "${nombreRol}" , {
		    closeButton: true,
		    progressBar: true,
		  });
	}
	if ("${rolAgregado}"){
		toastr.info("${rolAddedLabel}", "${nombreRol}" , {
		    closeButton: true,
		    progressBar: true,
		  });
	}

	if ("${centroHabilitado}"){
		toastr.info("${centroEnabledLabel}", "${nombreCentro}", {
		    closeButton: true,
		    progressBar: true,
		  } );
	}
	if ("${centroDeshabilitado}"){
		toastr.error("${centroDisabledLabel}", "${nombreCentro}" , {
		    closeButton: true,
		    progressBar: true,
		  });
	}
	if ("${centroAgregado}"){
		toastr.info("${centroAddedLabel}", "${nombreCentro}" , {
		    closeButton: true,
		    progressBar: true,
		  });
	}
	
  
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
    
    $(".lock").click(function(){ 
    	var titLock = $(this).data('whatever').substr(0,$(this).data('whatever').length-1);
    	$('#accionUrl').val($(this).data('whatever'));
    	$('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
    	$('#cuerpo').html('<h3>'+"${bloquear}"+' '+ titLock.substr(titLock.lastIndexOf("/")+1) +'?</h3>');
    	$('#basic').modal('show');
    });
    
    $(".unlock").click(function(){ 
    	var titUnLock = $(this).data('whatever').substr(0,$(this).data('whatever').length-1);
    	$('#accionUrl').val($(this).data('whatever'));
    	$('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
    	$('#cuerpo').html('<h3>'+"${desbloquear}"+' '+ titUnLock.substr(titUnLock.lastIndexOf("/")+1) +'?</h3>');
    	$('#basic').modal('show');
    });
 
    function ejecutarAccion() {
		window.location.href = $('#accionUrl').val();		
	}

    $("#addRol").click(function(){ 
		$('#inputAddRolUrl').val($(this).data('whatever'));
		if($('#roles').val()) {
			$('#rolesForm').modal('show');
		}
		else{
			toastr.info("${allRolesLabel}", "" ,{
			    closeButton: true,
			    progressBar: true,
			  } );
		}
    });

    $('#rolesForm').on('shown.bs.modal', function () {
    	$('#roles').select2({
        	dropdownParent: $("#rolesForm"),
        	theme: "bootstrap"
    	});
    })

    function ejecutarAgregarRol() {
		window.location.href = $('#inputAddRolUrl').val()+$('#roles').val()+'/';		
	}

    $("#addCentro").click(function(){ 
		$('#inputAddCentroUrl').val($(this).data('whatever'));
		if($('#centros').val()) {
			$('#centrosForm').modal('show');
		}
		else{
			toastr.info("${allCentrosLabel}", "" ,{
			    closeButton: true,
			    progressBar: true,
			  } );
		}
    });

    $('#centrosForm').on('shown.bs.modal', function () {
        $('#centros').select2({
        	dropdownParent: $("#centrosForm"),
        	theme: "bootstrap"
    	});
    })

    function ejecutarAgregarCentro() {
		window.location.href = $('#inputAddCentroUrl').val()+$('#centros').val()+'/';		
	}
	
  </script>
</body>
</html>