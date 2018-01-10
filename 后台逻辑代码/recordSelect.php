<?php
	$mysql_username='root'; //改成自己的mysql数据库用户名
	$mysql_password='Zwh5624959'; //改成自己的mysql数据库密码
	$mysql_database='cooker'; //改成自己的mysql数据库名
	$conn=mysql_connect("localhost",$mysql_username,$mysql_password) or die("error connecting to MySQL") ; //连接数据库
	mysql_query("set names 'utf8'"); //数据库输出编码 应该与你的数据库编码保持一致
	mysql_select_db($mysql_database); //打开数据库
	$sql ="select * from cookerInfo ORDER BY cookId DESC"; //SQL语句
	$result = mysql_query($sql,$conn)or die("error connecting to select"); //查询
	if (mysql_num_rows($result) > 0) {
		$response["records"] = array();
		while ($row = mysql_fetch_array($result)) {
			$record= array();
			$record["HardType"] = $row[1];
			$record["RiceType"] = $row[2];
			$record["cookTime"] = $row[3];
			$record["WarmTime"] = $row[4];
			// 将一个记录放到字符集里头
			array_push($response["records"], $record);
		}
		// echoing JSON response
		echo json_encode($response);
	}
	mysql_close($conn);
?>