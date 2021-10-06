$(document).ready(function () {
    showInfo();
});

function showInfo() {
    $.getJSON("http://localhost:8080/cars/post.do", {
        id: params.get('id')
    }).done(function (response) {
        $('#info').html(info(response));
    }).fail(function (response) {
        console.log("Request Failed: " + response);
    });
}

function info(response) {
    return '<div class="col-10 col-sm-8 col-lg-6">\n' +
        '                    <img src="/cars/download?name=' + response.photos[0].name + '" class="d-block mx-lg-auto img-fluid" ' +
        '                    width="700" height="500" loading="lazy">\n' +
        '                </div>\n' +
        '                <div class="col-lg-6 text-center text-lg-start">\n' +
        '                    <h1 class="display-5 fw-bold lh-1 mb-3">' + response.car.brand + '</h1>\n' +
        '                    <p class="col-lg-10 fs-4 mt-3">' + response.description + '</p>\n' +
        '                    <ul class="col-lg-10 fs-4 text-center text-lg-start">\n' +
        '                        <li>Body type: ' + response.car.type + '</li>\n' +
        '                        <li>Power: ' + response.car.power + '</li>\n' +
        '                        <li>Owner: ' + response.author.name + '</li>\n' +
        '                    </ul>\n' +
        '                </div>';
}

