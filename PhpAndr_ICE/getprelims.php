<?php
include './db.php';
extract($_POST);
$res = mysqli_query($link, "select distinct dept from applications where userid='$userid' and selectflag='prelims'");
    $output="";
    if(mysqli_num_rows($res)>0) {
        while($r=  mysqli_fetch_row($res)) {
            $output.=$r[0]."~**********************~";
        $result=  mysqli_query($link, "select studname,event from applications where userid='$userid' and dept='$r[0]' and selectflag='prelims'");
        while($row=  mysqli_fetch_row($result)) {
            $output.="Name : $row[0]~";
            $output.="Event : $row[1]~";
            $output.="----------------------------~";
        }
        mysqli_free_result($result);
        }
    } else {
        $output = "No Data Available~";
    }
    $output = substr($output, 0, strlen($output)-1);
    print($output);
    mysqli_close($link);
?>