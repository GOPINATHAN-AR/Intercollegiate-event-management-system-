<?php
include './adminheader.php';
if(isset($_GET['fid'])) {
    mysqli_query($link, "delete from feedbacks where id='$_GET[fid]'");
}
$result = mysqli_query($link, "select id,dt,uname,city,feedback from feedbacks f,newuser n where f.userid=n.userid") or die(mysqli_error($link));
    
    echo "<table class='report_tab' style='min-width:450px;float:none;margin:auto;'><thead><tr><th colspan='5' class='center'>FEEDBACK LIST";
    echo "<tr><th>Date<th>College Name<th>City<th>Feedback<th>Task<tbody>";
    while($row=mysqli_fetch_row($result)) {
	echo "<tr>";
        foreach($row as $k=>$r) {
            if($k!=0)
                echo "<td>$r";
        }
        echo "<td><a href='afeedbacks.php?fid=$row[0]' onclick=\"javascript:return confirm('Are You Sure to Remove ?')\">Delete</a>";
    }    
    echo "</tbody></table>";
    echo "</form>";
    mysqli_free_result($result);
include './footer.php';
?>