function loadTable() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/position");
    xhttp.send();
    xhttp.onreadystatechange=function(){
        if(this.readyState == 4 && this.status==200){
            var trHTML = '';
            constobjects = JSON.parse(this.responseText);
            for (let object of constobjects) {
                trHTML += '<tr>';
                trHTML += '<td>' + object['name'] + '</td>';
                trHTML += '<td><button type="button" class="btn btn-outline-secondary"onclick="showPositionEditBox(' + object['id'] + ')"> Редактировать</button>';
                trHTML += '<button type="button" class="btn btn-outline-danger" onclick="positionDelete(' + object['id'] + ')"> Удалить</button></td>';
                trHTML += "</tr>";
            }
            document.getElementById("myTablePosition").innerHTML = trHTML;
        }
    };
}
loadTable();

function showPositionCreateBox(){
    Swal.fire({
        title: 'Добавление должности',
        html:' <input id="id" type="hidden">'+
            ' <input id="name" class="swal2-input" placeholder="Название">',
        focusConfirm: false,
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: "Сохранить",
        denyButtonText: "Отмена"
    }).then((result) => {
        if(result.isConfirmed) {
            positionCreate();
        } else if(result.isDenied) {
            return;
        }
    });
}

function positionCreate(){
    const name = document.getElementById("name").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/position/add");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "name": name
    }));

    xhttp.onreadystatechange = function(){
        if(this.readyState == 4&&this.status==200) {
            loadTable();
        }
    };
}

function showPositionEditBox(id){
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/position/" + id);
    xhttp.send();
    xhttp.onreadystatechange = function(){
        if(this.readyState==4&&this.status==200){
            const position = JSON.parse(this.responseText);
            Swal.fire({
                title: 'Редактирование должности',
                html:' <input id="id" type="hidden" value='+position['id']+'>'+
                    ' <input id="name" class="swal2-input"placeholder="Название" value="'+ position['name'] +'">',
                focusConfirm: false,
                showConfirmButton: true,
                showDenyButton: true,
                confirmButtonText: "Сохранить",
                denyButtonText: "Отмена"
            }).then((result) => {
                if(result.isConfirmed) {
                    positionEdit();
                } else if(result.isDenied) {
                    return;
                }
            });
        }
    };
}

function positionEdit(){
    const id = document.getElementById("id").value;
    const name = document.getElementById("name").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/position/update");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "id": parseInt(id),
        "name": name
    }));
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
            loadTable();
        }
    };
}

function positionDelete(id){
    Swal.fire({
        title: 'Вы точно хотите удалить эту должность?',
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: 'Удалить',
        denyButtonText: 'Отмена'
    }).then((result) => {
        if(result.isConfirmed) {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST","http://localhost:8080/sweet-shop/v1/position/" + id);
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