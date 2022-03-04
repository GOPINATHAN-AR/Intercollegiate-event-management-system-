<?php
include './adminheader.php';
if(!isset($_GET['resetp']) && !isset($_GET['resetp1'])) {
    echo "<div class='center'><a href='resetp.php?resetp' onclick=\"javascript:return confirm('Are You Sure to Reset Project Data ?')\">Reset Project Data</div>";
} else if(isset($_GET['resetp'])) {
    echo "<div class='center'>This will remove all Project Data...!<br><a href='resetp.php?resetp1' onclick=\"javascript:return confirm('Are You Sure to continue ?')\">Continue...!</div>";
} else if(isset($_GET['resetp1'])) {
    mysqli_query($link, "delete from rules");
    mysqli_query($link, "delete from feedbacks");
    mysqli_query($link, "delete from applications");
    mysqli_query($link, "delete from newuser");
    mysqli_query($link, "delete from newevent");
    echo "<script>location.href='resetp.php';</script>";
}
include './footer.php';
?>