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

$id=$_REQUEST['id_utilizador'];


$sql = "SELECT id_utilizador, cyb3rmoney, realmoney FROM utilizador_carteira WHERE id_utilizador='$id' ";

$result = mysqli_query($conn, $sql);



//echo mysqli_num_rows($result);
if (mysqli_num_rows($result)>0) {
	$dataall = [];
	$result = $conn->query($sql);

/*
	while($row = $result->fetch_assoc()){
		$dataall[] = $row;
	}
*/
// echo $result->num_rows;

for ($i=0 ; $i < $result->num_rows ; $dataall[$i++] = $result->fetch_assoc());
	echo json_encode($dataall);

//var_dump($dataall);

//var_dump($dataall);

}else{
	echo "0 results";
}

mysqli_close($conn);
?>