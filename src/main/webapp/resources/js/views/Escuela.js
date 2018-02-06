var ProcessEscuela = function () {
	
return {
  //main function to initiate the module
  init: function (parametros) {
	  
  $.validator.setDefaults( {
    submitHandler: function () {
    	processEscuela();
    }
  } );
  $('#comunidad, #tipoEscuela, #catEscuela').select2({
	    theme: "bootstrap"
  });	
  $('#codigo').focus();
  jQuery.validator.addMethod("noSpace", function(value, element) { 
		  return value.indexOf(" ") < 0 && value != ""; 
	}, "Invalid");
  $( '#add-escuela-form' ).validate( {
    rules: {
      nombreEscuela: {
    	  minlength: 1,
          maxlength: 100,
          required: true
      },
      comunidad: {
          required: true
      },
      codigo: {
          required: true
      },
      tipoEscuela: {
          required: true
      },
      catEscuela: {
          required: true
      },
      descEscuela: {
    	  minlength: 1,
          maxlength: 500,
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
    }
  });
  
  function processEscuela(){
	  $.blockUI({ message: parametros.waitmessage });
	    $.post( parametros.saveEscuelaUrl
	            , $( '#add-escuela-form' ).serialize()
	            , function( data )
	            {
	    			escuela = JSON.parse(data);
	    			if (escuela.idUnico === undefined) {
	    				toastr.error(data, parametros.errormessage, {
	    					    closeButton: true,
	    					    progressBar: true,
	    					  });
	    				$.unblockUI();
					}
					else{
						$.blockUI({ message: parametros.successmessage });
						$('#idUnico').val(escuela.idUnico);
						setTimeout(function() { 
				            $.unblockUI({ 
				                onUnblock: function(){ window.location.href = parametros.escuelaUrl; } 
				            }); 
				        }, 1000); 
					}
	            }
	            , 'text' )
		  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
		    		alert( "error:" + errorThrown);
		    		$.unblockUI();
		  		});
	}
  
  
  $(document).on('keypress','form input',function(event)
  		{                
  		    event.stopImmediatePropagation();
  		    if( event.which == 13 )
  		    {
  		        event.preventDefault();
  		        var $input = $('form input');
  		        if( $(this).is( $input.last() ) )
  		        {
  		            //Time to submit the form!!!!
  		            //alert( 'Hooray .....' );
  		        }
  		        else
  		        {
  		            $input.eq( $input.index( this ) + 1 ).focus();
  		        }
  		    }
  		});
  }
 };
}();
