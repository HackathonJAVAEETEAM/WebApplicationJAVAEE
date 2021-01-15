/**
 * 
 */

 	$(document).ready(function(){
		$("#input0").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#table0 table tbody tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		   });
		 });
		$("#input1").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#table1 table tbody tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		   });
		 });
		$("#input2").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#table2 table tbody tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    	});
  	});
  	
  	$(function() {
	  $(".tablesorter").tablesorter();
	});
});