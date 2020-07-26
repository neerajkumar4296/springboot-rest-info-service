<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Weather-Report</title>
<style>

div{

	background-color: #F2B209;
	padding: 2px;
    flex-direction: column;
    border-radius: 5px;
}

table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 80%; 
  margin:auto;
  align:center;
  border-radius:4px;
}


td {
  border: 2px solid white;
  text-align: left;
  padding: 3px;
  font-size: 16px;

}

tr{
background-color:  #87ceeb;
}

h3{
color: black; 
font-family:verdana; 
background-color:#87ceeb;
padding:3px;
width:79%;
margin-left: auto;
margin-right: auto;
text-align: center;
}

</style>
</head>

<body>
  
<div>
<p><span style="color:black;font-size:12px;font-family:verdana;">Greetings,</span></p>

<h3 >Weather Report for:: ${cityName}(${countryCode})</h3>	


<table>

  <tr>
    <td>weather type</td>
    <td>${weatherType}</td>
  </tr>
  <tr>
    <td>current Temperature</td>
    <td>${currentTemperature}</td>
  </tr>
  <tr>
    <td>maximum Temperature</td>
    <td>${maxmTemperature}</td>
  </tr>
  <tr>
    <td>minimum Temperature</td>
    <td>${MinTemperature}</td>
  </tr>
  <tr>
    <td>actual Temperature feel</td>
    <td>${actualFeelTemperature}</td>
  </tr>
  <tr>
    <td>humidity</td>
    <td>${humdity}</td>
  </tr>
  <tr>
    <td>wind speed</td>
    <td>${windSpeed}</td>
  </tr>
  
    <tr>
    <td>sunrise</td>
    <td>${sunrise}</td>
  </tr>
  
    <tr>
    <td>sunset</td>
    <td>${sunset}</td>
  </tr>


</table>
<p><span style="color:black;font-size:12px;font-family:verdana;">Thanks for using weather reporting service,</span></p>

<p style="color:black;font-size:12px;font-family:verdana;"><i>Note: weather reporting service currently runs at 06:00 AM everyday.</i></p>
</div>
</body>
</html>