var labelArray;
var dataArray;
var myChart;

function showData(id) {
    var selVal = document.getElementById(id).value;
    console.log(selVal);

    if (id == 'type') {
        fillGraphWithType(id, selVal)
    } else if (id == 'type') {
        fillGraphWithData(id, selVal)
    } else if (id == 'period') {
        fillGraphWithPeriod(id, selVal)
    } else if (id == 'graphType'){
        fillGraphWithGraphType(id, selVal);
    }
}

function fillGraphWithType(id, selVal) {

}

function fillGraphWithData(id, selVal) {

}

function fillGraphWithPeriod(id, selVal) {


if(selVal == 'week'){
    labelArray = new Array("week 1", "week 2", "week 3", "week 4", "week 5", "week 6");
    dataArray = new Array(12, 19, 3, 5, 2, 3);
}

if(selVal == 'month'){
    labelArray = new Array("Januari", "Februari", "March", "April", "May", "June");
    dataArray = new Array(22, 4, 15, 5, 54, 10);
}

if(selVal == 'quarter'){
    labelArray = new Array("Q1", "Q2", "Q3", "Q4");
    dataArray = new Array(17, 1, 32, 8);
}

if(selVal == 'year'){
    labelArray = new Array("2012", "2013", "2014", "2015", "2016", "2017");
    dataArray = new Array(11, 13, 26, 3, 6, 21);
}

fillGraph(id, selVal, labelArray, dataArray, document.getElementById('graphType').value);
}

function fillGraphWithGraphType(id, selVal) {
    fillGraph(null, null, labelArray, dataArray, selVal);
}

function fillGraph(id, selVal, labelArray, dataArray, graphType){
    if(myChart != null){
        myChart.destroy();
    }
    beforePrintHandler();
    myChart = new Chart($("#chart"), {
    type: graphType,
    data: {
        labels: labelArray,
        datasets: [{
            label: 'Sold',
            data: dataArray,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});
}