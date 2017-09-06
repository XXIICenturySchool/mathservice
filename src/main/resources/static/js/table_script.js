function draw_table() {
    clear_table();

    var table = document.getElementById("data_table");

    table.insertRow().outerHTML = "    <tr>\n" +
        "        <th>#</th>\n" +
        "        <th>Configuration</th>\n" +
        "        <th>Amount</th>\n" +
        "    </tr>";

    for (var i = 0; i < exam.length; i++) {
        var new_number = i+1;
        var new_conf = exam[i].expression;
        var new_count = exam[i].amount;

        var row = table.insertRow(new_number).outerHTML = "<tr id='row" + new_number +
            "'><td id='number_row" + new_number + "'>" +
            new_number + "</td><td id='conf_row" + new_number + "'>" + new_conf +
            "</td><td id='count_row" + new_number + "'>" +
            new_count + "</td><td>  <input type='button' value='Delete' class='delete' onclick='delete_row(" + new_number + ")'></td></tr>";
    }
}

function clear_table() {
    var Table = document.getElementById("data_table");
    Table.innerHTML = "";
}

function delete_row(no) {
    exam.splice(no - 1, 1);
    //document.getElementById("row" + no + "").outerHTML = "";
    draw_table();
}

function add_row() {
    var table = document.getElementById("data_table");
    var table_len = table.rows.length;

    var new_number = table.rows.length;
    var new_conf = document.getElementById("expression").value;
    var new_count = document.getElementById("amount").value;

    var row = table.insertRow(table_len).outerHTML = "<tr id='row" + table_len +
        "'><td id='number_row" + table_len + "'>" +
        new_number + "</td><td id='conf_row" + table_len + "'>" + new_conf +
        "</td><td id='count_row" + table_len + "'>" +
        new_count + "</td><td>  <input type='button' value='Delete' class='delete' onclick='delete_row(" + table_len + ")'></td></tr>";
}