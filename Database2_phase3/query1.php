<?php
$input = $_POST['input'];

$myconnection = mysqli_connect('localhost', 'root', '') 
    or die ('Could not connect: ' . mysql_error());

$mydb = mysqli_select_db ($myconnection, 'bookstore') or die ('Could not select database');

// grab movie info from movies table
$query = "SELECT Title, Book_Cond, Author, Genre, Price FROM book WHERE title LIKE '%$input%' OR author LIKE '%$input%'";
$result = mysqli_query($myconnection, $query) or die ('Query failed: ' . mysql_error());

$response["titles"] = array();
$response["genres"] = array();
$response["conditions"] = array();
$response["prices"] = array();
$response["authors"] = array();

while($testrow = mysqli_fetch_array($result)) {
	//leave responses in json array
	array_push($response["titles"], $testrow['Title']);
	array_push($response["genres"], $testrow['Genre']);
	array_push($response["authors"], $testrow['Author']);
	array_push($response["prices"], $testrow['Price']);
	array_push($response["conditions"], $testrow['Book_Cond']);
}
$response["success"] = "true";
echo json_encode($response);


?>
