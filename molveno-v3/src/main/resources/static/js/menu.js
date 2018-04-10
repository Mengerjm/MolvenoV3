//var baseUrl = "http://molvenov3.test.carpago.nl/api/"
var baseUrl = "http://localhost:8080/api/"

function openTab(evt, tabName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

function openDescription(evt, selectedDiv) {
    var i, divcontent, selectableDivs;

    divcontent = document.getElementsByClassName("divcontent");
    for (i = 0; i < divcontent.length; i++) {
        divcontent[i].style.display = "none";
    }

    selectableDivs = document.getElementsByClassName("selectableDivs");
    for (i = 0; i < selectableDivs.length; i++) {
        selectableDivs[i].className = selectableDivs[i].className.replace(" active", "");
    }

    document.getElementById(selectedDiv).style.display = "block";
    evt.currentTarget.className += " active";
}


var els = document.getElementsByClassName('selectableDivs')
for (var i = 0; i < els.length; ++i) {
    els[i].addEventListener('focus', focus, true);
    els[i].addEventListener('blur', blur, true);
}

function focus() {
    this.classList.add('focus');
}

function blur() {
    this.classList.remove('focus');
}