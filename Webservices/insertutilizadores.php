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
$firstname=$_REQUEST['firstname'];
$lastname=$_REQUEST['lastname'];
$email=$_REQUEST['email'];
$date_created=$_REQUEST['date_created'];
$sex=$_REQUEST['sex'];
$country=$_REQUEST['country'];
$answer=$_REQUEST['answer'];
$question= $_REQUEST['question'];

$options=['cost'=>11];
$hash=password_hash($password, PASSWORD_BCRYPT, $options);

	$sql = "INSERT INTO utilizadores(username, password, isDeleted, banned, group_id) VALUES ('".$username."', '".$hash."', 0, 0, 1)";
	$result = mysqli_query($conn, $sql);
	
$id=mysqli_insert_id($conn);

	if ($result){
		echo json_encode(array("errcode"=>"You can now login into your new account"));
	

	$sql2="INSERT INTO info_utilizadores(id_utilizador, firstname, lastname, email, date_created, profile_img, sex, country, answer, question) VALUES ('".$id."','".$firstname."', '".$lastname."', '".$email."', '".$date_created."','Default.png', '".$sex."','".$country."','".$answer."','".$question."')";

$result2 = mysqli_query($conn, $sql2);
	

	$sql3="INSERT INTO utilizador_carteira(id_utilizador, cyb3rmoney, realmoney) VALUES ('".$id."',50000,30)";

$result3 = mysqli_query($conn, $sql3);
	
}else
		echo json_encode(array("errcode"=>"This username already exists"));

mysqli_close($conn);
?>