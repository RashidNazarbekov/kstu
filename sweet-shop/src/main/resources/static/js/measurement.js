function loadTable() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/measurement");
    xhttp.send();
    xhttp.onreadystatechange=function(){
        if(this.readyState == 4 && this.status==200){
            var trHTML = '';
            constobjects = JSON.parse(this.responseText);
            for (let object of constobjects) {
                trHTML += '<tr>';
                trHTML += '<td>' + object['name'] + '</td>';
                trHTML += '<td><button type="button" class="btn btn-outline-secondary"onclick="showMeasurementEditBox(' + object['id'] + ')"> Редактировать</button>';
                trHTML += '<button type="button" class="btn btn-outline-danger" onclick="measurementDelete(' + object['id'] + ')"> Удалить</button></td>';
                trHTML += "</tr>";
            }
            document.getElementById("myTableMeasurement").innerHTML = trHTML;
        }
    };
}
loadTable();

function showMeasurementCreateBox(){
    Swal.fire({
        title: 'Добавление единицы измерения',
        html:' <input id="id" type="hidden">'+
            ' <input id="name" class="swal2-input" placeholder="Название">',
        focusConfirm: false,
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: "Сохранить",
        denyButtonText: "Отмена"
    }).then((result) => {
        if(result.isConfirmed) {
            measurementCreate();
        } else if(result.isDenied) {
            return;
        }
    });
}

function measurementCreate(){
    const name = document.getElementById("name").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/measurement/add");
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

function showMeasurementEditBox(id){
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/measurement/" + id);
    xhttp.send();
    xhttp.onreadystatechange = function(){
        if(this.readyState==4&&this.status==200){
            const measurement = JSON.parse(this.responseText);
            Swal.fire({
                title: 'Редактирование единицы измерения',
                html:' <input id="id" type="hidden" value='+measurement['id']+'>'+
                    ' <input id="name" class="swal2-input"placeholder="Название" value="'+ measurement['name'] +'">',
                focusConfirm: false,
                showConfirmButton: true,
                showDenyButton: true,
                confirmButtonText: "Сохранить",
                denyButtonText: "Отмена"
            }).then((result) => {
                if(result.isConfirmed) {
                    measurementEdit();
                } else if(result.isDenied) {
                    return;
                }
            });
        }
    };
}

function measurementEdit(){
    const id = document.getElementById("id").value;
    const name = document.getElementById("name").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/measurement/update");
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

function measurementDelete(id) {
    Swal.fire({
        title: 'Вы точно хотите удалить эту единицу измерения?',
        showConfirmButton: true,
        showDenyButton: true,
        confirmButtonText: 'Удалить',
        denyButtonText: 'Отмена'
    }).then((result) => {
        if (result.isConfirmed) {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST", "http://localhost:8080/sweet-shop/v1/measurement/" + id);
            xhttp.send();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    //Swal.fire('Единица измерения успешно удалена');
                    loadTable();
                }
            };
        } else if (result.isDenied) {
            return;
        }
    });
}