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

function send_exam() {
    xhr.open("POST", "/save_exam", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let examToSend = {};
    examToSend.title = document.getElementById("exam_title").value;
    examToSend.inputConfigurations = exam;
    examToSend.type = "math.arithmetic";
    examToSend.teacherId = (new URL(window.location.href)).searchParams.get("teacherId");

    console.log(JSON.stringify(examToSend));

    xhr.send(JSON.stringify(examToSend));
    window.location.href = window.location.href.replace("new_exam", "exam_templates");
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
