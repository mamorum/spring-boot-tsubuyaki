$(function() {

  // utilities.
  function render(data) {
    var tmpl = $('#tweet-tmpl').html();
    Mustache.parse(tmpl);
    var rendered = Mustache.render(tmpl, data);
    $('#tweet-list').prepend(rendered);
  }
  function format(msecString) {
    var d = new Date(Number(msecString));
    if (d.toLocaleDateString() === (new Date()).toLocaleDateString()) {
      // today. return 'hh:mm'.
      return ('0' + d.getHours()).slice(-2) + ':' +
            ('0' + d.getMinutes()).slice(-2);
    }
    // not today. return 'yyyy.mm.dd'.
    return d.getFullYear() + '.' +
          ('0' + (d.getMonth()+1)).slice(-2) + '.' +
          ('0' + d.getDate()).slice(-2);
  }


  // onload.
  (function() {

    $('#txt').focus();

    // read.
    $.ajax({
      url: '/tweet',
      method: 'get',
      cache: false
    }).then(function(data, status, jqxhr) {
      render(data);
      $('.date').each(function(index, e) {
        $(e).html(format($(e).html()));
      })
    });
  })();

  
  // create.
  $('#create').click(function() {

    var txt = $('#txt').val();
    if (txt === '') return;

    $.ajax({
      url: '/tweet',
      data: JSON.stringify({'txt':txt}),
      contentType: 'application/json',
      method: 'post',
      cache: false
    }).then(function(data, status, jqxhr) {
      render(data);
      var $date = $('.tweet:first').find('.date');
      $date.html(format($date.html()));
      $('#txt').val('').focus();
    });
  });

  // update.
  var $tweet;
  $('body').on('click', '.edit', function() {
    $tweet = $(this).closest('.tweet');
    $('#new-txt').val($tweet.find('.txt p').html());
    $('#modal').modal();
  });
  $('#modal-update').click(function() {

    var txt = $('#new-txt').val();
    var url = '/tweet/' + $tweet.data('id');

    $.ajax({
      url: url,
      data: {'txt':txt},
      method: 'put',
      cache: false
    }).then(function(data, status, jqxhr) {
      $('#modal').modal('hide');
      $tweet.find('.txt p').html(txt);
    });
  });

  // delete.
  $('body').on('click', '.delete', function() {

    if (!confirm("削除しますか？")) return;

    var $tweet = $(this).closest('.tweet');
    var url = '/tweet/' + $tweet.data('id');

    $.ajax({
      url: url,
      method: 'delete',
      cache: false
    }).then(function(data, status, jqxhr) {
      $tweet.remove();
    });
  });
});