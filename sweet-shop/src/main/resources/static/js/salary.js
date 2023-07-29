function loadTable() {
    var year = document.getElementById("year");
    var month = document.getElementById("month");
    year.value = "0";
    month.value = "0";
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/salary");
    xhttp.send();
    let general_sum = 0;
    xhttp.onreadystatechange=function(){
        if(this.readyState == 4 && this.status==200){
            var trHTML = '';
            constobjects = JSON.parse(this.responseText);
            for (let object of constobjects) {
                trHTML += '<tr>';
                trHTML += '<td>' + object['staff'] + '</td>';
                trHTML += '<td>' + object['year'] + '</td>';
                trHTML += '<td>' + object['month'] + '</td>';
                trHTML += '<td>' + object['purchaseAmount'] + '</td>';
                trHTML += '<td>' + object['productionAmount'] + '</td>';
                trHTML += '<td>' + object['saleAmount'] + '</td>';
                trHTML += '<td>' + object['sumAmount'] + '</td>';
                trHTML += '<td>' + object['oklad'] + '</td>';
                trHTML += '<td>' + object['sumSalary'] + '</td>';
                if(object['isIssued'] === true) {
                    trHTML += '<td>Да</td>';
                } else {
                    trHTML += '<td>Нет</td>';
                }
                trHTML += '<td><button type="button" class="btn btn-outline-secondary" onclick="showSalaryEditBox(' + object['id'] + ', ' + object['isIssued'] + ')"> Редактировать</button>';
                trHTML += '<button type="button" class="btn btn-outline-danger" onclick="salaryDelete(' + object['id'] + ')"> Удалить</button></td>';
                trHTML += "</tr>";
                general_sum += object['sumSalary'];
            }
            document.getElementById("myTableSalary").innerHTML = trHTML;
            document.getElementById("generalSum").value = general_sum;
        }
    };
}
loadTable();

function hideColumn() {
    const tds = document.getElementsByTagName('tr');
    for (let i = 0; i < tds.length; i++) {
        tds[i].childNodes[2].style.display = 'none';
        tds[i].childNodes[3].style.display = 'none';
    }
};

function showColumn() {
    const tds = document.getElementsByTagName('tr');
    for (let i = 0; i < tds.length; i++) {
        tds[i].childNodes[2].style.display = 'table-cell';
        tds[i].childNodes[3].style.display = 'table-cell';
    }
};

function monthComboboxChangedEvent() {
    var year = document.getElementById("year");
    var month = document.getElementById("month");
    console.log(parseInt(year.value));
    console.log(month.options[month.selectedIndex].text);
    if(year.value != "0" && month.value != "0") {
        insertSalary(year.value, month.value);
        loadTableByYearAndMonth(year, month);
    }
}

function insertSalary(year, month) {
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/salary/add");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "year": parseInt(year),
        "month": parseInt(month)
    }));
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            console.log("Everything is alright!");
        }
    };
}

function loadTableByYearAndMonth(year, month) {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/sweet-shop/v1/salary/year-month?year=" + year.value + "&month=" + month.options[month.selectedIndex].text);
    xhttp.send();
    let general_sum = 0;
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var year = document.getElementById("year");
            var month = document.getElementById("month");
            var trHTML = '';
            constobjects = JSON.parse(this.responseText);
            for (let object of constobjects) {
                trHTML += '<tr>';
                trHTML += '<td>' + object['staff'] + '</td>';
                trHTML += '<td>' + object['year'] + '</td>';
                trHTML += '<td>' + object['month'] + '</td>';
                trHTML += '<td>' + object['purchaseAmount'] + '</td>';
                trHTML += '<td>' + object['productionAmount'] + '</td>';
                trHTML += '<td>' + object['saleAmount'] + '</td>';
                trHTML += '<td>' + object['sumAmount'] + '</td>';
                trHTML += '<td>' + object['oklad'] + '</td>';
                trHTML += '<td>' + object['sumSalary'] + '</td>';
                if (object['isIssued'] === true) {
                    trHTML += '<td>Да</td>';
                } else {
                    trHTML += '<td>Нет</td>';
                }
                trHTML += '<td><button type="button" class="btn btn-outline-secondary" onclick="showSalaryEditBox(' + object['id'] + ', ' + object['isIssued'] + ')"> Редактировать</button>';
                trHTML += '<button type="button" class="btn btn-outline-danger" onclick="salaryDelete(' + object['id'] + ')"> Удалить</button></td>';
                trHTML += "</tr>";
                general_sum += object['sumSalary'];
            }
            document.getElementById("myTableSalary").innerHTML = trHTML;
            document.getElementById("generalSum").value = general_sum;
        }
    };
}

function showSalaryEditBox(id, isIssued){
    if(isIssued == true) {
        Swal.fire({
            title: 'Зарплата в этот год и месяц уже была выдана. Нельзя редактировать!'
        });
        return;
    }
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/salary/" + id);
    xhttp.send();
    xhttp.onreadystatechange = function(){
        if(this.readyState==4&&this.status==200){
            const product = JSON.parse(this.responseText);
            Swal.fire({
                title: 'Редактирование зарплаты ',
                html:' <input id="id" type="hidden" value='+product['id']+'>'+
                    ' <p>Общая зарплата</p>' +
                    '<input id="sumSalary" class="swal2-input" placeholder="Общая зарплата" value="' + product['sumSalary'] +'">',
                focusConfirm: false,
                showConfirmButton: true,
                showDenyButton: true,
                confirmButtonText: "Сохранить",
                denyButtonText: "Отмена"
            }).then((result) => {
                if(result.isConfirmed) {
                    salaryEdit();
                } else if(result.isDenied) {
                    return;
                }
            });
        }
    };
}

function salaryEdit(){
    const id = document.getElementById("id").value;
    const sumSalary = document.getElementById("sumSalary").value;
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/salary/update");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "id": parseInt(id),
        "sumSalary": parseFloat(sumSalary)
    }));
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
            var year = document.getElementById("year");
            var month = document.getElementById("month");
            if(year.value != "0" && month.value != "0") {
                loadTableByYearAndMonth(year, month);
            } else {
                loadTable();
            }
        }
    };
}

function salaryDelete(id){
    Swal.fire({
        title: 'Вы точно хотите удалить это запись?',
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: 'Удалить',
        denyButtonText: 'Отмена'
    }).then((result) => {
        if(result.isConfirmed) {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST","http://localhost:8080/sweet-shop/v1/salary/" + id);
            xhttp.send();
            xhttp.onreadystatechange = function(){
                if(this.readyState == 4 && this.status==200) {
                    var year = document.getElementById("year");
                    var month = document.getElementById("month");
                    if(year.value != "0" && month.value != "0") {
                        loadTableByYearAndMonth(year, month);
                    } else {
                        loadTable();
                    }
                }
            };
        } else if(result.isDenied) {
            return;
        }
    });
}

function issueSalary() {
    var year = document.getElementById("year");
    var month = document.getElementById("month");
    console.log(parseInt(year.value));
    console.log(month.options[month.selectedIndex].text);
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/salary/issue-salary");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "year": parseInt(year.value),
        "month": parseInt(month.value)
    }));
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            if(this.responseText == '1') {
                loadTableByYearAndMonth(year, month);
            }
            if(this.responseText == '-1') {
                Swal.fire({
                    title: 'Недостаточно бюджета!',
                    showConfirmButton: true,
                    confirmButtonText: 'Ок'
                }).then((result) => {
                    return;
                });
            }
        }
    };
}