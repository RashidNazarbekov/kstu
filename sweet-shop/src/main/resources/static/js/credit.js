function loadTable() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/credit");
    xhttp.send();
    xhttp.onreadystatechange=function(){
        if(this.readyState == 4 && this.status==200){
            var trHTML = '';
            constobjects = JSON.parse(this.responseText);
            for (let object of constobjects) {
                trHTML += '<tr>';
                trHTML += '<td>' + object['sum'] + '</td>';
                trHTML += '<td>' + object['years'] + '</td>';
                trHTML += '<td>' + object['date'] + '</td>';
                trHTML += '<td>' + getStatus(object['status']) + '</td>';
                trHTML += '<td><button type="button" class="btn btn-outline-danger" onclick="creditDelete(' + object['id'] + ')"> Удалить</button></td>';
                trHTML += "</tr>";
            }
            document.getElementById("myTableCredit").innerHTML = trHTML;
        }
    };
}
loadTable();

function showCreditCreateBox(){
    Swal.fire({
        title: 'Получение кредита',
        html:' <input id="id" type="hidden">'+
            ' <input id="sum" class="swal2-input" placeholder="Общая сумма">'+
            ' <input id="years" class="swal2-input" placeholder="На кол-во лет">',
        focusConfirm: false,
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: "Сохранить",
        denyButtonText: "Отмена"
    }).then((result) => {
        if(result.isConfirmed) {
            creditCreate();
        } else if(result.isDenied) {
            return;
        }
    });
}

function creditCreate(){
    const sum = document.getElementById("sum").value;
    const years = document.getElementById("years").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/credit/add");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "sum": parseFloat(sum),
        "years": parseInt(years)
    }));

    xhttp.onreadystatechange = function(){
        if(this.readyState == 4&&this.status==200) {
            console.log(this.responseText);
            if(this.responseText === '1') {
                loadTable();
            } else if(this.responseText === '-1') {
                Swal.fire({
                    title: 'У вас уже имеется кредит, пожалуйста оплатите его полностью, чтобы получить новый кредит!',
                    showConfirmButton: true,
                    confirmButtonText: 'Ок'
                }).then((result) => {
                    loadTable();
                });
            }
        }
    };
}

function creditDelete(id){
    Swal.fire({
        title: 'Вы точно хотите удалить этот кредит?',
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: 'Удалить',
        denyButtonText: 'Отмена'
    }).then((result) => {
        if(result.isConfirmed) {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST","http://localhost:8080/sweet-shop/v1/credit/" + id);
            xhttp.send();
            xhttp.onreadystatechange = function(){
                if(this.readyState == 4 && this.status==200) {
                    loadTable();
                }
            };
        } else if(result.isDenied) {
            return;
        }
    });
}

function getStatus(status) {
    if(status === false) {
        return "Не погашено";
    } else {
        return "Погашено";
    }
}