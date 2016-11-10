<?php
$response = array();
require_once 'db_config.php';
$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE) or die(mysqli_connect_error());

//var_dump($_GET);
$messagesent = $_GET['messagesent'];
//$phone = trim($myArray[3]);
$i=0;
$k=0;
$p=0;
while(substr($messagesent,$i,1)!='~'){
	if(substr($messagesent,$i,1)===':'){
		if($p==0){
			$email = substr($messagesent,$k,$i);
		}
		else if($p==1){
			$subject = substr($messagesent,$k+1,$i-$k-1);
		}
		else if($p==2){
			$body = substr($messagesent,$k+1,$i-$k-1);
		}
		else{
			$number = substr($messagesent,$k+1,$i-$k-1);
		}
		$p++;
		$k=$i;
	}
	$i++;
}
$number = trim($number);
//print_r($myArray);
if(mysqli_query($con, "INSERT INTO messages(phone,messagesent) VALUES('$number', '$messagesent')")) {
 $response["result"] = "success";
 $response["data"] = "entered successfully";
} else {
    $response["result"] = "failure";
 $response["data"] ="error occured";
}

echo json_encode($response);

if($stmt = $con->prepare('SELECT name FROM users WHERE phone=?')){
$stmt->bind_param('s',$number);
$stmt->execute();
$stmt->bind_result($name);
while ($stmt->fetch()) {
        printf ("%s ", $name);
    }
$stmt->close();

}

//$result = mysqli_query($con, "SELECT * FROM users WHERE phone='".$number."'");
//$myArray[0]

///////////////////////////////////////////////////
/**
 * This example shows settings to use when sending via Google's Gmail servers.
 */
//SMTP needs accurate times, and the PHP time zone MUST be set
//This should be done in your php.ini, but this is how to do it if you don't have access to that
date_default_timezone_set('Etc/UTC');
require 'phpmailer/PHPMailerAutoload.php';
//Create a new PHPMailer instance
$mail = new PHPMailer;
//Tell PHPMailer to use SMTP
$mail->isSMTP();
//Enable SMTP debugging
// 0 = off (for production use)
// 1 = client messages
// 2 = client and server messages
//$mail->SMTPDebug = 2;
//Ask for HTML-friendly debug output
//$mail->Debugoutput = 'html';
//Set the hostname of the mail server
$mail->Host = 'smtp.gmail.com';
// use
// $mail->Host = gethostbyname('smtp.gmail.com');
// if your network does not support SMTP over IPv6
//Set the SMTP port number - 587 for authenticated TLS, a.k.a. RFC4409 SMTP submission
$mail->Port = 587;
//Set the encryption system to use - ssl (deprecated) or tls
$mail->SMTPSecure = 'tls';
//Whether to use SMTP authentication
$mail->SMTPAuth = true;
//Username to use for SMTP authentication - use full email address for gmail
$mail->Username = "mailbysms.official@gmail.com";
//Password to use for SMTP authentication
$mail->Password = "408257468";
//Set who the message is to be sent from
$mail->setFrom('mailbysms.official@gmail.com', 'MailBySMS ');
//Set an alternative reply-to address
$mail->addReplyTo($email, ' ');
//Set who the message is to be sent to
$mail->addAddress($email, ' ');
//Set the subject line
$mail->Subject = $subject;
$mail->Body    = $body . ' Sent From :' . $name . ' via MailBySMS';
//$mail->AltBody = 'This is the body in plain text for non-HTML mail clients';
//Read an HTML message body from an external file, convert referenced images to embedded,
//convert HTML into a basic plain-text alternative body
//$mail->msgHTML(file_get_contents('contents.html'), dirname(__FILE__));
//Replace the plain text body with one created manually
$mail->AltBody = $body . ' Sent From :' . $name . ' via MailBySMS';
//Attach an image file
//$mail->addAttachment('images/phpmailer_mini.png');
//send the message, check for errors
if (!$mail->send()) {
    //echo "Mailer Error: " . $mail->ErrorInfo;
} else {
    //echo "Message sent!";
}
///////////////////////////////////////////////////

?>