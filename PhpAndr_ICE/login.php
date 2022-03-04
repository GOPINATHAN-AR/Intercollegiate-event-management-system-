<?php
include './header.php';
?>
<header>
    <h2 class="tm-main-title">Login</h2>
</header>    
<?php
if(!isset($_POST['submit1'])) {
?>
<form name="f" action="login.php" method="post">
    <table class="login">
        <thead>
            <tr>
                <th colspan="2" class="text-center">LOGIN</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>User Id</th>
                <td><input type="text" name="userid" autofocus required></td>
            </tr>
            <tr>
                <th>Password</th>
                <td>
                    <input type="password" name="pwd" required>
                </td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="2" class="center">
                    <input type="submit" name="submit1" value="Login">
                </td>
            </tr>
        </tfoot>
    </table>
    <Br><br>
</form>
<?php
} else {
    extract($_POST);    
        $result = mysqli_query($link, "select * from admin where userid='$userid' and 
pwd='$pwd'") or die(mysqli_error($link));
        if(mysqli_num_rows($result)>0) {
            $_SESSION['adminuserid'] = $userid;
            header("Location:adminhome.php");
        } else {
            echo "<div class='center'>Invalid Userid/Password...!<br><a href='index.php'>Back</a></div>";
        }
        mysqli_free_result($result);
}
include './footer.php';
?>