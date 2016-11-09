<?php header('Access-Control-Allow-Origin: *'); ?>
<?php header('Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept');?>
<?php header('Access-Control-Allow-Methods: POST, GET, OPTIONS, PUT'); ?>

<?php
 
/*
 * All database connection variables
 */
 
define('DB_USER', "root"); // db user
define('DB_PASSWORD', "1234"); // db password (mention your db password here)
define('DB_DATABASE', "cs252-users"); // primary database name
define('DB_SERVER', "localhost"); // db server
?>
