<?php
$response = array();
require_once 'db_config.php';
$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE) or die(mysqli_connect_error());

//var_dump($_GET);
$messagesent = $_GET['messagesent'];

if(mysqli_query($con, "INSERT INTO usermessages(email,messagesent) VALUES('anja', '$messagesent')")) {
 $response["result"] = "success";
 $response["data"] = "entered successfully";
} else {
    $response["result"] = "failure";
 $response["data"] ="error occured";
}
echo json_encode($response);
?>