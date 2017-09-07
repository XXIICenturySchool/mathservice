
function send_exam() {
    xhr.open("POST", "/save_exam", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let examToSend = {};
    examToSend.title = document.getElementById("exam_title").value;
    examToSend.exerciseConfigurations = exam;
    examToSend.type = "math.equation";
    examToSend.teacherId = (new URL(window.location.href)).searchParams.get("teacherId");

    console.log(JSON.stringify(examToSend));

    xhr.send(JSON.stringify(examToSend));
    window.location.href = window.location.href.replace("new_equation_exam", "exam_templates");
}
