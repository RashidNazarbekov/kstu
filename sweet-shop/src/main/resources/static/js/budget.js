function loadTable() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/budget");
    xhttp.send();
    xhttp.onreadystatechange=function(){
        if(this.readyState == 4 && this.status==200){
            var trHTML = '';
            constobjects = JSON.parse(this.responseText);
            for (let object of constobjects) {
                trHTML += '<tr>';
                trHTML += '<td>' + object['sumOfBudget'] + '</td>';
                trHTML += '<td>' + object['percent'] + '</td>';
                trHTML += '<td>' + object['bonus'] + '</td>';
                trHTML += '<td><button type="button" class="btn btn-outline-secondary"onclick="showBudgetEditBox(' + object['id'] + ')"> Редактировать</button>';
                trHTML += "</tr>";
            }
            document.getElementById("myTableBudget").innerHTML = trHTML;
        }
    };
}
loadTable();

function showBudgetEditBox(id){
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/budget/" + id);
    xhttp.send();
    xhttp.onreadystatechange = function(){
        if(this.readyState==4&&this.status==200){
            const measurement = JSON.parse(this.responseText);
            Swal.fire({
                title: 'Редактирование бюджета',
                html:' <input id="id" type="hidden" value='+measurement['id']+'>'+
                    ' <input id="sumOfBudget" class="swal2-input"placeholder="Название" value="'+ measurement['sumOfBudget'] +'">'+
                    ' <input id="percent" class="swal2-input"placeholder="Название" value="'+ measurement['percent'] +'">'+
                    ' <input id="bonus" class="swal2-input"placeholder="Название" value="'+ measurement['bonus'] +'">',
                focusConfirm: false,
                showConfirmButton: true,
                showDenyButton: true,
                confirmButtonText: "Сохранить",
                denyButtonText: "Отмена"
            }).then((result) => {
                if(result.isConfirmed) {
                    budgetEdit();
                } else if(result.isDenied) {
                    return;
                }
            });
        }
    };
}

function budgetEdit(){
    const id = document.getElementById("id").value;
    const sumOfBudget = document.getElementById("sumOfBudget").value;
    const percent = document.getElementById("percent").value;
    const bonus = document.getElementById("bonus").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/budget/update");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "id": parseInt(id),
        "sumOfBudget": sumOfBudget,
        "percent": percent,
        "bonus": bonus
    }));
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status==200){
            console.log(this.responseText);
            if(this.responseText === "1") {
                loadTable();
            } else {
                Swal.fire({
                    title: 'На сервере произошла непредвиденная ошибка',
                    showConfirmButton: true,
                    confirmButtonText: 'Ок'
                }).then((result) => {
                    loadTable();
                });
            }
        }
    };
}