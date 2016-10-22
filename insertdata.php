<?php
echo "entered";
require_once 'db_config.php';
$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE) or die(mysqli_connect_error());

var_dump($_GET);
$messagesent = $_GET['messagesent'];
echo $messagesent;
if(mysqli_query($con, "INSERT INTO usermessages(email,messagesent) VALUES('anja', '$messagesent')")) {
    $successmsg = "Successfully sent!";
    echo $successmsg;
} else {
    $errormsg = "Error in sending...Please try again later!";

}
?>