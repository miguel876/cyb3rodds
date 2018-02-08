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
//----Verificar password na base de dados---
$id=$_REQUEST['id'];
$password=$_REQUEST['password'];
$nova=$_REQUEST['nova'];

$sql2="SELECT password FROM utilizadores WHERE id='$id'";

$result = mysqli_query($conn, $sql2);
$users= mysqli_fetch_array($result);

$hash=$users["password"];

if(password_verify($password,$hash)){



$options=['cost'=>11];
$hash2=password_hash($nova, PASSWORD_BCRYPT, $options);

$sql = "UPDATE utilizadores SET password= '$hash2' WHERE id='$id' ";
$result2 = mysqli_query($conn, $sql);

if ($result2)
		echo json_encode(array("errcode"=>"Your password has been successfully changed!"));
	else
		echo json_encode(array("errcode"=>400));
}
else
{
	echo json_encode(array("errcode"=>"Your old password is wrong!"));
}
mysqli_close($conn);
?>