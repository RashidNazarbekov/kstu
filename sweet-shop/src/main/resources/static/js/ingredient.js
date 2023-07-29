function loadTable(selectedValue){
    console.log(selectedValue);
    const xhttp = new XMLHttpRequest();
    if(selectedValue != 0 && selectedValue != null) {
        xhttp.open("GET","http://localhost:8080/sweet-shop/v1/ingredient/product/" + selectedValue);
    } else {
        xhttp.open("GET","http://localhost:8080/sweet-shop/v1/ingredient");
    }
    xhttp.send();
    xhttp.onreadystatechange=function(){
        if(this.readyState == 4 && this.status==200){
            if(selectedValue != 0 && selectedValue != null) {
                hideColumn();
                console.log(this.responseText);
                var trHTML='';
                constobjects = JSON.parse(this.responseText);
                for(let object of constobjects){
                    trHTML+='<tr>';
                    trHTML+='<td>'+object['supply']+'</td>';
                    trHTML+='<td>'+object['amount']+'</td>';
                    trHTML+='<td><button type="button" class="btn btn-outline-secondary"onclick="showIngredientEditBox('+object['id']+')"> Редактировать</button>';
                    trHTML+='<button type="button" class="btn btn-outline-danger" onclick="ingredientDelete('+object['id']+')"> Удалить</button></td>';
                    trHTML+="</tr>";
                }
                document.getElementById("myTableIngredients").innerHTML = trHTML;
            }
        } else {
            showColumn();
            console.log(this.responseText);
            var trHTML = '';
            constobjects = JSON.parse(this.responseText);
            for (let object of constobjects) {
                trHTML += '<tr>';
                trHTML += '<td>' + object['product'] + '</td>';
                trHTML += '<td>' + object['supply'] + '</td>';
                trHTML += '<td>' + object['amount'] + '</td>';
                trHTML += '<td><button type="button" class="btn btn-outline-secondary"onclick="showIngredientEditBox(' + object['id'] + ')"> Редактировать</button>';
                trHTML += '<button type="button" class="btn btn-outline-danger" onclick="ingredientDelete(' + object['id'] + ')"> Удалить</button></td>';
                trHTML += "</tr>";
            }
            document.getElementById("myTableIngredients").innerHTML = trHTML;
        }
    };
}
loadTable();
loadTableByProduct();

function loadTableByProduct() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/product");
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            var options = JSON.parse(this.responseText);
            var select = document.getElementById("products");
            var length = select.options.length;
            for (i = length-1; i > 0; i--) {
                select.options[i] = null;
            }
            for (let o of options) {
                var option = document.createElement("option");
                option.value = o['id'];
                option.text = o['name'];
                select.add(option);
            }
        }
    };
}

function hideColumn() {
    const tds = document.getElementsByTagName('tr');
    for (let i = 0; i < tds.length; i++)
        tds[i].childNodes[1].style.display = 'none';
};

function showColumn() {
    const tds = document.getElementsByTagName('tr');
    for (let i = 0; i < tds.length; i++)
        tds[i].childNodes[1].style.display = 'table-cell';
};

function getProductsValue() {
    var select = document.getElementById("products");
    console.log(select.value)
    return select.value;
}

function productDropDownCreate() {
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
                if(getProductsValue() != 0 && getProductsValue() != null && getProductsValue() == o['id']) {
                    option.selected = o['id'];
                }
                option.value = o['id'];
                option.text = o['name'];
                select.add(option);
            }
        }
    };
}

function productDropDownEdit(id) {
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
                if(id == o['id']) {
                    option.selected = o['id'];
                }
                option.value = o['id'];
                option.text = o['name'];
                select.add(option);
            }
        }
    };
}

function supplyDropDownCreate() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/supply");
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            var options = JSON.parse(this.responseText);
            var select = document.getElementById("supply");
            for (let o of options) {
                var option = document.createElement("option");
                option.value = o['id'];
                option.text = o['name'];
                select.add(option);
            }
        }
    };
}

function supplyDropDownEdit(id) {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/supply");
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            var options = JSON.parse(this.responseText);
            var select = document.getElementById("supply");
            for (let o of options) {
                var option = document.createElement("option");
                if(id == o['id']) {
                    option.selected = o['id'];
                }
                option.value = o['id'];
                option.text = o['name'];
                select.add(option);
            }
        }
    };
}

function showIngredientCreateBox(){
    Swal.fire({
        title: 'Добавление ингредиента',
        html:' <input id="id" type="hidden">'+
            ' <select id="product" class="swal2-select">' +
            '    <option value="0">Выберите продукцию...</option>' +
            ' </select>'+
            ' <select id="supply" class="swal2-select">' +
            '    <option value="0">Выберите сырье...</option>' +
            ' </select>'+
            ' <input id="amount" class="swal2-input" placeholder="Количество">',
        focusConfirm: false,
        didOpen: () => {
            productDropDownCreate();
            supplyDropDownCreate();
        },
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: "Сохранить",
        denyButtonText: "Отмена"
    }).then((result) => {
        if(result.isConfirmed) {
            ingredientCreate();
        } else if(result.isDenied) {
            return;
        }
    });
}

function ingredientCreate(){
    const product = document.getElementById("product").value;
    const supply = document.getElementById("supply").value;
    const amount = document.getElementById("amount").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/ingredient/add");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    console.log(JSON.stringify({
        "productId": parseInt(product),
        "supplyId": parseInt(supply),
        "amount": parseFloat(amount)
    }));
    xhttp.send(JSON.stringify({
        "productId": parseInt(product),
        "supplyId": parseInt(supply),
        "amount": parseFloat(amount)
    }));

    xhttp.onreadystatechange = function(){
        if(this.readyState == 4&&this.status==200) {
            if(this.responseText == '1') {
                loadTable(getProductsValue());
            }
            if(this.responseText == '-1') {
                Swal.fire({
                    title: 'Такая запись уже существует',
                    showConfirmButton: true,
                    confirmButtonText: 'Ок'
                }).then((result) => {
                    return;
                });
            }
        }
    };
}

function showIngredientEditBox(id){
    console.log(id);
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/ingredient/" + id);
    xhttp.send();
    xhttp.onreadystatechange = function(){
        if(this.readyState==4&&this.status==200){
            const ingredient = JSON.parse(this.responseText);
            Swal.fire({
                title: 'Редактирование ингредиента',
                html:' <input id="id" type="hidden" value='+ingredient['id']+'>'+
                    ' <select id="product" class="swal2-select">' +
                    '    <option value="0">Выберите продукцию...</option>' +
                    ' </select>'+
                    ' <select id="supply" class="swal2-select">' +
                    '    <option value="0">Выберите сырье...</option>' +
                    ' </select>'+
                    ' <input id="amount" class="swal2-input"placeholder="Количество" value="'+ ingredient['amount'] +'">',
                focusConfirm: false,
                didOpen: () => {
                    productDropDownEdit(ingredient['productId']);
                    supplyDropDownEdit(ingredient['supplyId']);
                },
                showConfirmButton: true,
                showDenyButton: true,
                confirmButtonText: "Сохранить",
                denyButtonText: "Отмена"
            }).then((result) => {
                if(result.isConfirmed) {
                    ingredientEdit();
                } else if(result.isDenied) {
                    return;
                }
            });
        }
    };
}

function ingredientEdit(){
    const id=document.getElementById("id").value;
    const productId=document.getElementById("product").value;
    const supplyId=document.getElementById("supply").value;
    const amount=document.getElementById("amount").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/ingredient/update");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "id": parseInt(id),
        "productId": parseInt(productId),
        "supplyId": parseInt(supplyId),
        "amount": parseFloat(amount)
    }));
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
            if(this.responseText == '1') {
                loadTable(getProductsValue());
            }
            if(this.responseText == '-1') {
                Swal.fire({
                    title: 'Такая запись уже существует',
                    showConfirmButton: true,
                    confirmButtonText: 'Ок'
                }).then((result) => {
                    return;
                });
            }
        }
    };
}

function ingredientDelete(id){
    Swal.fire({
        title: 'Вы точно хотите удалить этот ингредиент?',
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: 'Удалить',
        denyButtonText: 'Отмена'
    }).then((result) => {
        if(result.isConfirmed) {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST","http://localhost:8080/sweet-shop/v1/ingredient/" + id);
            xhttp.send();
            xhttp.onreadystatechange = function(){
                if(this.readyState==4) {
                    loadTable(getProductsValue());
                }
            };
        } else if(result.isDenied) {
            return;
        }
    });
}