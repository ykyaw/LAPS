$(document).ready(function() {

    // var disabledDate = ['2020-7-1', '2020-7-2','2020-7-3'];
    $('#starttime').datetimepicker();
    $('#endtime').datetimepicker();
    //set the min date of calendar
    let date = new Date();
    let Y = date.getFullYear() + '-';
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    let D = date.getDate() + ' ';
    $("#starttime").data("DateTimePicker").minDate(new Date(Y+M+D));
    $("#endtime").data("DateTimePicker").minDate(new Date(Y+M+D));

});


function check(){
    console.log("on submit")
    let starttime=new Date($("#starttime").val()).getTime();
    let endtime=new Date($("#endtime").val()).getTime();


    if(endtime-starttime<=0){
        alert("the to date can not less than from date");
        return false;
    }
    $("input[name='startTime']").val(starttime);
    $("input[name='endTime']").val(endtime);
    // return false;
    return true;
}