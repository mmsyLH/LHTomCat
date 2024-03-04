//查询车次
function selectTrain() {
    var startstationid = document.getElementById("startstationid").value.trim();
    var endstationid = document.getElementById("endstationid").value.trim();
    console.log("startstationid",startstationid);
    console.log("endstationid",endstationid);
    $.ajax({
        url: 'LhTomCat/TrainServlet',
        type: 'get',
        data: {
            action: "selectTrain",
            startstationid: startstationid,
            endstationid: endstationid
        },
        dataType:"JSON",
        success: function (res) {
            console.log("返回的res", res);
            if (res.code === 200) {
                displayTrainData(res.data); // 调用封装的展示数据方法
            }else{
                    
            }
        },
        error: function (err) {
            // 只有请求不正常（状态码不为200）才会执行
            console.log('error', err);
        },
    });
}

// 展示查询车次的数据
function displayTrainData(data) {
    var trainList = document.getElementById("trainList");
    trainList.innerHTML = ""; // 清空之前的数据
    for (var i = 0; i < data.length; i++) {
        var row = "<tr>" +
            "<td>" + data[i].number + "</td>" +
            "<td>" + data[i].startstationid + "</td>" +
            "<td>" + data[i].endstationid + "</td>" +
            "<td>" + data[i].starttime + "</td>" +
            "<td>" + data[i].endtime + "</td>" +
            "<td>" + data[i].num + "</td>" +
            "<td>" + data[i].money + "</td>" +
            "<td>" + data[i].ntid + "</td>" +
            "<td>" + data[i].info + "</td>" +
            "</tr>";
        trainList.innerHTML += row;
    }
}

//根据车站进行模糊查询
// function searchStation() {
//     var stationName = document.getElementById("stationName").value.trim();
//     $.ajax({
//         url: 'LhTomCat/TrainServlet',
//         type: 'get',
//         data: {
//             action: "searchStation",
//             stationName: stationName
//         },
//         dataType: "JSON",
//         success: function (res) {
//             console.log("返回的res", res);
//             if (res.code === 200) {
//                 displayStationList(res.data);
//             } else {
//                 alert(res.msg);
//             }
//         },
//         error: function (err) {
//             console.log('error', err);
//         },
//     });
// }
// // 展示模糊查询车站的数据
// function displayStationList(data) {
//     var stationList = document.getElementById("stationList");
//     stationList.innerHTML = ""; // 清空之前的数据
//     for (var i = 0; i < data.length; i++) {
//         var station = data[i];
//         var tr = document.createElement("tr");
//         tr.innerHTML = "<td>" + station.stationid + "</td>" +
//                        "<td>" + station.stationpy + "</td>" +
//                        "<td>" + station.stationinfo + "</td>";
//         stationList.appendChild(tr);
//     }
// }
var pageNo=1;
var pageSize=3;
//根据车站进行模糊查询
function searchStation() {
    var stationName = document.getElementById("stationName").value.trim();
    $.ajax({
        url: 'LhTomCat/TrainServlet',
        type: 'get',
        data: {
            action:"pageByName",
            pageNo: pageNo,
            pageSize: pageSize,
            pageName: stationName
        },
        dataType: "JSON",
        success: function (res) {
            console.log("返回的res", res);
            if (res.code === 200) {
                displayStationList(res.data.items);
            } else {
                alert(res.msg);
            }
        },
        error: function (err) {
            console.log('error', err);
        },
    });
}
// 展示模糊查询车站的数据
function displayStationList(data) {
    var stationList = document.getElementById("stationList");
    stationList.innerHTML = ""; // 清空之前的数据
    for (var i = 0; i < data.length; i++) {
        var station = data[i];
        var tr = document.createElement("tr");
        tr.innerHTML = "<td>" + station.stationid + "</td>" +
                       "<td>" + station.stationpy + "</td>" +
                       "<td>" + station.stationinfo + "</td>";
        stationList.appendChild(tr);
    }
}   
//上一页
function last(){
    pageNo=pageNo-1;
    searchStation();
}
//下一页
function next(){
    pageNo=pageNo+1;
    searchStation();
}

