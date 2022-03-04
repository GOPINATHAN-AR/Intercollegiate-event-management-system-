<?php
include './adminheader.php';
$result = mysqli_query($link, "select dept,studname,event,uname from applications a,newuser n where a.userid=n.userid and selectflag='prelims' order by dept") or die(mysqli_error($link));
    
    echo "<table class='report_tab' style='min-width:450px;float:none;margin:auto;'><thead><tr><th colspan='2' class='center'>PRELIMS LIST";
    echo "<tr><th>Student Name<th>Event Name<tbody>";
    $dept="";
    while($row=mysqli_fetch_row($result)) {
        if(strcasecmp($dept, "")==0 || strcasecmp($dept, $row[0])!=0) {
            echo "<tr><th colspan='2' style='border-bottom:double;border-top:solid thin;'>$row[0] [$row[3]]";
            $dept=$row[0];
        }
	echo "<tr>";
        foreach($row as $k=>$r) {
            if($k!=0&&$k!=3)
                echo "<td>$r";
        }        
    }    
    echo "</tbody></table>";
    echo "</form>";
    mysqli_free_result($result);
include './footer.php';
?>
