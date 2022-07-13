<?php
session_start();
$duplicate=false;
$invalidmail=false;
#including php mailer files
require "phpmailer/PHPMailer.php";
require "phpmailer/SMTP.php";
require "phpmailer/Exception.php";


#define namespaces
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;

if($_SERVER["REQUEST_METHOD"]=="POST"){
$id=$_POST["email"];

$server="localhost";
$username="root";
$password="";
$connection=mysqli_connect($server,$username,$password);

$sql="SELECT * FROM bloodbank.donorinfo WHERE Email LIKE '$id'";

$result=$connection->query($sql);

if($result->num_rows>0){
    $duplicate=true;
}else{
$x=rand(1000,9999);


// Creating instance of php mailer
$mail=new PHPMailer();
// set mailer to use SMTP
$mail->isSMTP();
// define smtp host
$mail->Host="smtp.gmail.com";
// enabling smtp authentication
$mail->SMTPAuth="true";
// setting type of encryption
$mail->SMTPSecure="tls";
// $mail port
$mail->Port="587";
// set gmail username
$mail->Username="bloodbankwt@gmail.com";
// set password

$mail->Password="blood@bank";
// set subject
$mail->Subject="User verification code";

// enable html
$mail->isHtml(true);
// body
$mail->Body="<b> User verification code <h1>$x </h1></b>";
// recipuent
$mail->addAddress($id);
// send mail
if($mail->Send()){
$_SESSION['code']=$x;
$_SESSION['email']=$id;
header("Location: codeconfirmation.php");
}else{
   $invalidmail=true;
}
$mail->smtpClose();
}
}

?>

<!DOCTYPE html>
<html lang="en">

    <head>
        <link rel="stylesheet" href="css/login.css">
        <title>Email Verification</title>
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
            <form action="emailverification.php" method='post'>
                <table cellpadding="20px" class="login">
                    <tr>
                        <th colspan="2">
                            <font color="black" size=5>Email - Verification
                        </th>
                        </font>
                    </tr>
                   
      
            <tr><th colspan='2' style='font-weight:400'><b>Enter your email id</b></th></tr><tr><td>E-mail id</td><td><input name='email' type='text' style='font-size:20px;'></td></tr><tr><td colspan='2'><input type='submit' name='ms' value='submit' style=' width:80px;height:40px;font-size:20px;font-weight:550;color:white;background-color: rgb(25, 114, 169);border:0px; box-shadow:1px 1px 6px rgb(0, 31, 50);'></td></tr>;
               <?php if($duplicate){
             echo "<tr>
             <th colspan=2 style='color:rgb(226, 83, 83); background-color:rgb(252, 203, 203);'> There is already an account with the following mail id</th>   </tr>";
                }
                if($invalidmail){
                     echo "<tr>
             <th colspan=2 style='color:rgb(226, 83, 83); background-color:rgb(252, 203, 203);'> Invalid mail id</th>   </tr>";
                }
        
?>
                </table>
            </form>
            </div>
        </center>


    </body>

</html>