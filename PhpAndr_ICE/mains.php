<?php
include './adminheader.php';
if(!isset($_POST['submit1']) && !isset($_POST['submit2'])) {    
    $res1  = mysqli_query($link, "select userid,uname from newuser");
?>
<div class="right">
    <a href="aviewmains.php">View Mains</a>
</div>
<form name="f" action="mains.php" method="post">
    <table class="center_tab">
        <thead>
            <tr>
                <th colspan="2" class="center">MAINS SELECTION</th>
            </tr>
        </thead>
        <tbody>            
            <tr>
                <th>Select College</th>
                <td>
                    <select name="clg" required>
                        <?php
                        while($row1=  mysqli_fetch_row($res1)) {
                            echo "<option value='$row1[0]'>$row1[1]</option>";
                        }
                        mysqli_free_result($res1);
                        ?>
                    </select>
                </td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="2" class="center">
                    <input type="submit" name="submit1" value="Fetch Participants">
                </td>
            </tr>
        </tfoot>
    </table>
    <br><br>
</form>
<?php
} else if(isset($_POST['submit1']) && !isset($_POST['submit2'])){
    extract($_POST);

    $result = mysqli_query($link, "select dept,studname,event,id from applications where userid='$clg' and selectflag='prelims' order by dept") or die(mysqli_error($link));
    echo "<form name='f' action='mains.php' method='post'>";
    echo "<table class='report_tab' style='min-width:450px;float:none;margin:auto;'><thead><tr><th colspan='3' class='center'>MAINS LIST";
    echo "<tr><th><th>Student Name<th>Event Name<input type='hidden' name='clg' value='$clg'><tbody>";
    $dept="";
    while($row=mysqli_fetch_row($result)) {
        if(strcasecmp($dept, "")==0 || strcasecmp($dept, $row[0])!=0) {
            echo "<tr><th colspan='3' style='border-bottom:double;border-top:solid thin;'>&nbsp;$row[0]";
            $dept=$row[0];
        }
	echo "<tr><td><input type='checkbox' name='s[]' value='$row[3]' style='min-height:0px;'>";
        foreach($row as $k=>$r) {
            if($k!=0 && $k!=3)
                echo "<td>$r";
        }
        //echo "<td><a href='viewevents.php?eid=$row[0]' onclick=\"javascript:return confirm('Are You Sure to Delete ?')\">Delete</a>";
    }
    echo "<tr><th colspan='3' class='center' style='border-top:solid thin;'><input type='submit' name='submit2' value='Submit'>";
    echo "</tbody></table>";
    echo "</form>";
    mysqli_free_result($result);
    echo "<div class='center'><br><a href='mains.php'>Back</a></div>";
} else if(isset($_POST['submit2'])) {
    extract($_POST);
    if(isset($_POST['s'])) {
        foreach($s as $ss) {
            mysqli_query($link, "update applications set selectflag='mains' where id='$ss' and userid='$clg'");
        }
        echo "<div class='center'><br>Mains Selected...!<br><a href='mains.php'>Back</a></div>";
    } else {
        echo "<div class='center'><br>Select any Candidate...!<br><a href='mains.php'>Back</a></div>";
    }
}
include './footer.php';
?>