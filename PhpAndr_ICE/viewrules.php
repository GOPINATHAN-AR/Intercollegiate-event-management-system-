<?php
include './adminheader.php';
if(isset($_GET['rid'])) {
    mysqli_query($link, "delete from ruless where id='$_GET[rid]'");
}
$result = mysqli_query($link, "select id,ruletxt from rules") or die(mysqli_error($link));
    
    echo "<table class='report_tab' style='min-width:450px;float:none;margin:auto;'><thead><tr><th colspan='2' class='center'>RULES LIST";
    echo "<tr><th>Rule<th>Task<tbody>";
    while($row=mysqli_fetch_row($result)) {
	echo "<tr>";
        foreach($row as $k=>$r) {
            if($k!=0) {
                echo "<td>".substr($r,0,50)."...";
            }
        }
        echo "<td><a href='viewrules.php?rid=$row[0]' onclick=\"javascript:return confirm('Are You Sure to Remove ?')\">Delete</a>";
    }
    echo "</tbody></table>";
    echo "</form>";
    mysqli_free_result($result);
include './footer.php';
?>