function loadTable() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/purchase");
    xhttp.send();
    xhttp.onreadystatechange=function(){
        if(this.readyState == 4 && this.status==200){
            var trHTML = '';
            constobjects = JSON.parse(this.responseText);
            for (let object of constobjects) {
                trHTML += '<tr>';
                trHTML += '<td>' + object['supply'] + '</td>';
                trHTML += '<td>' + object['amount'] + '</td>';
                trHTML += '<td>' + object['sum'] + '</td>';
                trHTML += '<td>' + object['date'] + '</td>';
                trHTML += '<td>' + object['staff'] + '</td>';
                trHTML += '<td><button type="button" class="btn btn-outline-secondary"onclick="showPurchaseEditBox(' + object['id'] + ')"> Редактировать</button>';
                trHTML += '<button type="button" class="btn btn-outline-danger" onclick="purchaseDelete(' + object['id'] + ')"> Удалить</button></td>';
                trHTML += "</tr>";
            }
            document.getElementById("myTablePurchase").innerHTML = trHTML;
        }
    };
}
loadTable();

function supplyDropDown(id=0) {
    console.log("supplyDropDown: " + id);
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/supply");
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            var options = JSON.parse(this.responseText);
            var select = document.getElementById("supply");
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

function staffDropDown(id=0) {
    console.log("staffDropDown: " + id);
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/staff");
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            var options = JSON.parse(this.responseText);
            var select = document.getElementById("staff");
            for (let o of options) {
                var option = document.createElement("option");
                if(id !== 0 && id === o['id']) {
                    option.selected = o['id'];
                }
                option.value = o['id'];
                option.text = o['fio'];
                select.add(option);
            }
        }
    };
}

function showPurchaseCreateBox(){
    Swal.fire({
        title: 'Добавление закупки сырья',
        html:' <input id="id" type="hidden">'+
            ' <select id="supply" class="swal2-select">' +
            '    <option value="0">Выберите сырье...</option>' +
            ' </select>'+
            ' <input id="amount" class="swal2-input" placeholder="Количестов">'+
            ' <input id="sum" class="swal2-input" placeholder="Сумма">'+
            ' <select id="staff" class="swal2-select">' +
            '    <option value="0">Выберите сотрудника...</option>' +
            ' </select>',
        focusConfirm: false,
        didOpen: () => {
            supplyDropDown();
            staffDropDown();
        },
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: "Сохранить",
        denyButtonText: "Отмена"
    }).then((result) => {
        if(result.isConfirmed) {
            purchaseCreate();
        } else if(result.isDenied) {
            return;
        }
    });
}

function purchaseCreate(){
    const supplyId = document.getElementById("supply").value;
    const amount = document.getElementById("amount").value;
    const sum = document.getElementById("sum").value;
    const staffId = document.getElementById("staff").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/purchase/add");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "supplyId": parseInt(supplyId),
        "amount": parseFloat(amount),
        "sum": parseFloat(sum),
        "staffId": parseInt(staffId),
    }));

    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200) {
            console.log("purchaseCreate = " + this.responseText);
            if(this.responseText === '1') {
                loadTable();
            } else if(this.responseText === '-1') {
                Swal.fire({
                    title: 'Сумма сырья превышает общий бюджет! У вас не достаточно денег!"',
                    showConfirmButton: true,
                    confirmButtonText: 'Ок'
                }).then((result) => {
                    loadTable();
                });
            }
        }
    };
}

function showPurchaseEditBox(id){
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/purchase/" + id);
    xhttp.send();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            const purchase = JSON.parse(this.responseText);
            Swal.fire({
                title: 'Редактирование закупки сырья',
                html:' <input id="id" type="hidden" value='+purchase['id']+'>'+
                    ' <select id="supply" class="swal2-select">' +
                    '    <option value="0">Выберите сырье...</option>' +
                    ' </select>'+
                    ' <input id="amount" class="swal2-input" placeholder="Количество" value="' + purchase['amount'] +'">'+
                    ' <input id="sum" class="swal2-input" placeholder="Сумма" value="' + purchase['sum'] +'">'+
                    ' <select id="staff" class="swal2-select">' +
                    '    <option value="0">Выберите сотрудника...</option>' +
                    ' </select>',
                focusConfirm: false,
                didOpen: () => {
                    supplyDropDown(purchase['supplyId']);
                    staffDropDown(purchase['staffId']);
                },
                showConfirmButton: true,
                showDenyButton: true,
                confirmButtonText: "Сохранить",
                denyButtonText: "Отмена"
            }).then((result) => {
                if(result.isConfirmed) {
                    purchaseEdit();
                } else if(result.isDenied) {
                    return;
                }
            });
        }
    };
}

function purchaseEdit(){
    const id = document.getElementById("id").value;
    const supplyId = document.getElementById("supply").value;
    const amount = document.getElementById("amount").value;
    const sum = document.getElementById("sum").value;
    const staffId = document.getElementById("staff").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/purchase/update");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "id": parseInt(id),
        "supplyId": parseInt(supplyId),
        "amount": parseFloat(amount),
        "sum": parseFloat(sum),
        "staffId": parseInt(staffId),
    }));
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
            loadTable();
        }
    };
}

function purchaseDelete(id){
    Swal.fire({
        title: 'Вы точно хотите удалить эту закупку сырья?',
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: 'Удалить',
        denyButtonText: 'Отмена'
    }).then((result) => {
        if(result.isConfirmed) {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST","http://localhost:8080/sweet-shop/v1/purchase/" + id);
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