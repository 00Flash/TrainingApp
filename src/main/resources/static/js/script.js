function togglePopup(date){
    document.getElementById("popup-1").classList.toggle("active");
    document.getElementById("custom-dish-date").value = date;
    document.getElementById("search-dish-date").value = date;
    console.log(date);
}