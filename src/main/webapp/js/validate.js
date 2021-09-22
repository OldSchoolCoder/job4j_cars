function validate(howManyInputs) {
    let counter = 0;
    $('form').find('input').each(function () {
        if ($(this).val() == '') {
            alert('Enter ' + $(this).attr('id'));
            return false;
        } else {
            counter++;
        }
    });
    if (counter == howManyInputs) {
        $("form").submit();
    }
    return false;
}