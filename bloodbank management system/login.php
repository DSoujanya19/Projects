<?php
session_start();

if($_SERVER["REQUEST_METHOD"]=="POST"){
$server="localhost";
$username="root";
$password="";

$invalid_creds=false;

$connection=mysqli_connect($server,$username,$password);
$uemail=$_POST['uname'];
$upass=$_POST['password'];
$sql="SELECT Email,Authorised FROM bloodbank.donorinfo WHERE Email LIKE '$uemail' AND password LIKE '$upass'";

$result=$connection->query($sql);

    if($result->num_rows>0){
        $rows=$result->fetch_assoc();
        $_SESSION['authorised']=$rows['Authorised'];
      
        $_SESSION['email']=$uemail;
       header("Location:lookfordonor.php");
       
    }else{
      $invalid_creds=true;
    }
}
?>

<!DOCTYPE html>
<html lang="en">

    <head>

        <title>Login</title>
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">

    </head>

    <body>
        <div class="navbar">
            <div id="navpart">
                <div id="part1">
                    <a href="index.html"><i class="fa fa-solid fa-house"></i> &nbsp;Home</a>
                </div>
                <div id="part2">

                    <a href="becomedonor.html">Become a donor</a>
                    <a href="login.php" style="background-color:rgb(180, 185, 195);color:rgb(34, 40, 49);"><i class="fa fa-solid fa-magnifying-glass fa-flip"></i> &nbsp; Looking
                        for a donor</a>
                    <a href="faq.html"> FAQ's<i class="fa fa-solid fa-question "></i></a>
                </div>
            </div>
        </div>

        </div>
        <center>
            <form action="login.php" method='post'>
                <table cellpadding="20px" class="login">
                    <tr>

                        <th colspan="2">Login</th>

                    </tr>
                    <tr>
                        <th colspan="2" style="font-weight:400;">Login to your account to get access to donor information</th>
                    </tr>
                    <tr>
                        <td>E-mail id</td>
                        <td><input name="uname" type="text" style="font-size:20px;"  ></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input name="password" type="password" style="font-size:20px;"> </td>
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