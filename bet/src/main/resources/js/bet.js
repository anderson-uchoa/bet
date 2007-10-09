function contaCheckboxesSelecionados(id){
	var table = document.getElementById(id);
	var chks = table.getElementsByTagName("input");
	var count = 0;
	for (var i = 0; i < chks.length; i++){
		if (chks[i].getAttribute("type") == "checkbox" && chks[i].checked)
			count++;
	}
	return count;
}

function verificaAlterar(id, formID){
	var count = contaCheckboxesSelecionados(id);
	if (count == 0)
		alert("Nenhuma linha foi selecionada!");
	else if (count > 1)
		alert("Somente uma linha pode ser selecionada");
	else{
		insereOperacao(formID, "alterar");
		document.getElementById(formID).submit();
	}
}

function verificaRemover(id, formID){
	var count = contaCheckboxesSelecionados(id);
	if (count == 0)
		alert("Nenhuma linha foi selecionada!");
	else{
		insereOperacao(formID, "remover");
		document.getElementById(formID).submit();
	}
}

function insereOperacao(formID, operacao){
	var operInput = document.createElement("input");
	operInput.setAttribute("type", "hidden");
	operInput.setAttribute("name", "operacao");
	operInput.setAttribute("value", operacao);
	document.getElementById(formID).appendChild(operInput);
}

function dataHoje(){
   var hoje = new Date();
   return hoje.toLocaleDateString();
}

function toggleElementVisibility(id){
	var elem = document.getElementById(id);
	elem.style.display = (elem.style.display == '') ? 'block' : '';
}

function controlaCamposData(opcao){
	document.getElementById("dtCorrida").ReadOnly = (opcao == "multipla");
	document.getElementById("dtInicio").ReadOnly = (opcao == "unica");
	document.getElementById("dtFim").ReadOnly = (opcao == "unica");
	document.getElementById("periodicidade").ReadOnly = (opcao == "unica");
}

function redireciona(url){
	document.location.href = url;
}