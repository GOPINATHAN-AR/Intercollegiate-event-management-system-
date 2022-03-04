<?php
include './db.php';
    extract($_POST);
    $dt=date('Y-m-d',time());
    $b = mysqli_query($link, "insert into feedbacks(dt,userid,feedback) values('$dt','$userid','$feedback')");    
    if($b) {
        $output = "ok";
    } else {
        $output = mysqli_error($link);
    }
    print $output;
    mysqli_close($link);
?>