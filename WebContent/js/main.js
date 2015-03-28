function randomBanner(){
	var number = Math.floor(Math.random() * 4)+1;
	document.getElementById('dynamicBanner').src = "images/banner"+number+".jpg";
}
/*this function is responsable for return current time*/
function showGreeting(){
	var time = new Date();
	var CurrenTtime = time.getHours();
	if (CurrenTtime>=6 && CurrenTtime<=11) {
		document.getElementById('greeting').innerHTML = "Good Morning";
	} else if (CurrenTtime>=12 && CurrenTtime<=17) {
		document.getElementById('greeting').innerHTML = "Good Afternoon";
	} else if (CurrenTtime>=18 && CurrenTtime<=23) {
		document.getElementById('greeting').innerHTML = "Good Evening";
	} else {
		document.getElementById('greeting').innerHTML = "Good Night";
	}
}
//this function shows the copyright symbol and current year
function displayCopyright(){
	var year = new Date(); //var year receives a new object from Date Class 
	document.write("© " + year.getFullYear());
}

function login(){
    document.getElementById("cd-login").showModal();
}