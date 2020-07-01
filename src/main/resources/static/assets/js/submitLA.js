
$(document).ready(function() {
    $(".compensation").hide();

    // var disabledDate = ['2020-7-1', '2020-7-2','2020-7-3'];
    $('#fromtime').datetimepicker({
        format: 'L',//date only
        format: 'YYYY-MM-DD',
        daysOfWeekDisabled: [0, 6],
        // disabledDates: disabledDate
    })
    $('#totime').datetimepicker({
        format: 'L',//date only
        format: 'YYYY-MM-DD',
        daysOfWeekDisabled: [0, 6],
        // disabledDates: disabledDate
    })
    $('#fromGranularity').datetimepicker({
        format: 'LT',//time only
        format: 'HH',
        enabledHours: [9, 12,18]
    })
    $('#toGranularity').datetimepicker({
        format: 'LT',//time only
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
    let fromtime=new Date(new Date($("#fromtime").val()).setHours(0,0,0,0)).getTime();
    let totime=new Date(new Date($("#totime").val()).setHours(24,0,0,0)).getTime();
    let reasons=$("input[name='reasons']").val();
    let type=$("#leavecategory").val();

    if(type=="COMPENSATION_LEAVE"){
        if($("#fromGranularity").val()=="09"){
            fromtime=new Date(new Date($("#fromtime").val()).setHours(0,0,0,0)).getTime();
        }else if($("#fromGranularity").val()=="12"){
            fromtime=new Date(new Date($("#fromtime").val()).setHours(12,0,0,0)).getTime();
        }else{
            fromtime=new Date(new Date($("#fromtime").val()).setHours(24,0,0,0)).getTime();
        }
        if($("#toGranularity").val()=="09"){
            totime=new Date(new Date($("#totime").val()).setHours(0,0,0,0)).getTime();
        }else if($("#toGranularity").val()=="12"){
            totime=new Date(new Date($("#totime").val()).setHours(12,0,0,0)).getTime();
        }else{
            totime=new Date(new Date($("#totime").val()).setHours(24,0,0,0)).getTime();
        }
    }


    console.log("fromtime",fromtime);
    console.log("totime",totime);

    if(reasons==""){
        alert("reason can not be empty");
        return false;
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