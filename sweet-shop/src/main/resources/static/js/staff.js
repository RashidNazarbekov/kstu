function loadTable() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/staff");
    xhttp.send();
    xhttp.onreadystatechange=function(){
        if(this.readyState == 4 && this.status==200){
            var trHTML = '';
            constobjects = JSON.parse(this.responseText);
            for (let object of constobjects) {
                trHTML += '<tr>';
                trHTML += '<td>' + object['fio'] + '</td>';
                trHTML += '<td>' + object['position'] + '</td>';
                trHTML += '<td>' + object['salary'] + '</td>';
                trHTML += '<td>' + object['address'] + '</td>';
                trHTML += '<td>' + object['phoneNumber'] + '</td>';
                trHTML += '<td><button type="button" class="btn btn-outline-secondary"onclick="showStaffEditBox(' + object['id'] + ')"> Редактировать</button>';
                trHTML += '<button type="button" class="btn btn-outline-danger" onclick="staffDelete(' + object['id'] + ')"> Удалить</button></td>';
                trHTML += "</tr>";
            }
            document.getElementById("myTableStaff").innerHTML = trHTML;
        }
    };
}
loadTable();

function positionDropDown(id=0) {
    console.log("positionDropDown: " + id);
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/position");
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            var options = JSON.parse(this.responseText);
            var select = document.getElementById("position");
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

function showStaffCreateBox(){
    Swal.fire({
        title: 'Добавление сотрудника',
        html:' <input id="id" type="hidden">'+
            ' <input id="fio" class="swal2-input" placeholder="ФИО">'+
            ' <select id="position" class="swal2-select">' +
            '    <option value="0">Выберите должность...</option>' +
            ' </select>'+
            ' <input id="salary" class="swal2-input" placeholder="Зарплата">'+
            ' <input id="address" class="swal2-input" placeholder="Адресс">'+
            ' <input id="phoneNumber" class="swal2-input" placeholder="Номер телефона">',
        focusConfirm: false,
        didOpen: () => {
            positionDropDown();
        },
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: "Сохранить",
        denyButtonText: "Отмена"
    }).then((result) => {
        if(result.isConfirmed) {
            staffCreate();
        } else if(result.isDenied) {
            return;
        }
    });
}

function staffCreate(){
    const fio = document.getElementById("fio").value;
    const positionId = document.getElementById("position").value;
    const salary = document.getElementById("salary").value;
    const address = document.getElementById("address").value;
    const phoneNumber = document.getElementById("phoneNumber").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/staff/add");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");

    console.log(JSON.stringify({
        "fio": fio,
        "positionId": parseInt(positionId),
        "salary": parseFloat(salary),
        "address": address,
        "phoneNumber": phoneNumber
    }));

    xhttp.send(JSON.stringify({
        "fio": fio,
        "positionId": parseInt(positionId),
        "salary": parseFloat(salary),
        "address": address,
        "phoneNumber": phoneNumber
    }));

    xhttp.onreadystatechange = function(){
        if(this.readyState == 4&&this.status==200) {
            loadTable();
        }
    };
}

function showStaffEditBox(id){
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/staff/" + id);
    xhttp.send();
    xhttp.onreadystatechange = function(){
        if(this.readyState==4&&this.status==200){
            const staff = JSON.parse(this.responseText);
            Swal.fire({
                title: 'Редактирование сотрудника',
                html:' <input id="id" type="hidden" value='+staff['id']+'>'+
                    ' <input id="fio" class="swal2-input" placeholder="Название" value="' + staff['fio'] +'">'+
                    ' <select id="position" class="swal2-select">' +
                    '    <option value="0">Выберите должность...</option>' +
                    ' </select>'+
                    ' <input id="salary" class="swal2-input" placeholder="Количество" value="' + staff['salary'] +'">'+
                    ' <input id="address" class="swal2-input" placeholder="Сумма" value="' + staff['address'] +'">'+
                    ' <input id="phoneNumber" class="swal2-input" placeholder="Сумма" value="' + staff['phoneNumber'] +'">',
                focusConfirm: false,
                didOpen: () => {
                    positionDropDown(staff['positionId']);
                },
                showConfirmButton: true,
                showDenyButton: true,
                confirmButtonText: "Сохранить",
                denyButtonText: "Отмена"
            }).then((result) => {
                if(result.isConfirmed) {
                    staffEdit();
                } else if(result.isDenied) {
                    return;
                }
            });
        }
    };
}

function staffEdit(){
    const id = document.getElementById("id").value;
    const fio = document.getElementById("fio").value;
    const positionId = document.getElementById("position").value;
    const salary = document.getElementById("salary").value;
    const address = document.getElementById("address").value;
    const phoneNumber = document.getElementById("phoneNumber").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/staff/update");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "id": parseInt(id),
        "fio": fio,
        "positionId": parseInt(positionId),
        "salary": parseFloat(salary),
        "address": address,
        "phoneNumber": phoneNumber
    }));
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
            loadTable();
        }
    };
}

function staffDelete(id){
    Swal.fire({
        title: 'Вы точно хотите удалить этого сотрудника?',
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: 'Удалить',
        denyButtonText: 'Отмена'
    }).then((result) => {
        if(result.isConfirmed) {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST","http://localhost:8080/sweet-shop/v1/staff/" + id);
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