$('a').on('click', function () {
  $(this.hash).toggleClass('active').focus();
});

$('div').on({
  focusout: function () {
    $(this).data('timer', setTimeout(function () {
      $(this).removeClass('active');
    }.bind(this), 0));
  },
  focusin: function () {
    clearTimeout($(this).data('timer'));
  },
  keydown: function (e) {
    if (e.which === 27) {
      $(this).removeClass('active');
      e.preventDefault();
    }
  }
});

$('a').on({
  focusout: function () {
    $(this.hash).data('timer', setTimeout(function () {
      $(this.hash).removeClass('active');
    }.bind(this), 0));
  },
  focusin: function () {
    clearTimeout($(this.hash).data('timer'));  
  }
});

function testAnim(x) {
    $('.modal .modal-dialog').attr('class', 'modal-dialog  ' + x + '  animated');
};
$('#ImageModal').on('show.bs.modal', function (e) {
  var anim = $('#entrance').val();
      testAnim(anim);
})
$('#ImageModal').on('hide.bs.modal', function (e) {
  var anim = $('#exit').val();
      testAnim(slideOutRight);
})