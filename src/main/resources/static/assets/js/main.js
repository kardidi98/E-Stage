var EducationSection=0;
var ExperienceSection=1;
var AdministrativeDocSection=1;

(function($) {
  "use strict";

  // Preloader (if the #preloader div exists)
  $(window).on('load', function() {
    if ($('#preloader').length) {
      $('#preloader').delay(100).fadeOut('slow', function() {
        $(this).remove();
      });
    }
  });

  // Back to top button
  $(window).scroll(function() {
    if ($(this).scrollTop() > 100) {
      $('.back-to-top').fadeIn('slow');
    } else {
      $('.back-to-top').fadeOut('slow');
    }
  });
  $('.back-to-top').click(function() {
    $('html, body').animate({
      scrollTop: 0
    }, 1500, 'easeInOutExpo');
    return false;
  });

  // Header scroll class
  $(window).scroll(function() {
    if ($(this).scrollTop() > 100) {
      $('#header').addClass('header-scrolled');
    } else {
      $('#header').removeClass('header-scrolled');
    }
  });

  if ($(window).scrollTop() > 100) {
    $('#header').addClass('header-scrolled');
  }

  var scrolltoOffset = $('#header').outerHeight() - 21;
  if (window.matchMedia("(max-width: 991px)").matches) {
    scrolltoOffset += 20;
  }
  $(document).on('click', '.main-nav a, .mobile-nav a, .scrollto', function(e) {
    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
      var target = $(this.hash);
      if (target.length) {
        e.preventDefault();

        var scrollto = target.offset().top - scrolltoOffset;

        if ($(this).attr("href") == '#header') {
          scrollto = 0;
        }

        $('html, body').animate({
          scrollTop: scrollto
        }, 1500, 'easeInOutExpo');

        if ($(this).parents('.main-nav, .mobile-nav').length) {
          $('.main-nav .active, .mobile-nav .active').removeClass('active');
          $(this).closest('li').addClass('active');
        }

        if ($('body').hasClass('mobile-nav-active')) {
          $('body').removeClass('mobile-nav-active');
          $('.mobile-nav-toggle i').toggleClass('fa-times fa-bars');
          $('.mobile-nav-overly').fadeOut();
        }
        return false;
      }
    }
  });

  // Activate smooth scroll on page load with hash links in the url
  $(document).ready(function() {
    if (window.location.hash) {
      var initial_nav = window.location.hash;
      if ($(initial_nav).length) {
        var scrollto = $(initial_nav).offset().top - scrolltoOffset;
        $('html, body').animate({
          scrollTop: scrollto
        }, 1500, 'easeInOutExpo');
      }
    }
  });

  // Navigation active state on scroll
  var nav_sections = $('section');
  var main_nav = $('.main-nav, .mobile-nav');

  $(window).on('scroll', function() {
    var cur_pos = $(this).scrollTop() + 200;

    nav_sections.each(function() {
      var top = $(this).offset().top,
        bottom = top + $(this).outerHeight();

      if (cur_pos >= top && cur_pos <= bottom) {
        if (cur_pos <= bottom) {
          main_nav.find('li').removeClass('active');
        }
        main_nav.find('a[href="#' + $(this).attr('id') + '"]').parent('li').addClass('active');
      }
      if (cur_pos < 300) {
        $(".nav-menu ul:first li:first").addClass('active');
      }
    });
  });

  // Mobile Navigation
  if ($('.main-nav').length) {
    var $mobile_nav = $('.main-nav').clone().prop({
      class: 'mobile-nav d-lg-none'
    });
    $('body').append($mobile_nav);
    $('body').prepend('<button type="button" class="mobile-nav-toggle d-lg-none"><i class="fa fa-bars"></i></button>');
    $('body').append('<div class="mobile-nav-overly"></div>');

    $(document).on('click', '.mobile-nav-toggle', function(e) {
      $('body').toggleClass('mobile-nav-active');
      $('.mobile-nav-toggle i').toggleClass('fa-times fa-bars');
      $('.mobile-nav-overly').toggle();
    });

    $(document).on('click', '.mobile-nav .drop-down > a', function(e) {
      e.preventDefault();
      $(this).next().slideToggle(300);
      $(this).parent().toggleClass('active');
    });

    $(document).click(function(e) {
      var container = $(".mobile-nav, .mobile-nav-toggle");
      if (!container.is(e.target) && container.has(e.target).length === 0) {
        if ($('body').hasClass('mobile-nav-active')) {
          $('body').removeClass('mobile-nav-active');
          $('.mobile-nav-toggle i').toggleClass('fa-times fa-bars');
          $('.mobile-nav-overly').fadeOut();
        }
      }
    });
  } else if ($(".mobile-nav, .mobile-nav-toggle").length) {
    $(".mobile-nav, .mobile-nav-toggle").hide();
  }

  // jQuery counterUp (used in Whu Us section)
  $('[data-toggle="counter-up"]').counterUp({
    delay: 10,
    time: 1000
  });

  // Porfolio isotope and filter
  $(window).on('load', function() {
    var portfolioIsotope = $('.portfolio-container').isotope({
      itemSelector: '.portfolio-item'
    });
    $('#portfolio-flters li').on('click', function() {
      $("#portfolio-flters li").removeClass('filter-active');
      $(this).addClass('filter-active');

      portfolioIsotope.isotope({
        filter: $(this).data('filter')
      });
      aos_init();
    });
  });

  // Portfolio details carousel
  $(".portfolio-details-carousel").owlCarousel({
    autoplay: true,
    dots: true,
    loop: true,
    items: 1
  });

  // Initiate venobox (lightbox feature used in portofilo)
  $(document).ready(function() {
    $('.venobox').venobox();
  });

  // Testimonials carousel (uses the Owl Carousel library)
  $(".testimonials-carousel").owlCarousel({
    autoplay: true,
    dots: true,
    loop: true,
    items: 1
  });

  // Init AOS
  function aos_init() {
    AOS.init({
      duration: 1000,
      once: true
    });
  }
  $(window).on('load', function() {
    aos_init();
  });


  $(document).ready(function(){
$("#addEducationSection").on('click',function(){
  $("#EducationSection").append('<div id="RemoveEducationSection'+EducationSection+'">&nbsp;<hr><div class="RemoveEducationSection" onclick="$(\'#RemoveEducationSection'+EducationSection+'\').remove()" data-toggle="tooltip" data-placement="top" title="Remove This Section"><i class="fa fa-close"></i></div>&nbsp;<div class="row form-group">'+
               '<div class="col-lg-6 col-md-6">'+
                  '<label for="formationTitle'+EducationSection+'">Title</label>'+
                  '<input type="text" class="form-control p-3" id="titre'+EducationSection+'" th:name="${educationForm.formation['+EducationSection+'].titre}"  th:field="${educationForm.formation['+EducationSection+'].titre}" placeholder="Title" required="required">'+
                '</div>'+
                '<div class="col-lg-6 col-md-6">'+
                  '<label for="Institution'+EducationSection+'">Institution</label>'+
                  '<input type="text" class="form-control p-3"  th:name="${educationForm.formation['+EducationSection+'].institution}"  th:field="${educationForm.formation['+EducationSection+'].institution}" placeholder="Institution" required="required">'+
                '</div>'+

             ' </div>'+
              '<div class="row form-group">'+
                '<div class="col-lg-6 col-md-6">'+
                  '<label for="formationDateDeb'+EducationSection+'">Start Day</label>'+
                  '<input type="date" class="form-control p-3" id="dateDeb'+EducationSection+'" th:name="${educationForm.formation['+EducationSection+'].dateDeb}" th:field="${educationForm.formation['+EducationSection+'].dateDeb}" placeholder="YYYY-MM-DD" title="Enter a date in this format: YYYY-MM-DD"'+
                  'pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" required="required">'+
                '</div>'+
                '<div class="col-lg-6 col-md-6">'+
                  '<label for="formationDateFin'+EducationSection+'">End Day</label>'+
                  '<input type="date" class="form-control p-3" id="dateFin'+EducationSection+'" th:name="*{formation['+EducationSection+'].dateFin}" th:field="${educationForm.formation['+EducationSection+'].dateFin}" placeholder="YYYY-MM-DD" title="Enter a date in this format: YYYY-MM-DD"'+
                  'pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" required="required">'+
                '</div>'+

              '</div>'+
              
              '<div class="form-group">'+
                '<label for="formationDescription'+EducationSection+'">Description</label>'+
                '<textarea class="form-control" id="Description'+EducationSection+'" th:name="${educationForm.formation['+EducationSection+'].Description}"  th:field="${educationForm.formation['+EducationSection+'].Description}" rows="5" data-rule="required" data-msg="Write something about this education" placeholder="Write.." required="required"></textarea>'+
                '<div class="validate"></div>'+
              '</div></div>');
              EducationSection=EducationSection+1;
});

$("#addExperienceSection").on('click',function(){
  $("#ExperienceSection").append('<div id="RemoveExperienceSection'+ExperienceSection+'">&nbsp;<hr><div class="RemoveExperienceSection" onclick="$(\'#RemoveExperienceSection'+ExperienceSection+'\').remove()" data-toggle="tooltip" data-placement="top" title="Remove This Section"><i class="fa fa-close"></i></div>&nbsp;<div class="row form-group">'+
               '<div class="col-lg-6 col-md-6">'+
                  '<label for="experienceTitle'+ExperienceSection+'">Title</label>'+
                  '<input type="text" class="form-control p-3" id="experienceTitle'+ExperienceSection+'"  th:field="*{experiences['+ExperienceSection+'].titre}" placeholder="Title" required="required">'+
                '</div>'+
                '<div class="col-lg-6 col-md-6">'+
                  '<label for="entreprise'+ExperienceSection+'">Company</label>'+
                  '<input type="text" class="form-control p-3" id="entreprise'+ExperienceSection+'"  th:field="*{experiences['+ExperienceSection+'].institution}" placeholder="Company" required="required">'+
                '</div>'+

             ' </div>'+
              '<div class="row form-group">'+
                '<div class="col-lg-6 col-md-6">'+
                  '<label for="experienceDateDeb'+ExperienceSection+'">Start Day</label>'+
                  '<input type="date" class="form-control p-3" id="experienceDateDeb'+ExperienceSection+'"  th:field="*{experiences['+ExperienceSection+'].dateDeb}" placeholder="YYYY-MM-DD" title="Enter a date in this format: YYYY-MM-DD"'+
                  'pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" required="required">'+
                '</div>'+
                '<div class="col-lg-6 col-md-6">'+
                  '<label for="experienceDateFin'+ExperienceSection+'">End Day</label>'+
                  '<input type="date" class="form-control p-3" id="experienceDateFin'+ExperienceSection+'"  th:field="*{experiences['+ExperienceSection+'].dateFin}" placeholder="YYYY-MM-DD" title="Enter a date in this format: YYYY-MM-DD"'+
                  'pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" required="required">'+
                '</div>'+

              '</div>'+
              
              '<div class="form-group">'+
                '<label for="experienceDescription'+ExperienceSection+'">Description</label>'+
                '<textarea class="form-control" id="experienceDescription'+ExperienceSection+'" th:field="*{experiences['+ExperienceSection+'].Description}" rows="5" data-rule="required" data-msg="Write something about this education" placeholder="Write.." required="required"></textarea>'+
                '<div class="validate"></div>'+
              '</div></div>');
              ExperienceSection=ExperienceSection+1;
});


$("#AddAdministartiveDocSection").on('click',function(){
  $("#AdministartiveDocSection").append('<div id="RemoveAdministrativeDocSection'+AdministrativeDocSection+'">&nbsp;<hr><div class="RemoveAdministrativeDocSection"  onclick="$(\'#RemoveAdministrativeDocSection'+AdministrativeDocSection+'\').remove()" data-toggle="tooltip" data-placement="top" title="Remove This Section"><i class="fa fa-close"></i></div>&nbsp;<div class="form-group" style="margin-top:10px;">'+
    '<div class="choose-file text-center py-1 rounded" style="border: 2px dashed #DCDCDC;">'+
        '<label for="document'+AdministrativeDocSection+'" class="LabelDocsUpload">'+
          '<div> '+
           ' <div class="d-block font-weight-bold text-dark">'+
            '  Drop the document to upload'+
            '</div> '+
            '<div class="d-block">or</div> '+
            '<div class="d-block btn bg-primary text-white select-files">'+
             ' Select the document'+
           ' </div>'+
          '</div> '+
          '<input  type="file"  name="titre" th:field="*{documentAdministratif['+AdministrativeDocSection+'].titre}" class="form-control-file d-none" id="document'+AdministrativeDocSection+'" >'+
        '</label>'+
      '</div></div></div>');
  AdministrativeDocSection=AdministrativeDocSection+1;
});

  });


})(jQuery);