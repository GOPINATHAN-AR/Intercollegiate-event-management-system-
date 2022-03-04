<?php
include './db.php';
    extract($_POST);
    $b = mysqli_query($link, "insert into applications(userid,dept,studname,event) values('$userid','$dept','$studname','$event')");
    if($b) {
        $output = "ok";
    } else {
        $output = mysqli_error($link);
    }
    print $output;
    mysqli_close($link);
?>
