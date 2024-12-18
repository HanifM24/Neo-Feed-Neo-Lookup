
function SubmitForm(){
  var startdate = document.getElementById("start").value;
  var enddate = document.getElementById("end").value;
  $.ajax({
    url: "/api/feed",
    data: {
      fromdate: startdate,
      enddate: enddate
    },
    success: function( result ) {
      $( "#result" ).html( result );
    }
  });
}
