// Objects
var students = [];


var courses = [];


var shifts = [
    { id: 1, name: 'Manhã'},
    { id: 2, name: 'Tarde'},
    { id: 3, name: 'Noite'}
];


// Masks
$('#inputNumber').mask('(99) 99999-9999');


// On load
loadCourses();
loadStudents();


// Load all students
function loadCourses() {
    $.ajax({
        url: "http://localhost:8080/courses",
        type: "GET",
        async: false,
        success: (response) => {
            courses = response;

            const coursesSelect = document.getElementById("selectCourses");

            for (let course of courses) {
                coursesSelect.innerHTML += `<option value=${course.id}>${course.name}</option>`
            }
        }
    })
}

function loadStudents() {
    $.getJSON("http://localhost:8080/students", (response) => {
        students = response;

        for (let student of students) {
            addNewRow(student);
        }
    })
}


// Add new row for a student
function addNewRow(student) {
    var table = document.getElementById('studentsTable');

    var newRow = table.insertRow();

    // Insert student id
    var idNode = document.createTextNode(student.id);
    newRow.insertCell().appendChild(idNode);
    
    // Insert student name
    var nameNode = document.createTextNode(student.name);
    newRow.insertCell().appendChild(nameNode);

    // Insert student email
    var emailNode = document.createTextNode(student.email);
    newRow.insertCell().appendChild(emailNode);

    // Insert student number
    var numberNode = document.createTextNode(student.phone);
    newRow.insertCell().appendChild(numberNode);

    // Insert student course
    var courseNode = document.createTextNode(courses[student.idCurso - 1].name);
    newRow.insertCell().appendChild(courseNode);

    // Insert student shift
    var shiftNode = document.createTextNode(shifts[student.period - 1].name);
    newRow.insertCell().appendChild(shiftNode);
}


// Save student from form
function saveStudent() {
    var student = {
        name: document.getElementById('inputName').value,
        email: document.getElementById('inputEmail').value,
        phone: document.getElementById('inputNumber').value,
        idCurso: document.getElementById('selectCourses').value,
        period: document.querySelector('input[name="radioShift"]:checked').value
    };

    $.ajax({
        url: "http://localhost:8080/students",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(student),
        success: (response) => {
            addNewRow(response);
            students.push(response);

            document.getElementById('form').reset();
        }
    })
}


// Alternative addNewRow function
// function addNewRow(student) {
//     var table = document.getElementById('studentsTable');

//     table.innerHTML += 
//     `
//     <tr>
//         <td>${student.id}</td>
//         <td>${student.name}</td>
//         <td>${student.email}</td>
//         <td>${student.number}</td>
//         <td>${courses[student.course - 1].name}</td>
//         <td>${shifts[student.course_shift - 1].name}</td>
//     </tr>
//     `
// }