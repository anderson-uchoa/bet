/**
 * DHTML date validation script. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */
// Declaring valid date character, minimum year and maximum year

function isInteger(s){
    for (var i = 0; i < s.length; i++){
        var c = s.charAt(i);
        if ((c < "0") || (c > "9")) return false;
    }
    return true;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function validaDataHora(id){
	var ok = true;
	var formulario = document.getElementById(id);
	var campos = formulario.getElementsByTagName('input');
	for (var i = 0; i < campos.length; i++){
		if (!campos[i].ReadOnly){
			if (campos[i].className.match(/^data\s+/) || campos[i].className.match(/\s+data$/) || campos[i].className.match(/\s+data\s+/))
				if (!isDate(campos[i].value))
					ok = false;
			if (campos[i].className.match(/^hora\s+/) || campos[i].className.match(/\s+hora$/) || campos[i].className.match(/\s+hora\s+/))
				if (!isHour(campos[i].value))
					ok = false;
		}
	}
	if (ok)
		formulario.submit();
}

function isHour(hora){
	var reHora=/\d{2,2}:\d{2,2}/;
	if (!hora.match(reHora)){
		alert("O formato da hora deve ser hh:mm");
		return false;
	}
	var partesHora = hora.split(":");

	if (partesHora[0].charAt(0)=="0" && partesHora[0].length>1) partesHora[0]=partesHora[0].substring(1);
	if (partesHora[1].charAt(0)=="0" && partesHora[1].length>1) partesHora[1]=partesHora[1].substring(1);
	
	var horas=parseInt(partesHora[0]);
	var minutos=parseInt(partesHora[1]);
	
	if (horas>23){
		alert("Por favor, digite uma hora válida");
		return false;	
	}
	if (minutos>59){
		alert("Por favor, digite a hora com os minutos corretos");
		return false;	
	}
}

function isDate(data){
	var reData = /\d{2,2}\/\d{2,2}\/\d{4,4}/;
	
	if (!data.match(reData)){
		alert("O formato da data deve ser: dd/mm/aaaa");
		return false;
	}
	
	var partesData = data.split("/");

	if (!isInteger(partesData[0]) || !isInteger(partesData[1]) || !isInteger(partesData[2])){
		alert("Por favor, digite uma data válida");
		return false;
	}
	
	if (partesData[0].charAt(0)=="0" && partesData[0].length>1) partesData[0]=partesData[0].substring(1);
	if (partesData[1].charAt(0)=="0" && partesData[1].length>1) partesData[1]=partesData[1].substring(1);
	for (var i = 1; i <= 3; i++) {
		if (partesData[2].charAt(0)=="0" && partesData[2].length>1) partesData[2]=partesData[2].substring(1);
	}
	
	var day=parseInt(partesData[0]);
	var month=parseInt(partesData[1]);
	var year=parseInt(partesData[2]);

	var daysInMonth = DaysArray(12)

	if (month<1 || month>12){
		alert("Por favor, digite um mês válido");
		return false;
	}
	if (day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Por favor, digite um dia válido");
		return false;
	}
	if (year<1900 || year>2900){
		alert("Por favor, digite um ano entre "+1900+" e "+2900);
		return false;
	}
return true;
}

function ValidateForm(){
	var dt=document.frmSample.txtDate
	if (isDate(dt.value)==false){
		dt.focus()
		return false
	}
    return true
 }