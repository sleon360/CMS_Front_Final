$(document).ready(function() {
  ///////header resize///////
  function navSize() {
    var altoMenu = $(".navbar").height();
    $("body").css("margin-top", altoMenu);
    ////
    console.log(altoMenu);
  }
  navSize();
  $( window ).resize(function() {
    navSize();
    });
  /////////Log in//////////
  $(".ver-ejemplo").click(function() {
    $(".ver-ejemplo").toggleClass("ver-ej-minus");
      if ( $(".ver-ejemplo").hasClass( "ver-ej-minus" ) ) {
        $(".cr-hide-ejemplo").fadeIn();
      } else {
        $(".cr-hide-ejemplo").fadeOut();
      }
    });
  $("#entrar-cuenta").click(function() {
    $("#hola-usuario").fadeIn();
    $("#login-btn").fadeOut(0);
    $('#loginModal').modal('hide');
    $('#settingModal').modal('show');
    });
  //////////////
  $(function () {
    $('[data-toggle="popover"]').popover()
  });
  //////////////
  $(".corner").click(function() {
    //$(".cloud-wrapper").fadeIn();
    $(".cloud-wrapper").addClass("cloud-appear");
    //$(this).fadeOut();
    $(".corner").addClass("corner-leave");
    });
  $(".cerrar-oferta").click(function() {
    //$(".corner").fadeIn();
    //$(".cloud-wrapper").fadeOut();
    $(".cloud-wrapper").removeClass("cloud-appear");
    $(".corner").removeClass("corner-leave");
    });
});