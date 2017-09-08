function create_exam() {
    const expressionsNumber = 4;

    for (let i = 0; i < expressionsNumber; i++) {
        let form = document.getElementById("form" + (i + 1));
        let formData = new FormData(form);

        addToExam(formData);
    }
    exam[0].template = "a+b";
    exam[1].template = "a-b";
    exam[2].template = "a*b";
    exam[3].template = "a/b";
}

function send_exam() {
    create_exam();
    xhr.open("POST", "/save_exam", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let examToSend = {};
    examToSend.title = document.getElementById("exam-title").value;
    examToSend.exerciseConfigurations = exam;
    examToSend.type = "math.simple_arithmetic";
    examToSend.teacherId = (new URL(window.location.href)).searchParams.get("teacherId");

    console.log(JSON.stringify(examToSend));

    xhr.send(JSON.stringify(examToSend));
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.location.href = window.location.href.replace("new_simple_exam?", "?");
        } else {

        }
    };
}