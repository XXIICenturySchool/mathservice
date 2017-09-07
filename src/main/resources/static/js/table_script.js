function draw_table() {
    clear_table();

    let table = document.getElementById("data_table");

    table.insertRow().outerHTML = "    <tr>\n" +
        "        <th>#</th>\n" +
        "        <th>Configuration</th>\n" +
        "        <th>Amount</th>\n" +
        "    </tr>";

    for (let i = 0; i < exam.length; i++) {
        const new_number = i + 1;
        const new_conf = exam[i].expression;
        const new_count = exam[i].amount;

        table.insertRow(new_number).outerHTML = "<tr id='row" + new_number +
            "'><td id='number_row" + new_number + "'>" +
            new_number + "</td><td id='conf_row" + new_number + "'>" + new_conf +
            "</td><td id='count_row" + new_number + "'>" +
            new_count + "</td><td>  <input type='button' value='Delete' class='delete' onclick='delete_row(" + new_number + ")'></td></tr>";
    }
}

function clear_table() {
    let Table = document.getElementById("data_table");
    Table.innerHTML = "";
}

function delete_row(no) {
    exam.splice(no - 1, 1);
    draw_table();
}