<?php
include './db.php';
extract($_POST);
$res = mysqli_query($link, "select studname,event from applications where userid='$userid' and dept='$dept'");
    if(mysqli_num_rows($res)>0) {
        $output="";
        while($row = mysqli_fetch_row($res)) {
        $output.= "Name : $row[0]~";
        $output.= "Event : $row[1]~";
        $output.="----------------------------~";
        }
    } else {
        $output = "No Record Available~";
    }
    $output = substr($output, 0, strlen($output)-1);
    print($output);
    mysqli_close($link);
?>