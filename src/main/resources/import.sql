INSERT INTO catRol (nombreRol) VALUES ('ROLE_PERFIL');
INSERT INTO catRol (nombreRol) VALUES ('ROLE_SUPER');
INSERT INTO catRol (nombreRol) VALUES ('ROLE_ADMIN');
INSERT INTO catUsuario (nombreUsuario, cuentaSinExpirar, cuentaSinBloquear, nombreCompleto, fechaCreacion, usuarioRegistro, credencialSinExpirar, correoElectronico, habilitado, fechaUltimoAcceso, ultimoCambioCredencial, fechaUltimaModificacion, usuarioModifica, contrasena) VALUES ('admin', '', '', 'Administrador', '2017-12-14 08:31:00', 'admin', '', 'fabrettome@fabretto.org.ni', '', '2017-12-14 10:05:40', NULL, '2017-12-14 17:58:06', 'admin', '6c36dc262b0e44be5811c2296669fc65643aec9dcaa4a76501e0a9508b633fd01ee59a207f8c6d68');
INSERT INTO tblusuariorol (nombreRol, nombreUsuario,  fechaRegistro, idEquipo, pasivo, usuarioRegistro) VALUES ('ROLE_ADMIN', 'admin', '2017-12-14 10:56:43', 'localhost', '0', 'admin');
INSERT INTO tblusuariorol (nombreRol, nombreUsuario, fechaRegistro, idEquipo, pasivo, usuarioRegistro) VALUES ('ROLE_SUPER', 'admin', '2017-12-14 10:56:43', 'localhost', '0', 'admin');
INSERT INTO tblusuariorol (nombreRol, nombreUsuario, fechaRegistro, idEquipo, pasivo, usuarioRegistro) VALUES ('ROLE_PERFIL', 'admin', '2017-12-14 10:56:43', 'localhost', '0', 'admin');


/*Plantilla*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'heading', 'Sistema de Monitoreo y Evaluación','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'title', 'Fabretto M&E','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'footer', '2018 Asociación Familia Padre Fabretto','0','0',0);

/*login page*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'login', 'Ingresar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'login.accountExpired', 'Cuenta de usuario ha expirado!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'login.accountLocked', 'Cuenta de usuario está bloqueada!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'login.accountNotLocked', 'Cuenta de usuario está desbloqueada!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'login.badCredentials', 'Nombre de usuario o contraseña incorrectos!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'login.credentialsExpired', 'Credenciales de usuario han expirado!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'login.maxSessionsOut', 'Tiene una sesión activa! No puede crear otra!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'login.message', 'Por favor ingresar su nombre de usuario y contraseña','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'login.username', 'Nombre de usuario','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'login.password', 'Contraseña','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'login.forgot.password', 'Olvidó contraseña?','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'login.userEnabled', 'Usuario esta activo!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'login.userDisabled', 'Usuario esta inactivo!','0','0',0);


/*Menu*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'home', 'Inicio','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'dashboard', 'Panel de control','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'admin', 'Administración','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'users', 'Usuarios','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'catalogs', 'Catálogos','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'catalogs.dep', 'Departamentos','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'catalogs.mun', 'Municipios','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'catalogs.com', 'Comunidades','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'catalogs.cen', 'Centros','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'catalogs.esc', 'Escuelas','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'logout', 'Salir','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'language', 'Lenguaje','0','0',0);

/*Usuarios*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'username', 'Usuario','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'userdesc', 'Descripción','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'useremail', 'Correo','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'userlock', 'Bloqueado','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'usercred', 'Contraseña vencida','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'userexp', 'Cuenta vencida','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'userroles', 'Roles','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'user.updated', 'Usuario actualizado','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'user.created', 'Usuario creado','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'profile', 'Perfil','0','0',0);
/*Accesos*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'access', 'Accesos de usuario','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'lastAccess', 'Ultimo acceso','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'dateCredentials', 'Ultimo cambio de contrasena','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'session', 'Id de sesion','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'ipaddress', 'Direccion IP','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'logindate', 'Fecha ingreso','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'logoutdate', 'Fecha salida','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'logouturl', 'URL salida','0','0',0);

/*Audit trail*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'audittrail', 'Bitacora de cambios','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'entityClass', 'Clase','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'entityName', 'Nombre','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'entityProperty', 'Propiedad','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'entityPropertyOldValue', 'Valor anterior','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'entityPropertyNewValue', 'Nuevo valor','0','0',0);

/*Roles*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'ROLE_ADMIN', 'Administrador','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'ROLE_SUPER', 'Supervisor','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'ROLE_PERFIL', 'Perfil de personas','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'rolAll', 'Todos los roles ya están agregados!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'rolEnabled', 'Rol esta activo!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'rolDisabled', 'Rol esta inactivo!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'rolAdded', 'Rol agregado!','0','0',0);

/*Metadata*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'createdBy', 'Creado por','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'dateCreated', 'Fecha creacion','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'modifiedBy', 'Modificado por','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'dateModified', 'Fecha de modificación','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'active', 'Activo','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'addedBy', 'Agregado por','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'dateAdded', 'Fecha','0','0',0);

/*Acciones, todas las paginas*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'actions', 'Acciones','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'add', 'Agregar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'save', 'Guardar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'edit', 'Editar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'back', 'Regresar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'cancel', 'Cancelar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'disable', 'Deshabilitar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'enable', 'Habilitar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'end', 'Finalizar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'unlock', 'Desbloquear','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'lock', 'Bloquear','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'export', 'Exportar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'ok', 'Aceptar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'search', 'Buscar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'confirm', 'Confirmar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'please.enter', 'Favor ingresar','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ('delete', 'Eliminar', '0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ('generate', 'Generar', '0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ('choose', 'Elegir', '0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ('parameter', 'Parámetro', '0','0',0);

/*Mensajes generales, todas las paginas*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'enabled', 'Habilitado','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'locked', 'Bloqueado','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'notenabled', 'Deshabilitado','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'notlocked', 'Desbloqueado','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'process.errors', 'Han ocurrido errores en el proceso!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'process.success', 'El proceso se ha completado exitosamente!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'noResults', 'No hay registros!','0','0',0);

/*Cambio contrasenia*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'changepass', 'Cambiar contraseña..','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'credentials.expired', 'Su contraseña ha caducado','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'pass.updated', 'Su contraseña ha sido actualizada','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'password.repeat', 'Repita la contraseña','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'Pattern.password.format', 'Al menos 8 caracteres combinando mayúsculas, minúsculas, numeros y caracteres especiales','0','0',0);

/*403 404*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'denied', 'Acceso denegado','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'deniedmessage', 'Lo sentimos, la página que solicitó no esta disponible con sus credenciales.','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'notfound', 'No encontrado','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'notfoundmessage', 'Lo sentimos, la página que solicitó no pudo ser encontrada.','0','0',0);


/* Pagina de gestión de usuarios */
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'users.list', 'Gestión de usuarios','0','0',0);
/* Nuevo usuario */
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'nombreUsuario', 'Favor ingresar nombre de usuario','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'nombreCompleto', 'Favor ingresar descripción de este usuario','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'correoElectronico', 'Favor ingresar el correo electrónico de este usuario','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'contrasena', 'Favor ingresar nueva contraseña','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'usuarioRoles', 'Favor seleccionar los roles que tiene el usuario','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'usuarioCentros', 'Favor seleccionar los centros donde el usuario va a trabajar','0','0',0);

/*Clases*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'class ni.org.fabretto.me.domain.catalogs.Departamento', 'Departamento','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'class ni.org.fabretto.me.domain.catalogs.Municipio', 'Municipio','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'class ni.org.fabretto.me.domain.catalogs.Comunidad', 'Comunidad','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'class ni.org.fabretto.me.domain.catalogs.Centro', 'Centro','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'class ni.org.fabretto.me.domain.catalogs.Escuela', 'Escuela','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'class ni.org.fabretto.me.users.model.Usuario', 'Usuario','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'class ni.org.fabretto.me.users.model.RolUsuario', 'Rol de Usuario','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'class ni.org.fabretto.me.users.model.UsuarioCentro', 'Centro del Usuario','0','0',0);

/*Catalogos*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'blank', 'Sin dato','0','0',0);
/*Departamentos*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'departamentos', 'Gestión de departamentos','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'idUnico', 'Identificador único','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'nombreDepartamento', 'Nombre del departamento','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'departamentoEnabled', 'Departamento esta activo!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'departamentoDisabled', 'Departamento esta inactivo!','0','0',0);

/*Municipios*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'municipios', 'Gestión de municipios','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'nombreMunicipio', 'Nombre del municipio','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'municipioEnabled', 'Municipio esta activo!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'municipioDisabled', 'Municipio esta inactivo!','0','0',0);

/*Comunidades*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'comunidades', 'Gestión de comunidades','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'nombreComunidad', 'Nombre de la comunidad','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'descComunidad', 'Descripción de la comunidad','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'comunidadEnabled', 'Comunidad esta activa!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'comunidadDisabled', 'Comunidad esta inactiva!','0','0',0);

/*Centros*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'centros', 'Gestión de centros','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'codigoCentro', 'Codigo del centro','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'nombreCentro', 'Nombre del centro','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'direccionCentro', 'Direccion del centro','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'telefonoCentro', 'Telefono del centro','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'directorCentro', 'Director del centro','0','0',0);

INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'centroEnabled', 'Centro esta activo!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'centroDisabled', 'Centro esta inactivo!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'centroAll', 'Todos los centros ya están agregados!!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'centroAdded', 'Centro fué agregado!!','0','0',0);


/*Escuelas*/
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'escuelas', 'Gestión de escuelas','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'codigoEscuela', 'Codigo de la escuela','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'nombreEscuela', 'Nombre de la escuela','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'telefonoEscuela', 'Telefono de la escuela','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'tipoEscuela', 'Tipo de escuela','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'catEscuela', 'Categoría de escuela','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'centroEscuela', 'Centro','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'comunidadEscuela', 'Comunidad','0','0',0);

INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'escuelaEnabled', 'Escuela esta activa!','0','0',0);
INSERT INTO catMensaje (messageKey, es, catPasive, isCat, orden) VALUES ( 'escuelaDisabled', 'Escuela esta inactiva!','0','0',0);


/*Si No*/
INSERT INTO catMensaje (messageKey, catKey, catRoot, en, isCat, orden, catPasive, es) VALUES ('CAT_SINO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si No');
INSERT INTO catMensaje (messageKey, catKey, catRoot, en, isCat, orden, catPasive, es) VALUES ('CAT_SINO_SI','S','CAT_SINO',NULL,'0',1,'0','Si');
INSERT INTO catMensaje (messageKey, catKey, catRoot, en, isCat, orden, catPasive, es) VALUES ('CAT_SINO_NO','N','CAT_SINO',NULL,'0',2,'0','No');

/*Tipo de escuela*/
INSERT INTO catMensaje (messageKey, catKey, catRoot, en, isCat, orden, catPasive, es) VALUES ('CAT_TIPESC', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Tipos de Escuela');
INSERT INTO catMensaje (messageKey, catKey, catRoot, en, isCat, orden, catPasive, es) VALUES ('CAT_TIPESC_PUB','1','CAT_TIPESC',NULL,'0',1,'0','Pública');
INSERT INTO catMensaje (messageKey, catKey, catRoot, en, isCat, orden, catPasive, es) VALUES ('CAT_TIPESC_PRI','2','CAT_TIPESC',NULL,'0',2,'0','Privada');

/*Categoria de escuela*/
INSERT INTO catMensaje (messageKey, catKey, catRoot, en, isCat, orden, catPasive, es) VALUES ('CAT_CATESC', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Categorias de Escuela');
INSERT INTO catMensaje (messageKey, catKey, catRoot, en, isCat, orden, catPasive, es) VALUES ('CAT_CATESC_A','A','CAT_CATESC',NULL,'0',1,'0','A');
INSERT INTO catMensaje (messageKey, catKey, catRoot, en, isCat, orden, catPasive, es) VALUES ('CAT_CATESC_B','B','CAT_CATESC',NULL,'0',2,'0','B');
INSERT INTO catMensaje (messageKey, catKey, catRoot, en, isCat, orden, catPasive, es) VALUES ('CAT_CATESC_C','C','CAT_CATESC',NULL,'0',2,'0','C');