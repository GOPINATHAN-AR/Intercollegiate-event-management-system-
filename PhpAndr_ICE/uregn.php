<?php
include './db.php';
    extract($_POST);
    $b = mysqli_query($link, "insert into newuser(uname,city,mobile,userid,pwd) values('$uname','$city','$mobile','$userid','$pwd')");    
    if($b) {
        $output = "ok";
    } else {
        $output = "Id already Exists...!";
    }
    print $output;
    mysqli_close($link);
?>