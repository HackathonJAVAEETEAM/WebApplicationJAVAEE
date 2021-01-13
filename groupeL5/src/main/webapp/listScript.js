/**
 * 
 */

 $(document).ready(function(){
	$("#igInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#igTable tbody tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	   });
	 });
	$("#adInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#adTable tbody tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	   });
	 });
	$("#ctInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#ctTable tbody tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    	});
  	});
});