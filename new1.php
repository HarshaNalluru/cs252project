<?php
require_once 'db_config.php';
$con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE) or die(mysqli_connect_error());
$phone = '7755057794';
$messagesent = "abhishek128959@gmail.com:asdfg:asdfgh:7755047794:~";
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
echo $email;
echo $subject;
echo $body;
echo $number;
if($number === '7755047794'){
	echo 's';
}
/*$result = mysqli_query($con, "SELECT * FROM users WHERE phone='$phone'");
if(mysqli_num_rows($result) > 0) {
 $row = mysqli_fetch_assoc($result);
 $name = $row['name'];
 echo $name;
} */
?>
