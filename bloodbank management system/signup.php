<?php
session_start();
$passnotmatch=false;
$underage=false;
$nullvalue=false;
$invalidphno=false;
$accountCreated=false;

if($_SERVER["REQUEST_METHOD"]=="POST"){
   
    $cells=array($_POST['fname'],$_POST['gender'],$_POST['bdate'],$_POST['contact'],$_SESSION['email'],strtoupper(strtolower($_POST['state'])),strtoupper($_POST['city']),$_POST['Bloodgroup'],$_POST['password'],$_POST['rpassword'],$_POST['rmail']);
   
    // checking for null values
    for($i=0;$i<=10;$i++){
    if(strlen($cells[$i])==0 ){
        $nullvalue=true;
     break;
    }
    } 
     //password and confirm password verification
   
    if($cells[8]!=$cells[9]){
        $passnotmatch=true;
    }
   
    // Checking if user is not underaged
    if(((int)(date("Y"))-(int)(explode("-",$cells[2])[0]))<16){
    $underage=true;
     }
//     //contact no digit verification
    if(strlen($cells[3])!=10 ){
        $invalidphno=true;
    }

    if(!($passnotmatch or $underage or $nullvalue or $invalidphno )) {
      $connection=mysqli_connect("localhost","root","");
      $sql="INSERT INTO bloodbank.donorinfo VALUES ('$cells[0]', '$cells[1]', '$cells[2]', '$cells[3]', '$cells[4]', '$cells[5]', '$cells[6]', '$cells[7]', '$cells[8]','$cells[10]','no');";
       
    //    try{
       if($connection->query($sql)){
           $accountCreated=true;
           
       }
    // }catch(Exception $e){
    //     if(str_contains($e,'PRIMARY')){
    //       $duplicate=true;
    //     }
    // }
    }
}
?>



<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Signup</title>
        <link rel="stylesheet" href="css/login.css">
           <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <script src="javascript/signup.js">

        </script>
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
        </div>

        </div>
        <center>
            <table cellpadding="10px 40px" class="signup">
                <form method="post" action="signup.php">
                    <?php
                   
                    if($accountCreated){
                        echo "<tr> <th style='font-size:14px;color:rgb(2, 85, 2);background-color: rgb(81, 216, 124);;'  colspan='2'>&#10003; Account created successfully !
                         </th></tr>";
                        
                    }
                    ?>
                    <tr>

                        <th colspan="2">Signup</th>

                    </tr>
                    <tr>
                        <th colspan="2" style="font-weight:400;">Enter the following details</th>
                    </tr>
                    <?php
                    if($nullvalue){
                     echo "<tr> <th style='font-size:14px;color:red'  colspan='2'> All the details are not entered</th></tr>";
                    }
                    ?>
                    <tr>
                        <td>Fullname</td>
                        <td><input name="fname" type="text"></td>
                    </tr>
                    <tr>
                        <td>Gender</td>
                        <td><input name="gender" type="radio" value="F" checked=checked>Female</td>

                    </tr>
                    <tr>
                        <td></td>

                        <td><input type="radio" value="M" name="gender">Male</td>
                        </td>
                    </tr>
                    <tr>
                        <td>DOB</td>
                        <td><input name="bdate" type="date"></td>
                    </tr>
                    <?php
                    if($underage){
                     echo "<tr> <th style='font-size:14px;color:red'  colspan='2'> You are underaged.   Cannot create an account</th></tr>";
                    }
                    ?>

                    <tr>
                        <td>Contact no</td>
                        <td><input name="contact" type="text"></td>
                    </tr>
                    <?php
                    if($invalidphno){
                     echo "<tr> <th style='font-size:14px;color:red'  colspan='2'> Invalid phone no entered </th></tr>";
                    }
                    ?>
                    
                    <tr>
                        <td>State</td>

                        <td><select name="state" style="font-size:20px;font-family:Arial, Helvetica, sans-serif;">
                                <option value="">&nbsp;--&nbsp;</option>
                                <option value="Andhra Pradesh">Andhra Pradesh</option>
                                <option value="	Arunachal Pradesh">Arunachal Pradesh</option>
                                <option value="Assam">Assam</option>
                                <option value="Bihar">Bihar</option>
                                <option value="Chhattisgarh">Chhattisgarh</option>
                                <option value="Goa">Goa</option>
                                <option value="Gujarat">Gujarat</option>
                                <option value="Haryana">Haryana</option>
                                <option value="Himachal Pradesh">Himachal Pradesh</option>
                                <option value="Jharkhand">Jharkhand</option>
                                <option value="Karnataka">Karnataka</option>
                                <option value="Kerala">Kerala</option>
                                <option value="Madhya Pradesh">Madhya Pradesh</option>
                                <option value="Maharashtra">Maharashtra</option>
                                <option value="Manipur">Manipur</option>
                                <option value="Meghalaya">Meghalaya</option>
                                <option value="Mizoram">Mizoram</option>
                                <option value="Nagaland">Nagaland</option>
                                <option value="Odisha">Odisha</option>
                                <option value="Punjab">Punjab</option>
                                <option value="Rajasthan">Rajasthan</option>
                                <option value="Sikkim">Sikkim</option>
                                <option value="Tamil Nadu">Tamil Nadu</option>
                                <option value="Telangana">Telangana</option>
                                <option value="Tripura">Tripura</option>
                                <option value="Uttar Pradesh">Uttar Pradesh</option>
                                <option value="Uttarakhand">Uttarakhand</option>
                                <option value="West Bengal">West Bengal</option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td>City</td>
                        <td><input name="city" type="text" ></td>
                    </tr>

                    <tr>
                        <td>Blood Group</td>
                        <td><select name="Bloodgroup" style="font-size:19px;font-family:Arial, Helvetica, sans-serif;">
                                <option value="">&nbsp;&nbsp;--&nbsp;&nbsp;</option>
                                <option value="A+">A+</option>
                                <option value="A-">A-</option>
                                <option value="B+">B+</option>
                                <option value="B-">B-</option>
                                <option value="AB+">AB+</option>
                                <option value="AB-">AB-</option>
                                <option value="O+">O+</option>
                                <option value="O-">O-</option>


                            </select></td>
                    </tr>


                    <tr>
                        <td>Password</td>
                        <td><input name="password" type="text"></td>
                    </tr>
                    <?php
                    if($passnotmatch){
                    echo "<tr> <th style='font-size:14px;color:red'  colspan='2'>&#11197; Passwords do not match</th></tr>";
                    }
                    ?>
                    <tr>
                        <td>Re-enter password</td>
                        <td><input name="rpassword" type="text"></td>
                    </tr>
                    <tr>
                        <td style="font-size:15px;" colspan="2">Receive mails regarding donation camps/ Choose no if you are not eligible to donate
                        </td>

                    </tr>
                    <tr>
                        <td style="font-size:15px;"><input type="radio" name="rmail" value="yes" checked=checked>YES
                        </td>
                        <td style="font-size:15px;"><input type="radio" name="rmail" value="no">NO</td>

                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" style="  width:80px;
    height:35px;
    font-size:20px;
    font-weight:550;
    color:white;
    background-color: rgb(25, 114, 169);
    border:0px;
    box-shadow:1px 1px 6px rgb(0, 31, 50);"></td>
                    </tr>

                </form>
            </table>
            <br><br><br><br><br><br>
            </div>
        </center>


    </body>

</html>