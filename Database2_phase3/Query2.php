<?php

$user = $_POST['input'];
$pass = $_POST['input2'];

$myconnection = mysqli_connect('localhost', 'root', '') 
    or die ('Could not connect: ' . mysql_error());

$mydb = mysqli_select_db ($myconnection, 'bookstore') or die ('Could not select database');

// customer login
$query = "SELECT DISTINCT password FROM customer where email = '$user'";
$result = mysqli_query($myconnection, $query) or die ('Query failed: ' . mysql_error());

$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

if($row == NULL || $row["password"] != $pass) { // inverted conditions 
	$response["success"] = "false";
	echo json_encode($response);
	die();
}

$query1 = "SELECT DISTINCT * FROM orders A, book B, in_order C WHERE A.Order_Num = C.Order_Num AND B.ISBN = C.ISBN AND A.Email = '$user' AND B.Book_Cond = C.Book_Cond";
$result1 = mysqli_query($myconnection, $query1) or die ('Query failed: ' . mysql_error());

$response["Titles"] = array();
$response["ISBN"] = array();
$response["Book_Cond"] = array();
$response["Types"] = array();
$response["Order_Num"] = array();
$response["Quantity"] = array();
$response["Shipping_Method"] = array();
$response["Shipping_Addr"] = array();

while($testrow = mysqli_fetch_array($result1)) {
	//leave responses in json array
	array_push($response["Titles"], $testrow['Title']);
	array_push($response["ISBN"], $testrow['ISBN']);
	array_push($response["Book_Cond"], $testrow['Book_Cond']);
	array_push($response["Types"], $testrow['Type']);
	array_push($response["Order_Num"], $testrow['Order_Num']);
	array_push($response["Quantity"], $testrow['Quantity']);
	array_push($response["Shipping_Method"], $testrow['Shipping_Method']);
	array_push($response["Shipping_Addr"], $testrow['Shipping_Addr']);

}

$response["success"] = "true";
echo json_encode($response);

mysqli_free_result($result1);
mysqli_free_result($result);
mysqli_close($myconnection);

?>