console.log("hello,world");

function printStr(a) {
    console.log(a);
}
//暴露出去，能够被require获得
module.exports=printStr;