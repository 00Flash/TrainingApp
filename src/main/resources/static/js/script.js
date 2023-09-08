function togglePopup(date){
    document.getElementById("popup-1").classList.toggle("active");
    document.getElementById("custom-dish-date").value = date;
    document.getElementById("search-dish-date").value = date;
    console.log(date);
}
function countCalories(){
    const weight = document.getElementById("weight").value;
    const height = document.getElementById("height").value;
    const age = document.getElementById("age").value;
    const gender = document.getElementById("gender").value;
    const activity = document.getElementById("activity").value;

    console.log(weight);
    console.log(weight);
    console.log(age);
    console.log(gender);
    console.log(activity);

    let swe;
    if (gender === "female"){
        swe = (10 * parseInt(weight)) + (6.25 * parseInt(height)) - (5 * parseInt(age)) - 161;
    }

    if (gender === "male"){
        swe = (10 * parseInt(weight)) + (6.25 * parseInt(height)) - (5 * parseInt(age)) + 5;
    }


    let cwe;
    if (activity === "light"){
        cwe = swe * 1.3;
    }

    if (activity === "moderate"){
        cwe = swe * 1.6;
    }

    if (activity === "active"){
        cwe = swe * 1.8;
    }

    document.getElementById("swe").textContent = parseInt(swe).toString() + "kcal";
    document.getElementById("cwe").textContent = parseInt(cwe).toString() + "kcal";
}