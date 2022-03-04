<?php
include './adminheader.php';
if(!isset($_POST['submit1'])) {    
?>
<div class="right">
    <a href="viewevents.php">View Events</a>
</div>
<form name="f" action="adminhome.php" method="post">
    <table class="center_tab">
        <thead>
            <tr>
                <th colspan="2" class="center">NEW EVENT</th>
            </tr>
        </thead>
        <tbody>            
            <tr>
                <th>Event Name</th>
                <td><input type="text" name="eventname" autofocus required></td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="2" class="center">
                    <input type="submit" name="submit1" value="Store">
                </td>
            </tr>
        </tfoot>
    </table>
    <br><br>
</form>
<?php
} else {
    extract($_POST);

    mysqli_query($link, "insert into newevent(eventname) values('$eventname')") or die(mysqli_error($link));
    echo "<div class='center'>Event Created Successfully...!<br><a href='adminhome.php'>Back</a></div>";
}
include './footer.php';
?>