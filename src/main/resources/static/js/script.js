function send_exam() {
    xhr.open("POST", "/save_exam", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let examToSend = {};
    examToSend.title = document.getElementById("exam-title").value;
    examToSend.exerciseConfigurations = exam;
    examToSend.type = "math.arithmetic";
    examToSend.teacherId = (new URL(window.location.href)).searchParams.get("teacherId");

    console.log(JSON.stringify(examToSend));

    xhr.send(JSON.stringify(examToSend));
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.location.href = window.location.href.replace("new_exam?", "?");
        } else {
        }
    };
}
