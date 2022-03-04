<?php
include './adminheader.php';
if(!isset($_POST['submit1'])) {    
?>
<div class="right">
    <a href="viewrules.php">View Rules</a>
</div>
<form name="f" action="rules.php" method="post">
    <table class="center_tab">
        <thead>
            <tr>
                <th colspan="2" class="center">RULES</th>
            </tr>
        </thead>
        <tbody>            
            <tr>
                <th>Enter Rules</th>
                <td><textarea name="ruletxt" autofocus required cols="50" rows="5"></textarea></td>
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

    mysqli_query($link, "insert into rules(ruletxt) values('$ruletxt')") or die(mysqli_error($link));
    echo "<div class='center'>Rules Created Successfully...!<br><a href='rules.php'>Back</a></div>";
}
include './footer.php';
?>