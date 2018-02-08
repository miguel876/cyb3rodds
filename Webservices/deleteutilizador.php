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

	$sql="UPDATE utilizadores SET isDeleted=1  WHERE id='".$id."' ";

$result = mysqli_query($conn, $sql);
	if ($result)
		echo json_encode(array("errcode"=>200));
	else
		echo json_encode(array("errcode"=>400));

mysqli_close($conn);
?>