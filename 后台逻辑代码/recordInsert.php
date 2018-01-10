<?php 
	$HardType = $_GET['HardType'];
	$RiceType = $_GET['RiceType'];
	$WarmTime = $_GET['WarmTime'];
	$cookTime = $_GET['cookTime'];
	$iWarmTime = (int)$WarmTime;
	$mysql_username='root'; //改成自己的mysql数据库用户名
	$mysql_password='Zwh5624959'; //改成自己的mysql数据库密码
	$mysql_database='cooker'; //改成自己的mysql数据库名
	$conn=mysql_connect("localhost",$mysql_username,$mysql_password) or die("error connecting to MySQL") ; //连接数据库
	mysql_query("set names 'utf8'"); //数据库输出编码 应该与你的数据库编码保持一致
	mysql_select_db($mysql_database); //打开数据库
	$sql ="select * from cookerInfo"; //SQL语句
	$result = mysql_query($sql,$conn)or die("error connecting to select"); //查询
	$recordCount = mysql_num_rows($result)+1;
	$sqlInsert = "insert into cookerInfo values(".$recordCount.",'".$HardType."','".$RiceType."','".$cookTime."',".$iWarmTime.",1)";
	mysql_query($sqlInsert,$conn) or die("error connecting to insert") ;
	mysql_close($conn);
?>
