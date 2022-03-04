<?php
include './adminheader.php';
if(isset($_GET['eid'])) {
    mysqli_query($link, "delete from newevent where eventname='$_GET[eid]'");
}
$result = mysqli_query($link, "select eventname from newevent") or die(mysqli_error($link));
    echo "<table class='report_tab' style='min-width:250px;float:none;margin:auto;'><thead><tr><th colspan='2' class='center'>EVENTS LIST";
    echo "<tr><th>Event Name<th>Task<tbody>";
    
    while($row=mysqli_fetch_row($result)) {
	echo "<tr>";
        foreach($row as $k=>$r) {
                echo "<td>$r";
        }
        echo "<td><a href='viewevents.php?eid=$row[0]' onclick=\"javascript:return confirm('Are You Sure to Delete ?')\">Delete</a>";
    }
    echo "</tbody></table>";
    mysqli_free_result($result);
include './footer.php';
?>