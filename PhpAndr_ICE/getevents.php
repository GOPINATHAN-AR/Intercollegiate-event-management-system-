<?php
include './db.php';
$res = mysqli_query($link, "select eventname from newevent");
    if(mysqli_num_rows($res)>0) {
        $output="";
        while($row = mysqli_fetch_row($res))
        $output .= "$row[0]~";
    } else {
        $output = "No Event Available~";
    }
    $output = substr($output, 0, strlen($output)-1);
    print($output);
    mysqli_close($link);
?>