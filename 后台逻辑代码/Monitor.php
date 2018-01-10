<?php
	$mysql_username='root'; //改成自己的mysql数据库用户名
	$mysql_password='Zwh5624959'; //改成自己的mysql数据库密码
	$mysql_database='cooker'; //改成自己的mysql数据库名
	$conn=mysql_connect("localhost",$mysql_username,$mysql_password) or die("error connecting to MySQL") ; //连接数据库
	mysql_query("set names 'utf8'"); //数据库输出编码 应该与你的数据库编码保持一致
	mysql_select_db($mysql_database); //打开数据库
	$sql ="select * from cookerInfo where CMD = true"; //SQL语句
	$result = mysql_query($sql,$conn)or die("error connecting to select"); //查询
	$row = mysql_fetch_array($result);
	$id = $row[0];
	$hardType = $row[1];
	$riceType = $row[2];
	$warmTime = $row[4];
//	echo $hardType."  ".$riceType." ".$warmTime;
	$h;
	$r;
         $sqlUp = "UPDATE cookerInfo SET CMD = 0 WHERE cookId =". $id;
//	echo $sqlUp;
         mysql_query($sqlUp,$conn)or die("error connecting to UPDATE");
//	echo "update success";
			$a = "较软";$b="软";$c="硬";
			if ($hardType == $a){
				$h = 1;
			}else if ($hardType == $b) {
				$h = 2;
			}else if ($hardType == $c) {
				$h = 3;
			}else{
				$h = 4;
			}
		//	echo "hardType"+$h;
			$d="煮饭";
			if ($riceType == $d) {
				$r = 1;
			}else{
				$r = 2;
			}
            echo "*".$h.$r.$warmTime."*";
    mysql_close($conn);
?>