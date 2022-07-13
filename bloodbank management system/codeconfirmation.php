<?php
session_start();
$wrongcode=false;
if($_SERVER["REQUEST_METHOD"]=="POST"){
  $x=$_SESSION['code'];
  if($_POST['code']==$x){
     header("Location: signup.php");
  }else{
     $wrongcode=true;
  }
}
?>


<!DOCTYPE html>
<html lang="en">
<head>
     <link rel="stylesheet" href="css/login.css">
        <title>Code Verification</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <style>
            input[type=checkbox] {
                transform: scale(1.5);
            }
        </style>
</head>
<body>
     <div class="navbar">
            <div id="navpart">
                <div id="part1">
                    <a href="index.html"><i class="fa fa-solid fa-house"></i> &nbsp;Home</a>
                </div>
                <div id="part2">
                    <a href="becomedonor.html">Become a donor</a>
                    <a href="login.php"><i class="fa fa-solid fa-magnifying-glass fa-flip"></i> &nbsp; Looking
                        for a donor</a>
                    <a href="faq.html"> FAQ's <i class="fa fa-solid fa-question "></i>&nbsp; &nbsp;</a>
                </div>
            </div>
        </div><br><br>

        </div>
         <center>
            <form action="codeconfirmation.php" method='post'>
                <table cellpadding="20px" class="login">
                    <tr>
                        <th colspan="2">
                            <font color="black" size=5>Code - Verification
                        </th>
                        </font>
                    </tr>
                   
    <tr><th colspan='2' style='font-weight:400'><b>Enter verification code sent to your email</b></th></tr><tr><td>Code</td><td><input name='code' type='text' style='font-size:20px;' ></td></tr><tr><td colspan='2'><input type='submit' name='cs' value='submit' style=' width:80px;height:40px;font-size:20px;font-weight:550;color:white;background-color: rgb(25, 114, 169);border:0px; box-shadow:1px 1px 6px rgb(0, 31, 50);'></td></tr>";
    <?php
            if($wrongcode){
                   echo "<tr>
             <th colspan=2 style='color:rgb(226, 83, 83); background-color:rgb(252, 203, 203);'> Invalid code entered</th>   </tr>";
            }
             ?>
</body>
</html>