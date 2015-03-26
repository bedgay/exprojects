<?php session_start(); 
//set_time_limit(1000); // ini_set("max_execution_time","1000");
require_once 'bonsai/bonsai-env.php';
//ini_set("post_max_size", "2M");
Dispatcher::go();
?>