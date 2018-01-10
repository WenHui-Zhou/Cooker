<?php 
	$workPattern = $_GET['workPattern'];//0 1
	$workState = $_GET['workState']; //8
	$workTemp = $_GET['Temp'];
	$cookTime = $_GET['cookTime'];


	$mysql_username='root'; //改成自己的mysql数据库用户名
	$mysql_password='Zwh5624959'; //改成自己的mysql数据库密码
	$mysql_database='cooker'; //改成自己的mysql数据库名
	$conn=mysql_connect("localhost",$mysql_username,$mysql_password) or die("error connecting to MySQL") ; //连接数据库
	mysql_query("set names 'utf8'"); //数据库输出编码 应该与你的数据库编码保持一致
	mysql_select_db($mysql_database); //打开数据库
	$sql ="UPDATE cookerState SET workPattern ='".$workPattern."',workTemp='".$workTemp."',workState='".$workState."',cookTime='".$cookTime."' WHERE 1"; //SQL语句
	echo $sql;
	mysql_query($sql,$conn)or die("error connecting to Update");
	echo "answer";
	mysql_close($conn);
?>