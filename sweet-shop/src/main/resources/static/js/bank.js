function loadTable() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/bank");
    xhttp.send();
    xhttp.onreadystatechange=function(){
        if(this.readyState == 4 && this.status==200){
            var trHTML = '';
            constobjects = JSON.parse(this.responseText);
            for (let object of constobjects) {
                trHTML += '<tr>';
                trHTML += '<td>' + object['interestRate'] + '</td>';
                trHTML += '<td>' + object['fine'] + '</td>';
                trHTML += '<td><button type="button" class="btn btn-outline-secondary"onclick="showBankEditBox(' + object['id'] + ')"> Редактировать</button>';
                trHTML += "</tr>";
            }
            document.getElementById("myTableBank").innerHTML = trHTML;
        }
    };
}
loadTable();

function showBankEditBox(id){
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/bank/" + id);
    xhttp.send();
    xhttp.onreadystatechange = function(){
        if(this.readyState==4&&this.status==200){
            const bank = JSON.parse(this.responseText);
            Swal.fire({
                title: 'Редактирование банка',
                html:' <input id="id" type="hidden" value=' + bank['id']+'>'+
                    ' <input id="interestRate" class="swal2-input"placeholder="Название" value="' + bank['interestRate'] +'">'+
                    ' <input id="fine" class="swal2-input"placeholder="Название" value="' + bank['fine'] +'">',
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
    const interestRate = document.getElementById("interestRate").value;
    const fine = document.getElementById("fine").value;

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/bank/update");
    xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({
        "id": parseInt(id),
        "interestRate": parseFloat(interestRate),
        "fine": parseFloat(fine)
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