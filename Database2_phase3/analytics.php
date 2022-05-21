<?php

$user = $_POST['input'];
$pass = $_POST['input2'];

$myconnection = mysqli_connect('localhost', 'root', '') 
    or die ('Could not connect: ' . mysql_error());

$mydb = mysqli_select_db ($myconnection, 'bookstore') or die ('Could not select database');


// publisher login
$query = "SELECT DISTINCT special_password FROM publisher where email = '$user'";
$result = mysqli_query($myconnection, $query) or die ('Query failed: ' . mysql_error());

$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

if($row == NULL || $row["special_password"] != $pass) { // inverted conditions 
	$response["success"] = "false";
	echo json_encode($response);
	die();
}

// analytics
$query1 = "SELECT title, author, genre, ISBN, Book_Cond, type, price, trade_value FROM book WHERE publisher_name = 
(SELECT publisher_name FROM publisher WHERE email = '$user')";
$result1 = mysqli_query($myconnection, $query1) or die ('Query failed: ' . mysql_error());

$response["titles"] = array();
$response["authors"] = array();
$response["genres"] = array();
$response["isbns"] = array();
$response["conditions"] = array();
$response["types"] = array();
$response["prices"] = array();
$response["trade_vals"] = array();
$response["profits"] = array();
$response["on_wishlists"] = array();

while($testrow = mysqli_fetch_array($result1)) {
	//leave responses in json array
	array_push($response["titles"], $testrow['title']);
	array_push($response["authors"], $testrow['author']);
	array_push($response["genres"], $testrow['genre']);
	array_push($response["isbns"], $testrow['ISBN']);
	array_push($response["conditions"], $testrow['Book_Cond']);
	array_push($response["types"], $testrow['type']);
	array_push($response["prices"], $testrow['price']);
	array_push($response["trade_vals"], $testrow['trade_value']);

	$ISBN = $testrow['ISBN'];
	$Book_Cond = $testrow['Book_Cond'];

	//find how many units have been sold
	$query2 = "SELECT sum(quantity) AS sold FROM in_order WHERE ISBN = '$ISBN' AND Book_Cond = '$Book_Cond'";
	$result2 = mysqli_query($myconnection, $query2) or die ('Query failed: ' . mysql_error());

	$row2 = mysqli_fetch_array($result2, MYSQLI_ASSOC);

	array_push($response["profits"], $row2["sold"] * $testrow["price"]); // total sold * price = total profit

	//find out how many wishlists the book is on
	$query3 = "SELECT count(*) AS wished_for FROM on_wishlist WHERE ISBN = '$ISBN' AND Book_Cond = '$Book_Cond'";
	$result3 = mysqli_query($myconnection, $query3) or die ('Query failed: ' . mysql_error());

	$row3 = mysqli_fetch_array($result3, MYSQLI_ASSOC);

	array_push($response["on_wishlists"], $row3['wished_for']); // total number of wishlists with the book on it

	mysqli_free_result($result2);
	mysqli_free_result($result3);

}
$response["success"] = "true";
echo json_encode($response);

mysqli_free_result($result);
mysqli_close($myconnection);

?>