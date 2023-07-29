function loadTable() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/production");
    xhttp.send();
    xhttp.onreadystatechange=function(){
        if(this.readyState == 4 && this.status==200){
            var trHTML = '';
            constobjects = JSON.parse(this.responseText);
            for (let object of constobjects) {
                trHTML += '<tr>';
                trHTML += '<td>' + object['product'] + '</td>';
                trHTML += '<td>' + object['amount'] + '</td>';
                trHTML += '<td>' + object['date'] + '</td>';
                trHTML += '<td>' + object['staff'] + '</td>';
                trHTML += '<td><button type="button" class="btn btn-outline-secondary"onclick="showProductionEditBox(' + object['id'] + ')"> Редактировать</button>';
                trHTML += '<button type="button" class="btn btn-outline-danger" onclick="productionDelete(' + object['id'] + ')"> Удалить</button></td>';
                trHTML += "</tr>";
            }
            document.getElementById("myTableProduction").innerHTML = trHTML;
        }
    };
}
loadTable();

function productDropDown(id=0) {
    console.log("productDropDown: " + id);
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/product");
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            var options = JSON.parse(this.responseText);
            var select = document.getElementById("product");
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

function showProductionCreateBox(){
    Swal.fire({
        title: 'Добавление производства продукции',
        html:' <input id="id" type="hidden">'+
            ' <select id="product" class="swal2-select">' +
            '    <option value="0">Выберите продукцию...</option>' +
            ' </select>'+
            ' <input id="amount" class="swal2-input" placeholder="Количестов">'+
            ' <select id="staff" class="swal2-select">' +
            '    <option value="0">Выберите сотрудника...</option>' +
            ' </select>',
        focusConfirm: false,
        didOpen: () => {
            productDropDown();
            staffDropDown();
        },
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: "Сохранить",
        denyButtonText: "Отмена"
    }).then((result) => {
        if(result.isConfirmed) {
            productionCreate();
        } else if(result.isDenied) {
            return;
        }
    });
}

function productionCreate(){
    const productId = document.getElementById("product").value;
    const amount = document.getElementById("amount").value;
    const staffId = document.getElementById("staff").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/production/add");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "productId": parseInt(productId),
        "amount": parseFloat(amount),
        "staffId": parseInt(staffId),
    }));

    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200) {
            console.log("productionCreate = " + this.responseText);
            if(this.responseText === '1') {
                loadTable();
            } else if(this.responseText === '-1') {
                Swal.fire({
                    title: 'Недостаточно сырья на складе!',
                    showConfirmButton: true,
                    confirmButtonText: 'Ок'
                }).then((result) => {
                    loadTable();
                });
            }
        }
    };
}

function showProductionEditBox(id){
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/production/" + id);
    xhttp.send();
    xhttp.onreadystatechange = function(){
        if(this.readyState==4&&this.status==200){
            const product = JSON.parse(this.responseText);
            Swal.fire({
                title: 'Редактирование производства продукции ',
                html:' <input id="id" type="hidden" value='+product['id']+'>'+
                    ' <select id="product" class="swal2-select">' +
                    '    <option value="0">Выберите продукцию...</option>' +
                    ' </select>'+
                    ' <input id="amount" class="swal2-input" placeholder="Количество" value="' + product['amount'] +'">'+
                    ' <select id="staff" class="swal2-select">' +
                    '    <option value="0">Выберите сотрудника...</option>' +
                    ' </select>',
                focusConfirm: false,
                didOpen: () => {
                    productDropDown(product['productId']);
                    staffDropDown(product['staffId']);
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

function productionEdit(){
    const id = document.getElementById("id").value;
    const productId = document.getElementById("product").value;
    const amount = document.getElementById("amount").value;
    const staffId = document.getElementById("staff").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/production/update");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "id": parseInt(id),
        "productId": parseInt(productId),
        "amount": parseFloat(amount),
        "staffId": parseInt(staffId),
    }));
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
            loadTable();
        }
    };
}

function productionDelete(id){
    Swal.fire({
        title: 'Вы точно хотите удалить это производство продукции?',
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: 'Удалить',
        denyButtonText: 'Отмена'
    }).then((result) => {
        if(result.isConfirmed) {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST","http://localhost:8080/sweet-shop/v1/production/" + id);
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