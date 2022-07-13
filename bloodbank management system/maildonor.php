<?php
session_start();
$result='';
$subject='';
$bodyuser='';
$cno='';
$loc='';
$date='';
 $greetings='';
$emptycells=false;
require "phpmailer/PHPMailer.php";
require "phpmailer/SMTP.php";
require "phpmailer/Exception.php";
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;
if($_SERVER["REQUEST_METHOD"]=="POST"){
   $connection=mysqli_connect("localhost","root","");
   $subject=trim($_POST['subject']);
       $sentonbehalf=trim($_POST['orgIn']);
       $greetings.=trim($_POST['greet'])."<br>";
       $bodyuser=trim($_POST['body']);
       if(strlen(trim($_POST['cno']))!=0){
       $cno="<br><b>Contact for any queries</b> :  ". $_POST['cno'];
       }
       if(strlen(trim($_POST['loc']))!=0){
           $loc.='<br><b>Location : </b>'.$_POST['loc'].'<br>';
       }
        if(strlen(trim($_POST['date']))!=0){
           $date.='<br><b>Date: </b>'.$_POST['date'].'<br>';
       }
   if(strlen($subject)!=0 && strlen($bodyuser)!=0 && strlen($sentonbehalf)!=0 ){
    
        //getting state or city value
        $file=fopen("statecity.txt",'r');
        $value=fread($file,filesize("statecity.txt"));
        fclose($file);
        // Getting user's mail id to mail data to it
        //writing query
        if($_SESSION['stategiven']==1){
        $sql="SELECT * FROM bloodbank.donorinfo WHERE State LIKE '$value' AND atc LIKE 'yes'";
        }else{
            $sql="SELECT * FROM bloodbank.donorinfo WHERE  City LIKE '$value' AND atc LIKE 'yes'";
        }

        $result=$connection->query($sql);

       $mail=new PHPMailer();
       $mail->isSMTP();
       $mail->Host="smtp.gmail.com"; 
       $mail->SMTPAuth=true;
       $mail->SMTPSecure="tls";
       $mail->Port="587";
       $mail->Username="bloodbankwt@gmail.com";
       $mail->Password="blood@bank";
       $mail->Subject=$subject;
       
       $mail->setFrom("Blood Bank");
       $mail->isHtml(true);
       $body='<b>(On behalf of '.$sentonbehalf.' )</b><br><br>'.$greetings.'<br>';
       $body.=$bodyuser.'<br>'.$loc.$date.$cno;
       
       $mail->Body=$body;
  //Fetching mail ids of donors of that specific state or location and mailing them
       while($row=$result->fetch_assoc()){
         $mailidusern=$row['Email'];
          $mail->addAddress($mailidusern);
          $mail->Send();
       }
       header("Location: mailsentdonor.php");
       $mail->smtpClose();
    }else{
        $emptycells=true;
    }
}

?>


<!DOCTYPE html>
<html lang="en">

    <head>

        <title>Enter details</title>
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <style>

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
                    <a href="login.php" style="background-color:rgb(180, 185, 195);color:rgb(34, 40, 49);"><i class="fa fa-solid fa-magnifying-glass fa-flip"></i> &nbsp; Looking
                        for a donor</a>
                    <a href="faq.html"> FAQ's<i class="fa fa-solid fa-question "></i></a>
                </div>
            </div>
        </div>

        </div>
        <center>
            <form action="maildonor.php" method='post'>
                <table cellpadding="20px" class="mailuser">
                    <tr>

                        <th colspan="2">Fill the columns to mail info to registered donors</th>

                    </tr>
                      <?php
        
       if($emptycells){
           echo "<tr><th style='color:rgb(226, 83, 83); background-color:rgb(252, 203, 203);font-size:25px;' colspan=2>Fill all cells</th></tr>";
       }
        
        ?>
                    <tr>
                        <td>Subject &nbsp;<font color=red>(must fill)</font></td>
                          <td><textarea name="subject" type="text" cols=50 rows=2 style="font-size:20px;"></textarea> </td>
                    </tr>
                    <tr>
                        <td>Organisation / Individual name &nbsp;<font color=red>(must fill)</font></td>
                        <td><input name="orgIn" type="text" style="font-size:20px;"  ></td>
                    </tr>
                      <tr>
                        <td>Greetings</td>
                        <td><input name="greet" type="text" style="font-size:20px;"  ></td>
                    </tr>
                    <tr>
                        <td>Body &nbsp;<font color=red>(must fill)</td>
                        
                        <td><textarea name="body" type="text" cols=50 rows=25 style="font-size:20px;"></textarea> </td>
                    </tr>
  <tr>
                        <td>Location</td>
                        <td><input name="loc" type="text" style="font-size:20px;"  ></td>
                    </tr>
                      <tr>
                        <td>Date / Dates </td>
                        <td><input name="date" type="text" style="font-size:20px;"  ></td>
                    </tr>
                   <tr>
                        <td>Contact number/s</td>
                        <td><input name="cno" type="text" style="font-size:20px;"  ></td>
                    </tr>


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

<br><br><br><br>
    </body>

</html>