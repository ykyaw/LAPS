$(document).ready(function() {
    if($("#leavecategory").val()!="COMPENSATION_LEAVE"){
        $(".compensation").hide();
    }


    console.log($("#fromtime").val());
    console.log(timeStampConvertToyyyMMdd(parseInt($("input[name='fromTime']").val())));
    console.log(timeStampConvertToyyyMMdd(parseInt($("input[name='toTime']").val())));
    $('#fromtime').datetimepicker({
        // format: 'L',//date only
        useCurrent : false,
        format: 'YYYY-MM-DD',
        defaultDate:timeStampConvertToyyyMMdd(parseInt($("input[name='fromTime']").val())),
        daysOfWeekDisabled: [0, 6],
    })
    $('#fromtime').data("DateTimePicker").options({defaultDate:new Date()});
    $('#totime').datetimepicker({
        // format: 'L',//date only
        useCurrent : false,
        format: 'YYYY-MM-DD',
        defaultDate:timeStampConvertToyyyMMdd(parseInt($("input[name='toTime']").val()))+"",
        daysOfWeekDisabled: [0, 6],
    })
    // $('#totime').data("DateTimePicker").options({defaultDate:timeStampConvertToyyyMMdd(parseInt($("#totime").val()))+""});

    $('#fromGranularity').datetimepicker({
        // format: 'LT',//time only
        format: 'HH',
        enabledHours: [9, 12,18]
    })
    $('#toGranularity').datetimepicker({
        // format: 'LT',//time only
        format: 'HH',
        enabledHours: [9, 12,18]
    })

});

function onLeaveTypeChange() {
    console.log("onLeaveTypeChange",$("#leavecategory").val());
    if($("#leavecategory").val()=="COMPENSATION_LEAVE"){
        $(".compensation").fadeIn();
    }else{
        $(".compensation").fadeOut();
    }
}

function check(){
    console.log("on submit")
    let fromtime=new Date($("#fromtime").val()).getTime();
    let totime=new Date($("#totime").val()).getTime();
    let reasons=$("input[name='reasons']").val();
    let dissemination=$("select[name='dissemination']").val();
    let type=$("#leavecategory").val();

    if(type=="COMPENSATION_LEAVE"){
        fromtime=new Date($("#fromtime").val()+" "+$("#fromGranularity").val()+":00").getTime();
        totime=new Date($("#totime").val()+" "+$("#toGranularity").val()+":00").getTime();
    }



    if(reasons==""){
        alert("reason can not be empty");
        return false;
    }
    if(dissemination==""){
        $("select[name='dissemination']").val(new Object());
    }
    if(totime-fromtime<=0){
        alert("the to date can not less than from date");
        return false;
    }
    $("input[name='fromTime']").val(fromtime);
    $("input[name='toTime']").val(totime);
    // return false;
    return true;
}


function timeStampConvertToyyyMMdd (timeStamp) {
    let date = new Date(timeStamp);
    let Y = date.getFullYear() + '-';
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    let D = date.getDate()<10?'0'+date.getDate():date.getDate();
    return Y + M + D ;
}