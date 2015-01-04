<?php
	$host='localhost';
	$uname='root@localhost';
	$pwd='';
	$db='test';

	$con = mysql_connect($host,$uname,$pwd) or die("connection failed");
	mysql_select_db($db,$con) or die("db selection failed");
	 
	 //mysql_connect("localhost","root","");
	 //mysql_select_db("test");
	 
	//$id=$_REQUEST['id'];
	$name = $_REQUEST['username'];
	$pass = $_REQUEST['password'];
	//$password = $_REQUEST['password'];
	//$name = "abc";

	$flag['code']=3;

	mysql_query("INSERT INTO login (username, password) VALUES ('$name', '$pass')");
	
	
		//$flag['code']=1;
		//echo"hi";
	

	print(json_encode($name));
	mysql_close($con);
?>