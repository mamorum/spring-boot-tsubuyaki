$(function() {
  const template = $('#tweet-template').html();
  Mustache.parse(template);

  // utilities.
  function render(tweet) {
    let rendered = Mustache.render(template, tweet);
    $('#tweet-list').prepend(rendered);
  }
  function deleteSec(dateString) {
    return dateString.substring(0, 16);
  }

  // onload.
  (function() {
    $('#txt').focus();
    $.ajax({
      url: '/tweet',
      method: 'get',
      cache: false
    }).then(function(data, status, jqxhr) {
      render(data);
      $('.date').each(function(index, e) {
        $(e).html(deleteSec($(e).html()));
      })
    });
  })();
  
  // create.
  $('#create').click(function() {
    let txt = $('#txt').val();
    if (txt === '') return;
    $.ajax({
      url: '/tweet',
      data: JSON.stringify({'txt':txt}),
      contentType: 'application/json',
      method: 'post',
      cache: false
    }).then(function(data, status, jqxhr) {
      render(data);
      let $date = $('.tweet:first').find('.date');
      $date.html(deleteSec($date.html()));
      $('#txt').val('').focus();
    });
  });

  // update.
  let $tweet;
  $('body').on('click', '.edit', function() {
    $tweet = $(this).closest('.tweet');
    $('#modal-txt').val($tweet.find('.txt p').html());
    $('#modal').modal('show');
  });
  $('#modal-update').click(function() {
    let txt = $('#modal-txt').val();
    let url = '/tweet/' + $tweet.data('id');
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
    let $tweet = $(this).closest('.tweet');
    let url = '/tweet/' + $tweet.data('id');
    $.ajax({
      url: url,
      method: 'delete',
      cache: false
    }).then(function(data, status, jqxhr) {
      $tweet.remove();
    });
  });
});
