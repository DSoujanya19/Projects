<?php

$invalid_creds=false;
$updated=false;
$markedAsDonor=false;
if($_SERVER["REQUEST_METHOD"]=="POST"){
$server="localhost";
$username="root";
$password="";



$connection=mysqli_connect($server,$username,$password);
$uemail=$_POST['uname'];
$upass=$_POST['password'];
$sql="SELECT * FROM bloodbank.donorinfo WHERE Email LIKE '$uemail' AND password LIKE '$upass'";

$result=$connection->query($sql);

if($result->num_rows<=0){
$invalid_creds=true;
}
if(!$invalid_creds){
    $sql="UPDATE bloodbank.donorinfo set atc='yes' where Email LIKE '$uemail' AND password LIKE '$upass'";
try{
if($connection->query($sql)){
    $updated=true;
}
}catch(Exception $e){
echo $e;
}
}
}

?>


<!DOCTYPE html>
<html lang="en">

    <head>

        <link rel="stylesheet" href="css/login.css">
        <title>Confirm donor</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <style>
            input[type=radio] {
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

                    <a href="becomedonor.html" style="background-color:rgb(180, 185, 195);color:rgb(34, 40, 49);">Become a donor</a>
                    <a href="login.php"><i class="fa fa-solid fa-magnifying-glass fa-flip"></i> &nbsp; Looking
                        for a donor</a>
                    <a href="faq.html"> FAQ's <i class="fa fa-solid fa-question "></i>&nbsp; &nbsp;</a>
                </div>
            </div>
        </div><br><br>

        </div>
        <center>
            <form action="confirmdonor.php" method='post'>
                <table cellpadding="20px" class="login">
                    <?php
                    if($updated){
echo "<tr><th colspan='2' style='background-color:rgb(163, 237, 229);color:rgb(2, 119, 107);'>You are now a donor</th></tr>";
                    }
                    ?>
                    <tr>

                        <th colspan="2">
                            <font color="black" size=5>Become a donor
                        </th>
                        </font>

                    </tr>

                    <tr>
                        <th colspan="2" style="font-weight:400;"><b>Click submit after entering valid credentials</b>
                        </th>
                    </tr>
                    <tr>
                        <td>E-mail id</td>
                        <td><input name="uname" type="text" style="font-size:20px;"></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input name="password" type="password" style="font-size:20px;"></td>
                    </tr>


                    <tr>
                        <td colspan="2"> <input type="radio" checked=checked readonly="readonly" /> &nbsp;&nbsp;I want to become a donor
                        </td>
                    </tr>
                    <?php
        
        if($invalid_creds){
          
        echo "<tr>
        <th colspan=2><p style='color:rgb(226, 83, 83);'>&#x274C; Invalid credentials</p> </th>   </tr>";
        }
        
        ?>
                    <tr>
                        <td colspan="2"><input type="submit" value="submit" class="submit" style="  width:80px;
    height:40px;
    font-size:20px;
    font-weight:550;
    color:white;
    background-color: rgb(25, 114, 169);
    border:0px;
    box-shadow:1px 1px 6px rgb(0, 31, 50);"></td>
                    </tr>
                </table>
            </form>
            </div>
        </center>


    </body>

</html>