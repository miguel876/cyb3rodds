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

$id=$_REQUEST['id'];
$firstname=$_REQUEST['firstname'];
$lastname=$_REQUEST['lastname'];
$email=$_REQUEST['email'];
$country=$_REQUEST['country'];

	$sql="UPDATE info_utilizadores SET firstname='".$firstname."', lastname='".$lastname."', email='".$email."', country='".$country."' WHERE id_utilizador='".$id."' ";

$result = mysqli_query($conn, $sql);
	if ($result)
		echo json_encode(array("errcode"=>200));
	else
		echo json_encode(array("errcode"=>400));

mysqli_close($conn);
?>