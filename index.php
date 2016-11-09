<?php
session_start();
include_once 'dbconnect.php';
?>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" >
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.php">CS252-PROJECT</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar1">
            <ul class="nav navbar-nav navbar-right">
                <?php if (isset($_SESSION['usr_id'])) { ?>
                <li><p class="navbar-text">Signed in as <?php echo $_SESSION['usr_name']; ?></p></li>
                <li><a href="logout.php">Log Out</a></li>
                <?php } else { ?>
                <li><a href="login.php">Login</a></li>
                <li><a href="register.php">Sign Up</a></li>
                <?php } ?>
            </ul>
        </div>
    </div>
</nav>
<div class="container text-center">
    <h1><?php 
		
        if($_SESSION['usr_name']){
            echo "<h1>Hello, ";
			echo $_SESSION['usr_name']; 
            echo "</h1>";
			if($stmt = $con->prepare('SELECT messagesent FROM messages WHERE phone=?')){
			$stmt->bind_param('s',$_SESSION['usr_phone']);
			$stmt->execute();
			$stmt->bind_result($messagesent);
            $i=1;
            echo '<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
			while ($stmt->fetch()) {
				//printf ("%s ", $messagesent);
                echo '<div class="panel panel-default">';
                    echo '<div class="panel-heading" role="tab" id="heading-';
                    echo $i; 
                    echo '">';
                    echo   '<h2 class="panel-title">';
                    echo     '<a data-toggle="collapse" data-parent="#accordion" href="#collapse-';
                    echo $i;
                    echo '" aria-expanded="true" aria-controls="collapseOne">';
                    echo "string";
                    echo '    </a>
                      </h2>
                    </div>';
                    echo '<div id="collapse-';
                    echo $i;
                    echo '" class="panel-collapse collapse';
                    if ($i==1) { echo 'in'; } 
                    echo'" role="tabpanel" aria-labelledby="heading-';
                    echo $i; 
                    echo '">
                      <div class="panel-body">';
                    printf ("%s \n", $messagesent);

                    echo '
                      </div>
                    </div>
                </div>';
                $i++;
			}
            echo '</div>';
			$stmt->close();

			}

        }
		else{
			header("Location: login.php");
		}
        
        ?> 
 
    </h1>

</div>
<script   src="https://code.jquery.com/jquery-3.1.1.min.js"   integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="   crossorigin="anonymous"></script>

<script src="js/bootstrap.min.js"></script>
</body>
</html>
