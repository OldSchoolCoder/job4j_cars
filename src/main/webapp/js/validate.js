function validate() {
    let isError = false;
    $('form').find('input').each(function () {
        if ($(this).val() === '' || $(this).val() === null ||
            $(this).val() === undefined) {
            alert('Enter ' + $(this).attr('id'));
            isError = true;
            return false;
        }
    });
    if (!isError) {
        $("form").submit();
    }
    return false;
}