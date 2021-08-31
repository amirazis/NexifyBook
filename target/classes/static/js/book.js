console.log("inside js");

	// var _jqueryConfirmation = function(s){ 
			var x = '';
			var y = '';

    	$(".form-book-main").on("click", ".deleteBtn", function(){
			var $form = $(".form-book-main");
 		   	s.val($(this).val()); console.log("here");			   	
			   		if($(this).val() == 'delete'){
	 			   		x = "This record will be deleted upon clicking the Yes button. No changes once Accepted."; 
				   		y = "Delete record?";
	 		   		} 
					$.confirm({
	 			    	title: y,
	 			    	content: x,
	 			    	autoClose: 'no|8000',
	 			    	buttons: {
	 			        	yes: {
	 			        		btnClass: 'btn-success',
	 			            	text: 'Yes',
	 			            	action: function () {
	 			            		if (sent == false){
	 			            			$form.submit();
	 			            			sent = true;
	 			            		}
	 			            	
	 			            	}
	 			        	},
	 			        	no: {
	 			        	btnClass: 'btn-danger',
	 			        	}
	 			    	}
	 				})			   	
	       });
	   // };