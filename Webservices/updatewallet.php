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

$cyb3r=$_REQUEST['cyb3rmoney'];
$money=$_REQUEST['realmoney'];
$id=$_REQUEST['id_utilizador'];

	$sql="UPDATE utilizador_carteira SET cyb3rmoney='".$cyb3r."', realmoney='".$money."' WHERE id_utilizador='".$id."' ";

$result = mysqli_query($conn, $sql);
	if ($result)
		echo json_encode(array("errcode"=>200));
	else
		echo json_encode(array("errcode"=>400));

mysqli_close($conn);
?>