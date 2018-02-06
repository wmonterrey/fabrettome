var ProcessSearch = function () {
	
return {
  //main function to initiate the module
  init: function (parametros) {	
	  
	$('#comunidad, #sexo').select2({
		theme: "bootstrap"
	});
	
	$('input[name="daterange"]').daterangepicker({
	   ranges: {
	     'Hoy': [moment(), moment()],
	 'Ayer': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	 'Ultimos 7 Dias': [moment().subtract(6, 'days'), moment()],
	 'Ultimos 30 Dias': [moment().subtract(29, 'days'), moment()],
	 'Este mes': [moment().startOf('month'), moment().endOf('month')],
	 'Mes pasado': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	   },
	   singleDatePicker: false,
	   startDate: moment().subtract(29, 'days'),
	   maxDate:moment(),
	   locale: {
	       "format": "DD/MM/YYYY",
	   "separator": " - ",
	   "applyLabel": "Aplicar",
	   "cancelLabel": "Cancelar",
	   "fromLabel": "Desde",
	   "toLabel": "Hasta",
	   "customRangeLabel": "Personalizado",
	   "weekLabel": "S",
	   "daysOfWeek": [
	   "Do",
	   "Lu",
	   "Ma",
	   "Mi",
	   "Ju",
	   "Vi",
	   "Sa"
	   ],
	   "monthNames": [
	   "Enero",
	   "Febrero",
	   "Marzo",
	   "Abril",
	   "Mayo",
	   "Junio",
	   "Julio",
	   "Agosto",
	   "Septiembre",
	   "Octubre",
	   "Noviembre",
	   "Diciembre"
	   ],
	   "firstDay": 1
	   },
	});
	
	$('#checkIdent').change(function() {
        if(this.checked) {
        	$("#ident").prop('disabled', false);
        	$("#ident").focus();
        }else{
        	$("#ident").prop('disabled', true);
        	$("#ident").val("");
        }
              
    });
	
	$('#checkPrimerNombre').change(function() {
        if(this.checked) {
        	$("#primerNombre").prop('disabled', false);
        	$("#primerNombre").focus();
        }else{
        	$("#primerNombre").prop('disabled', true);
        	$("#primerNombre").val("");
        }
              
    });
	
	$('#checkSegundoNombre').change(function() {
        if(this.checked) {
        	$("#segundoNombre").prop('disabled', false);
        	$("#segundoNombre").focus();
        }else{
        	$("#segundoNombre").prop('disabled', true);
        	$("#segundoNombre").val("");
        }
              
    });
	
	$('#checkPrimerApellido').change(function() {
        if(this.checked) {
        	$("#primerApellido").prop('disabled', false);
        	$("#primerApellido").focus();
        }else{
        	$("#primerApellido").prop('disabled', true);
        	$("#primerApellido").val("");
        }
              
    });
	
	$('#checkSegundoApellido').change(function() {
        if(this.checked) {
        	$("#segundoApellido").prop('disabled', false);
        	$("#segundoApellido").focus();
        }else{
        	$("#segundoApellido").prop('disabled', true);
        	$("#segundoApellido").val("");
        }
              
    });
	
	$('#checkDates').change(function() {
        if(this.checked) {
        	$("#daterange").prop('disabled', false);
        	$("#daterange").focus();
        }else{
        	$("#daterange").prop('disabled', true);
        }
              
    });
  

  $('#personas-form').validate( {
	    rules: {
	      ident: {
	    	  required: true
	      },
	      primerNombre: {
	    	  required: true
	      },
	      segundoNombre: {
	    	  required: true
	      },
	      primerApellido: {
	    	  required: true
	      },
	      segundoApellido: {
	    	  required: true
	      },
	      sexo: {
	          required: false
	      },
	      comunidad: {
	          required: false
	      },
	      daterange: {
	          required: true
	      }
	    },
	    errorElement: 'em',
	    errorPlacement: function ( error, element ) {
	      // Add the `help-block` class to the error element
	      error.addClass( 'form-control-feedback' );
	      if ( element.prop( 'type' ) === 'checkbox' ) {
	        error.insertAfter( element.parent( 'label' ) );
	      } else {
	        error.insertAfter( element );
	      }
	    },
	    highlight: function ( element, errorClass, validClass ) {
	      $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
	      $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
	    },
	    unhighlight: function (element, errorClass, validClass) {
	      $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
	      $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
	    },
        submitHandler: function (form) {
            processReport();
        }
	  });
  
  Date.prototype.yyyymmdd = function() {         
      
      var yyyy = this.getFullYear().toString();                                    
      var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based         
      var dd  = this.getDate().toString();             
                          
      return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0]);
 };
	  
  function processReport(){
	  $.blockUI({ message: parametros.waitmessage });
	  $.getJSON(parametros.personasUrl, $('#personas-form').serialize(), function(data) {
		  var table1 = $('#resultados').DataTable({
	          "oLanguage": {
	              "sUrl": parametros.dataTablesLang
	          },
	          "scrollX": true,
	          "bFilter": true, 
	          "bInfo": true, 
	          "bPaginate": true, 
	          "bDestroy": true,
	      });
		  table1.clear().draw();
		if (data == ''){
			toastr.info(data, parametros.noResults, {
				closeButton: true,
				progressBar: true,
			 });
		}
		else{
			for (var row in data) {
				var d = new Date(data[row].fechaNacimiento);
				var editUrl = parametros.personasUrl + data[row].ident+'/';
				btnEdit = '<a title="edit" href=' + editUrl + ' class="btn btn-xs btn-primary" ><i class="fa fa-edit"></i></a>';
				table1.row.add([data[row].ident, data[row].primerNombre, data[row].segundoNombre, data[row].primerApellido, data[row].segundoApellido, btnEdit]);
			}
		}
	})
	.fail(function() {
	    alert( "error" );
	    $.unblockUI();
	});
	$.unblockUI();
  }
  }
 };
}();
