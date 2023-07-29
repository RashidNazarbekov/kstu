function loadTable(selectedValue){
    const xhttp = new XMLHttpRequest();
    if(selectedValue != 0 && selectedValue != null) {
        xhttp.open("GET","http://localhost:8080/sweet-shop/v1/grafik/credit/" + selectedValue);
    } else {
        xhttp.open("GET","http://localhost:8080/sweet-shop/v1/grafik");
    }
    xhttp.send();
    nowDate = new Date();
    nowDate.setHours(6);
    nowDate.setMinutes(0);
    nowDate.setSeconds(0);
    nowDate.setMilliseconds(0);
    xhttp.onreadystatechange=function(){
        if(this.readyState == 4 && this.status==200){
            let trHTML= '';
            constobjects = JSON.parse(this.responseText);
            if(selectedValue != 0 && selectedValue != null) {
                console.log("selected value is not null");
                for(let object of constobjects) {
                    trHTML+='<tr>';
                    trHTML+='<td>'+object['sum']+'</td>';
                    trHTML+='<td>'+object['part']+'</td>';
                    trHTML+='<td>'+object['percent']+'</td>';
                    trHTML+='<td>'+object['fine']+'</td>';
                    trHTML+='<td>'+object['date']+'</td>';
                    trHTML+='<td>'+getStatus(object['status'])+'</td>';
                    let date = new Date(object['date']);
                    let status = object['status'];
                    if(status === false) {
                        if(date <= nowDate) {
                            trHTML += '<td><button type="button" class="btn btn-success" onclick="payCredit(' + object['id'] + ')"> Оплатить</button></td>';
                        } else {
                            trHTML += '<td><button type="button" class="btn btn-secondary" disabled onclick="payCredit(' + object['id'] + ')"> Оплатить</button></td>';
                        }
                    } else {
                        trHTML += '<td><button type="button" class="btn btn-secondary" disabled onclick="payCredit(' + object['id'] + ')"> Оплатить</button></td>';
                    }
                    trHTML+="</tr>";
                }
                document.getElementById("myTableGrafik").innerHTML = trHTML;
            } else {
                console.log("selected value is null");
                for (let object of constobjects) {
                    trHTML += '<tr>';
                    trHTML+='<td>'+object['sum']+'</td>';
                    trHTML+='<td>'+object['part']+'</td>';
                    trHTML+='<td>'+object['percent']+'</td>';
                    trHTML+='<td>'+object['fine']+'</td>';
                    trHTML+='<td>'+object['date']+'</td>';
                    trHTML+='<td>'+getStatus(object['status'])+'</td>';
                    let date = new Date(object['date']);
                    let status = object['status'];
                    if(status === false) {
                        if(date <= nowDate) {
                            trHTML += '<td><button type="button" class="btn btn-success" onclick="payCredit(' + object['id'] + ')"> Оплатить</button></td>';
                        } else {
                            trHTML += '<td><button type="button" class="btn btn-secondary" disabled onclick="payCredit(' + object['id'] + ')"> Оплатить</button></td>';
                        }
                    } else {
                        trHTML += '<td><button type="button" class="btn btn-secondary" disabled onclick="payCredit(' + object['id'] + ')"> Оплатить</button></td>';
                    }
                    trHTML += "</tr>";
                }
                document.getElementById("myTableGrafik").innerHTML = trHTML;
            }
        }
    };
}
loadTable();
loadTableByCredit();

function loadTableByCredit() {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET","http://localhost:8080/sweet-shop/v1/credit");
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            var options = JSON.parse(this.responseText);
            var select = document.getElementById("credits");
            var length = select.options.length;
            for (i = length-1; i > 0; i--) {
                select.options[i] = null;
            }
            for (let o of options) {
                var option = document.createElement("option");
                option.value = o['id'];
                option.text = o['date'] + " | " + o['sum'];
                select.add(option);
            }
        }
    };
}

function payCredit(id) {
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST","http://localhost:8080/sweet-shop/v1/grafik/pay-credit/" + id);
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            if(this.responseText == '1') {
                loadTable(getCreditsValue());
            }
            if(this.responseText == '-1') {
                Swal.fire({
                    title: 'Недостаточно бюджета!',
                    showConfirmButton: true,
                    confirmButtonText: 'Ок'
                }).then((result) => {
                    return;
                });
            }
        }
    }
}

function getCreditsValue() {
    var select = document.getElementById("credits");
    console.log(select.value)
    return select.value;
}

function getStatus(status) {
    if(status === false) {
        return "Не оплачено";
    } else {
        return "Оплачено";
    }
}