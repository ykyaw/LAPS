$(document).ready(function() {
    var disabledDate = ['2020-7-1', '2020-7-2','2020-7-3'];
    $('#fromtime').datetimepicker({
        format: 'L',//date only
        format: 'YYYY-MM-DD',
        daysOfWeekDisabled: [0, 6],
        disabledDates: disabledDate
    })
    $('#totime').datetimepicker({
        format: 'LT',//time only
        format: 'YYYY-MM-DD',
        daysOfWeekDisabled: [0, 6]
    }).on('changeDate', function (ev) {
        console.log("change to time", ev)
        if (ev.date.valueOf() < date - start - display.valueOf()) {

        }
    });
    //set the min date of calendar
    let date = new Date();
    let Y = date.getFullYear() + '-';
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    let D = date.getDate() + ' ';
    $("#fromtime").data("DateTimePicker").minDate(new Date(Y+M+D));
    $("#totime").data("DateTimePicker").minDate(new Date(Y+M+D));

});


function check(){
    console.log("on submit")
    let fromtime=new Date($("#fromtime").val()).getTime();
    let totime=new Date($("#totime").val()).getTime();
    let reasons=$("input[name='reasons']").val();
    let dissemination=$("select[name='dissemination']").val();
    if(reasons==""){
        alert("reason can not be empty");
        return false;
    }
    if(dissemination==""){
        $("select[name='dissemination']").val(new Object());
    }
    if(totime-fromtime<0){
        alert("the to date can not less than from date");
        return false;
    }
    $("input[name='fromTime']").val(fromtime);
    $("input[name='toTime']").val(totime);
    return true;
    // return false;
}