<?php 
$servername = "localhost";
$username = "miguel";
$password  = "Formando13516";
$db = "cyb3rodds";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $db);

//Check connection
if (!$conn) {
	die("connection failed: " .mysqli_connect_error());
}

$username=$_REQUEST['username'];
$password=$_REQUEST['password'];

$sql = "SELECT utilizadores.id, utilizadores.username, utilizadores.password, utilizadores.banned, utilizadores.isDeleted, info_utilizadores.firstname, info_utilizadores.lastname, info_utilizadores.email, info_utilizadores.sex, info_utilizadores.country, info_utilizadores.answer, info_utilizadores.question FROM utilizadores INNER JOIN info_utilizadores ON utilizadores.id= info_utilizadores.id_utilizador WHERE utilizadores.username='$username' ";

$result = mysqli_query($conn, $sql);
$users= mysqli_fetch_array($result);

$hash=$users["password"];

if(password_verify($password,$hash)){

if (mysqli_num_rows($result)>0) {
	$dataall = [];
	$result = $conn->query($sql);


for ($i=0 ; $i < $result->num_rows ; $dataall[$i++] = $result->fetch_assoc());
	echo json_encode($dataall);

}else{
	echo "0 results";
}
}
else
{
	echo json_encode(array("errcode"=>"Your data doesn't match!"));
}
mysqli_close($conn);
?>