<?php
session_start();
if(!isset($_SESSION['adminuserid'])) {
    header("Location:index.php");
}
include './db.php';
?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<!--
Template 2092 Shelf
http://www.tooplate.com/view/2092-shelf
-->
    <title>Inter College</title>

    <!-- load stylesheets -->
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Bootstrap style -->
    <link rel="stylesheet" href="css/tooplate-style.css">
    <!-- Templatemo style -->
</head>
    <body>
        
        <div class="container">
            <header class="tm-site-header" style="background:none;height:190px;padding-top: 5px;">
                <h1 class="tm-site-name" style="text-align: center;color:gray;">Inter College Event Portal</h1>
                <p class="tm-site-description"></p>
                
                <nav class="navbar navbar-expand-md tm-main-nav-container">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#tmMainNav" aria-controls="tmMainNav" aria-expanded="false" aria-label="Toggle navigation">
                            <i class="fa fa-bars"></i>
                    </button>

                    <div class="collapse navbar-collapse tm-main-nav" id="tmMainNav">
                        <ul class="nav nav-fill tm-main-nav-ul">
                            <li class="nav-item"><a class="nav-link active" href="adminhome.php">Events</a></li>
                            <li class="nav-item"><a class="nav-link active" href="aviewparticipants.php">Participants</a></li>
                            <li class="nav-item"><a class="nav-link active" href="prelims.php">Prelims</a></li>
                            <li class="nav-item"><a class="nav-link active" href="mains.php">Mains</a></li>
                            <li class="nav-item"><a class="nav-link active" href="afeedbacks.php">Feedback</a></li>
                            <li class="nav-item"><a class="nav-link active" href="rules.php">Rules</a></li>
                            <li class="nav-item"><a class="nav-link active" href="resetp.php">Reset</a></li>
                            <li class="nav-item"><a class="nav-link" href="signout.php">SignOut</a></li>
                        </ul>
                    </div>
                </nav>
                
            </header>
            
            <div class="tm-main-content">
                <section class="tm-margin-b-l">