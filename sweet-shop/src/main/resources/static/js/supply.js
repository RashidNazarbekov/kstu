function loadTable() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/supply");
    xhttp.send();
    xhttp.onreadystatechange=function(){
        if(this.readyState == 4 && this.status==200){
            var trHTML = '';
            constobjects = JSON.parse(this.responseText);
            for (let object of constobjects) {
                trHTML += '<tr>';
                trHTML += '<td>' + object['name'] + '</td>';
                trHTML += '<td>' + object['measurement'] + '</td>';
                trHTML += '<td>' + object['amount'] + '</td>';
                trHTML += '<td>' + object['sum'] + '</td>';
                trHTML += '<td><button type="button" class="btn btn-outline-secondary"onclick="showSupplyEditBox(' + object['id'] + ')"> Редактировать</button>';
                trHTML += '<button type="button" class="btn btn-outline-danger" onclick="supplyDelete(' + object['id'] + ')"> Удалить</button></td>';
                trHTML += "</tr>";
            }
            document.getElementById("myTableSupply").innerHTML = trHTML;
        }
    };
}
loadTable();

function measurementDropDown(id=0) {
    console.log("measurementDropDown: " + id);
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/measurement");
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            var options = JSON.parse(this.responseText);
            var select = document.getElementById("measurement");
            for (let o of options) {
                var option = document.createElement("option");
                if(id !== 0 && id === o['id']) {
                    option.selected = o['id'];
                }
                option.value = o['id'];
                option.text = o['name'];
                select.add(option);
            }
        }
    };
}

function showSupplyCreateBox(){
    Swal.fire({
        title: 'Добавление сырья',
        html:' <input id="id" type="hidden">'+
            ' <input id="name" class="swal2-input" placeholder="Название">'+
            ' <select id="measurement" class="swal2-select">' +
            '    <option value="0">Выберите единицу измерения...</option>' +
            ' </select>'+
            ' <input id="amount" class="swal2-input" placeholder="Количестов">'+
            ' <input id="sum" class="swal2-input" placeholder="Сумма">',
        focusConfirm: false,
        didOpen: () => {
            measurementDropDown();
        },
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: "Сохранить",
        denyButtonText: "Отмена"
    }).then((result) => {
        if(result.isConfirmed) {
            supplyCreate();
        } else if(result.isDenied) {
            return;
        }
    });
}

function supplyCreate(){
    const name = document.getElementById("name").value;
    const measurementId = document.getElementById("measurement").value;
    const amount = document.getElementById("amount").value;
    const sum = document.getElementById("sum").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/supply/add");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "name": name,
        "measurementId": parseInt(measurementId),
        "amount": amount,
        "sum": sum
    }));

    xhttp.onreadystatechange = function(){
        if(this.readyState == 4&&this.status==200) {
            loadTable();
        }
    };
}

function showSupplyEditBox(id){
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/supply/" + id);
    xhttp.send();
    xhttp.onreadystatechange = function(){
        if(this.readyState==4&&this.status==200){
            const supply = JSON.parse(this.responseText);
            Swal.fire({
                title: 'Редактирование сырья',
                html:' <input id="id" type="hidden" value='+supply['id']+'>'+
                    ' <input id="name" class="swal2-input" placeholder="Название" value="' + supply['name'] +'">'+
                    ' <select id="measurement" class="swal2-select">' +
                    '    <option value="0">Выберите единицу измерения...</option>' +
                    ' </select>'+
                    ' <input id="amount" class="swal2-input" placeholder="Количество" value="' + supply['amount'] +'">'+
                    ' <input id="sum" class="swal2-input" placeholder="Сумма" value="' + supply['sum'] +'">',
                focusConfirm: false,
                didOpen: () => {
                    measurementDropDown(supply['measurementId']);
                },
                showConfirmButton: true,
                showDenyButton: true,
                confirmButtonText: "Сохранить",
                denyButtonText: "Отмена"
            }).then((result) => {
                if(result.isConfirmed) {
                    supplyEdit();
                } else if(result.isDenied) {
                    return;
                }
            });
        }
    };
}

function supplyEdit(){
    const id = document.getElementById("id").value;
    const name = document.getElementById("name").value;
    const measurementId = document.getElementById("measurement").value;
    const amount = document.getElementById("amount").value;
    const sum = document.getElementById("sum").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/supply/update");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "id": parseInt(id),
        "name": name,
        "measurementId": parseInt(measurementId),
        "amount": amount,
        "sum": sum
    }));
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
            loadTable();
        }
    };
}

function supplyDelete(id){
    Swal.fire({
        title: 'Вы точно хотите удалить это сырье?',
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: 'Удалить',
        denyButtonText: 'Отмена'
    }).then((result) => {
        if(result.isConfirmed) {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST","http://localhost:8080/sweet-shop/v1/supply/" + id);
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