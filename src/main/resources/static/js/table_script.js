let xhr = new XMLHttpRequest();
let exam = [];

function onClick() {
    let form = document.getElementById("constraints");
    let formData = new FormData(form);

    xhr.open("POST", "/parse_expression", true);
    xhr.send(formData);
    xhr.onreadystatechange = function () {
        if (this.readyState !== 4) {
            return;
        }
        addFieldsToForm(this.responseText);
    }
}

function addFieldsToForm(args) {
    let form = document.getElementById("constraints");
    let divConfigs = document.createElement("div");
    divConfigs.setAttribute("id", "constraints-configuration");
    form.appendChild(divConfigs);
    let inputFrom;
    let inputTo;
    JSON.parse(args).forEach(function (arg) {
        divConfigs.appendChild(document.createTextNode(arg + " from "));
        inputFrom = document.createElement("input");
        inputFrom.type = "number";
        inputFrom.name = arg + ".from";
        divConfigs.appendChild(inputFrom);

        divConfigs.appendChild(document.createTextNode(" to "));
        inputTo = document.createElement("input");
        inputTo.type = "number";
        inputTo.name = arg + ".to";
        divConfigs.appendChild(inputTo);
        divConfigs.appendChild(document.createElement("br"));
    });

    const button = document.createElement("input");
    button.type = "button";
    button.value = "Submit";
    button.onclick = onSubmit;
    divConfigs.appendChild(button);
}

function onSubmit() {
    let form = document.getElementById("constraints");
    let formData = new FormData(form);
    let newExerciseConfiguration = {};
    newExerciseConfiguration.variables = [];
    let newVariable = {};

    for (let pair of formData.entries()) {
        if (pair[0].endsWith(".from")) {
            newVariable.varName = pair[0].split('.')[0];
            newVariable.from = pair[1];
        }
        if (pair[0].endsWith(".to")) {
            newVariable.to = pair[1];
            newExerciseConfiguration.variables.push(newVariable);
            newVariable = {};
        }
        if (pair[0] === "template") {
            newExerciseConfiguration[pair[0]] = pair[1];
        }
        if (pair[0] === "amount") {
            newExerciseConfiguration[pair[0]] = pair[1];
        }
    }
    exam.push(newExerciseConfiguration);
    draw_table();

    form.removeChild(document.getElementById("constraints-configuration"));
}

function draw_table() {
    clear_table();

    let table = document.getElementById("data_table");

    table.insertRow().outerHTML = "    <tr>\n" +
        "        <th>#</th>\n" +
        "        <th>Template</th>\n" +
        "        <th>Amount</th>\n" +
        "    </tr>";

    for (let i = 0; i < exam.length; i++) {
        const new_number = i + 1;
        const new_conf = exam[i].template;
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