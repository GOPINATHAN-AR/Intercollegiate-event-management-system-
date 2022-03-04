<?php
include './db.php';
extract($_POST);
$res = mysqli_query($link, "select ruletxt from rules");
    $output="";
    if(mysqli_num_rows($res)>0) {
        while($row=  mysqli_fetch_row($res)) {            
            $output.=$row[0]."~";
            $output.="----------------------------~";
        }
    } else {
        $output = "No Data Available~";
    }
    $output = substr($output, 0, strlen($output)-1);
    print($output);
    mysqli_close($link);
?>