<?php
include './db.php';
extract($_POST);
    $res = mysqli_query($link, "select uname,city from newuser where userid='$userid' and pwd='$pwd'");
    if(mysqli_num_rows($res)>0) {
        $row = mysqli_fetch_row($res);
        $output = "ok~$row[0]~$row[1]";
    } else {
        $output = "Invalid Userid/Password...!";
    }
    print($output);
    mysqli_close($link);
?>