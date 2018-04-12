function printPDF(path,printConfig){

    var code;

    if(!path){
        //$.messager.alert("提示","找不文件路径","error");
        throw new Error("找不到打印文件路径");
    }

    if(!printConfig){

        //code = "cmd.exe /C start acrord32 /P /h"+path;
        code = "start acrord32 "+path;


    }else{

        code = "cmd.exe /C start acrord32 /P /h"+'"'+printername+'"'+'"'+drivername+'"'+'"'+portname+'"';
    }

    callCMD(code);
}

function callCMD(callCode){

    var exec = require('child_process').exec;

    exec(callCode,function (error, stdout, stderr) {
        if (error !==null){
            console.log(error);
        }
    })
}

module.exports = printPDF;