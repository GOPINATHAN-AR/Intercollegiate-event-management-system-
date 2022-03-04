<?php
include './adminheader.php';
if(!isset($_POST['submit1'])) {    
    $res1  = mysqli_query($link, "select userid,uname from newuser");
?>
<form name="f" action="aviewparticipants.php" method="post">
    <table class="center_tab">
        <thead>
            <tr>
                <th colspan="2" class="center">VIEW PARTICIPANTS</th>
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
            <tr>
                <th>Select Dept</th>
                <td>
                    <select name="dept" required>
                        <option value="BCA">BCA</option>
                        <option value="BSC CS">BSC CS</option>
                        <option value="BSC IT">BSC IT</option>
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
} else {
    extract($_POST);

    $result = mysqli_query($link, "select studname,event from applications where userid='$clg' and dept='$dept'") or die(mysqli_error($link));
    echo "<table class='report_tab' style='min-width:250px;float:none;margin:auto;'><thead><tr><th colspan='2' class='center'>PARTICIPANTS LIST";
    echo "<tr><th>Student Name<th>Event Name<tbody>";
    
    while($row=mysqli_fetch_row($result)) {
	echo "<tr>";
        foreach($row as $k=>$r) {
                echo "<td>$r";
        }
        //echo "<td><a href='viewevents.php?eid=$row[0]' onclick=\"javascript:return confirm('Are You Sure to Delete ?')\">Delete</a>";
    }
    echo "</tbody></table>";
    mysqli_free_result($result);
    echo "<div class='center'><br><a href='aviewparticipants.php'>Back</a></div>";
}
include './footer.php';
?>