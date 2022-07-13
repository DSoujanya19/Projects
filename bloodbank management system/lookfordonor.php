<?php
session_start();
$stategiven=false;
$citygiven=false;
$noOfRows=0;
$result='';
require "phpmailer/PHPMailer.php";
require "phpmailer/SMTP.php";
require "phpmailer/Exception.php";
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\SMTP;
use PHPMailer\PHPMailer\Exception;
if($_SERVER["REQUEST_METHOD"]=="POST"){
   $connection=mysqli_connect("localhost","root","");
   
    if(isset($_POST['state'])){
        $state=$_POST['statename'];
        $_SESSION['stategiven']=1;
      $file=fopen("statecity.txt",'w');
      fwrite($file,$state);
      fclose($file);
        $sql="SELECT * FROM bloodbank.donorinfo where State like '$state' and atc like 'yes'";
        $result=$connection->query($sql);
        $noOfRows=$result->num_rows;
        $stategiven=true;
        }else if(isset($_POST['city'])){
           $_SESSION['stategiven']=0;
        $city=$_POST['cityname'];
      $file=fopen("statecity.txt",'w');
      fwrite($file,$city);
      fclose($file);
        $sql="SELECT * FROM bloodbank.donorinfo where City like '$city' and atc like 'yes'";
        $result=$connection->query($sql);
        $noOfRows=$result->num_rows;
        $citygiven=true;
        
    }else if(isset($_POST['maildonor'])){
        header('Location: maildonor.php');
    }else{
        //getting state or city value
        $file=fopen("statecity.txt",'r');
        $value=fread($file,filesize("statecity.txt"));
        fclose($file);
        // Getting user's mail id to mail data to it
    
    $mailiduser=$_SESSION['email'];
   
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
       $mail->Subject="Donor data ".$value;
       
       $mail->setFrom("Blood Bank");
       $mail->isHtml(true);
       $body='<b>Donor Data</b><br><br>';
       while($row=$result->fetch_assoc()){
          $body.=$row['Fullname']." : ".$row['Contactno']."<b> Bloodgroup</b>: ".$row['Bloodgroup'].'<br>';
       }
            
    $mail->Body=$body;
  
       $mail->addAddress($mailiduser);
       if($mail->Send()){
           header("Location: mailsent.html");
       }else{
           echo "no";
       }
       $mail->smtpClose();
    }
}
?>

<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Looking for Donor information</title>
        <link rel="stylesheet" href="css/common.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
<style>
    .widgets{
        font-size:28px;
    }
    .buttons,.mailme{
        color:white;
        background-color:rgb(11, 11, 29);
        border:0px;
         font-size:22px;
         height:40px;
         width:200px;
         box-shadow:8px 5px 9px grey;
    }
    .buttons:hover{
         background-color:green;
    }
    .mailme{
        background-color:rgb(78, 78, 7);
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
                    <a href="login.php"><i class="fa fa-solid fa-magnifying-glass"></i> &nbsp; Looking
                        for a donor</a>
                    <a href="faq.html"> FAQ's <i class="fa fa-solid fa-question "></i>&nbsp; &nbsp;</a>
                </div>
            </div>
        </div>
<div style='margin-top:200px;margin-left:100px;'>
   <h1><i class="fa-solid fa-city"></i> &nbsp;&nbsp;Search by :</h1>
   <form action='lookfordonor.php' method='POST'>
<table cellpadding='30px'>
<tr>
    <th class='widgets'>State</th>
    <th><input type=text name='statename' class='widgets' ></th>
</tr>
<br><br><br><br>
<tr>
    <th><input class='buttons' type=submit value='Search by state' name="state"> </th>
<?php
if($stategiven){
echo "<tr><th colspan=2 style='color:brown;font-size:25px;'> Donors found - $noOfRows</th>";
if($noOfRows>0){
    echo "<tr><th colspan=2 style='color:brown;font-size:25px;'><input type=submit value='Mail me data' class='mailme' name='mailmestate'></th>";
     if(strlen($_SESSION['authorised'])!=2){
         echo "<tr><th colspan=2 style='color:brown;font-size:25px;'><input type=submit value='Mail donors' class='mailme' name='maildonor'></th>";
   }
}
}

?>
</tr>

<tr>
    <th><br><br></th>
</tr>
    <tr>
<th class='widgets'>City</th>
<th><input type=text  name='cityname' class='widgets'></th>
</tr>
<?php
if($citygiven){
echo "<tr><th colspan=2 style='color:brown;font-size:25px;'> Donors found - $noOfRows</th>";
if($noOfRows>0){
    echo "<tr><th colspan=2 style='color:brown;font-size:25px;'><input type=submit value='Mail me data' class='mailme' name='mailmecity'></th>";
   if(strlen($_SESSION['authorised'])!=2){
        echo "<tr><th colspan=2 style='color:brown;font-size:25px;'><input type=submit value='Mail donors' class='mailme' name='maildonor'></th>";
   }
}
   

}
?>
    <tr>
<th ><input class='buttons' type=submit value='Search by city' name="city"> </th>

</tr>
</table>
</form>
</div>
    </body>

</html>