$(document).ready(function () {
    showCategory();
    hello();
    buildTable();
});

let params = (new URL(document.location)).searchParams;

function avatar(response) {
    return '<div class="dropdown">\n' +
        '     <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle"\n' +
        '       id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">\n' +
        '          <span style="font-size: 2rem; padding-right: 10px;">\n' +
        '            <span style="color: rgb(250, 250, 246);">\n' +
        '              <i class="fa fa-user-circle" aria-hidden="true"></i>\n' +
        '            </span>\n' +
        '          </span>\n' +
        '          <strong>' + response.name + '</strong>\n' +
        '     </a>\n' +
        '     <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">\n' +
        '       <li><a class="dropdown-item" href="/cars/logout.do">Sign out</a></li>\n' +
        '    </ul>\n' +
        '  </div>';
}

function hello() {
    $.getJSON("http://localhost:8080/cars/hello.do"
    ).done(function (response) {
        if (response != null) {
            $('#loginButtons').replaceWith(avatar(response));
        }
    }).fail(function (err) {
        console.log("Request Failed: " + err);
    });
}

function buildTable() {
    $.getJSON("http://localhost:8080/cars/build.do", {
        data: params.get('data'),
        filterType: params.get('filterType')
    }).done(function (response) {
        let cells = [];
        $.each(response, function (key, val) {
            cells.push(cell(val));
        });
        $('#cell').html(cells);
    }).fail(function (err) {
        console.log("Build Request Failed: " + err);
    });
}

function cell(val) {
    return '<div class="col">\n' +
        '                        <div class="card text-white bg-dark">\n' +
        '                            <img src="/cars/download?name=' + val.photos[0].name + '" class="card-img-top">\n' +
        '                            <div class="card-body">\n' +
        '                                <h5 class="card-title">' + val.car.brand + '</h5>\n' +
        '                                <p class="card-text">' + val.description + '</p>\n' +
        '                                <div class="d-flex justify-content-between align-items-center">\n' +
        '                                    <div class="btn-group btn-group-sm">\n' +
        '                                        <a href="/cars/post.html?id=' + val.id + '" class="btn btn-sm btn-outline-secondary">View</a>\n' +
        '                                        <a href="/cars/add.jsp?id=' + val.id + '" class="btn btn-sm btn-outline-secondary">Edit</a>\n' +
        '                                    </div>\n' +
        '                                    <small class="text-muted">' + val.author.name + '</small>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                    </div>';
}

function showCategory() {
    let brandCategories = [];
    let bodyCategories = [];
    let powerCategories = [];
    $.getJSON("http://localhost:8080/cars/build.do"
    ).done(function (response) {
        $.each(response, function (key, val) {
            brandCategories.push('<li><a class="dropdown-item" href="/cars/?data='
                + val.car.brand + '&filterType=brand">' + val.car.brand + '</a></li>');
            bodyCategories.push('<li><a class="dropdown-item" href="/cars/?data='
                + val.car.type + '&filterType=type">' + val.car.type + '</a></li>');
            powerCategories.push('<li><a class="dropdown-item" href="/cars/?data='
                + val.car.power + '&filterType=power">' + val.car.power + '</a></li>');
        });
        $('#brandCategory').html(brandCategories);
        $('#bodyCategory').html(bodyCategories);
        $('#powerCategory').html(powerCategories);
    }).fail(function (err) {
        console.log("Category error: " + err);
    });
}

